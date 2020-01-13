package com.qiaofengfangxh.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;


/**
 * netty客户端
 * @author qiaofengfangxh
 */
public class EchoClient {

    /**
     * 请求的端口
     */
    private final Integer port;

    /**
     * 请求的主机地址
     */
    private final String host;

    public EchoClient(Integer port, String host) {
        this.port = port;
        this.host = host;
    }

    /**
     * 启动客户端
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        new EchoClient(9999,"127.0.0.1").start();
    }

    /**
     * netty客户端启动处理逻辑
     */
    private void start() throws InterruptedException {
        /**线程组**/
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        /**客户端启动必备**/
        Bootstrap bootstrap = new Bootstrap();
        /**
         * channel(NioSocketChannel.class):指明使用nio进行网络通信
         */
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new EchoClientHandler());

        /***连接到远程节点，而且阻塞等待，直到连接完成***/
        ChannelFuture channelFuture = bootstrap.connect().sync();

        /***阻塞，直到channel关闭***/
        channelFuture.channel().close().sync();

    }
}
