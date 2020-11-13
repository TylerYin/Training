package com.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Tyler Yin
 * @Date: 2020-06-14 15:01
 * @Description:
 **/
public class AddressLngLatExchangeUtils {

    private final static Logger logger = LoggerFactory.getLogger(AddressLngLatExchangeUtils.class);

    private static final String KEY = "4b04d2a377395cc3c4de50a0f64d1aa0";
    private static final String OUTPUT = "JSON";
    private static final String GET_LNG_LAT_URL = "http://restapi.amap.com/v3/geocode/geo";
    private static final String GET_ADDRESS_FROM_LNG_LAT = "http://restapi.amap.com/v3/geocode/regeo";
    private static final String EXTENSIONS_ALL = "all";

    public static final String PROVINCE = "province";
    public static final String DISTRICT = "district";
    public static final String TOWNSHIP = "township";
    public static final String CITY = "city";
    public static final String ADDRESS = "address";

    /**
     * @param
     * @return Pair<BigDecimal,BigDecimal> 左节点值为经度，右节点值为纬度
     * @description 根据传进来的一个地址，查询对应的经纬度
     * @author jxp
     * @date 2017年7月12日
     */
    public static Pair<BigDecimal, BigDecimal> getLngLatFromOneAddr(String address) {
        if (StringUtils.isBlank(address)) {
            return null;
        }

        Map<String, String> params = new HashMap<>(3);
        params.put("address", address);
        params.put("output", OUTPUT);
        params.put("key", KEY);

        String result = HttpClientUtils.doPost(GET_LNG_LAT_URL, params);
        Pair<BigDecimal, BigDecimal> pair = null;

        // 解析返回的xml格式的字符串result，从中拿到经纬度
        // 调用高德API，拿到json格式的字符串结果
        JSONObject jsonObject = JSONObject.fromObject(result);
        // 拿到返回报文的status值，高德的该接口返回值有两个：0-请求失败，1-请求成功；
        int status = Integer.valueOf(jsonObject.getString("status"));

        if (status == 1) {
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                String lngLat = json.getString("location");
                String[] lngLatArr = lngLat.split(",");

                // 经度
                BigDecimal longitude = new BigDecimal(lngLatArr[0]);
                System.out.println("经度" + longitude);

                // 纬度
                BigDecimal latitude = new BigDecimal(lngLatArr[1]);
                System.out.println("纬度" + latitude);

                pair = new MutablePair<>(longitude, latitude);
            }
        } else {
            String errorMsg = jsonObject.getString("info");
        }
        return pair;
    }

    /**
     * @param lng：经度，lat：纬度
     * @return 地址
     * @description 根据经纬度查地址
     */
    public static Map<String, String> getAddressFromLngLat(String lng, String lat) {
        Map<String, String> params = new HashMap<>(4);
        params.put("key", KEY);
        params.put("output", OUTPUT);
        params.put("location", lng + "," + lat);
        params.put("extensions", EXTENSIONS_ALL);
        String result = HttpClientUtils.doGet(GET_ADDRESS_FROM_LNG_LAT, params);
        JSONObject json = JSONObject.fromObject(result);

        Map<String, String> addressMap = new HashMap<>(4);
        if ("1".equals(json.getString("status"))) {
            JSONArray provinceArray = new JSONArray();
            JSONArray cityArray = new JSONArray();
            JSONArray districtArray = new JSONArray();
            JSONArray townshipArray = new JSONArray();

            JSONObject regeocode = JSONObject.fromObject(json.get("regeocode"));
            JSONObject jo = regeocode.getJSONObject("addressComponent");
            if (jo.get(PROVINCE) instanceof JSONArray) {
                provinceArray = (JSONArray) jo.get(PROVINCE);
            }

            if (jo.get(CITY) instanceof JSONArray) {
                cityArray = (JSONArray) jo.get(CITY);
            }

            if (jo.get(DISTRICT) instanceof JSONArray) {
                districtArray = (JSONArray) jo.get(DISTRICT);
            }

            if (jo.get(TOWNSHIP) instanceof JSONArray) {
                townshipArray = (JSONArray) jo.get(TOWNSHIP);
            }

            addressMap.put(PROVINCE, provinceArray.size() == 0 ? jo.getString(PROVINCE) : "0");
            addressMap.put(CITY, cityArray.size() == 0 ? jo.getString(CITY) : "0");
            addressMap.put(DISTRICT, districtArray.size() == 0 ? jo.getString(DISTRICT) : "0");
            addressMap.put(TOWNSHIP, townshipArray.size() == 0 ? jo.getString(TOWNSHIP) : "0");
            addressMap.put(ADDRESS, regeocode.getString("formatted_address"));

            if (logger.isDebugEnabled()) {
                logger.debug("解析经度：" + lng + "，纬度：" + lat);
                logger.debug("解析结果：省=" + addressMap.get(PROVINCE) + "，市=" + addressMap.get(CITY) + "，区=" + addressMap.get(DISTRICT) + "，街道=" + addressMap.get(TOWNSHIP));
                logger.debug("解析结果：" + addressMap.get(ADDRESS));
            }
        }
        return addressMap;
    }
}