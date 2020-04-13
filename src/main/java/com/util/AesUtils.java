package com.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Tyler Yin
 */
public class AesUtils {

    /**
     * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES），是一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用
     * 实例化密钥
     */
    private static Key key;

    /**
     * 原始密钥
     */
    private static String KEY_STR = "springmvc";

    /**
     * 编码
     */
    private static String CHARSET_NAME = "UTF-8";

    /**
     * 密钥算法
     */
    private static String KEY_ALGORITHM = "AES";

    /**
     * 加密-解密算法 / 工作模式 / 填充方式
     */
    private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 初始化key
     */
    static {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(KEY_STR.getBytes());
            kgen.init(128, random);
            key = kgen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param str
     * @return
     * @description: AES对称加密字符串，并通过Jdk自带Base64转换为ASCII
     */
    public static String encryptString(String str) {
        try {
            byte[] bytes = str.getBytes(CHARSET_NAME);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return Base64.getEncoder().encodeToString(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param str
     * @return
     * @description: 对AES加密字符串进行解密
     */
    public static String decryptString(String str) {
        try {
            byte[] bytes = Base64.getDecoder().decode(str);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSET_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}