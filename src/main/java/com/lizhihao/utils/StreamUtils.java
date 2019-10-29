package com.lizhihao.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author LZH
 * @Date 2019年10月12日
 * 流处理工具类
 */

public class StreamUtils {

	/**
	 *  关闭所有的流
	 * @param n
	 * @param streams
	 * @throws IOException
	 */                           // 可变参数
	public static void closeStream(Closeable ... streams) throws IOException {
		
		for (int i = 0; i < streams.length; i++) {
			streams[i].close();
		}
		
	}
	
}

