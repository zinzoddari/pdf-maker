package com.maker.pdf.maker;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maker.pdf.template.TemplateManager;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.IOException;
import java.io.OutputStream;

import static com.lowagie.text.pdf.PdfWriter.STANDARD_ENCRYPTION_128;

public class PdfMakerBuilder<T> {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    private T data;

    private String userPassword;

    private String ownerPassword;

    private int permissions = PdfWriter.ALLOW_PRINTING;

    private final TemplateManager templateManager;

    private PdfMakerBuilder(TemplateManager templateManager) {
        if (templateManager == null) {
            throw new IllegalArgumentException("TemplateManager는 null일 수 없습니다.");
        }

        this.templateManager = templateManager;
    }

    public static <T> PdfMakerBuilder<T> builder(TemplateManager templateManager) {
        return new PdfMakerBuilder<>(templateManager);
    }

    public PdfMakerBuilder<T> withData(final T data) {
        this.data = data;
        return this;
    }

    public PdfMakerBuilder<T> withUserPassword(final String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public PdfMakerBuilder<T> withOwnerPassword(final String ownerPassword) {
        this.ownerPassword = ownerPassword;
        return this;
    }

    public PdfMakerBuilder<T> withPermissions(final int permissions) {
        this.permissions = permissions;
        return this;
    }

    public void build(final String templatePath, OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream은 null일 수 없습니다.");
        }

        if (templatePath == null || templatePath.isBlank()) {
            throw new IllegalStateException("템플릿 경로가 설정되지 않았습니다.");
        }

        final String fileContent = templateManager.load(templatePath, data);
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
        configureEncryption(renderer);
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    private void configureEncryption(ITextRenderer renderer) {
        PDFEncryption pdfEncryption = new PDFEncryption(userPassword.getBytes(), ownerPassword.getBytes(), permissions, STANDARD_ENCRYPTION_128);

        renderer.setPDFEncryption(pdfEncryption);
    }
}
