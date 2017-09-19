package com.flyingwillow.restaurant.util.web;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.OAuth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuxuhui on 2017/9/14.
 */
public class WebUtil {

    public static void writeJsonResponse(HttpServletResponse response, Integer statusCode, String body){
        try {
            response.setContentType(OAuth.ContentType.JSON);    //json
            response.setStatus(statusCode);

            final PrintWriter out = response.getWriter();
            out.print(body);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRedirect(HttpServletResponse response, String url){
        //response.setStatus(statusCode);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve client ip address
     *
     * @param request HttpServletRequest
     * @return IP
     */
    public static String retrieveClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean isUnAvailableIp(String ip) {
        return (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip));
    }

    public static HttpHeaders getUTF8Header(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }
}
