package com.cq.nio.server;

/**
 * @author chenqi
 * @date 2021-02-05 16:52
 */
public class DefaultBlance implements LoadBlance {
    int currentid;

    public DefaultBlance() {
        currentid=0;
    }

    public synchronized int blance(int total) {
        if(++this.currentid >= total)this.currentid=0;

        return this.currentid;
    }
}
