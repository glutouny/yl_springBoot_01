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
@ChildHandler(ParentAction.CHILD_ACTION2_LEVEL)
public class ChildAction2 extends ParentAction {
    @Override
    protected String runner(String var) {
        return "ChildAction2: "+var;
    }

    @Override
    protected int level() {
        return 2;
    }
}
