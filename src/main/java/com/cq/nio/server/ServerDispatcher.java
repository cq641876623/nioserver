package com.cq.nio.server;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author chenqi
 * @date 2021-02-05 16:27
 */
public class ServerDispatcher {

    private static final int DEFAULT_SELECTOR_SIZE=1;

    public Selector[] selectors;

    private LoadBlance blance=new DefaultBlance();

    public ServerDispatcher(int selectorSize) {
        this.selectors=new Selector[selectorSize];
        for(int i=0;i<selectorSize;i++){
            try{
                this.selectors[i]=Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public ServerDispatcher() {
        this(DEFAULT_SELECTOR_SIZE);
    }


    public Selector getSelector(){
        return this.selectors[blance.blance(this.selectors.length)];
    }



    public void start(){
        for(int i=0;i<selectors.length;i++){
            GottyHandler gottyHandler=new GottyHandler(selectors[i]);
            Thread thread=new Thread(gottyHandler);
            thread.setName("selector:"+i);
            thread.start();
        }




    }




}
