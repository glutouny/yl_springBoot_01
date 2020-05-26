package com.yl.practice.config;

import com.yl.practice.domain.TestChild.ParentAction;
import com.yl.practice.domain.TestChild.ParentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/16  16:18
 */
@Configuration
public class PropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
        ClassPathResource resource = new ClassPathResource("project.properties");
        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(resource);
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public ParentHandler getParentHandler(List<ParentAction> parentActionList) {
        return new ParentHandler(parentActionList);
    }
}
