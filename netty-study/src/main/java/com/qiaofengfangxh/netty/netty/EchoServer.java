package com.qiaofengfangxh.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;



/**
 * netty服务端
 * @author qiaofengfangxh
 */
public class EchoServer {

    private final Integer port;

    public EchoServer(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        final int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器即将关闭");
    }



    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        /***线程组***/
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            /***服务器端启动必备***/
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class)
                    /*******指明服务器监听端口*******/
                    .localAddress(new InetSocketAddress(port))
                    /*******接收到连接请求,新启一个socket通信，也就是channel，
                     同时每个channel必须有自己的事件处理器handler*******/
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            /**********************/
                            ch.pipeline().addLast(serverHandler);
                        }
                    });


            /**绑定到端口，阻塞等待，直到连接完成***/
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            /***阻塞，直到channel关闭***/
            channelFuture.channel().closeFuture().sync();
        } finally {

            eventLoopGroup.shutdownGracefully().sync();
        }
    }


}
