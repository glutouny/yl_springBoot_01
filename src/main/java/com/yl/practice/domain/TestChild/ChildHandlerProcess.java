package com.yl.practice.domain.TestChild;

import com.yl.practice.domain.ChildHandler;
import com.yl.practice.infa.convert.ApplicationContextHelper;
import com.yl.practice.infa.registry.ParentActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author yangli
 * @title   ChildHandlerProcess
 * @description 启动后扫描ChildHandler
 * @DATE 2019/12/31  10:46
 */
@Component
public class ChildHandlerProcess implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChildHandlerProcess.class);

    @Autowired
    private ParentDrive parentDrive;

    @Override
    public void run(String... args) throws Exception {
        scanChildHandler();
        //测试
        parentDrive.process("sdfh");
    }

    /**
     *  启动后扫描ChildHandler
     */
    private void scanChildHandler() {
        Map<String, Object> map = ApplicationContextHelper.getContext().getBeansWithAnnotation(ChildHandler.class);
        LOGGER.info("===map===:"+map.size());

        for (Object service : map.values()) {
            if (service instanceof ParentAction) {
                ChildHandler childHandler = service.getClass().getAnnotation(ChildHandler.class);
                if (ObjectUtils.isEmpty(childHandler)) {
                    LOGGER.debug("could not get target bean , childHandler : {}", service);
                } else {
                    ParentActionRegistry.addParentAction(childHandler.value(),(ParentAction)service);
                }
            }
        }
    }
}
