package com.maker.pdf;

import com.maker.pdf.example.User;
import com.maker.pdf.template.BasicTemplateManager;
import com.maker.pdf.template.FreemarkerTemplateManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Html2PdfGeneratorTest {

    private static final String PDF_PATH = "temp.pdf";

    @AfterEach
    void after() throws IOException {
        Files.delete(Path.of(PDF_PATH));
    }

    @Test
    @DisplayName("Freemarker를 이용해 PDF를 생성합니다.")
    void freemarkerDownload() throws IOException {
        Html2PdfGenerator generator = new Html2PdfGenerator(new FreemarkerTemplateManager());

        final User user = new User("반갑습니다zzz");
        final String template = "src/main/resources/template/freemarkerReceipt.html";
        OutputStream outputStream = new FileOutputStream(PDF_PATH);

        //when
        generator.create(template, outputStream, user);

        //then
        assertTrue(Files.exists(Path.of(template)));
        outputStream.close();
    }

    @Test
    @DisplayName("데이터와 함께 HTML을 PDF로 생성합니다.")
    void downloadWithData() throws IOException {
        //given
        Html2PdfGenerator generator = new Html2PdfGenerator(new BasicTemplateManager());

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
        Html2PdfGenerator generator = new Html2PdfGenerator(new BasicTemplateManager());
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
        Html2PdfGenerator generator = new Html2PdfGenerator(new BasicTemplateManager());
        final String template = "src/main/resources/template/notfound.html";
        OutputStream outputStream = new FileOutputStream(PDF_PATH);

        //when & then
        assertThrows(RuntimeException.class
                , () -> generator.create(template, outputStream));
    }
}
