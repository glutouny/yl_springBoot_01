package com.yl.practice.controller;

import com.yl.practice.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2020/1/2  9:44
 */
@RestController
@RequestMapping("/test")
public class TestConvertController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestConvertController.class);

    @RequestMapping(value = "/convert",method = RequestMethod.POST)
    public User testConvert(@RequestBody final User user){
        LOGGER.info("date:"+user.getModifiedTime());
        LOGGER.info("user:"+user.getUserName());
        return user;
    }
}
