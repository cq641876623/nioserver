package com.cq.nio.server;

import com.cq.nio.server.base.event.Event;
import com.cq.nio.server.base.event.EventListener;
import com.cq.nio.server.base.event.EventSource;
import com.cq.nio.server.event.ReadEvent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenqi
 * @date 2021-02-04 17:29
 */
public class Gotty implements EventSource {

    private List<EventListener> eventListenters=new ArrayList<>();


    private static final int DEFAULT_PORT=1080;
    private int port;

    private ServerSocketChannel server;

    private Selector selector;

    private EventLoopBootstarp eventLoopBootstarp;

    private ExecutorBootstarp executorBootstarp;


    public EventLoopBootstarp getEventLoopBootstarp() {
        if(eventLoopBootstarp==null){
            eventLoopBootstarp=new EventLoopBootstarp(this.getExecutorBootstarp());
        }
        return eventLoopBootstarp;
    }

    public ExecutorBootstarp getExecutorBootstarp() {
        if( executorBootstarp == null){
            executorBootstarp=new ExecutorBootstarp();
        }
        return executorBootstarp;
    }

    public Gotty() {
        this(DEFAULT_PORT);
    }

    public Gotty(int port) {
        this.port = port;
        try {
            server= ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(this.port));
            server.configureBlocking(false);
            selector=Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("nio服务open失败!端口号："+port);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Gotty gotty=new Gotty();
        gotty.run();
    }

    private void run()  {
        while(! Thread.currentThread().isInterrupted()){
            try{
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if(!key.isValid()||!key.channel().isOpen()){
                        key.cancel();
                        System.out.println("key关闭");
                        continue;
                    }
                    Event event=null;
                    if(key.isAcceptable()){
                        accept( key);
                    }
                    if(key.isConnectable()){

                    }
                    if(key.isReadable()){

                    }
                    if(key.isWritable()){

                    }



                }

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
    }



    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.socket().setSoTimeout(3000);
        clientChannel.configureBlocking(false);

        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected "+clientChannel.getRemoteAddress());


    }

    @Override
    public void addListener(EventListener listenter) {
        eventListenters.add(listenter);
    }

    @Override
    public void notifyAllListenter(Event event) {
        for( EventListener listenter :eventListenters ){
        }
    }



}
