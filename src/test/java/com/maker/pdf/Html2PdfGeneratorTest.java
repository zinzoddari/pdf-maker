package com.maker.pdf;

import com.maker.pdf.example.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("데이터와 함께 HTML을 PDF로 생성합니다.")
    void downloadWithData() throws IOException {
        //given
        final User user = new User("홍길동");
        final String template = "src/main/resources/template/receipt.html";
        OutputStream outputStream = new FileOutputStream(PDF_PATH);

        //when
        generator.create(template, outputStream, user);

        //then
        assertTrue(Files.exists(Path.of(template)));
        outputStream.close();
    }

    @Test
    @DisplayName("HTML을 PDF로 생성하여 다운 받습니다.")
    void download() throws IOException {
        //given
        final String template = "src/main/resources/template/receipt.html";
        OutputStream outputStream = new FileOutputStream(PDF_PATH);

        //when
        generator.create(template, outputStream);

        //then
        assertTrue(Files.exists(Path.of(template)));
        outputStream.close();
    }

    @Test
    @DisplayName("없는 템플릿을 불러왔을 때, 오류가 발생합니다.")
    void notFound() throws FileNotFoundException {
        //given
        final String template = "src/main/resources/template/notfound.html";
        OutputStream outputStream = new FileOutputStream(PDF_PATH);

        //when & then
        assertThrows(RuntimeException.class
                , () -> generator.create(template, outputStream));
    }
}
