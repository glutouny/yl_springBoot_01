package com.yl.practice.infa.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class DateDeserializer extends StdDeserializer<Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    protected DateDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (!StringUtils.hasText(p.getValueAsString())) {
            return null;
        }
        String date = p.getValueAsString();
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormatGmt.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
