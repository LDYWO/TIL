# What is the CSS?
## 1. CSS (Cascading Style Sheets)
- 종속형 시트, 또는
[**캐스케이딩 스타일 시트**](https://ko.wikipedia.org/wiki/%EC%A2%85%EC%86%8D%ED%98%95_%EC%8B%9C%ED%8A%B8)
- 마크업 언어가 실제 표시되는 방법을 기술하는 언어
- **HTML과 XHTML**에 주로 쓰인다.
- **W3C의 표준**이며 레이아웃과 스타일을 정의할 때의 자유도가 높다.
### 1.1 표현 방식
  ```
  꾸밀대상 {      
      속성 : 값;
  }
  ```
  - ```{}```는 셀렉터(**Selector**), ```속성 : 값;```은 선언(**Declaration**)
  - **속성**은 ```"유전"```된다.

  ### 1.2 유형
  1. **Element** Selector (Tag or Type Selector)
    - **특정 타입**의 **모든 HTML 엘리먼트**를 선택한다.
  ```
  p {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```

  2. **Id** Selector
    - **특정 아이디**를 가진 페이지의 엘리먼트 (**단 하나만 허용**)
  ```
  #my-id {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  3. **Class** Selector
    - **특정 클래스**를 가진 페이지의 엘리먼트 (**여러 개 허용**)
  ```
  .my-class {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  4. **Attribute** Selector
    - **특정 속성**을 갖는 페이지의 엘리먼트
  ```
  .my-class[status = "scrolled"] {
            color: red;
            width: 500px;
            border: 1px solid black;
  }
  ```
  5. **Pseudo** Selector
    - **특정 상태**에 있는 **특정 엘리먼트**
  ```
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
  ```
  <div class = "box"> ... </div>             
  <div class = "box"> ... </div>
  <div class = "box"> ... </div>
  ```
  ```
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
```
  max-width: 1200px;
  margin: auto;
```
- **margin**값을 **auto**로 두어 기기의 해상도에 상관 없이 **가운데 정렬**이 가능하다.

### 2.3 이미지 삽입
  1. **img tag**
  ```
  <img src="whiteowl.jpeg" alt="하얀 부엉이" width="100" height="auto">
  ```
  2. **CSS의 background 속성**
  ```
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

***
