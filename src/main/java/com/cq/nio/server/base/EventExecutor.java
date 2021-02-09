package com.cq.nio.server.base;

import java.util.concurrent.ExecutorService;

/**
 * @author chenqi
 * @date 2021-02-09 10:38
 */
public interface EventExecutor {
    ExecutorService getExecutorService();
}
