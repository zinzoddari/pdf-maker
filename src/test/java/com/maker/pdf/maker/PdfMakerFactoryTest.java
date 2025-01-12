package com.maker.pdf.maker;

import com.maker.pdf.example.User;
import com.maker.pdf.template.FreemarkerTemplateManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PdfMakerFactoryTest {

    private static final String PDF_PATH = "temp.pdf";

    private PdfMakerFactory pdfMakerFactory;

    @BeforeEach
    void init() {
        pdfMakerFactory = new PdfMakerFactory(new FreemarkerTemplateManager());
    }

    @Test
    @DisplayName("PdfMaker를 이용해 암호를 설정해 PDF를 생성합니다.")
    void freemarkerDownload() throws IOException {
        //given
        final User user = new User("홍길동이");
        final String template = "src/main/resources/template/freemarkerReceipt.html";

        PdfMaker pdfMaker = pdfMakerFactory.create()
                .withTemplatePath(template)
                .withData(user)
                .withOwnerPassword("1234")
                .withUserPassword("1234")
                .build();

        //when
        try (OutputStream outputStream = new FileOutputStream(PDF_PATH)) {
            pdfMaker.generate(outputStream);
        }

        assertTrue(Files.exists(Path.of(template)));
    }
}