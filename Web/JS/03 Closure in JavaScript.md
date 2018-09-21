# Closure in JavaScript
## 1. 클로져 (Closure)
- **외부함수의 변수**에 **접근**할 수 있는 **내부 함수**
- **스코프 체인 (Scope Chain)** 으로 표현되기도 한다.
- 클로저는 **세 가지의 스코프 체인**을 가진다.

```
1. 클로저 자신에 대한 접근
2. 외부 함수의 변수에 대한 접근
3. 전역 변수에 대한 접근
```
- **내부 함수**는 **외부 함수의 변수**뿐만 아니라 **파라미터에**도 접근할 수 있다.
- 단, **내부 함수**는 **외부 함수의 arguments 객체를 호출할 수 없다.**

***

**기본적인 클로저 예제**:
```javascript
function showName(firstName, lastName) {
    var nameIntro = "Your name is";

    // 이 내부 함수는 외부 함수의 변수 뿐만 아니라 파라미터 까지 사용할 수 있습니다.

    function makeFullName() {
        return nameIntro + firstName + " " + lastName;
    }
    return makeFullName();
}
showName("Michael", "Jackson"); // Your name is Michael Jackson
```
> 클로저는 **Node.js의 비동기, 논-블록킹 아키텍처**의 핵심 기능으로 활용되고 있다.
***

**jQuery의 전형적인 클로저 사용 예:**
```javascript
$(function() {
      var selections = [];
      $(".niners").click(function(){ // 이 클로저는 selections 변수에 접근한다.
            selections.push(this.prop("name")); // 외부 함수의 selections 변수를 갱신함
        });
  });
```

***
### 2. 클로저의 규칙과 부수 효과
> 클로저는 외부함수가 리턴된 이후에도 외부함수에 접근할 수 있다.
- **자바스크립트의 함수**가 실행되었을 때, 함수는 자신이 **생성되었을 때와 동일한 스코프 체인**을 사용한다.

```javascript
function celebrityName(firstName) {
    var nameIntro = "This is celebrity is ";
    // 이 내부 함수는 외부 함수의 변수와 파라미터에 접근할 수 있다.
    function lastName(theLastName) {
        return nameIntro + firstName + " " + theLastName;
  }
  return lastName;
}

var mjName = celebrityName("Michael"); // 여기서 celebrityName 외부함수가 리턴된다.
// 외부함수가 위에서 리턴된 후에, 클로저(lastName)이 호출된다.
// 아직, 클로저는 외부함수의 변수와 파라미터에 접근이 가능하다. (이상태로)
mjName("Jackson"); // This celebrity is Michael Jackson
```

- 클로저는 **외부 함수의 변수에 대한 참조를 저장한다.**
- 클로저는 **실제 값을 저장하지 않는다.**
- 클로저가 **호출되기 전에 외부함수의 변수가 변경되었을 경우** 창의적으로 활용이 가능하다.

***
#### 2.1 _클라스 크락포드(Douglas Crockford)_ 의 **내부(private)변수 예제:**
```javascript
function celebrityID() {
    var celebrityID = 999;
    // 우리는 몇개의 내부 함수를 가진 객체를 리턴할 것입니다.
    // 모든 내부함수는 외부 변수에 접근할 수 있습니다.
    return {
        getID: function() {
            // 이 내부함수는 갱신된 celebrityID 변수를 리턴합니다.
            // 이것은 changeThdID 함수가 값을 변경한 이후에도 celebrityID의 현재 값을 리턴합니다.
            return celebrityID;
        },
        setID: function(theNewID) {
            // 이 내부함수는 외부함수의 값을 언제든지 변경할 것입니다.
            celebrityID = theNewID;
        }
    }
}

var mjID = celebrityID(); // 이 시점에, celebrityID 외부함수가 리턴됩니다.
mjID.getID(); // 999
mjID.setID(567); // 외부함수의 변수를 변경합니다.
mjID.getID(); // 567, 변경된 celebrityID 변수를 리턴합니다.
```
***

#### 2.2 클로저 비꼬기
- **클로저가 갱신된 외부함수의 변수에 접근**함으로써, **외부함수의 변수가 for문에 의해 변경될 경우** 의도치 않은 **버그**가 발생할 수 있습니다.

```javascript
function celebrityIDCreater(theCelebrities) {
    var i;
    var uniqueID = 100;
    for (i=0; i<theCelebrities.length; i++) {
        theCelebrities[i]["id"] = function(){
            return uniqueID + i;
        }
    }
    return theCelebritices;
}

var actionCelebs = [{name: "Stallone", id: 0}, {name: "Cruise", id: 0}, {name: "Willis", id: 0}];

var createIdForActionCelebs = celebrityIDCreater(actionCelebs);

var stalloneID = createIdForActionCelebs[0];

console.log(stalloneID.id) // 103
```
- 위의 예제에서, 익명의 내부함수가 실행될 시점에 i의 값은 3이다.
- 애초에 기대 값은 0, 1, 2가 더해져 100, 101, 102 였을 것이다.
- 하지만 i의 값, 숫자 3은 uniqueID에 더해져 모든 celebritesID에 103을 할당하게 된다.

**이런 결과가 나타난 이유**는, 앞서 언급했듯이 **클로저**는 (이 예제에서 **내부의 익명함수**) 외부 변수에 대해 **값이 아닌 참조로 접근**을 하기 때문이다. 즉 클로저는 **최종 갱신된 변수(i)에 대해서만 접근**할 수 있으므로, 외부 함수가 전체 for문을 실행하고 리턴한 **최종 i의 값**을 리턴하게 된다. (103)

***
#### 2.3 즉시 호출된 함수 표현식 (Immediately Invoked Function Expression, IIFE)
- **위와 같은 부작용**을 고치기 위해 사용한다.
```javascript
function celebrityIDCreater(theCelebrities) {
    var i;
    var uniqueID = 100;
    for (i=0; i<theCelebrities.length; i++) {
      theCelebrities[i]["id"] = function(i) {
          // i 파라미터는 호출시 즉시 넘겨받은(IIFE) i의 값이 된다.
          return function() {
              // for문이 순환될 때마다 현재 i의 값을 넘겨주고, 배열에 저장한다.
              return uniqueID + i;
          } () // 함수의 마지막에 ()를 추가함으로써 함수를 리턴하는 대신 함수를 즉시 실행하여 그 결과 값을 리턴한다.
      } (i); // i 변수를 파라미터로 즉시 함수를 호출한다.
    }

    return theCelebrities;
}

var actionCelebs = [{name: "Stallone", id:0}, {name: "Cruise", id:0}, {name:"Wills", id:0}];

var createIdForActionCelebs = celebrityIDCreater(actionCelebs);

var stalloneID = createIdForActionCelebs[0];
console.log(stalloneID.id); // 100

var cruiseID = createIdForActionCelebs[1];
console.log(cruiseID.id); // 101
```

***
### 3. 참고
- [JavaScript Is Sexy](http://javascriptissexy.com/understand-javascript-closures-with-ease/)
