package com.yl.practice.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/16  16:12
 */
public class ThreadRejectedExecutionHandler implements RejectedExecutionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadRejectedExecutionHandler.class);

    private long maxWait;

    public ThreadRejectedExecutionHandler(long maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                final BlockingQueue e = executor.getQueue();

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Attempting to queue task execution for " + this.maxWait + " milliseconds");
                }
                if (!e.offer(r, maxWait, TimeUnit.MILLISECONDS)) {
                    throw new RejectedExecutionException("Max wait time expired to queue task");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RejectedExecutionException("Interrupted", e);
            }
        } else {
            throw new RejectedExecutionException("Executor has been shutdown!");
        }
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }
}
