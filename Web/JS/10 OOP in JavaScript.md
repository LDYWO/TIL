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

```javascript
student.setAge = function(age) {
    // this.age = age;
}

student.getAge = function() {
    // return this.age
}
```
- 단순히 위와 같이 그 기능을 확장시킬 수 있지만 코드가 지저분해진다.
- 자바스크립트에서는 범용적으로 ```extend()``` 함수로 객체에 자신이 원하는 객체 혹은 함수를 추가시킨다.
- **jQuery**의 ```extend()``` 함수를 살펴보도록 하자

```javascript
jQuery.extend = jQuery.fn.extend = function(obj, prop) {
    if (!prop) { // 두 번째 인자가 전달되지 않으면
        prop = obj; // 복사될 객체를 첫 번째 인자로
        obj = this;
    }
    for (var i in prop)
        obj[i] = prop[i]; // prop의 프로퍼티들을 obj로 복사

      return obj; // prop으로부터 복사된 obj 반환
}
```
- 이 함수는 첫 번째 인자 ```obj``` 객체에 두 번째 인자 ```prop``` 객체의 프로퍼티들을 복사하여 첫 번째 인사를 반환하는 함수다.
- 만약 두 번째 인자로 들어온 것이 없으면, 이 함수를 호출한 객체에 인자로 들어오는 객체의 프로퍼티를 복사하여 반환한다.
- 하지만, 이 코드는 **얕은 복사**를 구현한다.
- **얕은 복사**란 문자 혹은 숫자 리터럴 등이 아닌 객체인 경우 해당 객체를 복사하지 않고 참조하는 것이다.
- 이는 **두 번째 객체의 프로퍼티가 변경되면 첫 번째 객체의 프로퍼티도 변경이 된다는 것**을 의미한다.
- 그러므로 보통 ```extend``` 함수를 구현하는 경우 대상이 객체일 때는 **깊은 복사**를 하는 것이 일반적이다.

#### * ```extend()``` 함수로 상속을 구현한 예
```javascript
var person = {
    name: 'zzoon',
    getName: function () {
        return this.name
    },
    setName: function (value) {
        this.name = value;
    }
};

function create_object(o) {
    function F() {
    }

    F.prototype = o;
    return new F();
}

function extend(obj, prop) {
    if (!prop) {
        prop = obj;
        obj = this;
    }

    for (var i in prop)
        obj[i] = prop[i];

    return obj;
}

var student = create_object(person);
var added = {
    setAge: function (value) {
        this.age = value;
    },
    getAge: function () {
        return this.age;
    }
};

extend(student, added);

student.setAge(25);
console.log(student.getAge());
```
- 얕은 복사를 사용하는 ```extend()``` 함수를 사용하여 ```student``` 객체를 확장
- ```extend()``` 함수는 사용자에게 유연한 기능 확장을 할 수 있게 해줄 뿐만 아니라, 상속에서도 자식 클래스를 확장할 때 유용하게 사용되므로 중요하다.

#### 1.2.2 클래스 기반의 상속
- 원리는 1.2.1 프로토타입을 이용한 상속에서 소개한 내용과 거의 같다.
- 함수의 프로토타입을 적절히 엮어서 상속을 구현한다.
- 앞 절에서는 **객체 리터럴**로 생성된 객체의 상속을 소개했지만, 여기서는 **클래스의 역할을 하는 함수**로 상속을 구현한다.

```javascript
function Person(arg) {
    this.name = arg;
}

Person.prototype.setName = function (value) {
    this.name = value;
};

Person.prototype.getName = function () {
    return this.name
};

function Student(arg) {

}

var you = new Person("iamhjoo");
Student.prototype = you;

var me = new Student("zzoon");
me.setName("zzoon");
console.log(me.getName()); // zzoon
```
- ```Student``` 함수 객체를 만들어서, 이 함수 객체의 프로토타입으로 하여금 ```Person``` 함수 객체의 인스턴스를 참조한다.
- 이렇게 하면 ```Student``` 함수 객체로 생성된 객체 ```me```의 ```[[Prototype]]``` 링크가 생성자 ```Student```의 프로토타입 프로퍼티 ```Student.prototype```가 참조하는 ```you```를 가리키고 ```new Person()```으로 만들어진 객체의 ```[[Prototype]]``` 링크는 ```Person.prototype```을 가리키는 **프로토타입 체인**이 형성된다.
- 따라서 ```me```는 ```Person.prototype``` 프로퍼티에 접근할 수 있고, ```getName()``` 등의 메소드를 호출할 수 있다.
- 그러나 이 예제는 **```me``` 인스턴스를 생성할 때 부모 클래스인 ```Person```의 생성자를 호출하지 않는다**는 문제가 있다.

```javascript
var me = new Student("zzoon");
```
- 이 코드로 ```me``` 인스턴스를 생성할 때 ```"zzoon"```을 인자로 넘겼으나, 이를 반영하는 코드는 어디에도 없다.
- 결국 생성된 ```me``` 객체는 빈 객체이며 ```setName()``` 메소드가 호출되고 나서야 ```me``` 객체에 ```name``` 프로퍼티가 만들어진다.
- 이렇게 **부모의 생성자가 호출되지 않으면, 인스턴스의 초기화가 제대로 이루어지지 않아 문제가 발생할 수 있다.**
- 이를 해결하려면 ```Student``` 함수에 다음 코드를 추가하여 부모 클래스의 생성자를 호출해야 한다.

```javascript
function Student(arg) {
    Person.apply(this.argument);
}
```
- ```Student``` 함수 안에서 새롭게 생성된 객체를 ```apply``` 함수의 첫 번째 인자로 넘겨 ```this``` 바인딩하며 ```Person``` 함수를 실행시킨다.
- 이런 방식으로 자식 클래스의 인스턴스에 대해서도 부모 클래스의 생성자를 실행시킬 수 있다.
- **클래스 간의 상속**에서 **하위 클래스의 인스턴스를 생성할 때, 부모 클래스의 생성자를 호출해야 하는데**, 이 경우에 필요한 방식이다.
- 현재는 자식 클래스의 객체가 부모 클래스의 객체를 **프로토타입 체인**으로 직접 접근하는데, 부모 클래스의 인스턴스와 자식 클래스의 인스턴스는 서로 독립적일 필요가 있다.
- **두 클래스의 프로토타입 사이에 중개자**를 하나 만들자

```javascript
function Person(arg) {
    this.name = arg;
}

Function.prototype.method = function (name, func) {
    this.prototype[name] = func;
};

Person.method('setName', function (value) {
    this.name = value;
});

Person.method('getName', function () {
    return this.name
});

function Student(arg) {

}

function F() {
    // 중개자 함수 F
}

F.prototype = Person.prototype; // F의 프로토타입 프로퍼티에 Person.prototype 연결
Student.prototype = new F(); // Person.prototype을 [[Prototype]] 링크로 참조하는 빈 객체를 Student.prototype으로 참조
Student.prototype.constructor = Student;
Student.super = Person.prototype;

var me = new Student();
me.setName("zzoon");
console.log(me.getName());
```

- 위 예제의 **프로토타입 체인 형성** 과정은 ```2.2.1 프로토타입을 이용한 상속```의 상속 방식과 매우 유사하다.
- 빈 함수 ```F()```를 생성하고, 이 ```F()```의 인스턴스를 ```Person.prototype```과 ```Student``` 사이의 중개자로 두고 이 인스턴스를 ```Student.prototype```에 참조되게 두었다.
- ```Person```의 인스턴스와 ```Student```의 인스턴스는 서로 독립적이다.
- _스토얀 스테파노프_ 는 상속 관계를 **실행 함수**와 **클로저**를 활용하여 최적화된 함수로 소개하였다.

```javascript
var inherit = function(Parent, Child) {
    var F = new function() {

    };

    return function (Parent, Child) {
        F.prototype = Parent.prototype;
        Child.prototype = new F();
        Child.prototype.constructor = Child;
        Child.super = Person.prototype;
    };
}();
```

- 위 코드에서 **클로저(리턴되는 함수)** 는 ```F()``` 함수를 지속적으로 참조한다.
- 따라서 ```F()```는 가비지 컬렉션의 대상이 되지 않고 계속 남아있다.
- 이를 이용해 함수 ```F()```의 생성은 단 한번만 이루어진다.
- ```inherit``` 함수가 계속 호출이 되어도 함수 ```F()```를 재생성하지 않는다.
