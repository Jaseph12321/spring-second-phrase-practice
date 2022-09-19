package com.practice.springsecondphrasepractice.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

@Service
public class CryptService {
    private static final String SECRET = "AES";
    private static final String ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final String key = "";
    private static final String iv = "2021121018304400";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public String aesEbsEncrypt(String str, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"));
        byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(doFinal));

    }

    public String aesEbsDecrypt(String str, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"));
        byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(doFinal);

    }

    public String aesCbcEncrypt(String str,String key) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8),0,16);
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
            return new String(doFinal);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String aesCbcDecrypt(String str, String key) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8),0,16);
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(str));
            return new String(original);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


}
