package com.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密解密
 * 此工具类能使用指定的字符串，每次生成相同的公钥和私钥且在linux和windows密钥也相同；相同的原文和密钥生成的密文相同
 */
public class RSAUtils {
    private static final String SEED = "Ktc_582";
    private static final int KEY_SIZE = 1024;

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";
    private static final String TRANSFORMATION = "RSA/None/NoPadding";

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data) throws Exception {
        Map<String, Object> keyMap = RSAUtils.initKey(SEED);
        String privateKey = RSAUtils.getPrivateKey(keyMap);
        return new String(decryptByPrivateKey(BASE64Util.decodeToByte(data), privateKey));
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception {
        Map<String, Object> keyMap = RSAUtils.initKey(SEED);
        String publicKey = RSAUtils.getPublicKey(keyMap);
        return BASE64Util.encodeByte(encryptByPublicKey(data.getBytes(), publicKey));
    }

    @Test
    public void test() throws Exception {
        //原文
        String source = "e99a71e13e3882868592352d2d1c102b";
        System.out.println("原文：\n" + source);

        //加密
        String encodedStr = RSAUtils.encryptByPublicKey(source);
        System.out.println("密文：" + encodedStr);

        //解密
        String decodedStr = RSAUtils.decryptByPrivateKey(encodedStr);
        System.out.println("解密后的明文：" + decodedStr);

        System.out.println("原文和解密之后的明文是否相同：" + source.equals(decodedStr));
    }

    /**
     * 初始化公钥和私钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private static Map<String, Object> initKey(String seed) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        SecureRandom random = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);
        random.setSeed(seed.getBytes());
        keyPairGen.initialize(KEY_SIZE, random);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     */
    private static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return BASE64Util.encodeByte(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     */
    private static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return BASE64Util.encodeByte(key.getEncoded());
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = BASE64Util.decodeToByte(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = BASE64Util.decodeToByte(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
}

