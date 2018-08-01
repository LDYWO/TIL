# Functional Programming in JavaScript
## 1. 함수형 프로그래밍
- 함수형 프로그래밍은 프로그래밍의 여러 가지 패러다임 중 하나이다.
- 자바스크립트로 함수형 프로그래밍에서 제시하는 방법론 중 일부는 구현 가능하다.
- 하지만, 순수한 함수형 프로그래밍 언어라고 말하지는 않는다.

### 1.1 함수형 프로그래밍의 개념
- **함수형 프로그래밍** 은 함수의 조합으로 작업을 수행함을 의미한다.
- 중요한 것은  **이 작업이 이루어지는 동안 작업에 필요한 데이터와 상태는 변하지 않는다.**
- 아래 코드는 **함수형 프로그래밍** 을 표현하는 수도 코드이다.

```JavaScript
f1 = encrypt1;
f2 = encrypt2;
f3 = encrypt3;

pure_value = "zzoon";

encrypted_value = get_encrypted(f1);
encrypted_value = get_encrypted(f2);
encrypted_value = get_encrypted(f3);
```
- 여기서 ```pure_value```는 작업에 필요한 데이터고 작업이 수행되는 동안 변하지 않는다.
- ```get_encrypted()```가 작업하는 동안 변할 수 있는 것은 오로지 입력으로 들어오는 함수 뿐이다.
- ```f1```, ```f2```, ```f3```는 외부 (여기서는 ```pure_value```)에 아무런 영향을 미치지 않는다.
- 이런 함수를 **순수 함수(Pure Function)** 이라고 부른다.
- ```get_encrypted()``` 함수는 인자로서 ```f1```, ```f2```, ```f3``` 같은 **순수 함수** 를 받는다.
- 이렇게 함수를 또 하나의 값으로 간주하여 함수의 인자 혹은 반환 값으로 사용할 수 있는 함수를 **고계 함수(Higher-Order_Function)** 라고 한다.
- 이 예에서 프로그래머는 입력으로 넣을 암호화 함수를 새롭게 만드는 방식으로 암호화 방법을 개선할 수 있다.
- 이와 같이 **내부 데이터 및 상태는 그대로 둔 채 제어할 함수를 변경 및 조합함으로써 원하는 결과를 얻어내는 것** 이 **함수형 프로그래밍** 의 중요한 특성이다.

### 1.2 자바스크립트에서의 함수형 프로그래밍
- 자바스크립트는 다음을 지원하기 때문에 함수형 프로그래밍이 가능하다.
  - 일급 객체로서의 함수
  - 클로저

```JavaScript
var f1 = function (input) {
    var result;
    // 암호화 작업 수행
    result = 1;
    return result;
};

var f2 = function (input) {
    var result;
    // 암호화 작업 수행
    result = 2;
    return result;
};

var f3 = function (input) {
    var result;
    // 암호화 작업 수행
    result = 3;
    return result;
};

var get_encrypted = function (func) {
    var str = "zzoon";

    return function() {
        return func.call(null, str);
    }
};

var encrypted_value = get_encrypted(f1)();
console.log(encrypted_value); // 1
var encrypted_value = get_encrypted(f2)();
console.log(encrypted_value); // 2
var encrypted_value = get_encrypted(f3)();
console.log(encrypted_value); // 3
```

- 자바스크립트에서 함수는 **일급 객체** 이므로 함수의 인자로 함수를 넘기고, 결과로 함수를 반환할 수도 있다.
- 변수 ```str``` 값이 영향을 받지 않게 하려고 **클로저** 를 사용하였다.
- ```get_encrypted()``` 함수에서 반환하는 익명 함수가 **클로저** 이다.
- 이 **클로저** 에서 접근하는 변수 ```str```은 _자유 변수_ 이며 외부에서 접근할 수 없으므로 **클로저** 로 **함수형 프로그래밍** 의 개념을 정확히 구현할 수 있다.

#### 1.2.1 배열의 각 원소 총합 구하기
- 다음 코드는 일반적으로 배열의 합을 구할 때 작성하는 코드이다.

```JavaScript
function sum(arr) {
    var len = arr.length;
    var i = 0;
    var sum = 0;

    for (; i < len; i++)
        sum += arr[i];

    return sum;
}

var arr = [1, 2, 3, 4];
console.log(sum(arr));
```

- 배열은 원소를 모두 곱한 값을 구하고 싶어졌다.

```JavaScript
function multiply(arr) {
    var len = arr.length;
    var i = 0;
    var result = 0;

    for (; i < len; i++)
        result *= arr[i];

    return result;
}

var arr = [1, 2, 3, 4];
console.log(multiply(arr));
```
- 이 역시 간단하다.
- 이 두 코드는 모두 **명령형 프로그래밍** 방식으로 작성한 코드이다.
- 문제 하나 하나를 각각의 함수를 구현하여 문제를 풀고 있고, 배열의 각 원소를 또 다른 방식으로 산술하여 결고 값을 얻으려면 새로운 함수를 다시 구현해야 한다.
- 이를 함수형 프로그래밍 방식으로 해결할 수 있다.

```JavaScript
function reduce (func, arr, memo) {
    var len = arr.length;
    var i = 0;
    var accum = memo;

    for (; i < len; i++)
        accum = func(accum, arr[i])

    return accum;
}

var sum = function (x, y) {
    return x + y;
};

var multiply = function (x, y) {
    return x * y;
};

var arr = [1, 2, 3, 4];
console.log(reduce(sum, arr, 0));
console.log(reduce(multiply, arr, 1));
```
- 위와 같이 **함수형 프로그래밍** 을 이용하여 코드를 훨씬 간결하게 작성할 수 있다.
- 다른 문제가 나오더라도 사용자가 해당 연산을 하는 함수를 작성하여 ```reduce()``` 함수로 결과를 얻을 수도 있다.
- 이처럼 **함수형 프로그래밍** 은 기존 프로그래밍 방식보다 한 단계 높은 **모듈화** 를 이룰 수 있다.

#### 1.2.2 팩토리얼
- 다음은 팩토리얼을 **명령형 프로그래밍** 방식으로 구현한 코드이다.

```JavaScript
// 일반
function factorial(num) {
    var value = 1;
    for (var i = 2; i <= num; i++)
        value = value * i;
    return value;
}

console.log(factorial(3));

// 재귀
function recursive_factorial(num) {
    if (num === 0)
        return 1;
    else
        return num * recursive_factorial(num - 1);
}

console.log(recursive_factorial(5));
```

- 이 두 예제는 큰 무리 없이 팩토리얼을 구현하지만 이를 **함수형 프로그래밍** 방법으로 구현하여 성능이 향상될 수 있다.
- 먼저 팩토리얼의 특성을 살펴볼 필요가 있다.
- 처음 ```10!```을 실행한 후 ```20!```을 실행할 때는 앞에서 실행한 ```10!```을 중복하여 계산한다.
- 이렇게 중복되는 값, 즉 앞서 연산한 결과를 캐시에 저장하여 사용할 수 있는 함수를 작성한다면 성능 향상에 도움이 된다.

```JavaScript
var factorial = function () {
    var cache = {
      '0': 1
    };

    var func = function (n) {
        var result = 0;

        if (typeof (cache[n]) === 'number')
            result = cache[n];
        else {
            result = n * func(n-1);
            cache[n] = result;
        }

        return result;
    };

    return func;
}();

console.log(factorial(10));
console.log(factorial(20));
```

- 위 예제에서 ```factorial```은 ```cache```에 접근할 수 있는 **클로저** 를 반환 받는다.
- **클로저** 로 숨겨지는 ```cache```에는 팩토리얼을 연산한 값을 저장하고 있고 연산을 수행하는 과정에서 캐시에 저장된 값이 있으면 곧바로 그 값을 반환하고 없으면 계산 후 캐시에 저장하는 방식이다.

***
### 1.3 메모이제이션(Memoization) 패턴
- **메모이제이션** 패턴은 계산된 결과를 함수 프로퍼티 값으로 담아 놓고 나중에 사용하는 패턴이다.

```JavaScript
function Calculate(key, input, func) {
    Calculate.data = Calculate.data || {};

    if (!Calculate.data[key]) {
        var result;
        result = func(input);
        Calculate.data[key] = result;
    }

    return Calculate.data[key];
}

var result = Calculate(1, 5, function(input) {
    return input + input;
});
console.log(result);

var result = Calculate(2, 5, function(input) {
    return input * input / 4;
});
console.log(result);

console.log(Calculate(1));
console.log(Calculate(2));
console.dir(Calculate);
```

- 예제에서 보는 것과 같이 함수 ```Calculate()``` 프로퍼티에 ```data``` 프로퍼티를 만들어 객체를 할당하였다.
- 이곳에 사용자는 자신이 원하는 값을 원하는 키로 저장해 놓을 수 있다.
- ```jQuery``` 에서는 ```data()``` 라는 메소드로 이 **메모이제이션 패턴** 을 사용하였다.
