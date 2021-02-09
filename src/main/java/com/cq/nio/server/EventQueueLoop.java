package com.cq.nio.server;

import com.cq.nio.server.base.EventExecutor;
import com.cq.nio.server.base.event.Event;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Queue;

/**
 * @author chenqi
 * @date 2021-02-09 11:33
 */
public class EventQueueLoop implements Runnable {
    private Queue<Event> queue;

    private int index;

    private EventExecutor eventExecutor;

    public EventQueueLoop(Queue<Event> queue, int index, EventExecutor eventExecutor) {
        this.queue = queue;
        this.index = index;
        this.eventExecutor = eventExecutor;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("EventQueueLoop:"+index);
        while (true){
            try{
                synchronized (this.queue){
                    if(this.queue.isEmpty()){
                        this.queue.wait();
                    }
                    Event<Gotty, SocketChannel> event=this.queue.poll();
                    this.eventExecutor.getExecutorService().submit(
                            new Runnable() {
                                @Override
                                public void run() {


                                    ByteBuffer buf=ByteBuffer.allocate(1024);
                                    try {
                                        int len=event.getData().read(buf);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                    event.getSource().notifyAllListenter(event);
                                    System.out.println(event.getMessage());
                                }
                            }
                    );


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
