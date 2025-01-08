package com.maker.pdf.template;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * HTML 내 `{{variable}}` 형식으로 작성된 문자열을 데이터 값으로 치환해줍니다.
 */
public class BasicTemplateManager implements TemplateManager {

    @Override
    public <T> String load(final String templatePath, final T data) {
        String template = null;

        try {
            template = Files.readString(Path.of(templatePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (template != null) {
            return template;
        }

        return convert(template, data, data.getClass());
    }

    private <T> String convert(String template, final T data, final Class<?> clazz) {
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
