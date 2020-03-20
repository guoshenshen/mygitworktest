package com.elearning.common;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 〈<br>
 * 〈〉
 *  将返回的json数据中为null的值改为 空字符串
 * @author lxx
 * @create 2019/7/30 11:00
 */
public class JsonObjectMapper extends ObjectMapper {


    private static final long serialVersionUID = 1L;

    public JsonObjectMapper() {
        super();
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException {
                jg.writeString("");
            }
        });
    }




}
