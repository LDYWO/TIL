# OOP in JavaScript
## 1. 객체지향 프로그래밍
- 자바스크립트는 여러가지 특성으로 객체지향 언어의 특성을 구현해낼 수 있다.

#### * 클래스 기반의 언어
  - 클래스로 객체의 기본적인 형태와 기능을 정의
  - 생성자로 인스턴스를 만들어서 사용
  - 클래스에 정의된 메소드로 여러 기능 수행
  - 모든 인스턴스가 클래스에 저으이된 대로 같은 구조, 런타임에 바꿀 수 없음
  - 정확성, 안전성, 예측성
  - ```Java, C++``` 등

#### * 프로토타입 기반의 언어
  - 객체의 자료구조, 메소드 등을 동적으로 바꿀 수 있다.
  - 동적으로 자유롭게 객체의 구조와 동작 방식 변경 가능
  - ```JavaScript```

### 1.1 클래스, 생성자, 메소드
- ```C++```이나 ```Java```의 경우 ```class```라는 키워드를 사용하여 클래스를 만들지만, 자바스크립트에는 ```class```가 키워드가 없다.
- 자바스크립트는 거의 모든 것이 객체이고, 특히 함수 객체로 많은 것을 구현해낸다.
- 클래스, 생성자, 메소드 모두 함수로 구현이 가능하다.

#### * 잘못된 예
```JavaScript
function Person(arg) {
    this.name = arg;
    this.getName = function() {
        return this.name;
    };

    this.setName = function(value) {
        this.name = value;
    };
}

var me = new Person('zzoon');
console.log(me.getName());

me.setName('iamhjoo');
console.log(me.getName);
```
- 이 형태는 기존의 객체지향 프로그래밍 언어에서 객체를 생성하는 코드와 매우 유사핟.
- 함수 ```Person```이 클래스이자 생성자의 역할을 한다.
- 하지만 이 예제에는 문제가 많다.
- ```Person()```으로 객체를 생성할 때 마다 공통적으로 사용할 수 있는 ```getName()```과 ```setName()```을 계속 생성해낸다.
- 이 문제를 해결하려면 자바스크립트의 특성 중 **함수 객체의 프로토타입**을 이용한다.

#### * 바른 예
```javascript
function Person(arg) {
    this.name = arg;
}

Person.prototype.getName = function() {
    return this.name;
}

Person.prototype.setName = function(value) {
    this.name = value;
}

var me = new Person('me');
var you = new Person('you');

console.log(me.getName());
console.log(you.getName());
```

- 위 예제에서는 ```Person``` 함수 객체의 ```prototype``` 프로퍼티에 ```getName()```과 ```setName()```을 정의하였다.
- 이렇게 하면 ```Person``` 함수로 생성되는 객체들은 ```setName()```과 ```getName()``` 함수를 **프로토타입 체이닝**으로 접근할 수 있다.
- 이는 곧 불필요한 중복을 줄인다.
- 이와 같이 자바스크립트에서 클래스 안의 메소드를 정의할 때는 프로토타입 객체에 정의한 후, ```new```로 생성한 객체에서 **프로토타입 체이닝**으로 접근할 수 있게 하는 것이 좋다.
- 다음은 _더글라스 크락포드_ 가 소개한 메소드를 정의하는 방법이다.

```javascript
Function.prototype.method = function (name, func) {
    if (!this.prototype[name]) // this의 프로토타입에 name이라는 이름을 가진 프로퍼티(메소드)가 없으면
      this.prototype[name] = func;
};
```
- 이 패턴을 이용하면 위에서 살펴본 예제는 다음과 같이 작성할 수 있다.

```javascript
Function.prototype = function (name, func) {
    if (!this.prototype[name])
        this.prototype[name] = func;
};

function Person(arg) {
    this.name = arg;
}

Person.method('getName', function() {
    return this.name;
});

var me = new Person('me');
var you = new Person('you');

console.log(me.getName());
console.log(you.getName());
```

### 1.2 상속
- 자바스크립트에서는 클래스를 기반으로 하는 전통적인 상속을 지원하지 않는다.
- 하지만 자바스크립트의 특성 중 **객체 프로토타입 체인**을 이용하여 상속을 구현해 낼 수 있다.

#### * 구현 방식
- 상속의 구현 방식은 2가지 방법이 있다.
  1. **클래스 기반** 전통적인 상속 방식을 흉내내는 방법
  2. **클래스 개념 없이** 객체의 **프로토 타입**으로 상속을 구현하는 방법
    - 프로토 타입을 이용한 상속, **객체 리터럴**을 중심으로 철저히 **프로토 타입을 이용하여 상속**을 구현해낸다.

#### 1.2.1 프로토타입을 이용한 상속

```javascript
function create_object(o) {
    function F() {} // 새로운 빈 함수 객체 정의
    F.prototype = o; // 새로운 함수의 프로토타입 프로퍼티에 인자로 받은 객체 참조, 이 함수로 생성한 객체는 o를 부모로 한다.
    return new F(); // o를 부모로 하는 새로운 객체 생성하여 반환
}
```
- 이 코드는 _더글라스 크락포드_ 가 자바스크립트 객체를 상속하는 방법으로 오래 전에 소개한 코드이다.
- 간단해 보이지만 사실 이 코드를 이해하면 자바스크립트에서의 **프로토타입 기반의 상속**을 다 이해한 것이나 다름 없다.
- ```create_object()``` 함수는 인자로 들어온 객체를 부모로 하는 새로운 자식 객체를 생성하여 반환한다.
- 다음은 이 ```create_object()``` 함수를 이용하여 상속을 구현한 예이다.

```javascript
var person = {
    name: 'zzoon',
    getName: function() {
        return this.name
    },
    setName: function() {
        this.name = value;
    }
};

function create_object(o) {
    function F() {}
      F.prototype = o;
      return new F();
}

var student = create_object(person);

student.setName("me");
console.log(student.getName());
```
- ```person``` 객체를 상속하여 ```student``` 객체를 만들었다.
- 클래스에 해당하는 생성자 함수를 만들지도, 그 클래스의 인스턴스를 따로 생성하지도 않았다.
- 단지 부모 객체에 해당하는 ```person``` 객체와 이 객체를 **프로토타입 체인**으로 참조할 수 있는 자식 객체 ```student```를 만들어서 사용하였다.
- 지금까지는 부모 객체의 메소드를 그대로 상속받아 사용하였지만, 자식은 자신의 메소드를 재 정의 혹은 추가로 기능을 더 확장시킬 수 있어야 한다.
