package com.training.apache.commons.random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description random and UUID CommonCollectionsTest
 * @Author Tyler Yin
 * @Create 2017-11-16 21:23
 **/
public class RandomTest {

    /**
     * 因为Random生成的随机数是伪随机，相同的种子依据相同的调用顺序，会生成相同的随机数
     * 所以在实际使用时，应该加上当前时间做为Random对象的种子
     * Random使用一个48位的种子
     */
    @Test
    public void randomTest() {
        final Random rand = new Random();
        System.out.println(rand.nextBoolean());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextInt());

        //实际使用时，应该使用下面的方式
        Random rand1 = new Random(System.currentTimeMillis());
        System.out.println(rand1.nextFloat());
    }

    /**
     * 多线程环境下应该使用ThreadLocalRandom
     * 其用法基本上同Random
     */
    @Test
    public void threadLocalRandomTest() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        System.out.println(rand.nextInt(4, 20));
        System.out.println(rand.nextDouble(3.0, 10.0));
    }

    /**
     * 使用UUID生成一个随机数，一般情况下，使用其生成持久化对象的主键
     * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
     * 按照开放软件基金会(OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、芯片ID码和许多可能的数字。
     * 由以下几部分的组合：当前日期和时间(UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同)，时钟序列，
     * 全局唯一的IEEE机器识别号（如果有网卡，从网卡获得，没有网卡以其他方式获得），UUID的唯一缺陷在于生成的结果串会比较长。
     */
    @Test
    public void uuidTest() {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());

        //去掉“-”符号
        final String s = UUID.randomUUID().toString();
        System.out.println(s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24));
    }

    /**
     * RandomStringUtils.random已经过时，现在使用commons-text包下的RandomStringGenerator来生成随机字符串
     */
    @Test
    public void randomStringUtilsTest() {
        //使用字母a-z，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator1 = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        String randomLetters = generator1.generate(20);
        System.out.println(StringUtils.center("随机字母字符串", 20, "="));
        System.out.println(randomLetters);

        //使用数字0-9，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator2 = new RandomStringGenerator.Builder()
                .withinRange('0', '9').build();
        String randomNumbers = generator2.generate(4);
        System.out.println(StringUtils.center("随机数字字符串", 20, "="));
        System.out.println(randomNumbers);

        //使用码位为0到z的字符，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator4 = new RandomStringGenerator.Builder()
                .withinRange('0', 'z').build();
        String random = generator4.generate(20);
        System.out.println(StringUtils.center("随机混合字符串", 20, "="));
        System.out.println(random);
    }
}
