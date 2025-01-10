package com.maker.pdf.maker;

import com.maker.pdf.template.TemplateManager;

public class PdfMakerFactory {

    private final TemplateManager templateManager;

    public PdfMakerFactory(TemplateManager templateManager) {
        if (templateManager == null) {
            throw new IllegalArgumentException("TemplateManager는 null일 수 없습니다.");
        }

        this.templateManager = templateManager;
    }

    public <T> PdfMakerBuilder<T> create() {
        return PdfMakerBuilder.builder(templateManager);
    }
}
