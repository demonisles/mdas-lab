package com.thfund.mdas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Shutdown {
	public static void main(String[] args) {
		try {
			// 创建socket对象，指定服务器的ip地址，和服务器监听的端口号
			// 客户端在new的时候，就发出了连接请求，服务器端就会进行处理，如果服务器端没有开启服务，那么
			// 这时候就会找不到服务器，并同时抛出异常==》java.net.ConnectException: Connection
			// refused: connect
			Socket s1 = new Socket("127.0.0.1", 9999);
			// 打开输出流
			OutputStream os = s1.getOutputStream();
			// 封装输出流
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF("shutdown");
			dos.close();
			s1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
