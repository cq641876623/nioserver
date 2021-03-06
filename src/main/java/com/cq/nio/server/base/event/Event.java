package com.cq.nio.server.base.event;

import java.util.Date;

/**
 * @author chenqi
 * @date 2021-02-05 13:43
 */
public interface Event<T,Data>  {

    Data getData();

    T getSource();

    Date getWhen();

    String getMessage();

    void callBack();

    int getEventType();



}
