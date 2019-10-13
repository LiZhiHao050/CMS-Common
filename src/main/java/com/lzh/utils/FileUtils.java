package com.lzh.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

}

