package com.ustc.sse.sseoj.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/24 14:44
 */
public class getIp {
    public static String getUserIP(HttpServletRequest request)
    {
        // 优先取 X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip))
            {
                ip = "127.0.0.1";
            }
        }
        if ("unknown".equalsIgnoreCase(ip))
        {
            ip = "127.0.0.1";
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0)
        {
            ip = ip.substring(0, index);
        }

        return ip;
    }

}
