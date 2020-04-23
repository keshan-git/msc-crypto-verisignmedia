package msc.ai.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import msc.ai.model.MediaPack;
import msc.ai.model.ResourceFile;
import msc.ai.model.VerificationStatus;

/**
 *
 * @author Keshan De Silva
 */
public class FileOperations
{   
    public static boolean createPackage(MediaPack mediaPack)
    {
        try
        {
            File signatureFile = null;
            try (FileOutputStream fos = new FileOutputStream(mediaPack.getMediaPackName() + ".media");
                    ZipOutputStream zos = new ZipOutputStream(fos))
            {
                for (ResourceFile resourceFile : mediaPack.getResourceFilesSet().getResourceList())
                {
                    addToZipFile(resourceFile.getVirtualFile(), resourceFile.getFileName(), zos);
                    
                    signatureFile = new File(resourceFile.getFileName() + ".signature");
                    addToZipFile(signatureFile, resourceFile.getFileName() + ".signature", zos);
                    signatureFile.delete();
                }    
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void addToZipFile(File file, String fileName, ZipOutputStream zos)
            throws FileNotFoundException, IOException
    {
        try (FileInputStream fis = new FileInputStream(file))
        {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipEntry.getMethod();
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0)
            {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
        }
    }

    public static ArrayList<ResourceFile> unpackPackage(File file)
    {
        ArrayList<ResourceFile> fileList = new ArrayList<>();
        
        try
        { 
            File directory = new File(file.getParent() + "/" + file.getName().split("[.]")[0]);  
            directory.mkdir();
            String target = directory.getPath() + "/";

            byte[] buf = new byte[1024];
            try (ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(file)))
            {
                ZipEntry zipentry = zipinputstream.getNextEntry();
                while (zipentry != null)
                {
                    String entryName = zipentry.getName();
                    File newFile = new File(target + entryName);

                    try (FileOutputStream fileoutputstream = new FileOutputStream(target + entryName))
                    {
                        int n;
                        while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
                        {
                            fileoutputstream.write(buf, 0, n);
                        }
                    }
                    zipinputstream.closeEntry();
                    fileList.add(new ResourceFile(newFile));
                    zipentry = zipinputstream.getNextEntry();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return fileList;
    }
        
    public static boolean copyFile(String fileName, File destination)
    {
        File sourceFile = new File(fileName + ".media");
        File targetFile = new File(destination, fileName + ".media");
        try
        {   
            if (targetFile.exists())
            {
                targetFile.delete();
            }
            
            Files.copy(sourceFile.toPath(), targetFile.toPath());
            Files.delete(sourceFile.toPath());
            return true;
        }
        catch (IOException ex)
        {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ArrayList<VerificationStatus> getVerified(ArrayList<ResourceFile> fileList)
    {
        ArrayList<VerificationStatus> statusList = new ArrayList<>(fileList.size());
        try
        {
            for (ResourceFile resourceFile : fileList)
            {
                if (resourceFile.getFileName().endsWith(".signature"))
                {
                    Path path = Paths.get(resourceFile.getVirtualFile().getAbsolutePath());
                    byte[] data = Files.readAllBytes(path);
                    byte[] decyptHash = SecurityOperations.decrypt(data, SecurityOperations.getPublicKey("Reporter A"));
                    
                    File sourceFile = new File(resourceFile.getVirtualFile().getPath().replace(".signature", ""));
                    byte[] hashValue = SecurityOperations.generateHash(sourceFile);
                    
                    if (Arrays.equals(decyptHash, hashValue))
                    {
                        statusList.add(VerificationStatus.VERIFY_PASS);
                    }
                    else
                    {
                        statusList.add(VerificationStatus.VERIFY_FAIL);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return statusList;
    }
}
