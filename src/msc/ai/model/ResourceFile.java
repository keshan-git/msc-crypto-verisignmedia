package msc.ai.model;

import java.io.File;

/**
 *
 * @author Keshan De Silva
 */
public class ResourceFile
{
    private String fileName;
    private String fileType;
    private File virtualFile;
    private double fileSize;
    
    public ResourceFile(File file)
    {
        if (file != null)
        {
            this.virtualFile = file;
            this.fileName = file.getName();
            this.fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            this.fileSize = virtualFile.length();
        }
    }
    
    public ResourceFile(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public File getVirtualFile()
    {
        return virtualFile;
    }

    public void setVirtualFile(File virtualFile)
    {
        this.virtualFile = virtualFile;
    }

    public String getFileSize()
    {
        if ( fileSize < 1024)
        {
            return fileSize + " B";
        }
        else if ( fileSize < 1024 * 1024)
        {
            return String.format("%.2f KB", fileSize / 1024.0);
        }
        else if ( fileSize < 1024 * 1024 * 1024)
        {
            return String.format("%.2f MB", fileSize / ( 1024.0 * 1024.0));
        }
        else
        {
            return String.format("%.2f GB", fileSize / ( 1024.0 * 1024.0 * 1024.0));
        }
    }  
}
