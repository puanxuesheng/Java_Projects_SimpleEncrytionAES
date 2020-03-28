package com.example.jdbc;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class testCypto {


    public static SecretKey buildKey() {
        byte[] keyByte = new byte[16];
        SecretKey secretKey = null;

        keyByte =  new byte[]{-89,-99,101,-9,80,28,25,-111,-121,-40,14,53,54,2,53,24};
        secretKey = new SecretKeySpec(keyByte,"AES");

        return secretKey;
    }

    public static String encyptString (String PlainText, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher ciper =  Cipher.getInstance(secretKey.getAlgorithm());
        ciper.init(ciper.ENCRYPT_MODE,secretKey);

        byte[] textbyte = PlainText.getBytes();
        byte[] ciperbyte = ciper.doFinal(textbyte);

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(ciperbyte);
        return encryptedText;

    }

    public static char[] decyptString (String encrytedText, SecretKey secretkey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Base64.Decoder decoder =  Base64.getDecoder();
        byte[] encrytedByte = decoder.decode(encrytedText);

        Cipher ciper =  Cipher.getInstance(secretkey.getAlgorithm());
        ciper.init(ciper.DECRYPT_MODE,secretkey);

        byte[] decyptedByte = ciper.doFinal(encrytedByte);
        Charset utf8 = StandardCharsets.UTF_8;
        CharBuffer decryptChar = utf8.decode(ByteBuffer.wrap(decyptedByte));
        return decryptChar.array();
    }



    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        SecretKey testKey = buildKey();
        String encypted = encyptString("hello", testKey);

            System.out.println(encypted);

            char[] decoded = decyptString(encypted,testKey);

        System.out.println(decoded);

    }
}
