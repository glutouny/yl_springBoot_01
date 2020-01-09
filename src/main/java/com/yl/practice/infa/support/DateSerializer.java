package com.yl.practice.infa.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class DateSerializer extends StdSerializer<Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected DateSerializer() {
        super(Date.class);
    }


    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DATE_FORMAT);
        jsonGenerator.writeString(dateFormatGmt.format(date));
    }
}
