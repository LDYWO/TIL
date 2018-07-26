# Prototype Chaining
## 1. 프로토 타입 체이닝
### 1.1 프로토 타입의 두 가지 의미
- 자바스크립트는 **프로토 타입 기반의 객체지향 프로그래밍**을 지원한다.
- 자바스크립트에는 **클래스 개념이 없고, 객체 리터럴이나 생성자 함수를 객체로 생성**한다.
- 이렇게 생성된 객체의 부모 객체가 바로 **프로토 타입** 객체이며 상속 개념과 마찬가지로 자식 객체는 부모 객체가 가진 프로퍼티 접근이나 메소드를 상속받아 호출하는 것이 가능하다.
- 자바스크립트의 모든 객체는 자신의 부모인 프로토 타입 객체를 가리키는 참조 링크 형태의 숨겨진 프로퍼티가 있다.
- **ECMA** 에서는 이를 암묵적 프로토 타입 링크라고 부르며 이는 모든 객체의 ```[[Prototype]]``` 프로퍼티에 저장된다.
- 여기서 주의할 점은 객체의 부모 역할을 하는 프로토 타입 객체를 가리키는 ```[[Prototype]]``` 프로퍼티와 함수 객체의 ```prototype``` 프로퍼티를 혼동해서는 안된다.
- 이 둘의 차이점을 알려면 자바스크립트 객체 생성 규칙을 알아야 한다.
- 자바스크립트에서 모든 객체는 자신을 생성한 생성자 함수의 ```prototype``` 프로퍼티가 가리키는 프로토 타입 객체를 자신의 부모 객체를 가리키는 ```[[Prototype]]``` 링크로 연결한다.

```JavaScript
// Person 생성자 함수
function Person(name) {
    this.name = name;
}

// foo 객체 생성
var foo = new Person('foo');

console.dir(Person);
console.dir(foo);
```
- ```Person()``` 생성자 함수의 ```prototype``` 프로퍼티는 자신의 링크된 프로토 타입 객체(```Person.prototype```)를 가리킨다.
- ```Person()``` 생성자 함수로 생성된 foo 객체는 ```Person()``` 함수의 프로토 타입 객체(```Person.prototype```)를 자신의 부모를 가리키는 ```[[Prototype]]``` 링크로 연결한다.
- 즉, 생성자 함수의 ```prototype``` 프로퍼티와 foo 객체의 ```[[Prototype]]``` 프로퍼티는 모두 ```Person()``` 생성자 함수의 프로토 타입 객체인 ```Person.prototype```을 가리킨다.
- 결국, 자바스크립트에서 객체를 생성하는 건 생성자 함수의 역할이지만, 생성된 객체의 실제 부모 역할을 하는 건 생성자 자신(```Person()```)이 아닌 생성자의 ```prototype``` 프로퍼티가 가리키는 프로토 타입 객체(```Person.prototype```)다.

### 1.2 객체 리터럴 방식으로 생성된 객체의 프로토 타입 체이닝
- 자바스크립트에서 객체는 자기 자신의 프로퍼티 뿐만이 아니라, 자신의 부모 역할을 하는 프로토 타입 객체의 프로퍼티 또한 마치 자신의 것처럼 접근하는 게 가능하다.
- 이것을 가능케 하는게 프로토타입 체이닝이다.

```JavaScript
var myObject = {
    name: 'foo',
    sayName: function() {
        console.log('My name is' + this.name);
    }
};

myObject.sayName(); // My name is foo
console.log(myObject.hasOwnProperty('name')); // true
console.log(myObject.hasOwnProperty('nickname')); // false
myObject.sayNickname(); // error!
```
- **myObject** 객체는 ```hasOwnProperty()```라는 메소드를 가지고 있지 않다.
- 하지만, ```sayNickname()```처럼 에러가 발생하지 않고 정상적으로 함수가 실행되었다.
- 이를 이해하기 위해선 객체 리터럴 방식으로 생성한 객체와 **프로토 타입 체이닝**의 개념을 살펴봐야 한다.
- 객체 리터럴로 생성한 객체는 ```Object()```라는 내장 생성자 함수로 생성된 것이며 이 함수 또한 함수 객체이므로 ```prototype```이라는 프로퍼티 속성이 있다.
- 따라서, 앞서 설명한 자바스크립트의 규칙으로 생성한 객체 리터럴 형태의 **myObject**는 ```Object()``` 함수의 ```prototype``` 프로퍼티가 가리키는 ```Object.prototype``` 객체를 자신의 프로토 타입 객체로 연결한다.
- 예제에서 ```sayName()``` 메소드를 호출할 때는 **myObject** 객체 내에 메소드가 있어 바로 수행된다.
- 반면에 ```hasOwnProperty()``` 메소드를 호출할 때는 **myObject** 객체가 ```hasOwnProperty()``` 메소드를 가지고 있지 않기 때문에 **myObject**의 ```[[Prototype]]``` 링크를 따라 그것의 부모 역할을 하는 ```Object.prototype``` 프로토 타입 객체 내에 hasOwnProperty() 메소드가 있는지를 검색하고 존재하면 이를 실행한다.
- 반면 ```sayName()``` 함수는 **myObject** 객체에도, ```Object.prototype``` 프로토 타입 객체에도 존재하지 않으므로 에러가 발생한 것이다.

#### 1.2.1 프로토 타입 체이닝
> **자바스크립트**에서 특정 객체의 프로퍼티나 메소드에 접근하려고 할 때, 해당 객체에 접근하려는 프로퍼티 또는 메소드가 없다면 ```[[Prototype]]``` 링크를 따라 자신의 부모 역할을 하는 프로토 타입 객체의 프로퍼티를 차례대로 검색하는 것이다.

### 1.3 생성자 함수로 생성된 객체의 프로토 타입 체이닝
- **생성자 함수로 객체를 생성하는 경우**는 객체 리터럴 방식과 약간 다른 ```프로토 타입 체이닝```이 이루어진다.
- 하지만, 두 가지 방식 모두 다음의 원칙을 기준으로 ```프로토 타입 체이닝```이 이루어진다.
- 자바스크립트에서 모든 객체는 **자신을 생성한 생성자 함수의 ```prototype``` 프로퍼티가 가리키는 객체**를 자신의 프로토 타입 객체로 취급한다.

```JavaScript
function Person(name, age, hobby) {
    this.name = name;
    this.age = age;
    this.hobby = hobby;
}

var foo = new Person('foo', 30, 'tennis');

console.log(foo.hasOwnProperty('name')); // true
console.dir(Person.prototype); // Person
```
- ```foo``` 객체의 ```[[Prototype]]``` 프로퍼티가 가리키는 ```Person.prototype```은 디폴트로 ```constructor``` 프로퍼티만을 가지고 있으므로 ```hasOwnProperty()``` 메소드가 없다.
- 하지만, ```Person.prototype``` 역시 객체이므로 ```[[Prototype]]``` 프로퍼티를 가지고, 이는 ```Object.prototype```과 연결되어 있으므로 타고 올라가서 ```Object.prototype```의 ```hasOwnProperty()``` 메소드가 실행된다.

### 1.4 프로토 타입 체이닝의 종점
- 자바스크립트에서 ```Object.prototype``` 객체는 **프로토 타입 체이닝의 종점**이다.
- 객체 리터럴 방식이나 생성자 함수를 이용한 방식이나 결국엔 ```Object.prototype```에서 끝난다.
- 자바스크립트의 모든 객체는 **프로토 타입 체이닝**으로 ```Object.prototype``` 객체가 가진 프로퍼티와 메소드에 집근하고, 서로 공유가 가능하다.

### 1.5 기본 데이터 타입 확장
- ```Object.prototype```에 정의된 메소드를 자바스크립트 모든 객체의 표준 메소드라고 볼 수 있다.
- 이와 같은 방식으로 자바스크립트의 숫자, 문자열, 배열 등에서 사용되는 모든 표준 메소드들은 이들의 프로토 타입인 ```Number.prototype```, ```String.prototype```, ```Array.prototype``` 등에 정의되어 있다.
- 이 프로토 타입 또한 ```Object.prototype``` 을 자신의 프로토 타입 객체로 삼고 있다.
- 자바스크립트는 ```Object.prototype``` 같은 표준 빌트 인 프로토 타입 객체에도 사용자가 직접 정의한 메소드를 추가할 수 있다.

```JavaScript
String.prototype.testMethod = function() {
    console.log("This is test String.Prototype.testMethod")
};

var str = "String for test";
str.testMethod();

console.dir(String.prototype);
```

### 1.6 자바스크립트 객체로서의 프로토 타입
- 함수가 생성될 때, 자신의 ```prototype``` 프로퍼티에 연결되는 프로토 타입 객체는 디폴트로 ```constructor``` 프로퍼티만을 가진 객체이다.
- 프로토 타입 객체 역시 자바스크립트 객체이므로 일반 객체처럼 동적으로 프로퍼티를 추가/ 삭제하는 것이 가능하다.
- 이렇게 변경된 프로토 타입 객체는 실시간으로 **프로토 타입 체이닝**에 반영된다.

```JavaScript
function Person(name) {
    this.name = name;
}

var foo = new Person('foo');

// foo.sayHello(); // error!

Person.prototype.sayHello = function() {
    console.log("Hello");
};

foo.sayHello();
```

### 1.7 프로토 타입 메소드와 this 바인딩
- 프로토 타입 객체는 **메소드**를 가질 수 있다.
- 프로토 타입 메소드에서 **this**를 사용하면 그 **메소드를 호출한 객체에 바인딩**된다.

```JavaScript
function Person(name) {
    this.name = name;
}

Person.prototype.getName = function() {
    return this.name;
}

// foo 객체가 getName() 호출
var foo = new Person('foo');
console.log(foo.getName()); // 'foo'

// 바로 Person.prototype 객체에 name 프로퍼티를 추가하고 Person.prototype에 접근해서 getName()을 호출하면
// getName()을 호출한 객체가 Person.prototype이므로 this가 Person.prototype에 바인딩 된다.
Person.prototype.name = 'prototype name';
console.log(Person.prototype.getName()); // 'prototype name'
```

### 1.8 디폴트 프로토 타입은 다른 객체로 변경이 가능하다.
- **디폴트 프로토 타입 객체**는 함수가 생성될 때 같이 생성되며, 함수의 ```prototype``` 프로퍼티에 연결된다.
- 함수를 생성할 때 해당 함수에 연결되는 **디폴트 프로토 타입 객체를 다른 일반 객체로 변경할 수 있다.**
- 이러한 특징을 이용하여 **객체지향의 상속을 구현**할 수 있다.
- **여기서 주의할 점**은 생성자 함수의 프로토 타입 객체가 변경되면, 변경된 시점 이후에 생성된 객체들은 변경된 프로토 타입 객체로 ```[[Prototype]]``` 링크를 연결하고, 생성자 함수의 프로토 타입이 변경되기 이전에 생성된 객체들은 기존 프로토 타입 객체로의 ```[[Prototype]]``` 링크를 그대로 유지한다는 것이다.

```JavaScript
function Person(name) {
    this.name = name;
}
console.log(Person.prototype.constructor);

var foo = new Person('foo');
console.log(foo.country); // undefined

// 프로토 타입 객체 변경
Person.prototype = {
    country: 'korea'
};
console.log(Person.prototype.constructor);

var bar = new Person('bar');

console.log(foo.name); // foo
console.log(foo.country); // undefined
console.log(foo.constructor); // Person()

console.log(bar.name); // bar
console.log(bar.country); // Korea
console.log(bar.constructor); // Object()
```
- ```foo```는 변경되기 이전 ```Person.prototype```(```constructor``` 프로퍼티만 가지고 있는 프로토 타입 객체)를, ```bar```는 변경된 ```Person.prototype```(```country``` 프로퍼티만 가지고 있는 객체 리터럴로 생성한 객체)를 프로토 타입 객체로 삼고있다.
- ```bar.constructor```를 실행했을 때, ```bar```의 프로토 타입 객체는 ```country```라는 프로퍼티만 가지고 있으므로 ```constructor``` 프로퍼티가 없다.
- 따라서, 프로토 타입 체이닝이 일어나 ```Object.prototype```의 ```constructor(Object())```를 출력한다.

### 1.9 객체의 프로퍼티 읽기나 메소드를 실행할 때만 프로토 타입 체이닝이 동작한다.
- 객체의 특정 프로퍼티를 읽으려고 할 때, **프로퍼티가 해당 객체에 없는 경우 프로토 타입 체이닝이 발생**한다.
- 반대로 **객체에 있는 특정 프로퍼티에 값을 쓰려고 한다면 이때는 프로토 타입 체이닝이 일어나지 않는다.**
- 자바스크립트는 객체에 없는 프로퍼티에 값을 쓰려고 할 경우 동적으로 객체에 프로퍼티를 추가하기 때문이다.

```JavaScript
function Person(name) {
    this.name = name;
}

Person.prototype.country = 'korea';

var foo = new Person('foo');
var bar = new Person('bar');
console.log(foo.country); // 프로토 타입 체이닝 O --> Person.prototype.country 참조
console.log(bar.country); // 프로토 타입 체이닝 O --> Person.prototype.country 참조

foo.country = 'USA'; // foo 객체에 country 프로퍼티 동적으로 생성
console.log(foo.country); // 프로토 타입 체이닝 X --> foo 객체에 country라는 프로퍼티가 있기 때문이다.
console.log(bar.country); // 프로토 타입 체이닝 O --> Person.prototype.country 참조
```
