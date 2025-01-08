package com.maker.pdf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Html2PdfGeneratorTest {

    private static final String PDF_PATH = "temp.pdf";

    private Html2PdfGenerator generator;

    @BeforeEach
    void init() {
        generator = new Html2PdfGenerator();
    }

    @AfterEach
    void after() throws IOException {
        Files.delete(Path.of(PDF_PATH));
    }

    @Test
    @DisplayName("HTML을 PDF로 생성하여 다운 받습니다.")
    void download() throws IOException {
        //given
        final String template = "src/main/resources/template/receipt.html";
        OutputStream outputStream = new FileOutputStream(template);

        //when
        generator.create(template, outputStream);

        //then
        assertTrue(Files.exists(Path.of(template)));
        outputStream.close();
    }
}