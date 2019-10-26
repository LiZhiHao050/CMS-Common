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
	 * 	��֤�Ƿ�Ϊ���ӵ�ַ
	 * @param str
	 * @return
	 */
	public static boolean isUrl(String str) {
		str = str.toLowerCase();               // ת��ΪСд
		
		// ��������
		String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https��http��ftp��rtsp��mms
	                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp��user@  
	                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP��ʽ��URL- ���磺199.194.52.184  
	                + "|" // ����IP��DOMAIN��������
	                + "([0-9a-z_!~*'()-]+\\.)*" // ����- www.  
	                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // ��������  
	                + "[a-z]{2,6})" // first level domain- .com or .museum  
	                + "(:[0-9]{1,5})?" // �˿ں����Ϊ65535,5λ��
	                + "((/?)|" // a slash isn't required if there is no file name  
	                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		return str.matches(regex);                  // ����������֤
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
	
	/**
	 * 	����Ψһ��ǩ��
	 * @param term
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String toUniqueTerm(String term) throws UnsupportedEncodingException {
		term = term.toLowerCase();        // ת��ΪСд
		term = term.trim();               // ȥ��ǰ��ո�
		term = term.replaceAll(" ", "-"); // �滻�ַ���
		return URLEncoder.encode(term, "UTF-8");
	}
	
	
	
	/**
	 * ͳ���ַ�����
	 * @param text          Ҫͳ�Ƶ��ı�
	 * @param diffCase      �Ƿ�ת����Сд
	 * @return
	 */
	public static Map<Character, Integer> countChars(String text, boolean diffCase) {
		Map<Character, Integer> hashMap = new HashMap<>();
		
		// �ж��Ƿ����ִ�Сд
		if (!diffCase) {
			text = text.toLowerCase();           // ����ת��ΪСд
		}
		
		text = text.replace("\r\n", " ");        // ȥ������
		char[] charArray = text.toCharArray();   // ת����char����
		
		for (char c : charArray) {
			if (hashMap.containsKey(c)) {        // �жϼ������Ƿ���ڸü�(����)
				Integer num = hashMap.get(c);    // ��ȡֵ
				hashMap.put(c, num + 1);         // ֵ����
			} else {                                
				hashMap.put(c, 1);               // �����½�
			}
		}
		
		return hashMap;
	}
	
	/**
	 * ͳ�Ƶ��ʸ���
	 * @param text       Ҫͳ�Ƶ��ı�
	 * @param diffCase   �Ƿ����ִ�Сд
	 * @return
	 */
	public static Map<String, Integer> countLetter(String text, boolean diffCase) {
		Map<String, Integer> hashMap = new HashMap<>();
		
		// �ж��Ƿ����ִ�Сд
		if (!diffCase) {
			text = text.toLowerCase();           // ����ת��ΪСд
		}
		
		text = text.replace("\r\n", " ");        // ȥ������
		String[] letterArr = text.split("\\W");  // ͨ��������ݷ���ĸ�и��ַ���
		
		for (String str : letterArr) {
			if (hashMap.containsKey(str)) {      // �жϼ������Ƿ���ڸü�(����)
				Integer num = hashMap.get(str);  // ��ȡֵ
				hashMap.put(str, num + 1);       // ֵ����
			} else {
				hashMap.put(str, 1);             // �����½�
			}
		}
		
		return hashMap;
	}
	
	/**
	 * ����ٷֱȹ�����
	 * @param num
	 * @param total
	 * @return
	 */
	public static String percent(int num, int total) {
		String res = new DecimalFormat("0").format((double)num / total * 100);   // ����0λС��
		return res;
	}
	
	
}
