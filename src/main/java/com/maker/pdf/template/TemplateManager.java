package com.maker.pdf.template;

import java.io.OutputStream;

public interface TemplateManager {
    <T> String load(final String templatePath, final T data);
}
