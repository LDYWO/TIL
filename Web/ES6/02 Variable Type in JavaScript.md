# Variable Type in JavaScript
## 1. var, let const
- ```var```는 **function-scoped**
- ```let```, ```const```는 **block-scoped**
### 1.1 var (function-scoped)
```javascript
// var는 function-scope이다.
// 반복문이 끝난 후에 i를 호출하면 값이 잘 출력된다.
for (var j=0; j<10; j++) {
    console.log('j', j);
}
console.log('after loop j is', j); // after loop j is 10

// 아래의 경우에는 에러가 발생한다.
function counter() {
    for (var i; i<10; i++) {
        console.log('i',i);
    }
}
counter();
console.log('after loop i is', i) // ReferenceError: i is not undefined
```
- 이런 경우 **JavaScript**의 **IIFE**를 사용하여 ```function-scope```인 것 처럼 만들 수 있다.

```javascript
// IIFE를 사용하면
// i is not defined가 뜬다.
(function() {
    // var 변수는 여기까지 hoisting이 된다.
    for (var i=0; i<10; i++) {
        console.log('i', i);
    }
})();
console.log('after loop i is', i); // ReferenceError: i is not defiend
```
- 하지만 **JavaScript**의 **IIFE**는 ```function-scope```인 것처럼 만들어주지만 **결과가 같지는 않다.**

```javascript
// 이 코드를 실행하면 에러없이 after loop i is 10이 호출된다.
(function() {
    for (i=0; i<10; i++) {
        console.log('i', i);
    }
})();
console.log('after loop i is', i) // after loop i is 10
```
- 이 코드가 아무 에러없이 실행되는 이유는 **i**가 **hoisting**되어서 ```global variable```이 되었기 때문이다.

```javascript
var i;

(function() {
    for (i=0; i<10; i++) {
        console.log('i', i);
    }
})();
console.log('after loop i is', i); // after loop i is 10
```
- **IIFE**를 쓰는데도 불구하고 ***hoisting**이 발생한다.
- 이러한 **hoisting** 을 막기 위해 ```use strict```를 사용한다.

```javascript
// 아까랑 다르게 실행하면 i is not defined라는 에러가 발생한다.
(function() {
    'use strict'
    for (i=0; i<10; i++) {
        console.log('i', i);
    }
})();
console.log('after loop i is ' i); // ReferenceError: i is not defined
```
- 이렇게 **변수 선언** 하나를 처리하기 위해 ```IIFE```, ```hoisting``` 등 많은 경우를 생각해야한다.

***

### 1.2 let, const (block-scoped)
> **ECMA6(ES2015)** 에서는 ```let```, ```const```가 추가 되었다.

 기존의 **JavaScript**에서는 ```var```만 존재했기 때문에 많은 문제가 있었다.

```javascript
// Case 1: 이미 만들어진 변수 이름으로 재 선언했는데 아무런 문제가 발생하지 않는다.
var a = 'test';
var a = 'test2';

// Case 2: hoisting으로 인해 ReferenceError가 안 생긴다.
c = 'test';
var c;
```
#### 1.2.1 let과 const의 공통점
- **변수 재선언 불가능**

#### 1.2.2 let과 const의 차이점
- **immutable** 여부
    - ```let```은 변수에 **재 할당 가능**
    - ```const``` 변수에 **재 할당 불가능**

```javascript
// let
let a = 'test';
let a = 'test2'; // Uncaught SyntaxError: Identifier 'a' has already been declared
a = 'test3'; // 가능

// const
const b ='test';
const b = 'test2'; // Uncaught SyntaxError: Identifier 'a' has already been declared
b = 'test3'; // Uncaught TypeError: Assignment to constant variable.
```
그렇다고 ```let```, ```const```에 **hoisting**이 발생하지 않는 것은 아니다.
- ```var```는 ```function-scoped```단위로 **hoisting**이 이루어진다.
- ```let```, ```const```는 ```block-scoped``` 단위로 **hoisting**이 이루어진다.

```javascript
c = 'test'; // ReferenceError: c is not defined
let c;
```
- 위의 코드에서 ```ReferenceError```가 발생한 이유는 ```TDZ(Temporal Dead Zone)```때문이다.
- ```let```은 값을 할당하기 전에 **미리 변수가 선언되어 있어야 한다.**

```javascript
// let은 선언하고 나중에 값을 할당받는 것이 가능하다. (반드시 먼저 선언해야함)
let dd;
dd = 'test';

// const는 선언과 동시에 값을 할당 해야한다.
const aa; // Missing initializer in const declaration
```
- ```const```의 경우 ```let```보다 **엄격하다.**
- **선언**과 동시에 **변수가 할당**되어야 한다.
- 이렇게 **JavaScript**에 ```TDZ```가 있는 이유는 **동적언어**이기 때문에 **Runtime-Type-Check**가 필요하기 때문이다.

***
## 2. 참고
- [Temporal Dead Zone](https://github.com/ajzawawi/js-interview-prep/blob/master/answers/es6/temporal-dead-zone.md)
