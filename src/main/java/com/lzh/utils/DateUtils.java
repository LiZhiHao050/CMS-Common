package com.lzh.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LZH
 * @Date 2019年10月12日 日期工具类
 */

public class DateUtils {

	/**
	 * 计算年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAge(Date birthday) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		// 获取出生的年月日
		int birthYear = cal.get(Calendar.YEAR);
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDate = cal.get(Calendar.DAY_OF_MONTH);

		// 获取今天的年月日
		cal.setTime(new Date());
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDate = cal.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;
		if (currentMonth < birthMonth) {
			age--;
		}
		
		else if (currentMonth == birthMonth) {
			if (currentDate < birthDate) {
				age--;
			}
		}

		return age;
	}
	
	/**
	 * 判断是否为当天
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");
		
		String dateStr = smt.format(date);
		String todayStr = smt.format(new Date());
		return dateStr.equals(todayStr);
		
	}
	
	
	/**
	 * 判断是否为今年
	 * @param date
	 * @return
	 */
	public static boolean isThisYear(Date date) {
		SimpleDateFormat smt = new SimpleDateFormat("yyyy");
		
		String dateStr = smt.format(date);
		String todayStr = smt.format(new Date());
		return dateStr.equals(todayStr);
	}
	
	
	/** 
	 *  
	 * 给定时间对象，初始化到该月初的1日0时0分0秒0毫秒
	 * 例如  给定 2017-08-23 13:24:16  返回 2017-08-01 00:00:00 
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//设置0秒
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM,Calendar.AM);
		cal.set(Calendar.DAY_OF_MONTH,1);
		return cal.getTime();
	}
	
	
	/**
	 * 求月末
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//设置0秒
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM,Calendar.AM);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH, 1);           // 先加1个月
		cal.add(Calendar.SECOND, -1);         // 再减1秒定位到上一个月
		return cal.getTime();
	}

}
