package msc.ai.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Keshan De Silva
 */
public class KeyGenerator
{

    public static final String USER = "A";
    public static final String ALGORITHM = "RSA";
    public static final String PRIVATE_KEY_FILE = "C:/VeriSignKeys/" + USER + "/private.key";
    public static final String PUBLIC_KEY_FILE = "C:/VeriSignKeys/" + USER + "/public.key";

    public static void generateKey()
    {
        try
        {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

            if (privateKeyFile.getParentFile() != null)
            {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null)
            {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            try (ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile)))
            {
                publicKeyOS.writeObject(key.getPublic());
            }

            try (ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile)))
            {
                privateKeyOS.writeObject(key.getPrivate());
            }
        }
        catch (NoSuchAlgorithmException | IOException e)
        {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args)
//    {
//        KeyGenerator.generateKey();
//    }
}
