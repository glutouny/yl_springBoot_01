package com.yl.practice.infa.convert.date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2020/1/2  9:42
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public LocalDate convert(String localDate) {
        if (!StringUtils.hasText(localDate)) {
            return null;
        }
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
