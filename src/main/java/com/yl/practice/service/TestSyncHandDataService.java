package com.yl.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2020/1/15  15:53
 */
@Slf4j
public class TestSyncHandDataService {

    private static final String SUCCESS = "SUCCESS";

    private static final String FAILURE = "FAILURE";

    @Async("defaultThreadPoolTaskExecutor")
    public Future<String> handDataAsync(List<Object> objects) {
        Future<String> result = new AsyncResult<String>(SUCCESS);
        for (Object object:objects) {
			log.info("处理数据:{}",object);
        }
        return result;
    }
}
