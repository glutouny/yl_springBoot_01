package com.yl.practice.domain.TestChild.childdrive;

import com.yl.practice.domain.ChildHandler;
import com.yl.practice.domain.TestChild.ParentAction;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/27  10:48
 */
@Component
@ChildHandler(ParentAction.CHILD_ACTION1_LEVEL)
public class ChildAction1 extends ParentAction {

    @Override
    protected String runner(String var) {
        return "ChildAction1: "+var;
    }

}
