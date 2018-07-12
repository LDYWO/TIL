# What is the HTML?
## 1. HTML (HyperText Markup Language)
- 하이퍼 텍스트 마크업 언어라는 의미로 웹 페이지를 위한 언어이다.
- HTML은 제목, 단락, 목록 등과 같은 본문을 위한 구조적 의미를 나타낸다.
- 링크, 인용과 그 밖의 항목으로 구조적 문서를 만들 수 있는 방법을 제공한다.
- HTML은 웹 페이지 콘텐츠 안의 ```<>```에 둘러싸인 ```태그```로 되어있는 형태로 작성된다.
- **javascript**나 **CSS**를 포함하거나, 불러올 수 있다.
### 1.1 HyperText
  - 페이지 간 하이퍼 링크가 포함된 텍스트
  - 단순한 텍스트 이상의 **링크** 등의 개념이 포함된 텍스트

  ### 1.2 Markup
  - 태그를 사용하는 규격
  - 문서의 내용 어느 부분이 어떤 역할을 갖는지 명시해준다.
  - 브라우저가 문서를 해석하도록 **표시(Mark)** 해준다.

***
## 2. HTML의 구조
> ```<tag key = value>```

- 여기서 'key = value'는 **attribute**이다.
- 예를 들면 ```<div id="explanation" style="visibility: hidden;"> ... </div>```

***
## 3. HTML의 tag
> 해당 태그 영역이 어떤 역할을 해야 하는지 웹 브라우저의 렌더링 엔진에게 알려주는 역할
### 3.1 Close tag가 없는 HTML 요소
- 태그 내부에 넣을 값이 없는 경우
- **Self-Closing** 규칙을 맞춰주기 위해서 ```<tag/>```로 사용하기도 한다.
- ```<br>, <img>, <meta>, <link>, <hr>```

### 3.2 id와 class 속성
- 하나의 특정 id는 하나의 태그에만 적용할 수 있다.
- 하나의 class는 여러 개의 태그에 적용할 수 있다.
```
	<div id="bmw"></div>
	<div id="benz" class="car"></div>
	<div id="audi" class="car"></div>
	<div id="ford" class="car"></div>
```

### 3.3 ```<head>``` 태그
- 웹 페이지에서 **보이지 않는 정보**들을 담는 영역

  ### 3.3.1 종류
1. ```<title> ... </title>```
  - 웹 페이지의 제목을 나타내는 태그
  - 웹 페이지의 본문에는 보이지 않으며, 브라우저의 탭에서 확인할 수 있다.
2. ```<meta/>```
  - meta 태그는 웹 페이지의 보이지 않는 정보를 제공하는데 쓰이는 태그이다.
  - 페이지의 설명 요약, 핵심 키워드, 제작자, 크롤링 정책 등 수많은 정보를 제공할 수 있다.
3. ```<link> ... </link>```
  - 외부 파일을 연결 (Icon, Font..)
  - 주로 CSS 연결에 사용한다.
4. ```<script> ... </script>```
  - 외부 파일과 연결
  - 주로 javascript 연결에 사용한다.
***
### 3.4 ```<body>``` 태그
- 웹 페이지에서 **보이는 정보**들을 담는 영역

  ### 3.4.1 종류
1. ```<br>```
  - Line **Br**eak
  - 줄 바꿈으로 사용
  - HTML은 코드의 가독성 향상을 위해 엔터로 줄바꿈이 되지않는다.
2. ```<pre> ... </pre>```
  - **Pre**formatted text
  - HTML에서 엔터를 화면에 적용하고 싶을 때 사용하는 태그
  - ```<pre>``` 태그 안에선 줄바꿈, 띄어쓰기, 탭 등 원래 무시되던 문자들이 나타난다.
3. ```<p> ... </p>```
  - **P**aragraph
  - 하나의 문단을 만들 때 사용
  - 문단의 위쪽과 아래쪽은 줄바꿈이 생긴다.
4. ```<b> ... </b>```
  - **B**old
  - 문자를 굵게 표시하는 태그
  - 최신 표준에서는 ```<strong>``` 태그를 권고 (강조의 의미)
5. ```<i> ... </i>```
  - **I**talic
  - 글자를 기울여서 표시
6. ```<h1> ... </h1>```
  - **H**ead
  - 섹션, 문단의 제목을 나타낸다.
  - 숫자가 커질수록 글자의 크기가 작아진다.
  - 글자의 크기 뿐만 아니라 문서를 파악하기 위해 쓰인다.
7. ```<a> ... </a>```
  - **A**nchor
  - 하이퍼 링크를 걸어주는 태그 속성
  - **href**: 클릭시 이동할 링크
  - **target**: 링크를 여는 방법
    - _self: 현재 페이지 (default)
    - _blank: 새 탭
    - _parent: 부모 페이지
    - _top: 최상위 페이지
    - frame name: 직접 프레임 명시
8. ```<img> ... </img>```
  - __im__a**g**e
  - 이미지 파일이 경로에 없을 경우 출력되지 않거나 엑스박스가 뜬다.
  - 지정된 높이와 넓이만큼 이미지의 비율이 달라진다. (auto 가능)
  - CSS의 background-image는 이미지의 height를 지정해주어야 한다.
  - width는 둘 다 지정해주어야 한다. (text 첨부는 css만 가능)
  ```
  <img src="image.png" alt="이미지 설명" width="500" height="300">
  ```
9. ```<table>```
  - ```<tr>``` 표의 열을 나타낸다.
  - ```<td>``` 표의 행을 나타낸다.
  - ```<table>``` 태그는 적절한 사용방법이 아니다.
  - ```<div>``` 태그와 css를 이용해서 테이블을 만드는 것이 좋다.
10. ```<div> ... </div>```
  - **Div**ision
  - 레이아웃을 나누는 데 쓰인다.
  - 특별한 기능은 없다.
  - 가상의 레이아웃을 설계할 때 사용한다.
  - 주로 CSS와 연동하여 사용한다.
  - 기본 display 속성은 block (줄바꿈 적용)
11. ```<span> ... </span>```
  - ```<div>``` 태그와 비슷하다.
  - 하지만, display 속성이 inline이다. (줄바꿈 미적용)
12. ```<li> ... </li>```
  - **Li**st
  - 목록을 만드는데 쓰인다.
  - ```<ul> ... </ul>```: Unordered List로 순서가 없다.
  - ```<ol> ... </ol>```: Ordered List로 순서가 있다.

### 3.4.2  ```<form/>```
  - 웹 페이지에서의 입력 양식을 의미

  - ```<form>```태그의 속성에는 name, action, method, target 등이 있다.
  - 이 속성을 이용하여 전송할 때 어디로, 어떻게 보내야 하는지 방법을 정한다.
    - **action**: 폼을 전송할 서버 쪽 스크립트 파일을 지정한다.
    - **name**: 폼을 식별하기 위한 이름을 지정한다.
    - **accept-charset**: 폼 전송에 사용할 문자 인코딩을 지정한다.
    - **target**: action에서 지정한 스크립트 파일을 다른 위치에 열도록 지정한다.
    - **method**: 폼을 서버에 전송할 http 메소드를 정한다. (GET 또는 POST)

    ```
    <form action = "http://localhost:8080/form.jsp" accept-charset="utf-8"
          name = "person_info" method = "get">
    ```
  - ```<input>``` 태그
    - ```<text>```: 일반 문자
    - ```<password>```: 비밀번호
    - ```<button>```: 버튼
    - ```<submit>```: 양식 제출용 버튼
    - ```<reset>```: 양식 초기화용 버튼
    - ```<radio>```: 한개만 선택할 수 있는 컴포넌트
    - ```<checkbox>```: 다수를 선택할 수 있는 컴포넌트
    - ```<file>```: 파일 업로드
    - ```<image>```: 이미지 형태로 나타난다.
    - ```hidden```: 사용자에게 보이지 않는 숨은 요소
    - ```diabled```: 해당 form 요소를 비활성화 시킴
    - ```readonly```: 폼 안의 텍스트를 읽을 수만 있도록 한다.
    - ```maxlength```: 글자 수 제한으로 지정된 숫자 이상은 넣을 수 없게 한다.

**사용법**
```
	<input type="text" name="name" placeholder="아이디 입력">
```
```
	<input type="password" name="password" value="비밀번호 입력">
```
```
	<input type="radio" name="gender" value="M">남자
	<input type="radio" name="gender" value="F">여자
```
```
	<input type="checkbox" name="part" value="eng">영어
	<input type="checkbox" name="part" value="math">수학
```
```
	<input type="submit" value="제출">
```

* < select > & < option > 태그
드롭다운 리스트를 만드는 태그

**사용법**
```
	<select>
		<option value="ktx">KTX</option>
		<option value="itx">ITX 새마을</option>
		<option value="mugung">무궁화호</option>
	</select>
```

* 기타 < form >태그
`<label>`
`<textarea>`
`<button>`
`<optgroup>`
`<fieldset>`



* **< textarea > 태그**
높이와 너비를 가진 글 박스

**Input 태그의 속성**
```
cols: 너비
	cols="40"	// 40글자의 너비를 갖는다
```
```
rows: 높이
	rows=“3”	// 3줄의 높이를 갖는다
```
`disabled: 해당 form요소를 비활성화시킴`
`readonly: 폼안의 텍스트를 수정할 수 없고 읽을수만 있다`

**input 태그의 text type과 textarea의 차이점**
input태그는 한 줄로만 작성가능
textarea는 여러 줄 작성 가능 (엔터도 작성 가능)
***
## 4. HTML의 주석
> ```<!--Something--> ```의 형식으로 주석 작성이 가능하다.

***
