package com.lizhihao.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author LZH
 * @Date 2019年10月12日
 *	文件工具类
 */

public class FileUtils {
	
	/**
	 * 复制文件
	 * @param src 源文件目录
	 * @param dst 目标文件目录
	 * @throws Exception 
	 */
	public static void copyFile(String src, String dst) throws Exception {
		File scrFile = new File(src);
		// 对源文件验证
		if (!scrFile.exists() || !scrFile.isFile()) {
			System.out.println("源文件不存在,不能复制");
			return;
		}
		
		File dstFile = new File(dst);
		// 对目标文件存在验证
		if (dstFile.exists()) {
			System.out.println("目标文件已经存在!不能复制");
			return;
		}
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(scrFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dstFile));
		
		byte[] buf = new byte[8192];
		int len = 0;
		
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		
		// 调用工具类关闭流
		StreamUtils.closeStream(bos, bis);
		
		/*
		// 获取输入流
		FileInputStream fis = new FileInputStream(scrFile);
		FileOutputStream fos = new FileOutputStream(dstFile);
		
		byte bs[] = new byte[1024];
		while(fis.read(bs)>=0) {
			fos.write(bs);
		}
		
		//调用流工具类 去关闭流
		StreamUtils.closeStream(fis,fos);*/
		
	}
	
	
	/**
	 * 删除文件
	 * @param path 要删除的文件路径
	 */
	public static void del(String path) {
		
		File file = new File(path);
		
		if (!file.exists()) {
			System.out.println("该文件不存在");
			return;
		}
		
		if (file.isFile()) {      // 如果是文件
			System.out.println("删除文件:" + path);
			file.delete();
		} else {                  // 否则是路径
			String[] list = file.list();
			for (String str : list) {
				String subFileName = path + "\\" + str;
				del(subFileName);
			}
			System.out.println("删除目录  " + path);
			file.delete();
		}
		
	}
	
	
	/**
	 * 按行读取文件
	 * @return
	 * @throws IOException 
	 */
	public static String readFileByLine(String fileName) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(fis);
		BufferedReader  bufferedReader = new BufferedReader(reader);
		String str = null;
		while( (str=bufferedReader.readLine())!=null ) {
			sb.append(str).append("\r\n");// 追加
		}
		//关闭流
		StreamUtils.closeStream(fis);
		return sb.toString();
		
	} 
	
	/**
	 * 读取文件
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFile(String fileName) throws IOException {
		List<String> strList = new ArrayList<>();
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
		BufferedReader  bufferedReader = new BufferedReader(reader);
		String str = null;
		while( (str=bufferedReader.readLine())!=null ) {
			strList.add(str);
		}
		//关闭流
		StreamUtils.closeStream(fis);
		return strList;
	} 

	
	/**
	 * 	获取文件夹下的文件列表
	 * @param pathName          具体路径
	 * @return
	 */
	public static List<String> getFileList(String pathName) {
		String[] list = new File(pathName).list();
		List<String> fileList = new ArrayList<String>();
		for (String string : list) {
			File subFile = new File(pathName + "\\" + string);
			if (subFile != null && subFile.exists() && subFile.isFile())
				fileList.add(pathName + "\\" + string);
		}
		return fileList;
	}
	
	/**
	 * 	返回该文件的扩展名称
	 * @param fileName
	 * @return
	 */
	public static String getExtendName(String fileName) {
		
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		return suffix;
	}
	
}

