# PDF-MAKER
[![](https://jitpack.io/v/zinzoddari/pdf-maker.svg)](https://jitpack.io/#zinzoddari/pdf-maker)<br />
PDF-MAKER는 HTML 템플릿을 기반으로 PDF 파일을 생성할 수 있는 Java 라이브러리입니다. <br />
HTML 템플릿의 데이터 바인딩 및 PDF 암호화 기능을 제공하여 사용자 맞춤형 PDF를 생성할 수 있습니다.

## 주요 기능
- HTML 템플릿 렌더링: HTML 템플릿과 데이터를 결합하여 PDF 콘텐츠 생성.
- PDF 암호화: 사용자 및 소유자 비밀번호를 설정하고, 권한 제어 가능 (예: 인쇄 허용).
- 유연한 빌더 패턴: PdfMaker 빌더를 통해 간단한 PDF 생성 워크플로우 지원

## 설치 방법
Gradle
pdf-maker는 [🔗JitPack](https://jitpack.io/#zinzoddari/pdf-maker)에 배포되어 있습니다. 아래와 같이 Gradle에 의존성을 추가해주세요.
- `build.gradle.kts`
```yml
repositories {
    ...
    maven("https://jitpack.io")
}
```
```yml
dependencies {
    implementation("com.github.zinzoddari:pdf-maker:v0.1.5")
}
```

## 사용법
### 기본 설정 및 PDF 생성
```java
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

    class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
```
### 주요 메서드 설명
 - `withTemplatePath(String templatePath)`: HTML 템플릿 파일 경로를 설정합니다. 
 - `withData(T data)`: 템플릿에 바인딩할 데이터를 설정합니다. 
 - `withUserPassword(String userPassword)`: PDF 사용자 비밀번호를 설정합니다. 
 - `withOwnerPassword(String ownerPassword`: PDF 소유자 비밀번호를 설정합니다. 
 - `withPermissions(int permissions)`: PDF 권한을 설정합니다. (예: `PdfWriter.ALLOW_PRINTING`)
   - bit 연산으로 `|`를 이용해 다중 설정이 가능합니다.

## 사용 오픈소스 라이브러리 
`pdf-maker`는 다음 라이브러리를 사용하였습니다.
- [flying-saucer-pdf](https://github.com/flyingsaucerproject/flyingsaucer): HTML과 CSS 기반 PDF 생성
- [Freemarker](https://github.com/apache/freemarker): 템플릿 엔진
  기여

# 기여
이 프로젝트에 기여하려면 이슈를 생성하거나 풀 리퀘스트를 제출해주세요. 기여는 언제나 환영입니다. 😁

# License
이 프로젝트는 [MIT 라이선스](https://opensource.org/license/mit) 하에 배포됩니다.
