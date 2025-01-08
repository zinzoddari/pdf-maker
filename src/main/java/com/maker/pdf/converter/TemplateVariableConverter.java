package com.maker.pdf.converter;

import java.lang.reflect.Field;

public class TemplateVariableConverter {
    public static <T> String convert(String template, T data) {
        Class<?> clazz = data.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value != null) {
                template = template.replace("{{" + field.getName() + "}}", value.toString());
            }
        }

        return template;
    }
}
