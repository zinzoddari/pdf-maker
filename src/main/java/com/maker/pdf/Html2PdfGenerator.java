package com.maker.pdf;

import com.lowagie.text.pdf.BaseFont;
import com.maker.pdf.converter.TemplateVariableConverter;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Html2PdfGenerator {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public <T> void create(final String templatePath, final OutputStream outputStream, T data) {
        String fileContent = loadTemplate(templatePath, data);

        generate(fileContent, outputStream);
    }

    public void create(final String templatePath, final OutputStream outputStream) {
        String fileContent = loadTemplate(templatePath, null);

        generate(fileContent, outputStream);
    }

    private <T> String loadTemplate(String templatePath, T data) {
        try {
            String fileContent = Files.readString(Path.of(templatePath));

            if (data != null) {
                fileContent = TemplateVariableConverter.convert(fileContent, data);
            }

            return fileContent;
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException("Failed to load or process template: " + templatePath, e);
        }
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
