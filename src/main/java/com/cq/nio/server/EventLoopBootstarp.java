package com.cq.nio.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenqi
 * @date 2021-02-05 17:36
 */
public class EventLoopBootstarp {


    private static final int DEFAULT_THREAD_SIZE=1;


    public EventLoopBootstarp(int threads) {
        ExecutorService executorService=new ThreadPoolExecutor(threads,threads, 60, TimeUnit.SECONDS,);
    }

}
