package com.cq.nio.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author chenqi
 * @date 2021-02-05 15:12
 */
public class NettyTest {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(5);
        // workerGroup, 用于处理与各个客户端连接的 IO 操作
        EventLoopGroup workerGroup = new NioEventLoopGroup(6);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)           //绑定两个线程组
                // 用于构造socketchannel工厂
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 子处理器，用于处理workerGroup
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new NettyServerOutBoundHandler());
//                        socketChannel.pipeline().addLast(new SimpleNettyServerHandler());

                    }
                });
    }
}
