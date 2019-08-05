package com.fwzs.wechat;

/**
 * 类名: SnsUserInfo </br>
 * 描述: 通过网页授权获取的用户信息 </br>
 *
 * @Author Tyler Yin
 */
public class SnsUserInfo {
    /**
     * 用户标识
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别（1是男性，2是女性，0是未知）
     */
    private int sex;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 用户头像链接
     */
    private String headImgUrl;

    /**
     * 用户语言
     */
    private String language;

    /**
     * 是否订阅公众号:1表示订阅，0表示未订阅
     */
    private String subscribe;

    /**
     * 订阅公众号时间
     */
    private String subscribeTime;

    /**
     * 订阅公众号方式
     */
    private String subscribeScene;

    /**
     * 获取微信用户信息结果状态，1表示获取成功，0表示获取失败
     */
    private String status;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SnsUserInfo{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", language='" + language + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", subscribeScene='" + subscribeScene + '\'' +
                '}';
    }
}
