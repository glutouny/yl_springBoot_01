package com.yl.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/16  15:55
 */
@Service
public class ThreadPoolService {

    @Autowired
    private ThreadPoolTaskExecutor defaultThreadPoolTaskExecutor;
}
