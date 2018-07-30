# Scope Chain in JavaScript
## 1. 스코프 체인
- 자바스크립트도 다른 언어와 마찬가지로 **스코프**, 즉 **유효 범위**가 있다.
- 이 유효 범위 안에서 변수와 함수가 존재한다.
- 다음은 C 언어의 유효 범위를 확인할 수 있는 코드이다.

```c
void example_scope() {
    int i = 0;
    int value = 1;
    for (i = 0; i < 10; i++) {
        int a = 10;
    }
    printf("a: %d", a); // 컴파일 에러

    if (i == 10) {
        int b = 20;
    }

    printf("b : %d", b); // 컴파일 에러
    printf("value %d", value); // 1
}
```
- C 언어에서는 중괄호로 묶여 있는 범위 안에서 선언된 변수는 블록이 끝나는 순간 사라진다.
- 특히, 함수의 ```{}``` 뿐만 아니라 ```if, for``` 문의 ```{}``` 도 한 블록으로 묶여, 내부 선언된 변수가 밖에서는 접근이 불가능하다.

**하지만, 자바스크립트는 오직 함수만이 유효 범위의 한 단위가 된다.**

### 1.1 스코프 체인
- 유효 범위를 나타내는 스코프가 ```[[scope]]``` 프로퍼티로 각 함수 객체 내에서 연결 리스트 형식으로 관리되는 것
- 각 실행 컨텍스트의 변수 객체가 구성 요소인 리스트이다.
- 각각의 함수는 ```[[scope]]``` 프로퍼티로 자신이 생성된 실행 컨텍스트의 스코프 체인을 참조한다.
- 함수가 실행되는 순간 실행 컨텍스트가 만들어지고, 이 실행 컨텍스트는 실행된 함수의 ```[[scope]]``` 프로퍼티를 기반으로 새로운 스코프 체인을 만든다.

### 1.2 전역 실행 컨텍스트의 스코프 체인
- 각각의 함수는 자신이 생성될 때 ```[[scope]]``` 프로퍼티로 자신이 생성된 실행 컨텍스트의 스코프 체인을 참조한다.

```JavaScript
var var1 = 1;
var var2 = 2;
console.log(var1);
console.log(var2);
```
- 위 예제는 전역 코드이며 함수가 선언되지 않아 함수 호출이 없고, 실행 가능한 코드들만 나열되어 있다.
- 이 자바스크립트 코드를 실행하면, 먼저 전역 실행 컨텍스트가 생성되고, 변수 객체가 만들어진다.
- 이 변수 객체의 스코프 체인은 현재 전역 실행 컨텍스트 단 하나만 실행 되고 있다.
- 따라서, 참조할 상위 컨텍스트가 없으므로 자기 자신만을 가진다.
- 즉, 변수 객체의 ```[[scope]]``` 프로퍼티는 변수 객체 자신을 가리키고 있다.
- 그 후 ```var1```, ```var2``` 와 같은 변수들이 생성되고 이들은 변수 객체에 의해 참조된다.
- 이 변수 객체는 전역 컨텍스트의 변수 객체이므로 이 변수 객체가 곧 전역 객체가 된다.

### 1.3 함수를 호출한 경우 생성되는 실행 컨텍스트의 스코프 체인
```JavaScript
var var1 = 1;
var var2 = 2;

function func() {
    var var1 = 10;
    var var2 = 20;
    console.log(var1); // 10
    console.log(var2); // 20
}

func();
console.log(var1); // 1
console.log(var2); // 2
```
- 이 예제를 실행하면 전역 실행 컨텍스트가 생성되고, ```func()``` 함수 객체가 생성된다.
- 함수가 생성될 때 그 함수 객체의 ```[[scope]]``` 는 현재 실행되는 컨텍스트의 변수 객체의 ```[[scope]]``` 를 그대로 가진다.
- 따라서 ```func``` 함수 객체의 ```[[scope]]``` 는 전역 변수 객체가 된다.
- 이후, 함수를 실행하면 새로운 컨텍스트가 만들어진다.
- 새로 생성된 ```func``` 컨텍스트의 스코프 체인은 실행된 함수의 ```[[scope]]``` 프로퍼티를 그대로 복사한 후, 현재 생성된 변수 객체를 스코프 체인의 맨 앞에 추가한다.
- ```func``` 함수 객체가 ```[[scope]]``` 로 전역 변수 객체 하나만을 가지고 있었으므로, 여기에 ```func``` 변수 객체를 추가한다.
- 즉, ```func``` 실행 컨텍스트의 스코프 체인은 [```func 변수 객체``` - ```전역 객체```] 의 리스트가 된다.

#### 1.3.1 ```[[scope]]```
- 생성된 함수 객체의 ```[[scope]]``` 는 자신을 생성한 컨텍스트의 스코프 체인을 참조한다.
- 함수의 ```[[scope]]``` 프로퍼티는 차후 함수 실행으로 생성된 새로운 컨텍스트 스코프 체인에 의해 참조된다.
- 새로운 컨텍스트의 스코프 체인은 이 함수의 ```[[scope]]``` 프로퍼티의 복사본에 자신의 변수 객체를 그 앞에 추가하는 방법으로 만들어진다.

#### 1.3.2 스코프 체인
- 각 함수 객체는 ```[[scope]]``` 프로퍼티로 현재 컨텍스트의 스코프 체인을 참조한다.
- 한 함수가 실행되면 새로운 실행 컨텍스트가 만들어지는데, 이 새로운 실행 컨텍스트는 현재 실행되는 함수 객체의 ```[[scope]]``` 프로퍼티를 그대로 복사한 뒤 그 체인의 제일 앞에 새롭게 생성된 변수 객체를 추가하는 방법으로 자신의 스코프 체인을 만든다.
- 요약하면, ```스코프 체인 = 현재 실행 컨텍스트의 변수 객체 + 상위 컨텍스트의 스코프 체인``` 이라고 할 수 있다.

### 예제 1
- 다음 두 예제의 차이점을 명확히 알아두도록 합니다.

```JavaScript
var value = "value1";

function printFunc() {
    var value = "value2";

    function printValue() {
        return value;
    }

    console.log(printValue()); // value2
}

printFunc();
```
#### 실행 절차
> 1. 전역 실행 컨텍스트 생성
>  2. 전역 변수 ```value``` 생성
>  3. 함수 ```printFunc``` 생성
>  4. 전역 변수 ```value```를 ```"value1"``` 로 초기화
>  5. ```printFunc()``` 실행
>      1. ```printFunc``` 실행 컨텍스트 생성
>      2. ```printFunc``` 내 변수 ```value``` 생성
>      3. ```printFunc``` 내 ```printValue``` 함수 생성
>          1. 생성된 함수 ```printValue``` 의 ```[[scope]]```는 현재 실행중인 ```printFunc``` 컨텍스트의 스코프 체인을 참조
>      4. ```printFunc``` 내 변수 ```value```를 ```"value2"```로 초기화
>      5. ```printFunc``` 내 ```printValue()``` 실행
>          1. ```printValue``` 실행 컨텍스트 생성
>          2. ```printVlaue``` 내 ```return value;``` 실행
>          3. ```"value2"```를 반환하고 ```printValue``` 실행 컨텍스트 종료
>      6. ```console.log("vlaue2");``` 을 실행하고 ```printFunc``` 실행 컨텍스트 종료
>  6. 전역 실행 컨텍스트 종료

### 예제 2
```JavaScript
var value = "value1";

function printValue() {
    return value;
}

function prntFunc(func) {
    var value = "value2";
    console.log(func());
}

printFunc(printValue); // value1
```

#### 실행 절차
> 1. 전역 실행 컨텍스트 생성
>  2. 전역 변수 ```value``` 생성
>  3. 함수 ```printValue``` 생성
>      1. 생성된 함수 ```printValue```의 ```[[scope]]```는 현재 실행 중인 전역 실행 컨텍스트의 스코프 체인을 참조
>  4. 함수 ```printFunc``` 생성
>      1. 생성된 함수 ```printValue```의 ```[[scope]]```는 현재 실행 중인 전역 실행 컨텍스트의 스코프 체인을 참조
>  5. 전역 변수 ```value```를 ```"value1"```로 초기화
>  6. ```printFunc()``` 실행
>      1. ```printFunc``` 실행 컨텍스트 생성
>      2. ```printFunc``` 내 변수 ```value``` 생성
>      3. ```printFunc``` 내 변수 ```value```를 ```"value2"```로 초기화
>      4. ```func()(printValue())``` 실행
>          1. ```printValue``` 실행 컨텍스트 생성
>          2. ```return value;``` 실행
>          3. ```"value1"```을 반환하고 ```printValue``` 실행 컨텍스트 종료
>  7. 전역 실행 컨텍스트 종료

- 이렇게 만들어진 스코프 체인으로 식별자 인식이 이루어진다.
- 식별자 인식은 스코프 체인의 첫 번째 변수 객체부터 시작하여, 식별자와 대응되는 이름을 가진 프로퍼티가 있으면 이를 참조하고, 없으면 다음 변수 객체로 이동하여 찾는다.
- 여기서 ```this```는 식별자가 아닌 키워드로 분류되므로, 스코프 체인의 참조 없이 접근할 수 있다.

***
## 2. 클로저
### 2.1 클로저의 개념
```JavaScript
function outerFunc() {
    var x = 10;
    var innerFunc = function() {
        console.log(x);
    }
    return innerFunc;
}

var inner = outerFunc();
inner(); // 10
```
- 즉, 클로저란 **이미 생명 주기가 끝난 외부 함수의 변수를 참조하는 함수이다.**
- 따라서, 예제에서 ```outerFunc```에서 선언된 지역 변수 ```x```를 참조하는 ```innerFunc```가 클로저가 되고, 클로저로 참조되는 **외부변수**, 즉 ```x```를 **자유변수** 라고 한다.

```JavaScript
function outerFunc() {
    var x = 1;

    return function() {
        // x와 arguements를 활용한 로직
    };
}

var newFunc = outerFunc();
newFunc();
```
- 다음은 자바스크립트로 **클로저**를 구현하는 전형적인 패턴이다.

```JavaScript
function outerFunc (arg1, arg2) {
    var local = 1;

    function innerFunc(innerArg) {
        console.log(arg1 + arg2 + local + innerArg);
    }
    return innerFunc;
}

var inner = outerFunc(1, 1);
inner(1); // 4
```
- 예제에서는 ```outerFunc()``` 함수를 호출하고 반환되는 함수 객체인 ```innerFunc()```가 **inner**로 참조된다.
- 이것은 ```inner(n)```의 형태로 실행될 수 있다.
- 여기서 ```outerFunc()```가 실행되면서 생성되는 변수 객체가 **스코프 체인**에 들어가게 된다.
- 이 스코프 체인은 ```innerFunc```의 **스코프 체인**으로 참조된다.
- 즉, ```outerFunc``` 변수 객체는 ```outerFunc()``` 함수가 종료되었지만, 여전히 ```innerFunc(내부함수)```의 ```[[scope]]```로 참조되므로 가비지 컬렉션의 대상이 되지 않고 살아남는다.
- 이 ```outerFunc``` 변수 객체의 프로퍼티 값은 여전히 읽기 및 쓰기까지 가능하다.

### 2.2 클로저의 활용
- 클로저의 개념을 이해하였다면, 이 클로저를 어떻게 활용할 것인지 고민해야 한다.
- 클로저는 성능적인 면과 자원적인 면에서 약간 손해를 볼 수 있으므로 무차별적으로 사용해서는 안된다.
- 클로저를 잘 활용하는 좋은 자바스크립트 개발자가 되려면 많은 개발 경험을 쌓는 것이 가장 좋은 방법이다.

#### 2.2.1 특정 함수에서 사용자가 정의한 객체의 메소드 연결하기
```JavaScript
function HelloFunc(func) {
    this.greeting = "hello";
}

HelloFunc.prototype.call = function (func) {
    func ? func(this.greeting) : this.func(this.greeting);
};

var userFunc = function (greeting) {
    console.log(greeting);
};

var objHello = new HelloFunc();
objHello.func = userFunc;
objHello.call();
```
- 함수 ```HelloFunc```는 ```greeting``` 변수가 있고, ```func``` 프로퍼티로 참조되는 함수를 ```call()```함수로 호출한다.
- 사용자는 ```func``` 프로퍼티에 자신이 정의한 함수를 참조시켜 호출할 수 있다.
- 다만, ```HelloFunc.prototype.call()``` 을 보면 알 수 있듯이 자신의 지역 변수인 ```greeting``` 만을 인자로 사용자가 정의한 함수에 넘겨준다.
- 사용자는 ```userFunc()``` 함수를 정의하여 ```HelloFunc.func()``` 에 참조시킨 뒤, ```HelloFunc()```의 지역 변수인 ```greeting```을 화면에 출력 시킨다.
- 이 예제에서 ```HelloFunc()``` 는 ```greeting``` 만을 인자로 넣어 사용자가 인자로 넘긴 함수를 실행시킨다.
- 그래서 사용자가 정의한 함수도 한 개의 인자를 받는 함수를 정의할 수 밖에 없다.
- 여기서 사용자가 원하는 인자를 더 넣어서 ```HelloFunc()``` 를 이용하여 호출하려면 어떻게 해야할까?

```JavaScript
function HelloFunc(func) {
    this.greeting = "hello";
}

HelloFunc.prototype.call = function (func) {
    func ? func(this.greeting) : this. func(this.greeting);
};

var userFunc = function (greeting) {
    console.log(greeting);
};

var objHello = new HelloFunc();
objHello.func = userFunc;

// 새로운 함수 newObj()는 HelloFunc()의 객체를 좀 더 자유롭게 활용하려고 정의한 함수이다.
// 첫 번째 인자로 받는 obj는 HelloFunc()의 객체가 되고, 두 번째 인자 name은 사용자가 출력을 원하는 사람 이름이 된다.
// 첫 번째 인자 obj의 func 프로퍼티에 saySomething() 함수에서 반환되는 함수를 참조하고, 이 객체를 그대로 반환한다
// 즉, obj1은 인자로 넘겼던 objHello 객체에서 func 프로퍼티에 참조된 함수만 바뀐 객체가 된다.
function newObj(obj, name) {
    obj.func = saySomething(this, 'who', name);
    return obj;
}

// 첫 번째 인자: newObj 객체 - obj1
// 두 번째 인자: 사용자가 정의한 메소드 이름 - "who"
// 세 번째 인자: 사용자가 원하는 사람 이름 값 - "zzoon"

// 반환: 사용자가 정의한 newObj.prototype.who() 함수를 반환하는 helloFunc()의 func 함수
// 반환되는 함수 HelloFunc가 원하는 function(greeting) {} 형식의 함수가 되는데,
// 이것이 HelloFunc 객체의 func 프로퍼티 참조된다.
// obj.call()로 실행되는 것은 실질적으로 newObj.prototype.who()가 된다.
function saySomething(obj, methodName, name) {
    return (function (greeting)) {
        return obj[methodName](greeting, name);
    });
}

newObj.prototype.who = function (greeting, name) {
    console.log(greeting + " " + (name || "everyone"));
};

var obj = new newObj (objHello, "zzoom");
obj.call(); // obj.func, 즉 newObj.prototype.who 함수 호출 --> hello zzoon
```

- 이와 같은 방식으로 사용자는 자신의 객체 메서드인 ```who``` 함수를 ```HelloFunc```에 연결시킬 수 있다.
- 여기서 클로저는 ```saySomething()``` 에서 반환되는 ```function(greeting) {}```이 되고, 이 클로저는 자유 변수 ```obj```, ```methodName```, ```name```을 참조한다.
- 앞 예제는 정해진 형식의 함수를 콜백해주는 라이브러리가 있을 경우, 그 정해진 형식과는 다른 형식의 사용자 정의 함수를 호출할 때 유용하게 사용된다.
- 예를 들어 브라우저에서는 ```onclick``` 같은 프로퍼티에 해당 이벤트 핸들러를 사용자가 정의해 놓을 수가 있는데, 이 이벤트 핸들러의 형식은 ```function(event) {}``` 이다.
- 이를 통해 브라우저는 발생한 이벤트를 event 인자로 사용자에게 넘겨주는 방식이다.
- 여기에 event 외의 원하는 인자를 더 추가한 이벤트 핸들러를 사용하고 싶을 때, 앞과 같은 방식으로 클로저를 적절히 활용해 줄 수 있다.

#### 2.2.2 함수의 캡슐화
- 다음과 같은 함수를 작성한다고 가정해보자
- ```"I am XXX. I live in XXX. I am XX years old"``` 라는 문장을 출력하는데,
- XX 부분은 사용자에게 인자로 받아 값을 출력하는 함수
- 가장 먼저 생각할 수 있는 것은 앞 문장 템플릿을 전역 변수에 저장하고, 사용자의 입력을 받은 후, 이 전역 변수에 접근하여 완성된 문장을 출력하는 방식으로 함수를 작성하는 것이다.

```JavaScript
var buffArr = [
    'I am ',
    '',
    ', I live in ',
    '',
    ', I am ',
    '',
    ' years old.'
];

function getCompletedStr(name, city, age) {
      buffArr[1] = name;
      buffArr[3] = city;
      buffArr[5] = age;
      return buffArr.join('');
}

var str = getCompletedStr('zzoon', 'seoul', 16);
console.log(str);
```

- 하지만 위의 예제에는 단점이 있다.
- 바로 ```bufArr```라는 배열은 전역 변수로서, 외부에 노출되어 있다는 점이다.
- 이는 다른 함수에서 쉽게 이 배열에 접근하여 값을 바꿀 수도 있고, 같은 이름의 변수를 만들어 버그가 생길 수도 있다.
- 따라서 **클로저**를 활용하여 ```buffArr```을 추가적인 스코프에 넣고 사용하게 되면 이 문제를 해결할 수 있다.

```JavaScript
var getCompletedStr = (function () {
  var buffArr = [
      'I am ',
      '',
      ', I live in ',
      '',
      ', I am ',
      '',
      ' years old.'
  ];

return (function (name), city, age) {
      buffArr[1] = name;
      buffArr[3] = city;
      buffArr[5] = age;
      return buffArr.join('');
  });
})(); // 즉시 실행 함수 --> getCompletedStr에는 리턴된 함수가 할당

var str = getCompletedStr('zzoon', 'seoul', 16);
console.log(str);
```
- 위 예제에서 가장 먼저 주의해서 봐야 할 점은 변수 ```getCompletedStr```에 익명의 함수를 즉시 실행시켜 반환되는 함수를 할당하는 것이다.
- 이 반환되는 함수가 **클로저**가 되고, 이 클로저는 자유 변수 ```buffArr```을 스코프 체인에서 참조할 수 있다.

#### 2.2.3 setTimeout()에 지정되는 함수의 사용자 정의
- ```setTimeout``` 함수는 웹 브라우저에서 제공하는 함수인데, 첫 번째 인자로 넘겨지는 함수 실행의 스케줄링을 할 수 있다.
- 두 번째 인자인 밀리 초 단위 숫자 만큼의 시간 간격으로 해당 함수를 호출한다.
- ```setTimeout()```으로 자신의 코드를 호출하고 싶다면 첫 번째 인자로 해당 함수 객체의 참조를 넘겨주면 되지만, 이것으로는 실제 실행될 때 함수에 인자를 줄 수 없다.
- 그렇다면 자신이 정의한 함수에 인자를 넣어줄 수 있게 하려면 어떻게 해야 할까? **클로저**

```JavaScript
function callLater(obj, a, b) {
    return (function() {
        obj["sum"] = a + b;
        console.log(obj["sum"]);
    })
}

var sumObj = {
    sum: 0
};

var func = callLater(sumObj, 1, 2);
setTimeout(func, 500);
```
- 사용자가 정의한 함수 ```callLater```를 ```setTimeout``` 함수로 호출하려면, 변수 ```func```에 함수를 반환받아 ```setTimeout()``` 함수의 첫 번째 인자로 넣어주면 된다.
- 이 때 **클로저**는 반환받은 함수고 ```obj```, ```a```, ```b```가 자유 변수가 된다.

### 2.3 클로저를 활용할 때 주의 사항
- **클로저**는 자바스크립트의 강력한 기능이지만, 너무 남발하여서는 안된다.
- **클로저**에서 사용자가 쉽게 간과할 수 있는 사항을 정리 해본다.

#### 2.3.1 클로저의 프로퍼티 값이 쓰기 가능하므로 그 값이 여러 번 호출로 항상 변할 수 있음에 유의해야 한다.
```JavaScript
function outerFunc(argNum) {
    var num = argNum;
    return function(x) {
        num += x;
        console.log('num: ' + num);
    }
}

var exam = outerFunc(40);
exam(5);
exam(-10);
```
- 자유 변수 ```num```은 참조가 가능한데, 읽기 뿐만 아니라 쓰기까지 가능하다.
- ```exam``` 값을 호출할 때 마다, 자유 변수 ```num```의 값은 계속해서 변화하니 주의해야 한다.

#### 2.3.2 하나의 클로저가 여러 함수 객체의 스코프 체인에 들어가 있는 경우도 있다.
```JavaScript
function func() {
    var x = 1;
    return {
        func1: function () {
            console.log(++x);
        },
        func2: function () {
            console.log(-x);
        }
    }
}

var exam = func();
exam.func1();
exam.func2();
```
- 위 예제에서 반환되는 두 객체에는 두 개의 함수가 정의되어 있는데, 두 함수 모두 자유 변수 x를 참조한다.
- 각각의 함수가 호출될 때마다 x 값이 변화하므로 유의해야 한다.

#### 2.3.3 루프 안에서 클로저를 활용할 때는 주의하자
```JavaScript
function countSeconds(howMany) {
    for (var i = 1; i <= howMany; i++) {
        setTimeout(function() {
              console.log(i);
          }, i*1000);
    }
}

countSeconds(3);
```
- 이 예제는 1, 2, 3을 1초 간격으로 출력하는 의도로 만든 예제이나 결과는 4가 연속 3번 1초 간격으로 출력된다.
- **클로저**를 잘 이해했다면, 이유를 쉽게 이해할 수 있을 것이다.
- ```setTimeout``` 함수의 인자로 들어가는 함수는 자유 변수 ```i```를 참조한다.
- 하지만 이 함수가 실행되는 시점은 ```countSeconds()``` 함수의 실행이 종료된 이후이고, i 값은 이미 4가 된 상태이다.
- 원하는 결과를 얻기 위한 코드는 다음과 같다.

```JavaScript
function countSeconds(howMany) {
    for (var i = 1; i <= howMany; i++) {
        (function(currentI) {
            setTimeout(function() {
                console.log(current);
              }, currentI * 1000);
          })(i);
    }
}

countSeconds(3);
```
- 즉시 실행 함수를 실행시켜 루프 ```i``` 값을 ```currentI```에 복사해서 ```setTimeout()```에 들어갈 함수에 사용하면 원하는 결과를 얻을 수 있다.
