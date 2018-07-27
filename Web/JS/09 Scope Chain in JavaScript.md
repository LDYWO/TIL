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
