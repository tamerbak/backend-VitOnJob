package com.vitonjob.utils;

import java.util.Base64;

public class StringUtils {

	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().equals("");
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	public static String encode64(String strDecoded) {
		if (isEmpty(strDecoded)) {
			return null;
		}
		return new String(Base64.getEncoder().encode(strDecoded.getBytes()));
	}

	public static String decode64(String strEncoded) {
		if (isEmpty(strEncoded)) {
			return null;
		}
		return new String(Base64.getDecoder().decode(strEncoded.getBytes()));
	}
	
//	public static String replaceAll(String string) {
//        return string.replaceFirst("\\d*", "");
//    }
//	
	public static void main(String[] args) {
		System.out.println(encode64("Basic rachid@test.com:123456"));
	}
}
