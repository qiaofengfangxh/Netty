package com.qiaofengfangxh.netty.socket;

import java.io.*;
import java.net.Socket;


/**
 * 这是服务端的socket
 */
public class ServerSocket {

    public static final int port = 8089;

    public static void main(String[] args) {

        try {
            //1.创建一个服务器端的socket,
               //并且监听端口号为8089的客户端的连接
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
            //等待客户端的连接 accept方法是阻塞的方法
            Socket socket = serverSocket.accept();
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String info = null;
            while((info= reader.readLine())!=null){
                System.out.println("我是服务器，客户端说："+info);
            }
            socket.shutdownInput();//关闭输入流
            //4、获取输出流，响应客户端的请求
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("欢迎您！");
            pw.flush();

            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
