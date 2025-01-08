package com.maker.pdf.template;

public interface TemplateManager {
    <T> String load(final String templatePath, final T data);
}
