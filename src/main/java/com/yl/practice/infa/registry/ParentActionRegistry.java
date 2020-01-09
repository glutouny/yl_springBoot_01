package com.yl.practice.infa.registry;

import com.yl.practice.domain.TestChild.ParentAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/31  14:30
 */
public class ParentActionRegistry {

    public ParentActionRegistry() {
    }

    private static Map<Integer, ParentAction> parentActionMap = new ConcurrentHashMap<>();

    public static void addParentAction(int level,ParentAction parentAction) {
        parentActionMap.put(level,parentAction);
    }


    public static ParentAction getParentAction(int level) {
        return parentActionMap.get(level);
    }

    public static Map<Integer, ParentAction> getParentActionMap() {
        return parentActionMap;
    }

    public static void setParentActionMap(Map<Integer, ParentAction> parentActionMap) {
        ParentActionRegistry.parentActionMap = parentActionMap;
    }
}
