package com.lizhihao.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author LZH
 * @Date 2019年10月16日
 * 密码加密工具类
 */

public class MD5Utils {
	
	// 加盐
	private static String saltString = "eclipseidea";
	
	/**
	 * 加密算法
	 * @param pwd
	 * @return
	 */
	public static String md5(String pwd) {
		String md5 = DigestUtils.md5Hex(pwd + saltString);
		return md5;
	}
	
	/**
	 * 加密算法(自带盐)
	 * @param pwd
	 * @return
	 */
	public static String md5(String pwd, String salt) {
		String md5 = DigestUtils.md5Hex(pwd + salt);
		return md5;
	}

}

