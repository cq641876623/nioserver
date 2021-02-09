package com.cq.nio.server.Listener;


import com.cq.nio.server.base.event.Event;
import com.cq.nio.server.base.event.EventListener;

/**
 * @author chenqi
 * @date 2021-02-08 17:01
 */
public abstract class AbstractReadListener implements EventListener {
    @Override
    public void handle(Event event) {
        System.out.println("这是一个ReadListener");
    }
}
