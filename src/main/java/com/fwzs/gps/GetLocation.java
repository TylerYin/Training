package com.fwzs.gps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetLocation {
    public static void main(String[] args) {
        JSONObject a = getAdd("34.350178", "108.949324");
        System.out.println("省市县" + a.toString());
    }

    public static JSONObject getAdd(String log, String lat) {
        //阿里云地图调用(100代表道路，010代表兴趣点，001代表门址，111可以同时显示前三项)
        //构造URL
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
        String res = "";
        try {
            //先进行请求
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置可输入
            conn.setDoOutput(true);
            //请求方式为POST
            conn.setRequestMethod("POST");
            //提取获得的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            //关闭连接
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (res != null && !res.equals("")) {
            //此处可根据自己情况进行封装 此jsonObject为获得的所有数据构建的Json
            JSONObject jsonObject = JSONObject.fromObject(res);
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
            JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
            String allAdd = j_2.getString("admName");
            String ssx[] = allAdd.split(",");
            if (ssx.length == 3) {
                JSONObject address = new JSONObject();
                address.put("province", ssx[0]);
                address.put("city", ssx[1]);
                address.put("county", ssx[2]);
                System.err.println("省市县" + ssx[0] + " " + ssx[1] + " " + " " + ssx[2]);
                return address;
            }
            return null;
        } else {
            return null;
        }
    }
}
