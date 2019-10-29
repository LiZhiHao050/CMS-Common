package com.lizhihao.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author LZH
 * @Date 2019��10��12��
 * ����������
 */

public class StreamUtils {

	/**
	 *  �ر����е���
	 * @param n
	 * @param streams
	 * @throws IOException
	 */                           // �ɱ����
	public static void closeStream(Closeable ... streams) throws IOException {
		
		for (int i = 0; i < streams.length; i++) {
			streams[i].close();
		}
		
	}
	
}

