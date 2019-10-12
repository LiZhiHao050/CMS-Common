package com.lzh.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	// 定义字符数组
	public static char cs[] = new char[36];
	// 初始化数组
	static {
		int index = 0;
		for (char i = 'a'; i <= 'z'; i++) {
			cs[(int) index++] = i;
		}

		for (char i = '0'; i <= '9'; i++) {
			cs[(int) index++] = i;
		}

	}

	/**
	 * 判断源字符串是否为空，空引号也算没值；
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || 0 == str.trim().length());
	}

	/**
	 * 判断源字符串是否有值，空引号也算没值；
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str) {
		return str != null && 0 < str.trim().length();
	}

	/**
	 * 生成随机字符
	 * 
	 * @param n
	 * @return
	 */
	public static String randomChar(int n) {
		Random random = new Random();
		String str = "";
		for (int i = 0; i < n; i++) {
			char c = (char) ('a' + random.nextInt(26));// a - z
			str += c;
		}
		return str;

	}

	/**
	 * 随机生成长度为n的字符串，其中包含字母和数字
	 * 
	 * @param n
	 * @return
	 */
	public static String randomCharAndNumber(int n) {

		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			sb.append(cs[random.nextInt(36)]);
		}
		return sb.toString();

	}

	/**
	 * 获取一个文件名称的扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1) {
			return "";
		}

		return fileName.substring(fileName.lastIndexOf('.'));
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String reg = "[0-9]+";
		return str.matches(reg);
	}

	/**
	 * 判断是否为邮箱
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 校验一个字符串是否为正确的电话号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(phone);
		boolean isMatch = m.matches();
		return isMatch;
	}

}
