package com.allinpal.mdas;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public void start() {
		try {
            // 指定服务器端的端口号为8888
            ServerSocket s = new ServerSocket(9999);
            while (true) {
                // 建立连接
                Socket socket = s.accept();
                // 打开输出流
                //OutputStream os = socket.getOutputStream();
                // 封装输出流
                //DataOutputStream dos = new DataOutputStream(os);
                // s<li>.getInetAddress()获取远程ip地址，s<li>.getPort()远程客户端的断后好
                // 向客户端发送数据
                //dos.writeUTF("你好,客户端地址信息: " + socket.getInetAddress()
                //        + "\t客户端通信端口号: " + socket.getPort());
                //dos.writeUTF("i'm a server ,my name is hongten！");
                // 关闭打开的输出流
                //dos.close();
                
                //打开输入流
                InputStream is = socket.getInputStream();
                //封装输入流
                DataInputStream dis = new DataInputStream(is);
                String signal = dis.readUTF();
               
                //关闭输入流
                dis.close();
                // 关闭打开的socket对象
                socket.close();
                if("shutdown".equals(signal)) {
                	s.close();
                	System.exit(0);
                }
            }// 开始下一此循环
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
