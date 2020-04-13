package com.fwzs.system;

import com.util.HashIds;
import org.junit.Test;

public class HashIdsTest {

    @Test
    public void test_large_nummber() {
        long num_to_hash = 999999999999999L;
        HashIds a = new HashIds("salt");
        String res = a.encode(num_to_hash);
        System.out.println(res);
        System.out.println(a.decode(res)[0]);
    }

//	@Test(expected=IllegalArgumentException.class)
//	public void test_large_nummber_not_supported() throws Exception{
//		long num_to_hash = 9007199254740993L;
//		HashIds a = new HashIds("this is my salt");
//    a.encryption(num_to_hash);
//	}
//
//  @Test
//  public void test_wrong_decoding(){
//    HashIds a = new HashIds("this is my pepper");
//    long[] b = a.decode("NkK9");
//    Assert.assertEquals(b.length, 0);
//  }
//
//	@Test
//	public void test_one_number(){
//    String expected = "NkK9", res;
//    long num_to_hash = 12345L;
//    long[] res2;
//    HashIds a = new HashIds("this is my salt");
//    res = a.encryption(num_to_hash);
//    Assert.assertEquals(res, expected);
//    res2 = a.decode(expected);
//    Assert.assertEquals(res2.length, 1);
//    Assert.assertEquals(res2[0], num_to_hash);
//	}
//
//	@Test
//	public void test_serveral_numbers(){
//    String expected = "aBMswoO2UB3Sj", res;
//    long[] num_to_hash = {683L, 94108L, 123L, 5L}, res2;
//    HashIds a = new HashIds("this is my salt");
//    res = a.encryption(num_to_hash);
//    Assert.assertEquals(res, expected);
//    res2 = a.decode(expected);
//    Assert.assertEquals(res2.length, num_to_hash.length);
//    Assert.assertTrue(Arrays.equals(res2, num_to_hash));
//	}
//
//  @Test
//  public void test_specifying_custom_hash_length(){
//    String expected = "gB0NV05e", res;
//    long num_to_hash = 1L;
//    long[] res2;
//    HashIds a = new HashIds("this is my salt", 8);
//    res = a.encryption(num_to_hash);
//    Assert.assertEquals(res, expected);
//    res2 = a.decode(expected);
//    Assert.assertEquals(res2.length, 1);
//    Assert.assertEquals(res2[0], num_to_hash);
//  }
//
//  @Test
//  public void test_randomness(){
//    String expected = "1Wc8cwcE", res;
//    long[] num_to_hash = {5L, 5L, 5L, 5L}, res2;
//    HashIds a = new HashIds("this is my salt");
//    res = a.encryption(num_to_hash);
//    Assert.assertEquals(res, expected);
//    res2 = a.decode(expected);
//    Assert.assertEquals(res2.length, num_to_hash.length);
//    Assert.assertTrue(Arrays.equals(res2, num_to_hash));
//  }
//
//  @Test
//  public void test_randomness_for_incrementing_numbers(){
//    String expected = "kRHnurhptKcjIDTWC3sx", res;
//    long[] num_to_hash = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L}, res2;
//    HashIds a = new HashIds("this is my salt");
//    res = a.encryption(num_to_hash);
//    Assert.assertEquals(res, expected);
//    res2 = a.decode(expected);
//    Assert.assertEquals(res2.length, num_to_hash.length);
//    Assert.assertTrue(Arrays.equals(res2, num_to_hash));
//  }
//
//  @Test
//  public void test_randomness_for_incrementing(){
//    HashIds a;
//    a = new HashIds("this is my salt");
//    Assert.assertEquals(a.encryption(1L), "NV");
//    Assert.assertEquals(a.encryption(2L), "6m");
//    Assert.assertEquals(a.encryption(3L), "yD");
//    Assert.assertEquals(a.encryption(4L), "2l");
//    Assert.assertEquals(a.encryption(5L), "rD");
//  }
//
//  @Test
//  public void test_for_vlues_greater_int_maxval(){
//    HashIds a = new HashIds("this is my salt");
//    Assert.assertEquals(a.encryption(9876543210123L), "Y8r7W1kNN");
//  }
//
//	@Test
//	public void test_issue10(){
//		String expected = "3kK3nNOe", res;
//		long num_to_hash = 75527867232l;
//		long[] res2;
//  	HashIds a = new HashIds("this is my salt");
//		res = a.encryption(num_to_hash);
//		Assert.assertEquals(res, expected);
//		res2 = a.decode(expected);
//		Assert.assertEquals(res2.length, 1);
//		Assert.assertEquals(res2[0], num_to_hash);
//	}

    @Test
    public void testSubtract() {
        int result = Integer.valueOf(Integer.valueOf("3") - Integer.valueOf("5"));
        System.out.println(result);
    }
}