package com.lizhihao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

/**
 * @author LZH
 * @Date 2019��11��20��
 * 	����ַ�������
 */

public class RandomUtils {
	
	// ����1������min-max֮����������������min��maxֵ�������緵��1-3֮������������ô����1��2��3������ȷ�ģ�����4�Ͳ��ԡ� (5��)
	public static int random(int min, int max) {
		// ʵ���������������
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;
		return i;
	}

	// ����2������Сֵmin�����ֵmax֮���ȡsubs�����ظ����������������1-10֮��ȡ3�����ظ������������ô[2,6,9]�ǶԵģ�[3,5,5]�򲻶ԣ���Ϊ5�ظ��ˡ�Ӧ�ó�������100ƪ���������ȡ10ƪ���£��¿����ܻ�ʹ�õ���
	public static int[] subRandom(int min, int max, int subs) {
		// ����������.���������ظ�����
		HashSet<Integer> set = new HashSet<Integer>();

		// ����Ŀ������.������������
		int[] dest = new int[subs];
		
		//��set����������������,
		while (set.size() != subs) {
			int x = random(min, max);
			set.add(x);
		}
		//����set����,����Ŀ������
		int y = 0;
		for (int value : set) {
			dest[y] = value;
			y++;
		}

		return dest;
	}


	// ����3������1��1-9,a-Z֮�������ַ��� (8��)
	public static char randomCharacter() {
		String str="123456789qwrtyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		
		return str.charAt(random(0, str.length() -1));
	}

	
	// ����4�����ز���length���ַ���(5��)�������ڲ�Ҫ����randomCharacter()���� (4��)
	public static String randomString(int length) {
		String str="";
		for(int i =0;i<length; i++) {
			str+=randomCharacter();
		}
		
		return str;
	}
	
	//����ʱ�� �������(�ַ�������)
	public static Date randomDate(String stratDate,String endDate) {
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
		long date = 0L;
		try {
			Date d1 = st.parse(stratDate);
			Date d2 = st.parse(endDate);
			date = (long) (Math.random() * (d2.getTime() - d1.getTime() + 1) +d1.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(date);
		
	}
	
	//����ʱ�� �������(���ڲ���)
	public static Date randomDate(Date stratDate,Date endDate) {
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = st.format(stratDate);
		String s2 = st.format(endDate);
		return randomDate(s1, s2);
	}
	
	
	//���13��ͷ�ĵ绰��
	public static String random13Phone() {
		long i = (long)(Math.ceil((Math.random()*(999999999-100000000.0) + 1)+100000000));
		String phone = i+"";
		return "13"+i;
	}

}

