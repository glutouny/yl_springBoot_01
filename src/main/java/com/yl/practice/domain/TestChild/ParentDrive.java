package com.yl.practice.domain.TestChild;

import com.yl.practice.infa.registry.ParentActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/27  10:53
 */
@Component
public class ParentDrive {

    private final static Logger LOGGER = LoggerFactory.getLogger(ParentDrive.class);

    private ConcurrentHashMap<Integer, ParentAction> parentActionMap;

    public ParentDrive(List<ParentAction> parentActionList) {
        this.parentActionMap = new ConcurrentHashMap<>(parentActionList.size());
        parentActionList.forEach(parentAction -> {
            parentActionMap.put(parentAction.level(),parentAction);
        });
    }

    public boolean process(final String s) {
        boolean success = true;
        LOGGER.info("parentActionMap:"+ parentActionMap.size());
        for (Map.Entry<Integer,ParentAction> entry : parentActionMap.entrySet()) {
            final String result = entry.getValue().execute(s);
            LOGGER.info("result:"+result);
            if (StringUtils.isEmpty(result)) {
                success = false;
            }
        }
        return success;
    }


    private ConcurrentHashMap<Integer, ParentAction> getparentActionSort() {
        ConcurrentHashMap<Integer, ParentAction> collect1 =ParentActionRegistry.getParentActionMap().entrySet().stream().sorted(new Comparator<Map.Entry<Integer, ParentAction>>() {
            @Override
            public int compare(Map.Entry<Integer, ParentAction> o1, Map.Entry<Integer, ParentAction> o2) {
                LOGGER.info("o1.key:{},o2.key:{}",o1.getKey(),o2.getKey());
                return o2.getKey().compareTo(o1.getKey());
            }
        }).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, ConcurrentHashMap::new));
        return collect1;
    }

}
