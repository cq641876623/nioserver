package com.cq.nio.server;

import com.cq.nio.server.base.EventExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenqi
 * @date 2021-02-05 17:36
 */
public class ExecutorBootstarp implements EventExecutor {

    private static final int DEFAULT_THREAD_SIZE=1;
    private static final int DEFAULT_QUEUE_SIZE=5;
    private static final int DEFAULT_KEEPALIVETIME=60;
    private static final TimeUnit DEFAULT_KEEPALIVETIME_TIME_UNIT=TimeUnit.SECONDS;
    private  ExecutorService executorService;



    private LinkedBlockingQueue<Runnable> queue;


    public ExecutorBootstarp(int poolSize) {
        queue=new LinkedBlockingQueue<Runnable>(DEFAULT_QUEUE_SIZE);
        this.executorService=new ThreadPoolExecutor(poolSize,poolSize+1, DEFAULT_KEEPALIVETIME,DEFAULT_KEEPALIVETIME_TIME_UNIT,queue);


    }


    public ExecutorBootstarp() {
        this(DEFAULT_THREAD_SIZE);
    }
    @Override
    public  ExecutorService getExecutorService() {
        return executorService;
    }


    public static void main(String[] args) {
        ExecutorBootstarp executorBootstarp=new ExecutorBootstarp();
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) executorBootstarp.getExecutorService());

        for(int i=0;i<6;i++){
            executorBootstarp.getExecutorService().submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis());
                }
            });

        }




    }



}
