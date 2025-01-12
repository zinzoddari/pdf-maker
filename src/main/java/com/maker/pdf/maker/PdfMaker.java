package com.maker.pdf.maker;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maker.pdf.template.TemplateManager;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.IOException;
import java.io.OutputStream;

import static com.lowagie.text.pdf.PdfWriter.STANDARD_ENCRYPTION_128;

public class PdfMaker<T> {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    private String templatePath;

    private T data;

    private String userPassword;

    private String ownerPassword;

    private int permissions = PdfWriter.ALLOW_PRINTING;

    private final TemplateManager templateManager;

    private PdfMaker(TemplateManager templateManager) {
        if (templateManager == null) {
            throw new IllegalArgumentException("TemplateManager는 null일 수 없습니다.");
        }

        this.templateManager = templateManager;
    }

    public static <T> PdfMaker<T> builder(TemplateManager templateManager) {
        return new PdfMaker<>(templateManager);
    }

    public PdfMaker<T> withTemplatePath(final String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public PdfMaker<T> withData(final T data) {
        this.data = data;
        return this;
    }

    public PdfMaker<T> withUserPassword(final String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public PdfMaker<T> withOwnerPassword(final String ownerPassword) {
        this.ownerPassword = ownerPassword;
        return this;
    }

    public PdfMaker<T> withPermissions(final int permissions) {
        this.permissions = permissions;
        return this;
    }

    public PdfMaker<T> build() {
        if (templatePath == null) {
            throw new IllegalArgumentException("템플릿 경로는 필수입니다.");
        }

        return this;
    }

    public void generate(final OutputStream outputStream) {
        final String content = templateManager.load(templatePath, data);

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
