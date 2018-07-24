# Function in JavaScript
## 1. Function Object: 함수 객체
### 1.1 Function Object in JS
- **JavaScript**에서는 함수도 **객체**이다.
- 함수의 기본 기능인 **코드 실행** 기능을 가진다.
- 프로퍼티들을 가질 수 있는 등 **객체의 성질**도 가진다.
```JavaScript
var add = function(x, y) {
    return x + y;
};

add.result = add(3, 2);
add.status = 'OK';

print(add.result);
print(add.status);
console.dir(add);
```

### 1.2 Function Value in JS
- **JavaScript**에서 함수는 값으로 취급된다.
- **함수가 객체**이므로, 다음과 같은 동작들이 가능하다.
  - **리터럴**에 의해 생성
  - **변수나 배열의 요소, 객체의 프로퍼티** 등에 할당 가능
  - 함수의 **인자**로 전달 가능
  - 함수의 **리턴** 값으로 리턴 가능
  - **동적으로 프로퍼티를 생성** 및 할당 가능
- 이와 같은 기능으로 함수를 **일급 객체**라 부른다.
- **일급 객체인 함수**의 특성을 이용하여 **함수형 프로그래밍**이 가능하다.

#### 1.2.1 변수나 프로퍼티 값으로 할당
 - 함수는 값으로 취급될 수 있으므로 **변수나 다른 객체의 프로퍼티의 값**으로 할당할수 있다.
 - **함수 표현식**은 이와 같은 성질을 이용한 것이다.
```JavaScript
var foo = function() {
    return 100;
};

print(foo());

var obj = {};
obj.func = foo;
print(obj.func());
```
#### 1.2.2 함수 인자로 전달
- 함수는 다른 함수의 인자로도 전달이 가능하다.
```JavaScript
// 함수를 인자로 전달받아 그 함수를 실행시키는 함수 foo
var foo = function (func) {
    func();
}

// 익명 함수
foo (function () {
    print("Function can be used as the argument");
});
```
#### 1.2.3 리턴 값으로 활용
- 함수는 다른 함수의 리턴 값으로도 활용할 수 있다.
```JavaScript
var foo = function () {
    return function() {
        print ("This is function is the return value");
    }
};

var func = foo();
func();

foo()();
```
## 1.3 Property of Function Object
- 함수는 **객체**이다.
- **기본 객체 성질** + **정의된 코드를 실행**하는 기능
- 함수 객체만의 **표준 프로퍼티**가 정의되어 있다.

```JavaScript
function add(x, y) {
    return x + y;
}

console.dir(add);
```
- **arguments, caller, length** 등의 프로퍼티가 있다.
- **ECMA 표준**: prototype, length
- **ECMA 표준 X**: name(함수 이름), caller(이 함수를 호출한 함수), arguments(함수를 호출할 때 전달한 인자 값), **proto**(자신의 부모 역할을 하는 프로토 타입 객체를 가리킴)

#### 1.3.1 _ __proto___
- **객체의 부모 역할**을 하는 프로토 타입 객체를 ```Function.prototype``` 객체라고 한다.
- 이 또한 **함수 객체**이고, 이것의  __ _proto_ __는 ```Object.prototype``` 이다.
- ```Function.prototype```에는 **apply, call** 등의 메소드가 포함되어 있다.

#### 1.3.2 length 프로퍼티
- ECMA 표준에서 정의한 모든 함수가 가져야 하는 표준 프로퍼티
- 함수가 정상적으로 실행될 때 기대되는 인자의 개수 (함수를 작성할 때 정의한 인자 개수)
```JavaScript
function func0() {
  return;
}

function func1(x) {
  return x;
}

function func2(x, y) {
  return x + y;
}

print(func0.length); // 0
print(func1.length); // 1
print(func2.length); // 2
```

#### 1.3.3 prototype 프로퍼티
- 모든 함수는 객체로서 prototype 프로퍼티를 가지고 있다.
- **함수 객체의 prototype 프로퍼티** 와 **모든 객체의 부모를 나타내는 내부 프로퍼티** __proto__를 혼동하면 안된다.
- **prototype 프로퍼티**는 함수가 생성될 때 만들어지며, **constructor 프로퍼티 하나만 있는 객체**를 가리킨다.
- **자바스크립트**에서는 함수를 생성할 때 **자신과 연결된 프로토타입 객체를 동시에 생성**하며, 이 둘은 각각 **prototype**과 **constructor**라는 프로퍼티를 서로 참조한다.

```JavaScript
function myFunction() {
    return;
}

console.dir(myFunction.prototype);
// constructor(ex 함수의 프로토타입 객체이므로)와 __proto__(프로토 타입 객체 역시 객체이므로 예외 없이 자신의
// 부모의 역할을 하는 __proto__ 프로퍼티가 있다.)
console.dir(myFunction.prototype.constructor);
// 프로토 타입 객체와 매핑된 함수를 알아볼 수 있다.
```
- myFunction 함수 객체의 prototype 프로퍼티: myFunction.prototype (프로토타입 객체)
- myFunction.prototype (프로토타입 객체)의 constructor 프로퍼티: myFunction 함수 객체

***
## 2. 함수의 다양한 형태
### 2.1 콜백 함수
- **익명 함수**는 대표적인 용도가 **콜백 함수**이다.
- 개발자는 명시적으로 함수를 호출하는 것이 아닌 **단지 함수를 등록**한다.
- **어떤 이벤트가 발생**했거나 **특정 시점에 도달**했을 때 **시스템에서 호출되는 함수**
- 특정 함수의 인자로 넘겨서 **코드 내부에서 호출**되는 함수
```JavaScript
window.onload = function () {
    alert("Loaded");
};
```
### 2.2 즉시 실행 함수
- **함수를 정의함과 동시에 바로 실행**하는 함수
- **익명 함수**이므로 **같은 함수를 다시 호출할 수 없다.**
- **최초 한번의 실행만을 필요**로 하는 **초기화 코드 부분에 사용**한다.
```JavaScript
(function (arg) {
    print(arg);
})("init");
```
- jQuery 등의 라이브러리는 처음부터 끝까지를 익명 함수로 묶여져 있다.
- 즉시 실행 함수를 이용하는 이유는 자바스크립트의 함수 유효 범위 지원 때문이다.
- 기본적으로 자바스크립트는 변수를 선언하면 프로그램 전체에서 접근할 수 있는 전역 유효 범위를 가진다.
- 그러나 함수 내에서 정의된 매개변수와 변수들은 함수 코드 내부에서만 유효하다.
- 함수 외부의 코드에서 함수 내부의 변수를 액세스하는게 불가능하다.
- 여러 라이브러리를 사용할 때 라이브러리들은 즉시 실행 함수로 전체 라이브러리를 감싸 놓는다.

### 2.3 내부 함수
- 자바스크립트에서는 함수 코드 내부에서도 다시 함수를 정의할 수 있다.
- 내부 함수는 클로저를 생성한다.
- 부모 함수 코드에서 외부에서의 접근을 막고 독립적인 헬퍼 함수를 구현하는 용도로 쓰인다.
``` JavaScript
function parent() {
    var a = 100;
    var b = 200;

    function child() {
        var b = 300;

        console.log(a); // 부모 함수의 a에 접근 100
        console.log(b); // 자신의 b에 접근 300
    }
    child(); // 내부 함수이므로 부모 함수에서 접근 가능
}

parent();
child(); // error! --> 내부 함수이므로 외부에서 접근 불가능
```
- 스코프 체이닝: 내부 함수는 자신을 둘러싼 외부 함수의 변수에 접근이 가능하다.
- 내부 함수는 일반적으로 자신이 정의된 부모 함수 내부에서만 호출이 가능하다.
- 하지만 함수 외부에서도 특정 함수 스코프 안에 선언된 내부 함수를 호출할 수 있다.
- 이는 부모 함수에서 내부 함수를 외부로 리턴하는 방식을 사용할 수 있다.
```JavaScript
function parent() {
    var a = 100;
    var child = function () {
        console.log(a);
    }

    return child;
}

var inner = parent(); // child 함수 리턴
inner(); // child 함수 실행
```
- 이와 같이 실행이 끝난 parent()와 같은 부모 함수 스코프의 변수를 찾아 참조하는 inner()와 같은 함수를 **클로저**라 한다.

### 2.4 함수를 리턴하는 함수
- 함수는 일급 객체이기 때문에 일반 값처럼 함수 자체를 리턴할 수 있다.
- 이 특성을 활용해 함수를 호출함과 동시에 다른 함수로 바꾸거나, 자기 자신을 재정의하는 함수를 구현할 수 있다.
```JavaScript
var self = function () {
    print ('a');
    return function () {
        print ('b');
    };
};

self = self(); // 함수를 호출함과 동시에 다른 함수로 바꾸고, 재 정의
               // 'a'를 출력한 뒤 리턴된 'b'를 프린트하는 함수를 self에 재 정의한다.
self(); // b가 출력된다.
```
***
## 3. 함수 호출과 this
### 3.1 arguments 객체
- 자바스크립트에서는 함수를 호출할 때 함수 형식에 맞춰 인자를 넘기지 않더라도 에러가 발생하지 않는다.
```JavaScript
function func (arg1, arg2) {
    console.log(arg1, arg2);
}

func(); // undefined, undefined
func(1); // 1, undefined
func(1, 2); // 1, 2
func(1, 2, 3); // 1, 2, 3
```
- 정의된 함수의 인자보다 적게 함수를 호출했을 경우, 넘겨지지 않은 인자에는 undefined가 할당된다.
- 정의된 함수의 인자보다 많게 함수를 호출했을 경우, 초과된 인자는 무시된다.
- 자바스크립트의 이런 특성 때문에, 런타임 시에 호출된 인자의 개수를 확인하고 이에 따라 동작을 다르게 해줘야 할 경우가 있다.
- 이를 가능하게 하는 것이 **argument** 객체이다.
- **argument** 객체는 함수를 호출할 때 넘긴 인자들이 배열 형태로 저장된 객체를 의미한다.
- 이는 유사 배열 객체(length 프로퍼티를 가진 객체)이다.
```JavaScript
function add(a, b) {
    console.dir(arguments);
    return a + b;
}

console.dir(add); // add.length = 2
console.log(add()); // add.arguments.length = 0
console.log(add(1)); // add.arguments.length = 1
console.log(add(1, 2)); // add.arguments.length = 2
console.log(add(1, 2, 3)); // add.arguments.length = 3
```
- arguments 객체는 매개변수 개수가 정확하게 정해지지 않은 함수를 구현하거나
- 전달된 인자의 개수에 따라 서로 다른 처리를 해줘야 하는 함수를 개발할 때 유용하게 사용할 수 있다.

```JavaScript
function sum() {
    var ret = 0;
    if (arguments.length < 2)
      ret = 'insert more than 1 numbers';
    else {
        for (index in arguments)
            ret += arguments[index];
    }

    return ret;
}

console.log(sum()); // insert more than 1 numbers
console.log(sum(1)); // insert more than 1 numbers
console.log(sum(1, 2)); // 3
console.log(sum(1, 2, 3)); // 6
console.log(sum(1, 2, 3, 4, 5, 6, 7, 8, 9)); // 45
```
### 3.2 호출 패턴과 this 바인딩
- 자바스크립트에서 함수를 호출할 때, 기존 매개 변수로 전달되는 인자 값에 더해 arguments와 this가 전달된다.
- this를 이해하기 어려운 이유는 자바스크립트의 여러가지 함수가 호출되는 방식(호출 패턴)에 따라 this가 다른 객체를 참조 (this 바인)하기 때문이다.
- 여러가지 함수 호출 패턴과 각 패턴에 따라 this가 어떤 객체에 바인딩 되는지 숙지해야 한다.
#### 3.2.1 객체의 메소드 호출할 때 this 바인딩
- 메소드 내부 코드에서 사용된 **this는 해당 메소드를 호출한 객체로 바인딩**된다.
```JavaScript
var myObj = {
    name: 'foo',
    sayName: function () {
        console.log(this.name);
    }
};

var otherObj = {
    name: 'bar'
};

otherObj.sayName = myObj.sayName;

myObj.sayName();
otherObj.sayName();
```
- myObj와 otherObj는 둘 다 각각 name과 sayName이라는 프로퍼티가 있다.
- sayName 메소드에서 사용된 this는 자신을 호출한 객체에 바인딩된다.

#### 3.2.2 함수를 호출할 때 this 바인딩
- 자바스크립트에서는 함수를 호출하면, 해당 함수 내부 코드에서 사용된 **this는 전역 객체에 바인딩**된다.
- 브라우저에서 자바스크립트를 실행하는 경우 전역 객체는 **window** 객체가 된다.
```JavaScript
var foo = "I'm foo";

console.log(foo);
console.log(this.foo);
console.log(window.foo);
```
- 함수를 호출할 때 this는 전역 객체에 바인딩된다.
```JavaScript
var foo = "I'm foo";
console.log(window.foo);

var sayFoo = function() {
    console.log(this.foo);
};

sayFoo();
```
- 하지만, 이러한 함수 호출에서의 this 바인딩 특성은 내부 함수를 호출했을 경우에도 적용되므로, 내부 함수에서 this를 이용할 때는 주의해야 한다.
```JavaScript
var value = 100;

var myObj = {
    value: 1,
    func1: function () {
      this.value += 1;
      console.log('func1() called. this.value : ' + this.value);
      // func1은 myObj의 메소드이므로 메소드를 호출한 객체에 this가 바인딩 되어 myObj의 프로퍼티인 1에서 +1한 2가 출력

    func2 = function () {
        this.value += 1;
        console.log('func2() called. this.value : ' + this.value);
        // func2는 내부 함수로 메소드가 아니다. 메소드가 아닌 일반 함수의 this는 전역 객체에 바인딩 되므로
        // 전역 객체 100에서 +1 한 101이 출력된다.

        func3 = function () {
            this.vlaue += 1;
            console.log('func3() called. this.value : ' + this.value);
            // func3도 내부 함수로 메소드가 아니다. 그러므로 this는 전역객체에 바인딩되며 101 + 1인 102가 출력된다.
        };
        func3();
    };
    func2();
    }
};

myObj.func1();
```
- 자바스크립트에서는 내부 함수 호출 패턴을 정의해 놓지 않았다.
- 그러므로 내부 함수를 호출하면 this는 전역 객체에 바인딩 된다.
- 이렇게 내부 함수가 this를 전역 객체로 참조하는 자바스크립트의 한계를 극복하려면 부모함수의 this를 내부함수가 접근 가능한 다른 변수에 저장하는 방법을 사용한다.
