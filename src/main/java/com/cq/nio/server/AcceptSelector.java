package com.cq.nio.server;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author chenqi
 * @date 2021-02-05 15:43
 */
public class AcceptSelector implements Runnable{



    private Selector acceptSelector;


    public AcceptSelector() {
        try {
            this.acceptSelector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Selector getAcceptSelector() {
        return acceptSelector;
    }



    public void run() {

    }
}
