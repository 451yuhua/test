package com.newpermission.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

	private static String LOCAL_IP = "127.0.0.1";
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
        if (!isIpAddress(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (!isIpAddress(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!isIpAddress(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }

        if (!isIpAddress(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (!isIpAddress(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals(LOCAL_IP) || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if (!isIpAddress(ipAddress) && ipAddress.length() > 15) {
            Optional<String> optional = Arrays.asList(ipAddress.split(","))
                    .stream()
                    .filter(ip -> !"unknown".equalsIgnoreCase(ip))
                    .findFirst();
            if (null != optional) {
                ipAddress = optional.get();
            }
        }

        return ipAddress;
	}
	
	/**
     * 判断是否ip地址
     *
     * @param ipAddress
     * @return
     */
    public static boolean isIpAddress(String ipAddress) {
        return null != ipAddress
                && ipAddress.length() > 0
                && !"unknown".equalsIgnoreCase(ipAddress)
                && !"0:0:0:0:0:0:0:1".equals(ipAddress);
    }
}
