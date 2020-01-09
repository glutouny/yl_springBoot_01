package com.yl.practice.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yl.practice.service.SentinelService;
import org.springframework.stereotype.Service;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/6  17:06
 */
@Service
public class SentinelServiceImpl implements SentinelService {

    private int counter;

    @Override
    @SentinelResource(value = "SentinelService.call", blockHandler = "callBlocked")
    public void call() {
        System.out.println("Hello (" + ++counter + ")");
    }

    @Override
    public void callBlocked(BlockException ex) {
        System.err.println("Blocked (" + ++counter + ") : " + ex.toString());
    }
}
