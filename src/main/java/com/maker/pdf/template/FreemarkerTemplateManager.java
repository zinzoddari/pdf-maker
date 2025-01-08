package com.maker.pdf.template;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class FreemarkerTemplateManager implements TemplateManager {
    private final Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    public FreemarkerTemplateManager() {
        configuration.setDefaultEncoding("UTF-8");
    }

    @Override
    public <T> String load(final String templatePath, final T data) {
        try {
            File templateFile = new File(templatePath);
            Template template = loadTemplate(templateFile);

            StringWriter writer = new StringWriter();
            template.process(data != null ? data : new Object(), writer);

            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Freemarker를 이용하여 랜더링하는 것을 실패하였습니다.", e);
        }
    }

    private Template loadTemplate(final File templateFile) throws IOException {
        File templateDirectory = templateFile.getParentFile();
        String templateName = templateFile.getName();

        configuration.setTemplateLoader(new FileTemplateLoader(templateDirectory));
        return configuration.getTemplate(templateName);
    }
}
