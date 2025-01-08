package com.maker.pdf.template;


import com.maker.pdf.converter.TemplateVariableConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        return TemplateVariableConverter.convert(template, data);
    }
}
