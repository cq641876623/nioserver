package com.cq.nio.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author chenqi
 * @date 2021-02-05 17:12
 */
public class GottyHandler implements Runnable {

    Selector selector;

    public GottyHandler(Selector selector) {
        this.selector = selector;
    }

    public void run() {
        while (true){
            try {
                if (!(selector.select()>0)) break;
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if(!key.isValid()){

                    }
                    if(key.isAcceptable()) {

                    }
                    if(key.isReadable()){

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
