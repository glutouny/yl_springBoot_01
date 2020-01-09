package com.yl.practice.infa.convert.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author yangli
 * @title
 * @description
 * @DATE 2020/1/2  9:39
 */
public class DateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormatGmt.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
