package com.newpermission.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class GenerateUtil {

	/**
	 * token生成工具
	 * @param params
	 * @param algorithm
	 * @return
	 */
	public static String generateToken(String[] params, String algorithm) {
		String result = null;
		if (params != null & params.length > 0) {
			String[] array = params;
			Arrays.sort(array);
			StringBuilder builder = new StringBuilder();
			for (String string : array) {
				builder.append(string);
			}
			MessageDigest messageDigest;
			byte[] bytes = {};
			try {
				messageDigest = MessageDigest.getInstance(algorithm);
				bytes = messageDigest.digest(builder.toString().getBytes(StandardCharsets.UTF_8));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			result = byteToStr(bytes);
		}
		return result;
	}
	
	private static String byteToHexStr(byte mByte){
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }
	
	//二进制转字符串
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString();
    }
    
    private static String byteToStr(byte[] byteArray) {
    	StringBuilder digest =  new StringBuilder();
        for(int i = 0;i < byteArray.length; i++) {
            digest.append(byteToHexStr(byteArray[i]));
        }
        return digest.toString();
    }
    
    public static void main(String[] args) {
		String[] params = {"12345678",System.currentTimeMillis()+""};
		String token = generateToken(params, "SHA-1");
		System.out.println(token);
	}
}
