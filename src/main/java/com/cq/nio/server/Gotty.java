package com.cq.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author chenqi
 * @date 2021-02-04 17:29
 */
public class Gotty {

    private static final int DEFAULT_PORT=1080;
    private int port;

    private ServerSocketChannel server;




    public Gotty() {
        this(DEFAULT_PORT);
    }

    public Gotty(int port) {
        this.port = port;




        try {
            server= ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(this.port));
            server.configureBlocking(false);
            AcceptSelector acceptSelector=new AcceptSelector();
            server.register(acceptSelector.getAcceptSelector(), SelectionKey.OP_ACCEPT);
            acceptSelector.start();

        } catch (IOException e) {
            System.out.println("nio服务open失败!端口号："+port);
            e.printStackTrace();
        }
    }
}
