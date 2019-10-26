package com.lzh.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LZH
 * @Date 2019年10月12日
 * 字符串工具类
 */

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
	 * 判断源字符串是否为空，空引号也算没值
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || 0 == str.trim().length());
	}

	/**
	 * 判断源字符串是否有值，空引号也算没值
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
		String reg = "[0-9]+\\.?[0-9]*";
		return str.matches(reg);
	}
	
	/**
	 * 	验证是否为链接地址
	 * @param str
	 * @return
	 */
	public static boolean isUrl(String str) {
		str = str.toLowerCase();               // 转换为小写
		
		// 定义正则
		String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
	                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
	                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184  
	                + "|" // 允许IP和DOMAIN（域名）
	                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
	                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
	                + "[a-z]{2,6})" // first level domain- .com or .museum  
	                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
	                + "((/?)|" // a slash isn't required if there is no file name  
	                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		return str.matches(regex);                  // 返回正则验证
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
	
	/**
	 * 字符串转HTML
	 * @param src
	 * @return
	 * Windows 系统换行符是 “\r\n”,Linux 系统是 “\n”，因此要将 \n\r 替换成一个 \n
	 * 再将 \n 结尾的这行文本用 <p></p > 标签包起来
	 * 如果遇到单个 \r 字符要使用 <br/> 标签替换
	 */
	public static String toHtml(String src) {
		String dst = src.replaceAll("\r\n", "\n");
		dst = dst.replaceAll("\n", "</p><p>");
		dst = "<p>" + dst + "</p>";
		dst = dst.replaceAll("\r", "<br/>");
		return dst;
	}
	
	/**
	 * 	生成唯一标签名
	 * @param term
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String toUniqueTerm(String term) throws UnsupportedEncodingException {
		term = term.toLowerCase();        // 转换为小写
		term = term.trim();               // 去除前后空格
		term = term.replaceAll(" ", "-"); // 替换字符串
		return URLEncoder.encode(term, "UTF-8");
	}
	
	
	
	/**
	 * 统计字符个数
	 * @param text          要统计的文本
	 * @param diffCase      是否转换大小写
	 * @return
	 */
	public static Map<Character, Integer> countChars(String text, boolean diffCase) {
		Map<Character, Integer> hashMap = new HashMap<>();
		
		// 判断是否区分大小写
		if (!diffCase) {
			text = text.toLowerCase();           // 将其转换为小写
		}
		
		text = text.replace("\r\n", " ");        // 去掉换行
		char[] charArray = text.toCharArray();   // 转换程char数组
		
		for (char c : charArray) {
			if (hashMap.containsKey(c)) {        // 判断集合内是否存在该键(存在)
				Integer num = hashMap.get(c);    // 获取值
				hashMap.put(c, num + 1);         // 值自增
			} else {                                
				hashMap.put(c, 1);               // 否则新建
			}
		}
		
		return hashMap;
	}
	
	/**
	 * 统计单词个数
	 * @param text       要统计的文本
	 * @param diffCase   是否区分大小写
	 * @return
	 */
	public static Map<String, Integer> countLetter(String text, boolean diffCase) {
		Map<String, Integer> hashMap = new HashMap<>();
		
		// 判断是否区分大小写
		if (!diffCase) {
			text = text.toLowerCase();           // 将其转换为小写
		}
		
		text = text.replace("\r\n", " ");        // 去掉换行
		String[] letterArr = text.split("\\W");  // 通过正则根据非字母切割字符串
		
		for (String str : letterArr) {
			if (hashMap.containsKey(str)) {      // 判断集合内是否存在该键(存在)
				Integer num = hashMap.get(str);  // 获取值
				hashMap.put(str, num + 1);       // 值自增
			} else {
				hashMap.put(str, 1);             // 否则新建
			}
		}
		
		return hashMap;
	}
	
	/**
	 * 计算百分比工具类
	 * @param num
	 * @param total
	 * @return
	 */
	public static String percent(int num, int total) {
		String res = new DecimalFormat("0").format((double)num / total * 100);   // 保留0位小数
		return res;
	}
	
	
}
