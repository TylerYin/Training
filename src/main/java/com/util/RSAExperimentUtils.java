package com.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.Cipher;

/**
 * RSA加密解密
 * 此工具类能使用指定的字符串，每次生成相同的公钥和私钥且在linux和windows密钥也相同；相同的原文和密钥生成的密文相同
 */
public class RSAExperimentUtils {
    private static final int KEY_SIZE = 1024;
    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final String TRANSFORMATION = "RSA/None/NoPadding";

    /**
     * 解密
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String key) throws Exception {
        return new String(decryptByPrivateKey(BASE64Util.decodeToByte(data), key));
    }

    /**
     * 加密
     * 用公钥加密，加密字符串，返回用base64加密后的字符串
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {
        return encryptByBytePublicKey(data.getBytes(), key);
    }


    /**
     * 解密
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        //私钥解密
        byte[] keyBytes = BASE64Util.decodeToByte(key);

        /*取得私钥*/
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        /*对数据解密*/
        //相同的原文、公钥能生成相同的密文
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        //对公钥解密
        byte[] keyBytes = BASE64Util.decodeToByte(key);

        /*取得公钥*/
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        /*对数据加密*/
        //相同的原文、公钥能生成相同的密文。
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    @Test
    public void test() throws Exception {
        //原文
        String source = "12dfefDKLJKLKL464d中文f465as43f1a3";

        //种子
        String seed = "abc123";

        System.out.println("原文：\n" + source);

        //初始化密钥
        Map<String, Object> keyMap = RSAExperimentUtils.initKey(seed);

        //公钥
        String publicKey = RSAExperimentUtils.getPublicKey(keyMap);

        //私钥
        String privateKey = RSAExperimentUtils.getPrivateKey(keyMap);

        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        //加密
        String encodedStr = RSAExperimentUtils.encryptByPublicKey(source, publicKey);
        System.out.println("密文：" + encodedStr);

        //解密
        String decodedStr = RSAExperimentUtils.decryptByPrivateKey(encodedStr, privateKey);
        System.out.println("解密后的结果：" + decodedStr);
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
     * 初始化公钥和私钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private static Map<String, Object> initKey(String seed) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);

        //如果使用SecureRandom random = new SecureRandom();//windows和linux默认不同，导致两个平台生成的公钥和私钥不同
        SecureRandom random = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);

        //使用种子则生成相同的公钥和私钥
        random.setSeed(seed.getBytes());
        keyPairGen.initialize(KEY_SIZE, random);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 加密
     * 用公钥加密，加密byte数组，返回用base64加密后的字符串
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static String encryptByBytePublicKey(byte[] data, String key) throws Exception {
        return BASE64Util.encodeByte(encryptByPublicKey(data, key));
    }
}

