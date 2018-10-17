package com.ypika.mp.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类 获取用户的ip 地址
 * Created by duanpengyang on 2017/12/27.
 */
public class IpUtil {

    /**
     * 默认ip  如果获取不到真实设置一个默认ip
     */
    public final static String ERROR_IP ="127.0.0.1";

    /**
     * 获取用户真实的ip 地址
     * @param request
     * @return
     */
    public static String getUserIP(HttpServletRequest request) {

        // 优先取X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = ERROR_IP;
            }
        }

        if ("unknown".equalsIgnoreCase(ip)) {
            ip = ERROR_IP;
            return ip;
        }

        int pos = ip.indexOf(',');
        if (pos >= 0) {
            ip = ip.substring(0, pos);
        }
        return ip;
    }

    /**
     * 获取真实ip地址
     *
     * @param request 请求体
     * @return ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StrUtil.isNotNull(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                ip = ip.substring(0, index);
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StrUtil.isNotNull(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return StrUtil.isNotNull(ip) ? "127.0.0.1" : ip;
    }
}
