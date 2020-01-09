package com.yl.practice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/6  17:05
 */
public interface SentinelService {

    void call();


    void callBlocked(BlockException ex);
}
