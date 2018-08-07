# 1. JavaScript의 데이터 타입과 연산자
## 1. 자바스크립트의 타입
### 1.1 자바스크립트 기본 타입
```javascript
Number
String
Boolean
undefined
null
```
- JavaScript에서는 한번 생성된 문자열을 변경할 수 없다. (index로 조회는 가능하다.)
- null **VS** undefined
  - null
    - 개발자가 명시적으로 값이 비어있음을 나타낼 때
  - undefined
    - 값이 할당되지 않은 변수
    - undefined type의 변수는 변수 자체의 값 또한 undefined
    - undefiend는 type이자 변수

### 1.2 자바스크립트 참조 타입 (객체)
```javascript
Object
Array
Function
Regular Expression
```
- 자바스크립트의 거의 모든 것은 객체이다.
- 자바스크립트에서 객체는 이름(key): 값(value) 형태의 프로퍼티들을 저장하는 컨테이너이다.

#### 1.2.1 객체 생성
##### 1.2.1.1 Object(): 내장 생성자 함수
```javascript
var foo = new Object();

foo.name = 'foo';
foo.age = 30;
foo.gender = 'male';

console.log(typeof foo2);
console.log(foo2);
```
#### 1.2.2 객체 프로퍼티 읽기/ 쓰기 갱신
- 객체의 프로퍼티에 접근하는 2가지 방법
  - [] 표기법
  - . 표기법
```javascript
var foo = {
    name: "foo",
    major: "computer science"
};

console.log(foo.name); // . 표기법
console.log(foo['name']); // [] 표기법
```
#### 1.2.3 for in 문과 객체 프로퍼티 출력
- 객체의 모든 프로퍼티에 대해 루프를 수행할 때 유용하다.
```javascript
var foo = {
    name: "foo",
    age: 30,
    gender: "male"
};

var key;
for (key in foo) {
    console.log("key:" + key + " value" + foo[key]); // 대괄호 표기법으로 접근
}
```
#### 1.2.4 객체 프로퍼티 삭제
```javascript
var foo = {
    name: "foo",
    age: 30,
    gender: "male"
};

console.log(foo.name); // foo
delete.foo.name;
console.log(foo.name); // undefined - javascript에서는 존재하지 않는 프로퍼티에 접근할 경우 undefined를 반환

delete.foo;
console.log(foo.name); // delete 함수는 객체의 프로퍼티를 삭제할 뿐, 객체를 삭제하지는 못한다.  
}
```
***
## 2. 참조 타입의 특성
- Number, String, Boolean, undefined, null을 제외한 모든 값은 객체이다.
- 배열, 함수 등도 객체이며 이들은 '참조 타입'으로 불린다.
```javascript
var obj A = {
    val : 30
}; // objA는 객체 자체가 아니라 객체의 참조 값을 저장할 뿐이다.

var objB = objA; // 이제 objA와 objB는 같은 객체를 참조한다.

console.log(objA.val);
console.log(objB.val);

objB.val = 50; // 따라서 objB가 참조하는 객체의 프로퍼티를 변경하면 같은 객체를 참조하는 objA의 값 또한 바뀐다.
console.log(objB.val);
console.log(objA.val);
```

### 2.1 객체 비교
- 동등 연산자 (= =)를 사용하여 두 객체를 비교할 때는 객체의 프로퍼티 값이 아닌 참조 값을 비교한다.
```javascript
var a = 100;
var b = 100;

var objA = {val: 100};
var objB = {val: 100};
var objC = objB;

console.log(a == b); true;
console.log(objA == objB); // false;
console.log(objA.val == objB.val); // true
console.log(objB == objC); // true
```
  - obj는 참조 타입이기 때문에 값이 같다고 해도 동등 연산자에서 false 값을 뱉는다.

### 2.2 참조에 의한 함수 호출 방식
- call-by-value **VS** call-by-reference
- **기본 타입**은 **call-by-value**, **참조 타입**은 **call-by-reference**로 동작한다.

```javascript
var a = 100;
var objA = {
    value: 100
};

function changeArg(num, obj) {
    num = 200;
    obj.value = 200;

    console.log(num); // 200
    console.log(obj.value); //200
}

console.log(a); // 100
console.log(objA.value); // 100
changeArg(a, objA);
console.log(a); // 100
console.log(objA.value);
```
### 2.3 프로토 타입
- 부모 객체, 상속과 비슷한 개념
- 자바스크립트의 모든 객체는 자신의 프로토 타입을 가리키는 [Prototype]라는 숨겨진 프로퍼티를 가진다.
- 모든 객체의 프로토 타입은 객체를 생성할 때 결정된다.
- Chrome에서는 ```__proto__```
```javascript
var foo = {
    name: 'foo',
    age: 30
};

console.log(foo.toString());
console.dir(foo);
```
## 3. 배열
- 배열도 객체이다.
### 3.1 배열 리터럴
- 객체는 프로퍼티 명으로 접근, 배열의 요소는 인덱스로 접근한다.
```javascript
var colorArr = ["orange", "yellow", "green", "red"];

console.log(colorArr[0]);
console.log(colorArr[1]);
console.log(colorArr[2]);
console.log(colorArr[3]);
```
### 3.2 배열의 요소 생성
- 동적으로 추가 가능
```javascript
var emptyArr = [];
emptyArr[1] = '1';
emptyArr[3] = '3';

console.log(emptyArr); // --> undefined, 1, undefined, 3
```
### 3.3 배열의 length 프로퍼티
- 배열은 length라는 프로퍼티를 가지고 있다.
- length는 원소 개수를 의미하지만, 실제 원소 개수와 항상 같은 것은 아니다.
- 그저 배열의 크기를 반환할 뿐, 실제 원소 개수를 알려주지는 않는다.
- 배열의 가장 큰 인덱스에 1을 더한 값이다.
- 명시적으로 변경이 가능하다.
```javascript
var arr = [];

arr[0] = 11;
arr[1] = 12;
arr[2] = 13;
arr[100] = 1;

console.log(arr.length); // 실제로 존재하는 원소는 4개지만 length는 101
```

#### 3.3.1 배열 표준 메소드와 length 프로퍼티
- push, pop, shift 등이 있다.
- push의 경우, 배열의 마지막 인덱스 +1, 즉, length index에 새로운 원소를 추가한다.
- 그러므로 length 프로퍼티는 중요하다.
```javascript
var arr = ['one', 'two'];

arr.push('three');
console.log(arr);
console.log(arr.length);

arr.length = 4;
console.log(arr);
arr.push('four');
console.log(arr);
console.log(arr.length);
```
### 3.4 배열의 프로퍼티 동적 생성
- 배열도 객체이므로, 프로퍼티를 가질 수 있다.
- 단, 배열의 원소가 아닌 객체로서의 프로퍼티는 length에 영향을 주지 않는다.
```javascript
var arr = ['one', 'two'];

console.log(arr.length);

arr.gender = 'male'; // 프로퍼티 추가
console.log(arr.length); // length는 변하지 않는다.

arr[2] = 'three'; // 원소 추가
console.log(arr);
console.log(arr.length); // length 변환
```

### 3.5 배열의 프로퍼티 열거
- 일반적으로 객체의 프로퍼티를 열거할 때 for in 문을 쓰지만, 배열의 원소들을 취급할 때는 그냥 for문을 쓰는 것을 권장한다.
```javascript
var arr = ['one', 'two'];

arr.arrayName = "arrayName";

console.log(arr.length);

for (var prop in arr) console.log(arr[prop]); // for in 문: 프로퍼티를 포함한 전체 출력
for (var i=0; i<arr.length; i++) console.log(arr[i]); // for 문: 프로퍼티를 제외한 배열 원소만 출력
```

### 3.6 배열 요소 삭제
- delete와 splice가 있다.
- delete는 원소의 값을 undefined로 삭제하지만, 그 원소 자체를 삭제하지는 않는다.
- splice는 원소 자체를 삭제하고 length에도 영향을 미친다.

### 3.7 Array() 생성자 함수
- 배열은 리터럴로 생성하는 것이 일반적이지만, 이는 결국 Array() 생성자 함수로 생성하는 과정을 단순화 시켜놓은 것이다.
```javascript
var arr1 = new Array(3); // 인자 1개만 전달 -> 배열의 길이 결정 print(arr1);
var arr2 = new Array(1, 2, 3); // 인자 1개 이상 전달 -> 초기화 원소들 print(arr2);
```
***
