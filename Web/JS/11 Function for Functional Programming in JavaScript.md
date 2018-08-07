# Function for Functional Programming in JavaScript
## 1. 자바스크립트에서의 함수형 프로그래밍을 활용한 주요 함수
- 이 장에서는 자바스크립트에서 함수형 프로그래밍을 사용한 여러 가지 함수를 소개한다.
- 이를 통해 자바스크립트를 이용한 함수형 프로그래밍의 감을 잡아 나가자.

### 1.1 함수 적용
- ```Function.prototype.apply``` 함수로 함수 호출을 수행할 수 있다.
- 왜 이름이 우리에게 익숙한 **call** 이 아니라 **apply** 일까?
- **함수 적용(Applying Functions)** 은 함수형 프로그래밍에서 사용하는 용어이다.
- 함수형 프로그래밍에서는 특정 데이터를 여러 가지 함수를 적용시키는 방식으로 작업을 수행한다.
- 여기서 함수는 단순히 입력을 넣고 출력을 받는 기능을 수행하는 것 뿐만 아니라, 인자 혹은 반환 값으로 전달된 함수를 특정 데이터에 적용시키는 개념으로 이해해야 한다.
- 따라서 ```func.apply(Obj, Args)``` 와 같은 함수 호출을 ```"func 함수를 Obj 객체와 Args 인자 배열에 적용시킨다."``` 라고 표현한다.

### 1.2 커링
- 특정 함수에서 정의된 인자의 일부를 넣어 고정시키고, 나머지를 인자로 받는 새로운 함수를 만드는 것을 의미한다.

```JavaScript
function calculate(a, b, c) {
    return a * b * c;
}

function curry(func) {
    var args = Array.prototype.slice.call(arguments, 1);

    return function() {
        return func.apply(null, args.concat(Array.prototype.slice.call(arguments)));
    }
}

var new_func1 = curry(calculate, 1); // args = [1]
console.log(new_func1(2, 3)); // calculate(1, 2, 3)
var new_func2 = curry(calculate, 1, 3); // args = [1, 3]
console.log(new_func2(3)); // calculate(1, 3, 3)
```

- ```calculate()``` 함수는 인자 세 개를 전달받아 연산을 수행한다.
- 여기서 ```curry()``` 함수로 **첫 번째 인자를 1로 고정** 시킨 뒤 새로운 함수 ```new_func1()```과 첫 번째, 두 번째 인자를 1과3으로 고정시킨 ```new_func2()``` 함수를 새롭게 만들 수 있다.
- ```curry()``` 함수는 넘어온 인자를 ```args```에 담아 놓고, 새로운 함수 호출로 넘어온 인자와 합쳐서 함수를 적용한다.
- 이러한 **커링** 은 **함수형 프로그래밍 언어에서 기본적으로 제공하지만 자바스크립트에서는 제공하지 않는다.**
- 다음과 같이 ```Function.prototype```에 **커링 함수를 정의하여 사용** 할 수 있다.

```JavaScript
Function.prototype.curry = function() {
    var fn = this;
    var args = Array.prototype.slice.call(arguments);

    return function() {
        return fn.apply(this, args.concat(Array.prototype.slice.call(arguments)));
    };
}
```

- 예제에서 첫 번째 인자와 세 번째 인자를 고정시키고 싶다면 어떻게 해야할까?

```JavaScript
function calculate(a, b, c) {
    return a + b + c;
}

function curry2(func) {
    var args = Array.prototype.slice.call(arguments, 1);

    return function() {
        var arg_idx = 0;
        for (var i = 0; i < args.length && arg_idx < arguments.length; i++) {
            if (args[i] === undefined)
                args[i] = arguments[arg_idx++];
        }

        return func.apply(null, args);
    }
}

var new_func = curry2(calculate, 1, undefined, 4); // args = [1, undefined, 4]
console.log(new_func(3)); // calculate(1, 3, 4)
```

- ```curry2()``` 함수를 사용할 때는 ```calculate()``` 함수가 원하는 인자를 전부 넣어주되, 그 중에서 고정시키지 않을 인자를 ```undefined``` 로 넘기면 된다.
- 이와 같이 함수를 부분적으로 적용하여 새로운 함수를 반환받는 방식을 **함수의 부분 적용(Partially Applying Functions)** 이라고 한다.

### 1.3 bind

```JavaScript
Function.prototype.bind = function (thisArg) {
    var fn = this;
    var slice = Array.prototype.slice;
    var args = slice.call(arguments, 1);
    return function() {
        return fn.apply(thisArg, args.concat(slice.call(arguments)))
    }
}
```

- 앞서 설명된 ```curry()``` 함수와 상당히 유사하다.
- ```커링``` 과 같이 사용자가 고정시키고자 하는 인자를 ```bind()``` 함수를 호출할 때 인자로 넘겨주고 반환받은 함수를 호출하면서 나머지 가변 인자를 넣어줄 수 있다.
- ```curry()``` 와 **다른 점** 은 함수를 호출할 때, ```this```에 **바인딩 시킬 객체를 사용자가 넣어줄 수 있다는 점** 이다.

```JavaScript
var print_all = function (arg) {
    for (var i in this)
        console.log(i + " : " + this[i]);
    for (var i in arguments)
        console.log(i + " : " + arguments[i]);
};

var myObj = {name: "zzoon"}; // name: zzoon

var myFunc = print_all.bind(myObj); // this가 myObj로 바인딩 된 print_all 함수 반환
myFunc();

var myFunc1 = print_all.bind(myObj, "iamjoo", "others");
myFunc1("insideJs");

/*
name : zzoon
0 : iamjoo
1 : others
2 : insideJs
*/
```

- ```myFunc``` 함수는 ```myObj``` 객체를 ```this``` 에 바인딩 시켜 ```print_all()``` 함수를 실행하는 새로운 함수이다.
- ```myFunc1``` 함수는 인자도 ```bind()``` 함수에 모두 넘겨진다.
- 이와 같이 특정 함수에 원하는 객체를 바인딩 시켜 새로운 함수를 사용할 때 ```bind()``` 함수가 사용된다.

### 1.4 래퍼
- **래퍼(Wrapper)** 란 쉽게 말하면 **특정 함수를 자신의 함수로 덮어씌우는 것** 을 말한다.
- 당연히 사용자는 원래 함수 기능을 잃어버리지 않은 상태로 자신의 로직을 수행할 수 있어야 한다.
- **객체지향 프로그래밍** 에서 다형성의 특성을 살리기 위해 **오버라이드** 를 지원하는데, 이와 상당히 유사하다.

```JavaScript
function wrap(object, method, wrapper) {
    var fn = object[method];

    return object[method] = function() {
        // return wrapper.apply(this, [fn].concat(Array.prototype.slice.call(arguments)));
        return wrapper.apply(this, [fn.bind(this)].concat(Array.prototype.slice.call(arguments)));
    };
}

Function.prototype.original = function(value) {
    this.value = value;
    console.log("value: " + this.value);
}

var myWrap = wrap(Function.prototype, 'original', function(orig_func, value){
    this.value = 20;
    orig_func(value);
    console.log('wrapper value: ' + this.value);
});

var obj = new myWrap('Oppa');

/*
    value: Oppa
    wrapper value: Oppa
*/
```
- 함수를 덮어쓰기 위해 ```wrap``` 함수를 호출하였다.
- 세 번째 인자로 넘긴 자신의 익명함수를 ```Function.prototype.original``` 에 덮어쓰려는 것이다.
- 여기서 자신의 익명 함수의 첫 번째 인자로 원래 함수의 참조를 받을 수 있다.
- 만약 참조를 ```[fn]```으로 하였다면 ```this```가 익명함수로 바인딩 되어 의도하지 않은 결과가 나오므로 ```[fn.bind(this{``` 를 적절하게 이용해야 한다.

### 1.5 맵
- ```map``` 함수는 **배열의 각 요소를 꺼내어 사용자 정의 함수를 적용시켜 새로운 값을 얻은 후, 새로운 배열에 넣는다.**

```JavaScript
Array.prototype.map = function(callback) {
   /* this가 null인지, 배열인지 체크 */
   /* callback이 함수인지 체크 */

   var obj = this;
   var value, mapped_value;
   var A = new Array(obj.length);

   for (var i = 0; i < obj.length; i++) {
      value = obj[i];
      mapped_value = callback.call(null, value);
      A[i] = mapped_value;
    }

    return A;
  };

  var arr = [1, 2, 3];
  var new_arr = arr.map(function(value) {
    return value * value;
  });

  console.log(new_arr); // [1, 4, 9]
```

- 배열 각 요소의 제곱 값을 새로운 요소로 하는 배열을 반환받는 코드이다.

### 1.6 each
- ```each``` 함수는 **배열의 각 요소 혹은 객체의 각 프로퍼티를 꺼내서 차례대로 특정 함수에 인자로 넣어 실행** 시키는 함수다.
- 매우 유용한 함수로 대부분의 자바스크립트 라이브러리에 기본적으로 구현되어 있다.
- 보통 ```each``` 또는 ```forEach``` 라는 이름으로 제공된다.

#### jQuery 1.0의 each 함수

```JavaScript
// jQuery 1.0의 each() 함수

function each(obj, fn, args) {
if (obj.length == undefined) {
  for (var i in obj) {
     fn.apply(obj[i], args || [i, obj[i]]);
    }
  }
  else {
     for (var i = 0; i < obj.length; i++) {
        fn.apply(obj[i], args || [i, obj[i]]);
       }
     }

     return obj;
   }

 each([1, 2, 3], function(idx, num) {
    console.log(idx + ': ' + num);
   });
  /* 0: 1 1: 2 2: 3 */

  var blog = {
     name : 'oppacoding',
     age: 25,
     gender: 'man'
   };

   each(blog, function(name, value) {
     console.log(name + ': ' + value);
   });
         /* name: 'oppacoding' age: 25 gender: 'man' */
```

- ```obj``` 에 ```length``` 프로퍼티가 있는 경우(배열)와 없는 경우(객체)로 나누어서 루프를 돈다.
- 각 요소를 인자로 하여 차례대로 함수를 호출한다.
