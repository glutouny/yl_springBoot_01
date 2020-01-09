package com.yl.practice.domain.TestChild.childdrive;

import com.yl.practice.domain.ChildHandler;
import com.yl.practice.domain.TestChild.ParentAction;
import org.springframework.stereotype.Component;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/27  10:48
 */
@Component
@ChildHandler(ParentAction.CHILD_ACTION3_LEVEL)
public class ChildAction3 extends ParentAction {

    @Override
    protected String runner(String var) {
        return "ChildAction3: "+var;
    }


}
