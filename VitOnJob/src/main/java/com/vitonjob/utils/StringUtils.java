package com.vitonjob.utils;

public class StringUtils {

	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().equals("");
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}
}
