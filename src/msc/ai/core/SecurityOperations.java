package msc.ai.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import msc.ai.model.MediaPack;
import msc.ai.model.ResourceFile;

/**
 *
 * @author Keshan De Silva
 */
public class SecurityOperations
{

    public static final String ALGORITHM = "RSA";
    public static final String HASH_ALGORITHM = "SHA-512";

//    public static PublicKey getMediaCenterPublicKey()
//    {
//        try
//        {
//            String publicKeyFile = "C:/VeriSignKeys/MediaCenter/public.key";
//            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(publicKeyFile));
//            return (PublicKey) inputStream.readObject();
//        }
//        catch (IOException | ClassNotFoundException ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }

    public static PublicKey getPublicKey(String userName)
    {
        try
        {
            String publicKeyFile = "C:/VeriSignKeys/" + userName + "/public.key";
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(publicKeyFile));
            return (PublicKey) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static PrivateKey getPrivateKey()
    {
        try
        {
            String privateKeyFile = "C:/VeriSignKeys/private.key";
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(privateKeyFile));
            return (PrivateKey) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String text, PublicKey key)
    {
        try
        {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(text.getBytes());
            return new String(cipherText);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] text, PrivateKey key)
    {
        try
        {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(text);
            return cipherText;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(String text, PrivateKey key)
    {
        try
        {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] dectyptedText = cipher.doFinal(text.getBytes());
            return dectyptedText;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static byte[] decrypt(byte[] input, PublicKey key)
    {
        try
        {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] dectyptedText = cipher.doFinal(input);
            return dectyptedText;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }
        
    public static boolean encrypt(String plainFileName, String encryptedFileName, PublicKey key)
    {
        try
        {
            FileInputStream fis = new FileInputStream(plainFileName);
            FileOutputStream fos = new FileOutputStream(encryptedFileName);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            try (CipherOutputStream cos = new CipherOutputStream(fos, cipher))
            {
                byte[] block = new byte[32];
                int i;
                while ((i = fis.read(block)) != -1)
                {
                    cos.write(block, 0, i);
                }
            }

            fis.close();
            fos.close();
            return true;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException ex)
        {
            Logger.getLogger(SecurityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean decrypt(String encryptedFileName, String resultFileName, PrivateKey key)
    {
        try
        {
            FileInputStream fis = new FileInputStream(encryptedFileName);
            FileOutputStream fos = new FileOutputStream(resultFileName);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            try (CipherInputStream cis = new CipherInputStream(fis, cipher))
            {
                byte[] block = new byte[32];
                int i;
                while ((i = cis.read(block)) != -1)
                {
                    fos.write(block, 0, i);
                }
            }

            fis.close();
            fos.close();
            return true;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException ex)
        {
            Logger.getLogger(SecurityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean generateSignatures(MediaPack mediaPack) throws FileNotFoundException
    {
        try
        {
            for (ResourceFile resourceFile : mediaPack.getResourceFilesSet().getResourceList())
            {
                File file = new File(resourceFile.getFileName() + ".signature");
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                
                byte[] hashValue = SecurityOperations.generateHash(resourceFile.getVirtualFile());
                if (hashValue != null)
                {
                    byte[] signature = SecurityOperations.encrypt(hashValue, SecurityOperations.getPrivateKey());
                    fos.write(signature);
                    fos.close();
                }
            }
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return false;
    }

    public static byte[] generateHash(File file)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            FileInputStream fis = new FileInputStream(file);

            byte[] dataBytes = new byte[1024];
            int nread;
            while ((nread = fis.read(dataBytes)) != -1)
            {
                md.update(dataBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();
            return mdbytes;
        }
        catch (NoSuchAlgorithmException | IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
