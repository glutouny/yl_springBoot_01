package com.yl.practice;

import com.yl.practice.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author 杨黎
 * @Title   TestRedis
 * @description redis测试类
 * @DATE 2019/1/9  14:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("111","aaa");
        Assert.assertEquals("aaa",stringRedisTemplate.opsForValue().get("111"));
    }

    @Test
    public void testObj() throws InterruptedException {
        User user = new User("goudan","123456","11029621223@qq.com");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("com.yl", user);
//        operations.set("com.yl.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.yl.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        Assert.assertEquals("goudan", operations.get("com.yl").getUserName());
    }
}
