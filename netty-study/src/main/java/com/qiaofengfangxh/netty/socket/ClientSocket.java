package com.qiaofengfangxh.netty.socket;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 这是客户端的socket
 */
public class ClientSocket {

    public static void main(String[] args) throws IOException {

        //创建一个客户端socket请求
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",8089);
            //发送数据到服务器端
            //去连接服务器端
            //socket.connect(socket.getLocalSocketAddress());
//            if (socket.isConnected()) {
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                writer.write("你好，这是我的第一个socket");
//            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("你好，这是我的第一个socket");

            //没有加上这个flush()方法导致服务器端没有接收到客服端发送的消息
            /****没有执行这个flush方法就会导致：无法自动将数据从缓冲区刷出，这也就是为什么在Socket中使用输出流的情况下一般都需要手动刷新***/
            //参考博客：https://blog.csdn.net/jjarchu/article/details/88962817
            writer.flush();

            System.out.println("end!");

            //关闭客户端的连接，为了告诉服务器端消息发送完成了，否则服务器端的accept方法和read方法一直在阻塞
            //socket.close();

            socket.shutdownOutput();
        } catch (IOException e) {
            if (socket != null) {
                //socket.close();

                socket.shutdownOutput();
            }

        }

    }
}
