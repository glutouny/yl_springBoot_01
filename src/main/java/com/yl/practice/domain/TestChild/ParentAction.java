package com.yl.practice.domain.TestChild;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/27  10:46
 */
public abstract class ParentAction {

    public final static int CHILD_ACTION1_LEVEL = 1;

    public final static int CHILD_ACTION2_LEVEL = 2;

    public final static int CHILD_ACTION3_LEVEL = 3;


    protected abstract String runner(final String var);

    public String execute(String s) {
        try {
            return runner(s);
        } catch (Exception e) {
            return "liangliang";
        }

    }
}
