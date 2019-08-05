package com.training.apache.commons.codec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Tyler Yin
 */
public class CodecTest {

    private String test;

    @Before
    public void before() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        test = generator.generate(15);
    }

    @Test
    public void testSha1() {
        System.out.println("SHA1 明文：" + test);
        System.out.println("SHA1 密文：" + DigestUtils.sha1Hex(test));
    }

    @Test
    public void testMd5() {
        System.out.println("MD5 明文：" + test);
        System.out.println("MD5 密文：" + DigestUtils.md5Hex(test));
    }
}
