package com.tanx.music.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/27 下午10:23
 */

@SuppressWarnings("all")
public class EncryptionUtil {
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String md5(String password) {
        return md5(password, 5);
    }

    public static String md5(String password, int length) {
        StringBuilder result = new StringBuilder();
        md5.reset();
        byte[] digest = md5.digest(password.getBytes());
        for (byte d : digest) {
            String temp = Integer.toHexString(d & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result.append(temp);
        }
        return encoder(result.toString(), length);
    }

    public static String simpleMD5(String password) {
        StringBuilder result = new StringBuilder();
        md5.reset();
        byte[] digest = md5.digest(password.getBytes());
        for (byte d : digest) {
            String temp = Integer.toHexString(d & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result.append(temp);
        }
        return result.toString();
    }

    public static String encoder(byte[] digest, int length) {
        for (int i = 0; i < length; i++) {
            digest = ENCODER.encode(digest);
        }
        return new String(digest);
    }

    public static String encoder(byte[] digest) {
        return encoder(digest, 5);
    }

    public static String encoder(String digest, int length) {
        byte[] data = digest.getBytes();
        for (int i = 0; i < length; i++) {
            data = ENCODER.encode(data);
        }
        return new String(data);
    }

    public static String encoder(String digest) {
        return encoder(digest, 5);
    }

    public static String decoder(byte[] digest, int length) {
        for (int i = 0; i < length; i++) {
            digest = DECODER.decode(digest);
        }
        return new String(digest);
    }

    public static String decoder(byte[] digest) {
        return decoder(digest, 5);
    }

    public static String decoder(String digest, int length) {
        byte[] data = digest.getBytes();
        for (int i = 0; i < length; i++) {
            data = DECODER.decode(data);
        }
        return new String(data);
    }

    public static byte[] decoderByte(String digest, int length) {
        byte[] data = digest.getBytes();
        for (int i = 0; i < length; i++) {
            data = DECODER.decode(data);
        }
        return data;
    }

    public static String decoder(String digest) {
        return decoder(digest, 5);
    }
}