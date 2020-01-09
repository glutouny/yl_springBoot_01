package com.yl.practice.domain.TestChild;

import com.yl.practice.infa.registry.ParentActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/27  10:53
 */
@Component
public class ParentDrive {

    private final static Logger LOGGER = LoggerFactory.getLogger(ParentDrive.class);

    public boolean process(final String s) {
        boolean success = true;
        LOGGER.info("parentActionMap:"+ ParentActionRegistry.getParentActionMap().size());
        for (Map.Entry<Integer,ParentAction> entry : ParentActionRegistry.getParentActionMap().entrySet()) {
            final String result = entry.getValue().execute(s);
            LOGGER.info("result:"+result);
            if (StringUtils.isEmpty(result)) {
                success = false;
            }
        }
        return success;
    }

}
