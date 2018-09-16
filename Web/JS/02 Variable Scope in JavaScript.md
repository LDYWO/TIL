# Variable Scope in JavaScript
## 1. 변수 범위 (Variable Scope)
- 변수 범위는 변수가 존재하는 **컨텍스트**이다.
- 어디에서 그 변수에 접근할 수 있는지 **명시적**으로 나타낸다.
- 변수는 **지역 범위**(**local scope**)와 **전역 범위**(**global scope**) 둘 중 하나를 가진다.

### 1.1 지역 변수 (함수 수준 범위)
- 대부분의 프로그래밍 언어와 달리 **자바스크립트는 블록 수준의 범위를 갖지 않는다.**
- 대신 자바스크립트는 **함수 수준(function-level)의 범위**를 가진다.
- 함수 내에 정의된 변수는 **지역 범위**를 가지며 해당 함수 내부에서만 접근이 가능하다.
- 내부 함수에서 외부 함수의 변수 접근에 관해서는 [Closure](https://ko.wikipedia.org/wiki/%ED%81%B4%EB%A1%9C%EC%A0%80_(%EC%BB%B4%ED%93%A8%ED%84%B0_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D))를 참고하자.

```javascript
var name = "Richard";

function showName() {
    var name = "Jack"; // 지역 변수, showName() 함수에서만 접근 가능
    console.log(name); // Jack
}
consle.log(name); // Richard; 전역 변수
```
- 잘못된 예제 (블록 범위 수준이 된다고 착각하는 경우)
```javascript
var name = "Richard";

if (name) {
    name = "jack";
    console.log(name); // jack = 전역 변수
}

// name은 여전히 전역 변수이며 if 문에서 변경되었습니다.
console.log(name); // Jack;
```
- **지역변수를 선언하지 않는다면 문제를 일으킬 가능성이 높아진다.**
- **항상 지역변수를 사용하기 이전에 선언하도록 해야한다.**
- **지역변수는 함수 내에서 전역변수보다 높은 우선순위를 가진다.**

### 1.2 전역 변수 (Global Scope)
- 함수의 외부에서 선언된 모든 변수는 **전역 범위(Global Scope)** 를 가진다.
- 브라우저에서 전역 컨텍스트(Scope)는 **Window 객체**를 가리킨다.

```javascript
// 전역 변수는 아래와 같이 선언될 수 있다.
var myName = "Richard";
// 또는
firstName = "Richard";
// 또는
var name;
name;
```
* 모든 전역 변수는 **Window 객체**와 연결된다.
* 그러므로 아래와 같이 Window 객체를 통해 모든 전역변수에 접근이 가능하다.
```javascript
console.log(window.myName);
// 또는
console.log("myName" in window);
console.log("firstName" in window);
```
- 만약 변수가 최초 선언없이 초기화 되었다면, 이 변수는 자동으로 전역 컨텍스트에 추가된다.
- **전역 범위를 오염시키지 마라**
- **자바스크립트 전문가**가 되려면, **가급적 전역 범위에 변수를 생성하는 것을 피하도록 해야한다.**

***

## 2. 변수 호이스팅 (Variable Hoisting)
- 모든 변수 선언은 **호이스트** 된다.
- **호이스트**란, 변수의 정의가 그 **범위**에 따라 **선언과 할당**으로 분리되는 것을 의미한다.
- 변수가 **함수 내**에서 정의되었을 경우 선언이 **함수의 최 상위**로 변경된다.
- 변수가 **함수 밖**에서 정의되었을 경우 **전역 컨텍스트의 최 상위**로 변경된다.

**변수의 선언이 초기화나 할당시에 발생하는 것이 아니라, 최상위로 호이스트 된다는 것을 명심 해야한다.**

```javascript
function showName() {
    console.log("Fisrst Name : " + name);
    var name = "Ford";
    console.log("Last Name " + name);
}
showName();
// First Name: undefined
// Last Name : Ford
// Fist Name이 undefined인 이유는 지역변수 name이 호이스트 되었기 때문이다.
```
- **호이스트 되었을 때, 함수 선언은 변수 선언을 덮어쓴다.**
```javascript
// 다음 두 변수와 함수는 myName으로 이름이 같다.
var myName; // String

function myName() {
    console.log("Rich");
}

console.log(typeof myName); // String
```
- 하지만, **변수에 값이 할당될 경우에는 반대로 변수가 함수선언을 덮어쓴다.**

> **"strict mode"** 에서 최초의 선언 없이 변수에 값을 할당하려 한다면 오류가 발생한다. 변수에 값을 할당하려 할 때는 항상 미리 선언하는 습관을 들이는 것이 좋다.
