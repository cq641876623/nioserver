package com.cq.nio.server;

import com.cq.nio.server.base.EventExecutor;
import com.cq.nio.server.base.event.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chenqi
 * @date 2021-02-09 10:24
 */
public class EventLoopBootstarp {


    private Queue<Event>[] queues;

    private Thread[] threads;

    private EventExecutor eventExecutor;

    private static final int DEFAULT_EVENT_QUEUE_POOL_SIZE=1;

    private LoadBlance blance;


    public EventLoopBootstarp(EventExecutor eventExecutor) {
        this(DEFAULT_EVENT_QUEUE_POOL_SIZE,eventExecutor);
    }


    public EventLoopBootstarp(int poolSize,EventExecutor eventExecutor) {
        queues=new Queue[poolSize];
        threads=new Thread[poolSize];
        this.eventExecutor=eventExecutor;
        blance=new DefaultBlance();
        for(int i =0;i<queues.length;i++){
            queues[i]=new LinkedList<Event>();
            threads[i]=new Thread(new EventQueueLoop(queues[i],i,eventExecutor));
            threads[i].start();
        }
    }


    public void addEvent(Event event){
        Queue queue=queues[blance.blance(queues.length)];
        synchronized (queue){
            queue.add(event);
            queue.notifyAll();
        }
    }

}
