# Design Pattern in JavaScript
## 1. 디자인 패턴
- 모든 개발자는 유지 보수 가능하고 읽기 쉽고 재사용 가능한 코드를 작성하려고 노력한다.
- 응용 프로그램이 커질수록 코드 구조화가 중요해진다.
- 디자인 패턴은 이러한 문제를 해결하는 데 중요한 역할을 하며 공통된 환경에 대한 조직 구조를 제공한다.

### 1.1 디자인 패턴의 종류

- Module Pattern
- Prototype Pattern
- Observer Pattern
- Singleton Pattern

### 1.2 강조점
- **Context**: 어떤 상황에서 어떤 패턴이 사용되었는가?
- **Problem**: 무엇을 해결하려는가?
- **Solution**: 이 패턴을 사용하면 제안된 문제를 어떻게 해결할 수 있는가?
- **Implementation**: 코드 구현은 어떻게 하는가?

***
## 2. 패턴 종류
### 2.1 MODULE 패턴
- 모듈은 특정 구성 요소를 다른 구성 요소와 독립적으로 유지하는데 가장 널리 사용되는 디자인 패턴이다.
- 이는 잘 구조화된 코드를 지원하기 위해 느슨한 결합을 제공한다.
- 말하자면 모듈은 JavaScript의 ```클래스``` 역할을 한다.
- ```클래스```의 많은 장점 중 하나는 ```캡슐화```이다.
- 모듈 패턴은 ```public``` 및 ```private``` 접근 권한 설정을 가능하게 한다.
  - 모듈은 변수와 메소드를 보호하는 클로저 (하지만 함수 대신 객체를 반환해야 한다.) 와 같다.
  - private 범위를 허용하는 IIFE(Immediately-Invoked Function-Expressions)이어야 한다.

#### 2.1.1 기본적인 형태
```javascript
(function() {
    // private 변수들과 함수들을 선언

    return {
        // public 변수들과 함수들을 선언
    }
})();
```

#### 2.1.2 구체적인 형태
```JavaScript
var HTMLChanger = (function() {
  var contents = 'contents'

  var changeHTML = function() {
    var element = document.getElementById('attribute-to-change');
    element.innerHTML = contens;
  }

  return {
    callChangeHTML: function() {
      changeHTML();
      console.log(contents);
    }
  };

})();

HTMLChanger.callChangeHTML(); // Outputs: 'contents'
console.log(HTMLChanger.contetns); // undefined
```
- ```callChangeHTML```은 반환된 객체에 바인딩되며 ```HTMLChanger``` 네임스페이스 내부를 참조할 수 있다.
- 그러나, 모듈 외부에서 ```contents```를 참조할 수 없다.

### 2.2 REVEALING MODULE 패턴
- **REVEALING MODULE 패턴** 은 **MODULE 패턴** 의 한 변형이다.
- 이 패턴의 목적은 ```캡슐화 유지``` 와 ```객체 리터럴에서 반환된 특정 변수와 메소드들을 잘 나타내기 위함``` 이다.

#### 2.2.1 구체적인 형태
```javascript
var Exposer = (function() {
  var privateVariable = 10;

  var privateMethod = function() {
    console.log('Inside a private method!');
    privateVariable++;
  }

  var methodToExpose = function() {
    console.log('This is a method I want to expose!');
  }

  var otherMethodIWantToExpose = function() {
    privateMethod();
  }

  return {
    first: methodToExpose,
    second: otherMethodIWantToExpose
  };
})();

Exposer.first(); // Output: This is a method I want to expose!
Exposer.second(); // Output: Inside a private method!
Exposer.methodToExpose; // undefined
```
- 훨씬 깔끔해 보인다.
- 하지만, ```private``` 메소드를 참조할 수 없다는 명백한 단점을 가지고 있다.
- 이는 단위 테스트 문제를 야기할 수 있다.
- 또한 모듈 외부에서 반환된 메소드들을 오버라이드 할 수 없게 된다.

### 2.3 PROTOTYPE 패턴
- **PROTOTYPE 패턴** 은 ```JavaScript의 프로토타입 상속```에 기반하여 만들어졌다.
- ```prototype 모델```은 주로 성능이 중요한 상황에서 객체를 생성하는데 사용된다.
- 생성된 객체는 전달된 원본 객체의 복제본이다.
- **PROTOTYPE 패턴** 의 활용 사례는 광범위한 데이터 베이스 작업을 수행하는 경우이다.
  - 애플리케이션의 다른 부분에 사용되는 객체를 만든다.
  - 다른 프로세스가 이 객체를 사용해야 할 경우 데이터 베이스 작업 수행 대신 이전에 만든 객체를 복제하는 것이 좋다.


#### 2.3.1 기본적인 형태
- 객체를 복제하려면 생성자가 있어야 첫 번째 객체를 인스턴스화 할 수 있다.
- 다음에 ```prototype``` 키워드를 이용하여 변수와 메소드를 객체의 구조체에 바인딩한다.

```javascript
var TeslaModelS = function() {
  this.numWheels = 4;
  this.manufacturer = 'Tesla';
  this.make = 'Model S';
};

TeslaModelS.prototype.go = function() {
  // Rotate Wheels
}

TeslaModelS.prototype.stop = function() {
  // Apply break pads
}
```

#### 2.3.2 구체적인 형태
- 생성자를 사용하면 단일 TeslaModelS 객체를 만들 수 있다.
- TeslaModelS 객체를 만들면 생성자에서 초기화된 상태가 유지된다.
- 또한 ```prototype```을 사용하여 선언한 ```go```와 ```stop``` 함수를 쉽게 유지보수 할 수 있다.

```javascript
var TeslaModelS = function() {
  this.numWheels = 4;
  this.manufacturer = 'Tesla';
  this.make = 'Model S';
};

TeslaModelS.prototype = {
  go: function() {
    // Rotate Wheels
  },
  stop: function() {
    // Apply break pads
  }
}
```

### 2.4 REVEALING PROTOTYPE  패턴
- **REVEALING PROTOTYPE 패턴** 은 객체 리터럴을 반환하여 ```public``` 및 ```private``` 멤버를 ```캡슐화``` 한다.
- 객체를 반환하기 때문에 ```prototype``` 객체에 ```function``` 키워드를 선언할 것이다.

#### 2.4.1 구체적인 예제

```javascript
var TeslaModelS = function() {
  this.numWheels = 4;
  this.manufacturer = 'Tesla';
  this.make = 'Model S';
}

TeslaModelS.prototype = function() {

  var go = function() {
    // Rotate Wheels
  };

  var stop = function() {
    // Apply break pads
  };

  return {
    pressBreakPedal: stop,
    pressGasPedal: go
  }

}();
```
- ```go``` 함수와 ```stop``` 함수는 반환되는 객체의 밖에 존재하기 때문에 외부로부터 보호된다.
- JavaScript에서 ```prototype 상속```을 지원하기 때문에 기본 기능들을 다시 작성할 필요가 없다.

### 2.5 Observer 패턴
- 애플리케이션의 한 부분이 변경되면 다른 부분들도 같이 변경되어야 하는 경우가 많다.
- **Angular JS** 에서 ```$scope``` 객체가 변경되었을 때, 이를 다른 구성요소에 알리기 위해 이벤트를 트리거할 수 있다.
- **Observer 패턴** 은 이를 구체화 한다.
- 객체가 수정되면 종속 객체에 변경 사항을 **브로드 캐스팅** 한다.

#### 2.5.1 기본적인 예제
- 이벤트 관리를 통한 **Observer 패턴**을 **Angular JS** 예제로 살펴보도록 하자.

```JavaScript
// Controller 1
$scope.$on('nameChanged', function(event, args) {
  $scope.name = args.name;
});

...

// Controller 2
$scope.userNameChanged = function(name) {
  $scope.$emit('nameChanged', {name: name});
};
```

- **Observer 패턴** 에서 독립적인 객체와 **subject** 를 구분하는 것이 중요하다.
- **Observer 패턴** 은 많은 이점들을 제공하지만 **observer** 수가 증가함에 따라 성능이 크게 저하된다는 단점이 있다.
- 가장 유명한 **observer** 중 하나는 **watcher** 이다.
- **Angular JS** 에서 변수, 함수 그리고 객체들을 관찰할 수 있다.
- ```$$digest``` 주기가 실행되고 관찰중인 객체가 변경될 때마다 새 값으로 각 관찰자에게 알린다.

#### 2.5.2 구체적인 예제
- JavaScript에서 자체 **Subject** 와 **Observer** 를 만들 수 있다.

```JavaScript
var Subject = function() {
  this.observer = [];

  return {
    subscribeObserver: function(observer) {
      this.observers.push(observer);
    },
    unsubscribeObserver: function(observer) {
      var index = this.observers.indexOf(observer);
      if(index > -1) {
        this.observers.splice(index, 1);
      }
    },
    notifyObserver: function(observer) {
      var index = this.observers.indexOf(observer);
      if(index > -1) {
        this.observers[index].notify(index);
      }
    },
    notifyAllObservers: function() {
      for (var i=0; i < this.observers.length; i++) {
        this.observers[i].notify(i);
      };
    }
  };
};

var Observer = function() {
  return {
    nofity: function(index) {
      console.log("Observer " + index + " is notified!");
    }
  }
};

var subject = new Subject();

var observer1 = new Observer();
var observer2 = new Observer();
var observer3 = new Observer();
var observer4 = new Observer();

subject.subscribeObserver(observer1);
subject.subscribeObserver(observer2);
subject.subscribeObserver(observer3);
subject.subscribeObserver(observer4);

subject.nofityObserver(observer2); // Observer 2 is notified!

subject.notifyAllObservers();
// Observer 1 is nofitied!
// Observer 2 is notified!
// Observer 3 is notified!
// Observer 4 is notified!
```

### 2.6 PUBLISH/ SUBSCRIBE 패턴
- **Punlish/ Subscribe 패턴** 은 알림을 수신하려는 객체(**Subscriber**)와
- 이벤트를 발생시키는 객체(**Publisher**) 사이에 위치하는 **topic/ event** 채널을 사용한다.
- 이 방식은 ```구독자와 게시자 간의 종속성```을 피할 수 있습니다.

#### 2.6.1 Observer 패턴과의 차이
- **구독자** 가 적절한 **이벤트 핸들러** 를 구현하여 **게시자** 가 **브로드 캐스트** 하는 **topic** 알림을 등록하고 수신한다.
- **Publish/ Subscribe 패턴** 의 구독자 (**Subscriber**)는 **일부 메시징 매체** 를 통해 알림을 받는다.
- **Observer 패턴** 에서는 **subject** 와 유사한 **핸들러** 를 구현하여 알림을 받는다.

#### 2.6.2 Angular JS 에서의 구현
- 구독자(**Subscriber**): ```$on('event', callback)``` 을 통해 이벤트를 구독한다.
- 발행자(**Publisher**): ```$emit('event', args)``` 혹은 ```$broadcast('event', args)``` 를 통해 이벤트를 발행한다.

### 2.7 Singleton 패턴
 - **Singleton 패턴** 은 단일 인스턴스 생성만 허용하지만 **동일한 객체의 여러 인스턴스를 허용** 한다.
 - **Singleton 패턴** 은 클라이언트가 여러 객체를 생성하지 못하도록 제한한다.
 - 첫 번째 객체가 생성된 이후에는 첫 번째 객체를 반환한다.

 #### 2.7.1 기본적인 예제
 - 사무용 프린터를 사용하는 경우의 예이다.
 - 사무실에 10명의 사람들이 있고, 그들은 하나의 프린터를 사용한다면,
 - 10개의 컴퓨터가 하나의 프린터(**instance**)를 공유하는 것이다.
 - 하나의 프린터를 공유함으로써 동일한 자원을 공유한다.

 ```javascript
 var printer = (function () {

   var printerInstance;

   function create () {

     function print () {
       // underlying printer mechanics
     }

     function turnOn () {
       // warm up
       // check for paper
     }

     return {
       // public + private states and behaviors
       print: print,
       turnOn: turnOn
     };
   }

   return {
     getInstance: function() {
       if(!printerInstance) {
         printerInstance = create();
       }
       return printerInstance;
     }
   };

   function Singleton() {
     if(!printerInstance) {
       printerInstance = initialize();
     }
   };

 })();
 ```

 - 클라이언트가 ```create``` 메소드에 접근하는 것을 원하지 않기 때문에 이 메소드는 ```private``` 이다.
 - 하지만, ```getInstance``` 메소드는 ```public```이다.
 - 각 사무실 직원은 다음과 같이 ```getInstance``` 메소드를 사용하여 프린터 인스턴스를 생성할 수 있다.

 ```javascript
 var officePrinter = printer.getInstance();
 ```

 - **Angular JS** 에서는 **Singleton 패턴** 이 널리 퍼져있다.
 - 가장 주목할만한 것은 ```service```, ```factories```, ```provider``` 이다.
 - 이들은 상태를 유지하고 자원 액세스를 제공한다.
 - 때문에, 두 인스턴스를 생성하면 겹쳐지는 ```service```, ```factory```, ```provider``` 지점을 무효화 시킨다.
 - **Singleton 패턴** 은 **Race Condition** 에 영향을 받기 쉽기 때문에 처음에 인스턴스가 초기화 되지 않으면 인스턴스 초기화와 반환 대신에 두 개의 객체를 생성할 수도 있다.
 - 이는 **Singleton** 의 목적을 위배하기 때문에 개발자는 멀티스레드 응용 프로그램에서 **Singleton** 구현 시에 **동기화** 에 신경써야 한다.

***
## 3. 결론
- 디자인  패턴은 대규모 응용 프로그램에서 자주 사용된다.
- 특정 패턴이 쓰여야할 상황, 다른 패턴과 비교했을 때의 장점 등을 이해하기 위해 연습해야 한다.
