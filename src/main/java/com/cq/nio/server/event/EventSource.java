package com.cq.nio.server.event;

/**
 * @author chenqi
 * @date 2021-02-05 13:45
 */
public interface EventSource {

    void addListenter(EventListenter listenter);

    void notifyListenter();

}
