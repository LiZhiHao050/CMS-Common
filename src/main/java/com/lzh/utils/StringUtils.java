package com.lzh.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LZH
 * @Date 2019��10��12��
 * �ַ���������
 */

public class StringUtils {

	// �����ַ�����
	public static char cs[] = new char[36];
	// ��ʼ������
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
	 * �ж�Դ�ַ����Ƿ�Ϊ�գ�������Ҳ��ûֵ
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || 0 == str.trim().length());
	}

	/**
	 * �ж�Դ�ַ����Ƿ���ֵ��������Ҳ��ûֵ
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str) {
		return str != null && 0 < str.trim().length();
	}

	/**
	 * ��������ַ�
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
	 * ������ɳ���Ϊn���ַ��������а�����ĸ������
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
	 * ��ȡһ���ļ����Ƶ���չ��
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
	 * �ж��Ƿ�Ϊ����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String reg = "[0-9]+\\.?[0-9]*";
		return str.matches(reg);
	}

	/**
	 * �ж��Ƿ�Ϊ����
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
	 * У��һ���ַ����Ƿ�Ϊ��ȷ�ĵ绰����
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
	
	/**
	 * �ַ���תHTML
	 * @param src
	 * @return
	 * Windows ϵͳ���з��� ��\r\n��,Linux ϵͳ�� ��\n�������Ҫ�� \n\r �滻��һ�� \n
	 * �ٽ� \n ��β�������ı��� <p></p > ��ǩ������
	 * ����������� \r �ַ�Ҫʹ�� <br/> ��ǩ�滻
	 */
	public static String toHtml(String src) {
		String dst = src.replaceAll("\r\n", "\n");
		dst = dst.replaceAll("\n", "</p><p>");
		dst = "<p>" + dst + "</p>";
		dst = dst.replaceAll("\r", "<br/>");
		return dst;
	}
	
}
