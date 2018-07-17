# What is the JavaScript?
## 1. JavaScript
- **동적**으로 컨텐츠를 바꾸고, **멀티미디어**를 다루고, 움직이는 이미지 등 **웹 페이지**를 꾸며주도록 하는 **프로그래밍 언어**

### 1.1 특징
 1. **인터프린터 언어**
 2. **클라이언트 스크립트 언어**

### 1.2 외부 파일 연결
```javascript
<scrpit type="text/javascript" src="script.js"></script>
```
***
## 2. Using JavaScript
### 1. 변수 선언 (Variable Declaration)
```javascript
var a = 0; // Number
var b = ""; // String
var c = []; // Array
var d = {}; // Object
```
***

### 2. Console.log()
```javascript
var a = 0;
console.log(a);
```

***

### 3. 비교 연산자
- ```==```과 ```===```의 차이
  - ```==```는 타입 체크를 하지 않는다. (**Loose**)
  - ```===```는 타입 체크를 한다. (**Strict**)

```javascript
var a = 0;
var b = "0";

console.log(a==b); // true
console.log(a===b); // false;
```
***

### 4. 함수 (Function)
```javascript
function 함수이름 (Parameter1, Parameter2,...) {
  // 함수
  ...
  // return 값;
}
```

#### 4.1 함수 선언 (Function Declaration)
- 함수 문장 (Function Statement)
- 함수의 정의를 나타낸다.
- 코드 자체는 실행할 수 없다.
```javascript
function foo() {
  console.log("Hello");
}
```

#### 4.2 함수 표현 (Function Expression)
- Fucntion Literal
- 변수나 데이터 구조에 할당되어지고 있음을 의미한다.
- 특정 변수에 할당되거나 즉시 실행이 가능하다.
```javascript
var foo = function() {
  console.log("hello");
};
```
```javascript
// anonymous function Expression
var foo = function() {
  console.log("hello");
};

// named function Expression
var foo = function foo() {
  console.log("hello");
};
```
- 자기 호출 함수 (self invoking function)
- 해석과 동시에 실행되는 코드 블럭
```javascript
// self invoking function Expression
(function foo() {
  console.log("hello");
})();
```
***

### 5. 기타
#### 5.1 getElementBy..()
```javascript
document.getElementById("id Selector");
document.getElementByClasssName("class Selector");
```
```html
<div id="parent-id">
  <p>hello-word1</p>
  <p class="test">hello word2</p>
  <p>hello word3</p>
  <p>hello word4</p>
</div>
```
```javascript
var parentDOM = document.getElementById("parent-id");

// test is not target getElement
var test= parentDOM.getElementByClasssName("test");
console.log(test); // HTMLCollection[1]

// hear, this element is target
var testTarget= parentDOM.getElementByClasssName("test")[0];
console.log(testTarget); // <p class="test">hello word2</p>
```

#### 5.2 addEventListener()
- 마우스 클릭, 키보드 입력 등의 이벤트를 듣는 역할을 하는 **Method**
```javascript
element.addEventListener(event, function, useCapture);
```
- 순서대로 **이벤트 종류**, **이벤트 시 호출하는 함수**, 그 뒤는 자유롭게 작성

```javascript
// anonymous method
element.addEventListener('click', function() { alert("Hello World!"); });

// function declaration
element.addEventListener('click', myFunction); // 원하는 이벤트 추가 가능

function myFunction() {
    alert("Hello World");
}
```

#### 5.3 innerHTML
- HTML 요소에 접근하여 값을 바꾸는 기능
```javascript
element.addEventListener('click', myFunction);

function myFunction() {
    docment.getElementById("#hi").innerHTML = "Hi";
}
```
***
### 6. jQuery
> 엘리먼트를 선택하는 강력한 방법과 선택된 엘리먼트들을 효율적으로 제어할 수 있는 다양한 수단을 제공하는 **자바스크립트 라이브러리**

#### 6.1 장 단점
- **장점**: ```멀티 브라우저 지원```
- **단점**: ```새로운 API 학습```, ```퍼포먼스 문제```

#### 6.2 연결법
```html
// CDN 호스트 사용
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

// 직접 내려받아 사용
<script src="jquery-1.10.2.js"></script>
```

#### 6.3 선택자 (Selector)
```javascript
$("선택대상").메소드(); // .class, #id, "tag"
```

#### 6.4 이벤트 처리 (Event Handling)
```javascript
$("선택대상").click(function() {
  ...;
});
```

#### 6.5 html()
- HTML 요소에 접근하여 값을 바꾸는 기능 (= **innerHTML**)
```javascript
function myFunction() {
    $("#hi").html();
    $("#hi").html("Hello World");
}
```
***
