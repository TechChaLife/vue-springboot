package com.xzit.util;

/**
 * 字符串相关的工具类
 * @author Charles
 *
 */
public class StringTools {
	
	public static boolean isNullOrEmpty(String str) {
		return null ==str || "".equals(str) || "null".equals(str);
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		return null==obj || "".equals(obj);
	}
}
