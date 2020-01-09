package com.yl.practice.domain;

import java.lang.annotation.*;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2019/12/31  10:33
 *
 *   Documented注解表明这个注释是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注释了文档化，它的注释成为公共API的一部分。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ChildHandler {

    int value();
}
