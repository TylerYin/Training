package com.fwzs.wechat;

import com.util.DateUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * @Author Tyler Yin
 */
public class AccessTokenTest {

    @Test
    public void testGetUserInfo() {
        String openId = "oVocE0o31jNIdxFr72BjTTItxBzU";
        try {
            String accessToken = AccessTokenTest.getAccessToken();
            if (StringUtils.isNotBlank(accessToken)) {
                getSnsUserInfo(accessToken, openId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SnsUserInfo getSnsUserInfo(String accessToken, String openId) {
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        // 通过网页授权获取用户信息
        SnsUserInfo snsUserInfo = null;
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                snsUserInfo = new SnsUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户语言
                snsUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户是否订阅公众号
                snsUserInfo.setSubscribe(jsonObject.getString("subscribe"));

                // 订阅公众号时间
                Long subscribeTime = new Long(jsonObject.getString("subscribe_time"));
                subscribeTime = subscribeTime * 1000;
                Date date = new Date(subscribeTime);
                snsUserInfo.setSubscribeTime(DateUtils.formatDateTime(date));

                // 订阅公众号方式
                snsUserInfo.setSubscribeScene(jsonObject.getString("subscribe_scene"));
                snsUserInfo.setStatus("1");

                System.out.println(snsUserInfo);
            } catch (Exception e) {
                snsUserInfo.setStatus("0");
                System.out.println("获取微信用户信息失败：" + e.getMessage());

                if (null != jsonObject.get("errcode")) {
                    System.out.println("获取微信用户信息失败错误代码：" + jsonObject.getString("errcode"));
                }

                if (null != jsonObject.get("errmsg")) {
                    System.out.println("获取微信用户信息失败错误信息：" + jsonObject.getString("errmsg"));
                }
            }
        }
        return snsUserInfo;
    }

    /**
     * 公众平台的API调用所需的access_token的
     *
     * @return
     * @throws Exception
     */
    private static String getAccessToken() throws Exception {
        //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8bab270a99d0025b&secret=e99a71e13e3882868592352d2d1c102b";
        System.out.println("URL for getting accessToken accessTokenUrl=" + accessTokenUrl);

        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");

        //获取access_token
        JSONObject jsonObject = JSONObject.fromObject(message);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("accessToken=" + accessToken);
        System.out.println("expires_in=" + expires_in);
        return accessToken;
    }
}
