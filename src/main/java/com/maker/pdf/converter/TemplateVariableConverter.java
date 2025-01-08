package com.maker.pdf.converter;

import java.lang.reflect.Field;

public class TemplateVariableConverter {
    public static <T> String convert(String template, T data) throws IllegalAccessException {
        Class<?> clazz = data.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // private 필드 접근 허용
            Object value = field.get(data); // 필드 값 가져오기
            if (value != null) {
                template = template.replace("{{" + field.getName() + "}}", value.toString());
            }
        }

        return template;
    }
}
