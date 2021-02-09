package com.cq.nio.server.event;

import com.cq.nio.server.base.event.Event;

import java.util.Date;

/**
 * @author chenqi
 * @date 2021-02-09 9:15
 */
public class ReadEvent<T,Data> implements Event<T,Data> {


    private Data data;

    private T source;

    private Date when;

    private String message;

    private int eventType;

    public ReadEvent(Data data, T source) {
        this.data = data;
        this.source = source;

        this.when=new Date();
        this.eventType=EventType.READ;
        this.message="触发读操作";
    }

    @Override
    public Data getData() {
        return data;
    }

    @Override
    public T getSource() {
        return this.source;
    }

    @Override
    public Date getWhen() {
        return when;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void callBack() {

    }

    @Override
    public int getEventType() {
        return eventType;
    }
}
