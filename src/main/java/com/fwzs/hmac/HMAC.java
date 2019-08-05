package com.fwzs.hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {
    /**
     * 定义加密方式
     * MAC算法可选以下多种算法
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    private static final String HMAC_SHA1 = "sha256";

    /**
     * 生成签名数据_HmacSHA1加密
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     */
    public static String getSignature(String data, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        // 根据给定的字节数组构造一个密钥。
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());
        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }

    private static String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //测试签名c
            System.out.println("测试签名：" + HMAC.getSignature("appid=wx8bab270a99d0025b&mch_id=1493317872&nonce_str=E475277BDBE34FF8ACD0AD4F066866B6&partner_trade_no=20190419161746736207402", "wnzckj753951WNZCKJ75395174185296"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
