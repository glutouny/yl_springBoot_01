package com.yl.practice.thread.pool;

import com.yl.practice.thread.ThreadRejectedExecutionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/16  15:38
 * 线程池配置说明
 * 1、属性字段说明
 *
 * corePoolSize：线程池维护线程的最少数量
 *
 * keepAliveSeconds：允许的空闲时间
 *
 * maxPoolSize：线程池维护线程的最大数量
 *
 * queueCapacity：缓存队列
 *
 * rejectedExecutionHandler：对拒绝task的处理策略
 *
 * 2、 execute(Runable)方法执行过程
 * 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 *
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maxPoolSize，建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maxPoolSize，那么通过handler所指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
 *
 * 当线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 */

@Configuration
public class ThreadPoolConfig {

    @Value("${default.thread.core.pool.size}")
    private int corePoolSize;

    @Value("${default.thread.max.pool.size}")
    private int maxPoolSize;

    @Value("${default.thread.queue.capacity}")
    private int queueCapacity;

    @Value("${default.thread.name.prefix}")
    private String threadNamePrefix;

    @Value("${default.thread.keep.alive.seconds}")
    private int keepAliveSeconds;

    @Value("${thread.pool.attempt.queue.wait.time}")
    private long maxWait;

    @Bean
    public ThreadPoolTaskExecutor defaultThreadPoolTaskExecutor () {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数量
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        //最大线程数量
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        //队列中最大任务数
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        //线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        //当到达最大线程数是如果处理新任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadRejectedExecutionHandler(maxWait));
        //线程空闲后最大空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
