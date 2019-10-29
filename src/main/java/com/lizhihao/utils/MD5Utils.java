package com.lizhihao.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author LZH
 * @Date 2019��10��16��
 * ������ܹ�����
 */

public class MD5Utils {
	
	// ����
	private static String saltString = "eclipseidea";
	
	/**
	 * �����㷨
	 * @param pwd
	 * @return
	 */
	public static String md5(String pwd) {
		String md5 = DigestUtils.md5Hex(pwd + saltString);
		return md5;
	}
	
	/**
	 * �����㷨(�Դ���)
	 * @param pwd
	 * @return
	 */
	public static String md5(String pwd, String salt) {
		String md5 = DigestUtils.md5Hex(pwd + salt);
		return md5;
	}

}

