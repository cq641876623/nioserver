package com.cq.nio.server.event;

import java.util.Date;

/**
 * @author chenqi
 * @date 2021-02-05 13:43
 */
public interface Event {

    Object getSource();

    Date getWhen();

    String getMessage();


    void callBack();

}
