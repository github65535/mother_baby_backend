package com.ypika.mp.common.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *获取用户地理位置工具
 *
 * Created by duanpengyang on 2018/1/9.
 */
public class LocationUtil {

    /**
     * 获取用户所在的省
     * @param lon
     * @param lat
     * @return
     */
    public static String getLocation(String lon,String lat){
        if (StrUtil.isNull (lon) || StrUtil.isNull (lat)) {
            return null;
        }
        String add = null;
        String city = null;
        try {
            if (lon != null && lat != null) {
                add = getAdd(lat, lon);
            } else {
                return null;
            }
            Map map = JsonUtil.jsonToMap(add);
            List list = (List) map.get("addrList");
            HashMap arrayList = (HashMap) list.get(0);
            city = arrayList.get("admName").toString().substring(0, arrayList.get("admName").toString().indexOf(","));
        }catch (Exception e){
            return city;
        }
        return  city;
    }



    private static String getAdd(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
//        System.out.println(res);
        return res;
    }

}
