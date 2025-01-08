package com.maker.pdf;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Html2PdfGenerator {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public void create(final String templatePath, final OutputStream outputStream) {

        String fileContent = null;

        try {
            fileContent = Files.readString(Path.of(templatePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert fileContent != null;

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

        renderer.setDocumentFromString(fileContent);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
