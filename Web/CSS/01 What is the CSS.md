# What is the CSS?
## 1. CSS (Cascading Style Sheets)
- 종속형 시트, 또는
[**캐스케이딩 스타일 시트**](https://ko.wikipedia.org/wiki/%EC%A2%85%EC%86%8D%ED%98%95_%EC%8B%9C%ED%8A%B8)
- 마크업 언어가 실제 표시되는 방법을 기술하는 언어
- **HTML과 XHTML**에 주로 쓰인다.
- **W3C의 표준**이며 레이아웃과 스타일을 정의할 때의 자유도가 높다.
### 1.1 표현 방식
  ```css
  꾸밀대상 {      
      속성 : 값;
  }
  ```
  - ```{}```는 셀렉터(**Selector**), ```속성 : 값;```은 선언(**Declaration**)
  - **속성**은 ```"유전"```된다.

  ### 1.2 유형
  1. **Element** Selector (Tag or Type Selector)
    - **특정 타입**의 **모든 HTML 엘리먼트**를 선택한다.
  ```css
  p {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```

  2. **Id** Selector
    - **특정 아이디**를 가진 페이지의 엘리먼트 (**단 하나만 허용**)
  ```css
  #my-id {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  3. **Class** Selector
    - **특정 클래스**를 가진 페이지의 엘리먼트 (**여러 개 허용**)
  ```css
  .my-class {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  4. **Attribute** Selector
    - **특정 속성**을 갖는 페이지의 엘리먼트
  ```css
  .my-class[status = "scrolled"] {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  5. **Pseudo** Selector
    - **특정 상태**에 있는 **특정 엘리먼트**
  ```css
  .my-class:hover {
            color: blue;
  }
  ```
***
## 2. CSS의 구조
### 2.1 Box Model
  - CSS의 레이아웃을 구성하는 특징

  ![CSS Box Model](https://www.topalovich.com/wp-content/uploads/2017/09/Box_Model.png)

    >- **Content**: 컨텐츠
    - **Padding**: 컨텐츠 주위의 공간
    - **Border**: 패딩의 바깥쪽 실선
    - **Margin**: 엘리먼트 바깥쪽을 둘러싼 공간

### 2.1 Display 속성
  ```css
  <div class = "box"> ... </div>             
  <div class = "box"> ... </div>
  <div class = "box"> ... </div>
  ```
  ```css
  .box {
      width: 500px;
      height: 30px;
      background: #fof;
      margin: 10px;
  }
  ```
1. ```display: none;```

    - 화면에 보이지 않게 만든다.

2. ```display: block;```

    - **default** 값이며 태그를 **block** 형식으로 변환 시켜준다.

3. ```display: inline;```

    - **block** 형식과 반대이며 태그를 **inline** 형식으로 변환 시켜준다.
    - **width, height** 지정 불가능
    - **좌우만 margin 적용 가능**

4. ```display: inline-block;```

    - **inline + block**
    - **width, height** 지정 가능
    - **상, 하, 좌, 우 margin 적용 가능**

### 2.2 해상도에 관계 없는 정렬
```css
  max-width: 1200px;
  margin: auto;
```
- **margin**값을 **auto**로 두어 기기의 해상도에 상관 없이 **가운데 정렬**이 가능하다.

### 2.3 이미지 삽입
  1. **img tag**
  ```css
  <img src="whiteowl.jpeg" alt="하얀 부엉이" width="100" height="auto">
  ```
  2. **CSS의 background 속성**
  ```css
  <div class="bgImage"></div>

  .bgImage {
          background: url('/image/whiteowl.jpeg') no-repeat;
          background-size: contain;
          width: 100px;
          height: auto;
  }
  ```
- ```background-size: cover;```: 이미지 크기 비율을 유지한 상태에서 이미지가 들어있는 가로 또는 세로 중 큰 값에 이미지를 맞춘다.
    ![cover](https://t1.daumcdn.net/cfile/tistory/9938B5485A5DC37F0D)
- ```background-size: contain;```: 이미지 크기 비율을 그대로 유지한 상태에서 원하는 영역에 전체 이미지가 들어가도록 가장 작은 크기로 이미지 스케일을 조정한다.
    ![contain](https://t1.daumcdn.net/cfile/tistory/99EAD3485A5DC3801D)


> 이외의 **Img태그와 CSS-background의 차이점**은 [What is the HTML](https://github.com/LDYWO/TIL/blob/master/Web/HTML/01%20What%20is%20the%20HTML.md)을 참고하자.

### 2.4 테두리 속성
#### 2.4.1 border style

- **border-style**

  ![border-style](https://www.w3.org/TR/css-backgrounds-3/images/borderstyles.png)

- **border-radius**

  ![border-radius](http://www.studentsempire.com/wp-content/uploads/2018/03/border.png)

- **border-top, right, bottom, left**

```css
p {
    border-left: 6px solid red;
    background-color: lightgrey;
}

p {
    border-bottom: 6px solid red;
    background-color: lightgrey;
}
```
- **border**
```css
border-style: solid;
border-width: 5px;         ---------> border: solid 5px #000;
border-color: #000;
```

### 2.5 속성 적용 방향
**시계 방향**으로 적용된다
``` margin: 10px 5px 3px 1px;```

***

## 3. 그 외 디자인 소스
### 1. Google Material Design
- [Google Material Design](https://material.io/icons/)
### 2. Google Fonts
- [Google Fonts](https://fonts.google.com/)

***

## 4. 반응형 웹
웹 디자인 기법 중 하나. __웹(Web)__ 에 접속하는 **디바이스**에 반응하는(**Responsive**) 디자인(**Design**)을 말한다.

### 4.1 미디어 쿼리
```@media only all and (조건문) {실행문}```
* **@media** : **미디어 쿼리가 시작**됨을 선언
* **only** : **미디어 쿼리를 지원하는 사용자 에이전트**만, 미디어 쿼리 구문을 해석하라는 명령이며 생략 가능, 생략시 **default는 only**
* **all** : 미디어 쿼리를 해석해야 할 대상 미디어를 선언한 것, ```all```은 모든 미디어가 이 구문을 해석해야 한다. **screen**이나 **print**와 같은 특정 미디어를 언급할 수 있음. 생략 시 **default는 all**
* **and** : 논리적으로 **'AND' 연산**을 수행하여 **앞과 뒤의 조건을 모두 만족**해야 한다는 것을 의미, **조건이 유일하거나** 또는 **only, all과 같은 선행 키워드가 생략되면 and 키워드는 사용하지 말아야 한다.** and 대신 **콤마 ',' 기호를 사용하면 'OR' 연산을 수행**한다. 'OR' 연산은 나열된 조건 중에서 **하나만 참이어도 {실행문}을 해석**한다.
* **조건문** : **조건문이 참일 때 {실행문}을 처리**하고 거짓일 때 무시, 조건문은 **두 가지 이상 등장할 수 있다.** 둘 이상의 조건문은 **'and' 키워드 또는 콤마 ',' 기호로 연결**해야 한다.
* **실행문** : 일반적인 **CSS 코드**를 이 괄호 안에 작성한다. 브라우저는 **(조건문)이 참일 때 실행문 안쪽에 있는 CSS 코드를 해석**한다.

```css
  /* iPads {portrait and landscape} */
  @media only screen
  and (min-width : 768px)
  and (max-width : 1024px) {
    body  { background-color: orange; }
    h1 { color: black; }
  }
```

***


