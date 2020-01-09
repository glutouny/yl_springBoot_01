package com.yl.practice;

import com.yl.practice.daos.UserRepository;
import com.yl.practice.domain.User;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2019/1/9  15:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        userRepository.save(new User("sp004","123456","sp00401@qq.com"));
        userRepository.save(new User("sp005","123456","sp00501@qq.com"));
        userRepository.save(new User("sp006","123456","sp00601@qq.com"));
        Assert.assertEquals(6,userRepository.findAll().size());
        Assert.assertEquals("123456",userRepository.findByUserNameAndAndEmail("sp001","sp001@qq.com").getPassword());
        userRepository.delete(userRepository.findByUserName("sp002"));
    }

    @Test
    public void testUpdate(){

    }
}
