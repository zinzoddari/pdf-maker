<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>거래명세서</title>
    <style>
        @font-face {
            font-family: 'NanumGothic';
            src: url('./fonts/NanumGothic.ttf') format('truetype');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'NanumGothic', serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            border: 1px solid #ddd;
            padding: 20px;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
        }
        .header p {
            margin: 5px 0;
        }
        .info {
            margin-bottom: 20px;
        }
        .info .row {
            display: flex;
            justify-content: space-between;
        }
        .info .row div {
            width: 48%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f5f5f5;
        }
        .footer {
            text-align: right;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>거래명세서</h1>
        <p>발행일: 2025-01-07</p>
    </div>

    <div class="info">
        <div class="row">
            <div>
                <strong>공급자:</strong><br/>
                회사명: ABC 상사<br/>
                대표자: ${name}<br/>
                주소: 서울특별시 강남구 테헤란로 123<br/>
                연락처: 02-123-4567<br/>
            </div>
            <div>
                <strong>공급받는자:</strong><br/>
                회사명: XYZ 상사<br/>
                대표자: 김철수<br/>
                주소: 부산광역시 해운대구 우동 456<br/>
                연락처: 051-987-6543<br/>
            </div>
        </div>
    </div>

    <table>
        <thead>
        <tr>
            <th>품목</th>
            <th>수량</th>
            <th>단가</th>
            <th>금액</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>품목 A</td>
            <td>10</td>
            <td>10,000</td>
            <td>100,000</td>
        </tr>
        <tr>
            <td>품목 B</td>
            <td>5</td>
            <td>20,000</td>
            <td>100,000</td>
        </tr>
        <tr>
            <td>품목 C</td>
            <td>2</td>
            <td>50,000</td>
            <td>100,000</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="3"><strong>총 합계</strong></td>
            <td><strong>300,000</strong></td>
        </tr>
        </tfoot>
    </table>

    <div class="footer">
        <p>위와 같이 거래명세서를 발행합니다.</p>
        <p>발행자: ABC 상사</p>
    </div>
</div>
</body>
</html>