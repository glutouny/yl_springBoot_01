package com.yl.practice.domain.TestChild;

import java.util.List;

/**
 * @author li.yang01@hand-china.com 2020/4/20 5:38 下午
 */
public class ParentHandler{


    public ParentHandler(List<ParentAction> parentActionList) {
        System.out.println("ParentHandler:"+parentActionList.size());
    }

}
