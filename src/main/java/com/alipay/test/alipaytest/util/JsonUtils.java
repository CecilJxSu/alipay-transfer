package com.alipay.test.alipaytest.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json 相关工具
 *
 * @author 苏金兴
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
                                                            .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

    /**
     * json 字符串转换成对应类型
     * @param json json 字符串
     * @return bean
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Bean 转换成 json 字符串
     * @param bean Bean
     * @return json 字符串
     */
    public static String beanToJson(Object bean) {
        try {
            return OBJECT_MAPPER.writeValueAsString(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
