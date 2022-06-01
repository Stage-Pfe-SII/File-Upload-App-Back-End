package com.sii.fileupload.services;

import com.sii.fileupload.entities.File;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

@Service
public class EncryptionServiceImpl implements EncryptionService{
    public static final int AES_KEY_SIZE = 256;
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TAG_LENGTH = 16;


    public File encrypt(File file) throws Exception
    {
        SecretKey key = readKeyFromKeyStore();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            byte[] IV = new byte[]{66,12,12,1,1,1,1,1,1,1,1,1};
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        file.setContent(cipher.doFinal(file.getContent()));
        return file;
    }

    public  File decrypt(File file) throws Exception
    {
        SecretKey key = readKeyFromKeyStore();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        byte[] IV = new byte[]{66,12,12,1,1,1,1,1,1,1,1,1};
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        file.setContent(cipher.doFinal(file.getContent()));
        return file;
    }

    public SecretKey readKeyFromKeyStore() throws Exception{
        InputStream keystoreStream = new FileInputStream("aes-keystore.jck");
        KeyStore keystore = KeyStore.getInstance("JCEKS");
        String keystorePass = "password";
        String alias = "aesgcmks";
        keystore.load(keystoreStream, keystorePass.toCharArray());
        if (!keystore.containsAlias(alias)) {
            throw new RuntimeException("Alias for key not found");
        }
        return (SecretKey) keystore.getKey(alias, "password".toCharArray());
    }

}
