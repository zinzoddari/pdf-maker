package com.maker.pdf;

import com.lowagie.text.pdf.BaseFont;
import com.maker.pdf.template.TemplateManager;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;

public class Html2PdfGenerator {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    private final TemplateManager templateManager;

    public Html2PdfGenerator(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }

    public <T> void create(final String templatePath, final OutputStream outputStream, T data) {
        String fileContent = templateManager.load(templatePath, data);

        generate(fileContent, outputStream);
    }

    public void create(final String templatePath, final OutputStream outputStream) {
        String fileContent = templateManager.load(templatePath, null);

        generate(fileContent, outputStream);
    }

    private void generate(final String content, final OutputStream outputStream) {
        ITextRenderer renderer = new ITextRenderer();

        try {
            renderer.getFontResolver().addFont(
                    FONT_PATH,
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );
        } catch (IOException e) {
            throw new RuntimeException("폰트를 읽어오는데 실패하였습니다.", e);
        }

        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
