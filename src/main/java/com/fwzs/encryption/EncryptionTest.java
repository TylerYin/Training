package com.fwzs.encryption;

import com.util.AesUtils;
import com.util.DateUtils;
import com.util.FileUtils;
import com.util.RsaUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Tyler Yin
 */
public class EncryptionTest {

    /**
     * 一、RSA加密简介
     * RSA加密是一种非对称加密，可以在不直接传递密钥的情况下完成解密，这能够确保信息的安全性，避免了直接传递密钥所造成的被破解的风险。
     * RSA加密是由一对密钥来进行加解密的过程，分别称为公钥和私钥。该加密算法的原理就是对一极大整数做因数分解的困难性来保证安全性，通常个人保存私钥，公钥是公开的（可能同时多人持有）。
     * 二、加密、签名区别
     * 加密和签名都是为了安全性考虑，但略有不同。简单的说，加密是为了防止信息被泄露，而签名是为了防止信息被篡改。
     * 三、加密算法
     * 加密算法分对称加密和非对称加密，其中对称加密算法的加密与解密密钥相同，非对称加密算法的加密密钥与解密密钥不同。此外，还有一类不需要密钥的散列算法。
     * <p>
     * 对称加密算法：
     * DES（Data Encryption Standard，使用56位密钥）
     * 3DES（使用2条不同的56位的密钥对数据进行三次加密）
     * AES（Advanced Encryption Standard，AES可以使用128、192、和256位密钥，并且用128位分组加密和解密数据）
     * <p>
     * 非对称加密算法：
     * RSA（三个最初发明人的姓氏开头字母拼在一起组成的，RSA密钥至少为500位长，一般推荐使用1024位）
     * DSA（Digital Signature Algorithm）
     * <p>
     * 散列算法：SHA-1（Secure Hash Algorithm）、MD5（Message Digest Algorithm）
     * <p>
     * https://blog.csdn.net/baidu_22254181/article/details/82594072
     *
     * @throws Exception
     */
    @Test
    public void testRasEncryption() throws Exception {
        //原文
        String source = "40400260021837406685301910663379";
        System.out.println("原文：\n" + source);

        //加密
        String encodedStr = RsaUtils.encryptByPublicKey(source);
        System.out.println("密文：" + encodedStr);

        //解密
        String decodedStr = RsaUtils.decryptByPrivateKey(encodedStr);
        System.out.println("解密后的明文：" + decodedStr);
        System.out.println("原文和解密之后的明文是否相同：" + source.equals(decodedStr));
    }

    @Test
    public void testAesEncryption() throws IOException {
        String fileRootPath = "src/main/java/com/fwzs/encryption/";
        String sourceFileName = "C_001_20191023141206_500000_0.txt";
        String sourceFilePath = fileRootPath + sourceFileName;
        String encryptionFileName = "encryption_" + DateUtils.formatDate(new Date(), "yyyyMMdd") + ".txt";
        String decryptionFileName = "decryption_" + DateUtils.formatDate(new Date(), "yyyyMMdd") + ".txt";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Collection<String> sourceLines = FileUtils.readList(new File(sourceFilePath));
        Collection<String> encryptionLines = sourceLines.parallelStream()
                .map(line -> StringUtils.substringAfterLast(line, "="))
                .map(line -> AesUtils.encryptString(line))
                .collect(Collectors.toList());
        FileUtils.writeLines(new File(fileRootPath + encryptionFileName), encryptionLines);
        stopWatch.stop();
        System.out.println(MessageFormat.format("加密写文件共耗时：{0} 秒", stopWatch.getTime(TimeUnit.SECONDS)));
        System.out.println(MessageFormat.format("加密后的集合中含有不重复的密文：{0} 条", encryptionLines.stream().distinct().collect(Collectors.toList()).size()));
        encryptionLines.stream().forEach(System.out::println);

        stopWatch.reset();
        stopWatch.start();
        encryptionLines = FileUtils.readLines(new File(fileRootPath + encryptionFileName), Charset.forName("utf-8"));
        Collection<String> decryptionLines = encryptionLines.parallelStream().map(line -> AesUtils.decryptString(line)).collect(Collectors.toList());
        FileUtils.writeLines(new File(fileRootPath + decryptionFileName), decryptionLines);

        stopWatch.stop();
        System.out.println(MessageFormat.format("解密写文件总共耗时：{0} 秒", stopWatch.getTime(TimeUnit.SECONDS)));
        System.out.println(MessageFormat.format("解密后的集合中含有不重复的明文：{0} 条", decryptionLines.stream().distinct().collect(Collectors.toList()).size()));
        decryptionLines.stream().forEach(System.out::println);
    }
}