package com.maker.pdf;

import com.lowagie.text.pdf.PdfWriter;
import com.maker.pdf.maker.PdfMaker;
import com.maker.pdf.maker.PdfMakerFactory;
import com.maker.pdf.maker.User;
import com.maker.pdf.template.FreemarkerTemplateManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    private static final String TEMPLATE_PATH = "src/main/resources/template/freemarkerReceipt.html";
    private static final String PDF_PATH = "temp.pdf";

    public static void main(String[] args) throws IOException {
        PdfMakerFactory factory = new PdfMakerFactory(new FreemarkerTemplateManager());

        PdfMaker pdfMaker = factory.create()
            .withTemplatePath(TEMPLATE_PATH)
            .withData(new User("홍길동"))
            .withUserPassword("user-pass")
            .withOwnerPassword("owner-pass")
            .withPermissions(PdfWriter.ALLOW_PRINTING)
            .build();

        try (OutputStream outputStream = new FileOutputStream(PDF_PATH)) {
            pdfMaker.generate(outputStream);
        }
    }
}

