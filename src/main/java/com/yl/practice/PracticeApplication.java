package com.yl.practice;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.yl.practice.service.SentinelService;
import com.yl.practice.service.TestSyncHandDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootApplication
public class PracticeApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(PracticeApplication.class);

    @Autowired
    private SentinelService sentinelService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ThreadPoolTaskExecutor defaultThreadPoolTaskExecutor;

    @Autowired
    private TestSyncHandDataService testSyncHandDataService;


    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    public static void main(String[] args) throws Exception {
        initRules();
        SpringApplication.run(PracticeApplication.class, args);
    }

    private static void initRules() throws Exception {
        FlowRule rule1 = new FlowRule();
        rule1.setResource("SentinelService.call");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setCount(5);   // 每秒调用最大次数为 5 次

        List<FlowRule> rules = new ArrayList<>();
        rules.add(rule1);

        // 将控制规则载入到 Sentinel
        FlowRuleManager.loadRules(rules);
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }


    @PostConstruct
    public void run1() {
        List<String> strings  = new ArrayList<>();
        strings.add("do");
        strings.add("co");
        String poscode = "kf";
        List<Object> result = stringRedisTemplate.executePipelined(new RedisCallback<Double>() {
            @Override
            public Double doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (String string : strings) {
                    connection.zScore(string.getBytes(), poscode.getBytes());
                }
                return null;
            }
        });
        System.out.println("result:"+(Double)result.get(0));

    }

    /**
     * springboot使用线程池方案1:线程池管理
     */
    @PostConstruct
    public void run() {
//		for (int i = 0; i < 10; i++) {
//			sentinelService.call();
//		}

        //objectList为需要处理的数据
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objectList.add(i);
        }
        int taskTotal = 3;
        int handlerNum = 10;
        if (objectList.size() > handlerNum) {
            final List<Future<String>> results = new ArrayList<>();
            final AtomicInteger taskCounter = new AtomicInteger(0);
            List<Object> objects = new ArrayList<>();
            for (Object object : objectList) {
                objects.add(object);
                if (objects.size() == handlerNum) {
                    results.add(process(new ArrayList<>(objects), taskCounter, taskTotal));
                    objects.clear();
                }
            }
            if (CollectionUtils.isNotEmpty(objects)) {
                handData(objects);
            }

            //结果
            for (final Future<String> result : results) {
                try {
                    result.get();
                } catch (InterruptedException | ExecutionException e) {
                    log.error("processDatas thread happened error!", e);
                }
            }
        } else {
            //单线程处理
            handData(objectList);
        }
    }

    /**
     * springboot使用线程池方案2：@Async
     */
    @PostConstruct
    public void run2() {
        //最大线程数
        int threadNum = 4;

        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objectList.add(i);
        }

        int size = objectList.size();
        if (threadNum > size) {
            threadNum = size;
        }

        int start, end;

        final List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            start = size / threadNum * i;
            end = size / threadNum * (i + 1);
            if (i == threadNum - 1) {
                end = size;
            }
            List<Object> objects = objectList.subList(start, end);
            results.add(testSyncHandDataService.handDataAsync(objects));
        }
        for (final Future<String> result : results) {
            try {
                result.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("processDatas thread happened error!", e);
            }
        }
    }


    private void handData(List<Object> objects) {
        //数据处理
        for (Object object : objects) {
//			log.info("处理数据:{}",object);
        }
    }

    private Future<String> process(List<Object> objects, final AtomicInteger taskCounter, final int taskTotal) {
        Callable callable = new Callable<String>() {
            @Override
            public String call() {
                taskCounter.incrementAndGet();
                //此处实现多线程
                try {
                    handData(objects);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    taskCounter.decrementAndGet();
                }
                return SUCCESS;
            }
        };
        while (true) {
            //限定最大线程池
            if (taskCounter.get() < taskTotal) {
                return defaultThreadPoolTaskExecutor.submit(callable);
            }
        }
    }

}

