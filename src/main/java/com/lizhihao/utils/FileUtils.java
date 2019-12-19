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
 * @Date 2019��10��12��
 *	�ļ�������
 */

public class FileUtils {
	
	/**
	 * �����ļ�
	 * @param src Դ�ļ�Ŀ¼
	 * @param dst Ŀ���ļ�Ŀ¼
	 * @throws Exception 
	 */
	public static void copyFile(String src, String dst) throws Exception {
		File scrFile = new File(src);
		// ��Դ�ļ���֤
		if (!scrFile.exists() || !scrFile.isFile()) {
			System.out.println("Դ�ļ�������,���ܸ���");
			return;
		}
		
		File dstFile = new File(dst);
		// ��Ŀ���ļ�������֤
		if (dstFile.exists()) {
			System.out.println("Ŀ���ļ��Ѿ�����!���ܸ���");
			return;
		}
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(scrFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dstFile));
		
		byte[] buf = new byte[8192];
		int len = 0;
		
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		
		// ���ù�����ر���
		StreamUtils.closeStream(bos, bis);
		
		/*
		// ��ȡ������
		FileInputStream fis = new FileInputStream(scrFile);
		FileOutputStream fos = new FileOutputStream(dstFile);
		
		byte bs[] = new byte[1024];
		while(fis.read(bs)>=0) {
			fos.write(bs);
		}
		
		//������������ ȥ�ر���
		StreamUtils.closeStream(fis,fos);*/
		
	}
	
	
	/**
	 * ɾ���ļ�
	 * @param path Ҫɾ�����ļ�·��
	 */
	public static void del(String path) {
		
		File file = new File(path);
		
		if (!file.exists()) {
			System.out.println("���ļ�������");
			return;
		}
		
		if (file.isFile()) {      // ������ļ�
			System.out.println("ɾ���ļ�:" + path);
			file.delete();
		} else {                  // ������·��
			String[] list = file.list();
			for (String str : list) {
				String subFileName = path + "\\" + str;
				del(subFileName);
			}
			System.out.println("ɾ��Ŀ¼  " + path);
			file.delete();
		}
		
	}
	
	
	/**
	 * ���ж�ȡ�ļ�
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
			sb.append(str).append("\r\n");// ׷��
		}
		//�ر���
		StreamUtils.closeStream(fis);
		return sb.toString();
		
	} 
	
	/**
	 * ��ȡ�ļ�
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
		//�ر���
		StreamUtils.closeStream(fis);
		return strList;
	} 

	
	/**
	 * 	��ȡ�ļ����µ��ļ��б�
	 * @param pathName          ����·��
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
	 * 	���ظ��ļ�����չ����
	 * @param fileName
	 * @return
	 */
	public static String getExtendName(String fileName) {
		
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		return suffix;
	}
	
}

