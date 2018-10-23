# 02 Learning ECMAScript
> 자바스크립트는 1995년에 탄생한 이래 수많은 변화를 겪었다.
원래 자바스크립트는 웹 페이지에 상호작용을 손쉽게 추가하기 위해 만든 언어였다.
그 후 DHTML과 AJAX를 거치면서 더 활발히 사용되었다. 노드가 등장함에 따라 이제 자바스크립트는 풀스택 애플리케이션을 개발하는데 쓰이는 언어가 되었다. 노드가 등장함에 따라 이제 자바스크립트는 풀스택 애플리케이션을 개발하는데 쓰이는 언어가 되었다. 자바스크립트의 변화를 주도하는 기관이 바로 ECMA이다. 자바스크립트 언어의 변화는 커뮤니티에 의해 이루어진다. 자바스크립트를 어떻게 변경할지 커뮤니티 멤버라면 누구든지 ECMA에 제안서를 제출할 수 있다. ECMA 위원회는 이런 제안서를 관리하고 우선순위를 부여해서 각 명세에 어떤 것이 포함될지 결정하는 책임을 가진다. 제안서는 잘 정의된 여러 단계를 거쳐 채택된다. 0 단계는 새로 제출된 제안서를 의미하며, 4단계는 채택이 결정된 제안서를 의미한다. 자바스크립트 명세는 2015년 6월에 크게 개정되었으며 ES6, ECMAScript2015 등 다양한 이름으로 불리고 있다.

## 2.1 ES6 에서 변수 선언하기
- ES6 이전에는 ```var``` 키워드가 변수를 선언하는 유일한 방법이었다.
- 지금은 더 나은 기능을 제공하는 몇가지 방식이 추가되었다.

### 2.1.1 const
- 상수는 값을 변경할 수 없는 변수다.
- 상수가 없던 시절에는 모든 값을 변수에 넣어 사용했다.
- 하지만, 변수는 값을 변경할 수 있다.

```javascript
var pizza = true
pizza = false
console.log(pizza); // false
```

- 상수에 값을 재 설정하는 것은 불가능하다. 따라서, 다음은 콘솔 오류가 발생한다.

```javascript
const pizza = true
pizza = false
```

### 2.1.2 let
- 자바스크립트도 이제는 구문적인 변수 영역 규칙 (**렉시컬 스코프**) 을 지원한다.
- 함수의 경우 ```{}``` 중괄호를 사용해 코드 블록을 만들고 별도의 변수 영역을 이룬다.
- 하지만, if/else 블록 안에서 변수를 새로 만들면 그 변수의 영역이 그 블록 안으로만 한정되지 않는다.

```javascript
var topic = "자바스크립트"

if (topic) {
  var topic = "리액트"
  console.log('블록', topic) // 블록 리액트
}

console.log('글로벌', topic) // 글로벌 리액트
```

- 이 코드에서 if 블록 안의 ```topic``` 변수의 값을 변경하면 if 블록 밖의 topic 변수의 값도 변경된다.
- ```let``` 키워드를 사용하면 변수 영역을 코드 블록 안으로 한정시킬 수 있다.
- 그러므로 **글로벌 변수의 값을 보호할 수 있다.**

```javascript
var topic = "자바스크립트"

if (topic) {
  let topic = "리액트"
  console.log('블록', topic) // 블록 리액트
}

console.log('글로벌', topic) // 블록 자바스크립트
```

- if 블록 안의 topic을 변경해도 if 블록밖의 topic에는 아무런 영향이 없다.
- 중괄호가 새로운 영역을 만들어내지 못하는 다른 부분으로는 **for** 루프가 있다.

```javascript
var div, container = document.getElementById('container');

for (var i=0; i<5; i++) {
  div = document.createElement('div')
  div.onClick = function() {
    alert('이것은 박스 #' + i + '입니다.')
  }
  container.appendChild(div)
}
```

- 이 루프에서는 컨테이너 안에 5개의 div를 만든다.
- 각 div에서는 그 div의 인덱스를 경고창에 표시해주는 onClick 핸들러가 할당된다.
- for 루프 안에서 i를 선언해도 글로벌 영역에 i가 생기며 i가 5가 될 때까지 루프를 돈다.
- 그러므로 어느 것을 클릭해도 표시되는 인덱스는 모두 같다.
- 여기서 ```var``` 로 선언된 i를 ```let``` 으로 바꾸어주면 정상적으로 작동한다.

### 2.1.3 템플릿 문자열
- 템플릿 문자열을 문자열 연결대신 사용할 수 있다.
- 그러면 문자열 중간에 변수를 삽입할 수도 있다.
- 전통적인 문자열은 더하기 기호로 문자열과 변수를 서로 이어붙이는 방식을 사용한다.

```javascript
console.log(lastName + ", " + firstName + " " + middleName)
```

- 템플릿에서는 ```${}``` 를 사용해 문자열 안에 벼수를 집어넣을 수 있기 떄문에 문자열을 단 하나만 사용해도 된다.

```javascript
console.log('${lastName}, ${firstName} ${middleName}')
```

- 템플릿 문자열은 공백 (빈 칸뿐 아니라 탭이나 개행 문자 등도 포함) 을 유지한다.
- 따라서, 전자우편 템플릿이나 코드 예제 등 공백이 들어가야 하는 문자열을 표시할 때 코드가 꺠질 염려를 할 필요가 없다!

```javascript
`
${firstName} 님께,

${event} 티켓 ${qty} 건을 구매해주셔서 감사합니다.

`
```

- 예전에는 자바스크립트에서 HTML 문자열을 직접 사용하려면 모든 문자열을 +로 연결하여 처리하여야 했기 때문에 쉽지않았다.
- 이제는 아니다!

### 2.1.4 디폴트 파라미터
- C++ 이나 파이썬 같은 언어에서는 함수의 인자로 디폴트 값을 선언할 수 있다.
- ES6 명세에도 **디폴트 파라미터** 가 추가되었다.
- 따라서, 함수를 호출하면서 값을 지정하지 않으면 디폴트 값이 사용된다.

```javascript
function logActivity (name="오성원", activity="테니스") {
  console.log( '${name}은 ${activity}를 좋아합니다.')
}
```

- logActivity 함수를 호출하면서 인자를 지정하지 않아도 디폴트 값을 사용해 함수가 정상적으로 실행된다.

```javascript
var defaultPerson = {
  name: {
    first: "성원",
    last: "오"
  },
  favActivity: "테니스"
}

function logActivity(p=defaultPerson) {
  console.log('${p.name.first}은(는) ${p.favActivity}를 좋아합니다.')
}
```

## 2.2 화살표 함수
- **화살표 함수 (Arrow Function)** 는 ES6에 새로 추가된 유용한 기능이다.
- 이를 사용하면 ```function``` 키워드 없이도 함수를 만들 수 있으며, ```return```을 사용하지 않아도 식을 계산한 값이 자동으로 반환된다.

```javascript
// 전통적인 함수
var lordify = function (firstname) {
  return '캔터베리의 ${firstName}'
}

console.log(lordify("오정원")); // 캔터베리의 오정원

// 화살표 함수
var lordify = firstname => '캔터베리의 ${firstname}'
```

- 화살표를 사용하면 모든 함수 정의를 한 줄로 끝낼 수 있다.
- ```function``` 키워드를 없앴고, 화살표가 어떤 값을 반환하는지 지정해주기 때문에 ```return``` 도 없앴다.
- 또다른 장점은 함수가 파라미터를 단 하나만 받는 경우 파라미터 주변의 괄호를 생략해도 된다는 것이다.
- 파라미터가 2개 이상이라면 괄호가 필요하다.

```javascript
// 예전 방식
var lodify = function (firstName, land) {
  return '${land}의 ${firstName}'
}

// 새로운 방식
var lordify = (firstName, land) => '${land}의 ${firstName}'

console.log(lordify("오성원", "브리즈번")) // 브리즈번의 오성원
console.log(lordify("오정원","시드니")) // 시드니의 오정원
```

- 하지만, 결과를 계산하기 위해 여러 줄을 사용해야 한다면 함수 본문 전체를 중괄호로 둘러싸야 한다.
- 화살표 함수는 **this** 를 새로 바인딩하지 않는다.

```javascript
var gangwon = {
  resorts: ["용평", "평창", "강촌", "강릉", "홍천"],
  print: function(delay = 1000) {
    setTimeout(function(){
      console.log(this.resorts.join(","))
    }, delay)
  }
}

gangwon.print() // Cannot read property 'join' of undefined 오류 발생
```

- 이 오류는 ```this.resorts``` 의 ```join``` 메소드를 호출하려고 시도했기 때문에 발생했다.
- 이 경우 **this** 가 **window** 객체이기 때문에 ```resorts```가 ```undefined``` 이다.

```javascript
var gangwon = {
  resorts: ["용평", "평창", "강촌", "강릉", "홍천"],
  print: function(delay = 1000) {
    setTimeout(() => {
      console.log(this.resorts.join(","))
    }, delay)
  }
}

gangwon.print() // 용평, 평창, 강촌, 강릉, 홍천
```

- 이 코드는 정상 작동하며 ```.join``` 을 사용하여 리조트 이름을 콤마로 연결할 수 있다.
- 하지만, 항상 영역을 염두에 두어야 한다는 점을 유의하라.
- 화살표 함수는 새로운 **this** 영역을 만들어내지 않는다.

```javascript
var gangwon = {
  resorts: ["용평", "평창", "강촌", "강릉", "홍천"],
  print: (delay = 1000) => { // function (...) 대신 (...) => 로 바꾸었다.
    setTimeout(function(){
      console.log(this.resorts.join(","))
    }, delay)
  }
}

gangwon.print() // Cannot read property 'join' of undefined 오류 발생
```

- 여기서 ```print``` 함수를 화살표 함수로 바꾼다는 것은 **this** 가 **window** 객체가 된다는 뜻이다.
- 따라서, 이 문제를 해결하기 위해서는 ```print``` 함수는 화살표 함수가 아닌 ```function``` 키워드로 정의해야 한다.

## 2.3 ES6 트랜스파일링
- 모든 웹 브라우저가 ES6를 지원하지는 않는다.
- 만약 ES6를 지원하더라도 모든 기능을 지원하지 않는 경우도 많다.
- 그러니 브라우저에서 ES6 코드를 실행하기 전에 ES5로 컴파일하면 제대로 작동하도록 보장할 수 있다.
- 이러한 변환을 **트랜스파일링(Transpiling)** 이라고 한다.
- 가장 유명한 **트랜스 파일링** 도구로는 **바벨** 이 있다.

### 2.3.1 트랜스파일링
- 트랜스파일링 단계는 자바스크립트를 다른 컴파일 언어와 비슷한 느낌이 들도록 만든다.
- 트랜스파일링은 코드를 바이너리로 변환하는 것이 아니라는 점에서 컴파일과는 다르다.
- 트랜스파일링은 한 버전의 JS 코드를 더 많은 브라우저가 이해할 수 있는 다른 버전의 JS 구문으로 변환하는 것이다.

### 2.3.2 트랜스파일러 사용법
- 인라인 바벨 트랜스파일러를 사용하면 브라우저에서 자바스크립트를 직접 트랜스파일 할 수도 있다.
- 단지 browser.js 파일을 포함시키고 (HTML의 script 태그 사용)
- 변환하고 싶은 script 태그에 ```type="text/babel"``` 을 지정하면 된다.

```javascript
<div id="output"></div>
<!-- 바벨 로딩 -->
<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<!-- 변환할 코드를 script 태그 안에 넣기 -->
<script type="text/babel">
const getMessage = () => "Hello world";
document.getElementById('output').innerHTML = getMessage();
</script>
<!-- 파일에 있는 소스 코드를 트랜스파일링 하기 -->
<script src = "script.js" type="text/babel">
```

- 다만 이 방식을 이용하면 브라우저가 런타임에 **트랜스파일링** 을 수행하기 때문에 어플리케이션의 구동 속도가 느려진다.
- 일단 지금은 CDN 링크를 통해 바벨 트랜스파일링을 수행해 ES6의 기능을 활용할 수 있다.

## 2.4 ES6 객체와 배열
- ES6는 객체와 배열을 다루는 방법과 객체와 배열 안에서 변수의 영역을 제한하는 방법을 다양하게 제공한다.
- 그러한 기능으로는 **구조 분해, 객체 리터럴 개선, 스프레드 연산자** 등이 있다.

### 2.4.1 구조 분해를 사용한 대입
- **구조분해(destructuring)** 를 사용하면 객체 안에 있는 필드 값을 원하는 변수에 대입할 수 있다.

```javascript
var sandwich = {
  bread: "더치 크런치",
  meat: "참치",
  cheese: "스위스",
  toppings: ["상추", "토마토", "머스타드"]
}

var {bread, meat} = sandwich

console.log(bread, meat) // 더치 크런치 참치
```

- 이 코드는 ```sandwich``` 를 분해해서 ```bread```와 ```meat``` 필드를 같은 이름의 변수에 넣어준다.
- 두 변수의 값은 ```sandwich``` 에 있는 같은 이름의 필드 값으로 초기화되지만, 두 변수를 변경해도 원래의 필드 값은 바뀌지 않는다.

```javascript
var {bread, meat} = sandwich

bread = "마늘"
meat = "칠면조"

console.log(bread) // 마늘
console.log(meat) // 칠면조

console.log(sandwich.bread, sandwich.meat) // 더치 크런치 참치
```

- 객체를 분해해서 함수의 인자로 넘길 수도 있다.
- 어떤 사람의 이름을 영주처럼 표현해주는 함수를 생각해보자

```javascript
var lordify = regularPerson => {
  console.log('켄터베리의 ${regularPerson.firstname}')
}

var regularPerson = {
  firstname: "현석",
  lastname: "오"
}

lordify(regularPerson) // 켄터베리의 현석
```

- 객체의 필드에 접근하기 위해 점(.)과 필드 이름을 사용하는 대신 ```regularPerson``` 에서 필요한 값을 **구조 분해** 로 가져올 수도 있다.

```javascript
var lordify = ({firstname}) => {
  console.log('켄터베리의 ${firstname}')
}

lordify(regularPerson) // 켄터베리의 현석
```

- **구조 분해** 는 더 선언적이다.
- 따라서, 코드를 작성한 사람의 의도를 더 잘 설명해준다.
- 구조 분해로 ```firstname``` 을 가져옴으로써 객체의 필드 중에서 ```firstname``` 만을 사용한다는 사실을 선언한다.

```javascript
var [firstResort] = ["용평","평창", "강촌"]

console.log(firstResort) // 용평
```

- **배열** 을 **구조 분해** 해서 원소의 값을 변수에 대입할 수도 있다.
- 불필요한 값을 콤마(,)를 사용해 생략하는 **리스트 매칭** 을 사용할 수 있다.

```javascript
var [,,thirdResort] = ["용평","평창", "강촌"]

console.log(thirdResort) // 강촌
```

- 무시하고 싶은 원소 위치에 콤마를 넣으면 **리스트 매칭** 이 된다.
- 위 배열에서 첫 두 원소를 콤마로 대치하면 다음과 같다.

### 2.4.2 객체 리터럴 개선
- **객체 리터럴 개선** 은 **구조 분해** 의 반대라 할 수 있다.
- **객체 리터럴 개선** 은 **구조를 다시 만들어내는 과정 또는 내용을 한데 묶는 과정** 이라 할 수 있다.

```javascript
var name = "탈락" // 캘리포니아에 있는 산
var elevation = 9738 // 높이 (단위: 피트)

var funHike = {name, elevation}

console.log(funHike) // {name: "탈락", elevation: 9738}
```

- 이제 ```funHike``` 객체에는 ```name``` 과 ```elevation``` 이라는 필드가 들어 있다.

```javascript
var name = "Tallac"
var elevation = 9738
var print = function() {
  console.log('${this.name} 산의 높이는 ${this.elevation} 피트입니다.')
}

var funHike = {name, elevation, print}

funHike.print() // 탈락 산의 높이는 9738 피트입니다.
```

- **객체 리터럴 개선** 또는 **객체 재구축** 으로 객체 메서드를 만드는 것도 가능하다.
- 이 때 객체의 키에 접근하기 위해 **this** 를 사용했다는 사실에 유의하라.

#### 2.4.2.1 새로운 방식과 예전 방식의 객체 선언 문법 비교
```javascript
// 예전 방식
var skier = {
  name: name,
  sound: sound,
  powederYell: function() {
    var yell = this.sound.toUpperCase()
    console.log('${yell} ${yell} ${yell}!!!')
  },
  speed: function(mph) {
    this.speed = mph
    console.log('속력(mph):', mph)
  }
}

// 새로운 방식
const skier = {
  name,
  sound,
  powderYell() {
    let yell = this.sound.toUpperCase()
    console.log('${yell} ${yell} ${yell}!!!')
  },
  speed(mph) {
    this.speed = mph
    console.log('속력(mph):', mph)
  }
}
```

- **객체 리터럴 개선** 으로 현재 영역에서 볼 수 있는 변수들을 객체의 필드에 대입할 수 있으며,
- ```function``` 키워드를 입력하지 않아도 되기 떄문에 입력해야할 코드 양도 줄어든다.

## 2.4.3 스프레드 연산자
- **스프레드 연산자** 는 세 개의 점```(...)``` 으로 이루어진 연산자로, 몇 가지 다른 역할을 담당한다.

```javascript
var peaks = ["대청봉", "중청봉", "소청봉"]
var canyons = ["천불동계곡", "가야동계곡"]
var seoraksan = [...peaks, ...canyons]

console.log(seoraksan.join(', ')) // 대청봉, 중청봉, 소청봉, 천불동계곡, 가야동계곡
```

- ```peaks``` 와 ```canyons``` 에 포함된 모든 원소가 ```seoraksan``` 이라는 새 배열에 들어간다.
- 이제 **스프레드 연산자** 가 해결해주는 문제를 한 가지 살펴보자.
- 위 예제에서 정의한 ```peaks``` 배열의 마지막 원소를 변수에 담고싶다고 하자.
- ``` Arrays.reverse ``` 메서드를 사용해 배열을 뒤집고 구조 분해를 사용해 첫 번째 원소를 변수에 넣으면 될 것 같다.

```javascript
var peaks = ["대청봉", "중청봉", "소청봉"]
var [last] = peaks.reverse()

console.log(last) // 소청봉
console.log(peaks.join(', ')) // 소청봉, 중청봉, 대청봉
```

- 문제점을 발견했는가?
- ```reverse``` 메서드는 원본 배열을 변경한다.
- 하지만 **스프레드 연산자** 를 사용하면 **원본 배열을 변경하지 않고 복사본을 만들어서 뒤집을 수 있다.**

```javascript
var peaks = ["대청봉", "중청봉", "소청봉"]
var [last] = [...peaks].reverse()

console.log(last) // 소청봉
console.log(peaks.join(', ')) // 대청봉, 중청봉, 소청봉
```

- **스프레드 연산자** 를 사용해 배열의 원소를 복사했기 때문에 원본 ```peaks```를 다시 쓸 수 있다.

```javascript
var lakes = ["경포호", "화진포", "송지호", "청초호"]
var [first, ...rest] = lakes

console.log(rest.join(", ")) // "화진포, 송지호, 청초호"
```

- **스프레드 연산자** 를 사용해 배열의 나머지 원소를 얻을 수 있다.

```javascript
function directions(...args) {
  var [start, ...remaining] = args
  var [finish, ...stops] = remaining.reverse()

  console.log('${args.length} 도시를 운행합니다.')
  console.log('${start}에서 출발합니다.')
  console.log('목적지는 ${finish}입니다.')
  console.log('중간에 ${stops.length}군데 들립니다.')
}

directions(
  "서울",
  "수원",
  "천안",
  "대전",
  "대구",
  "부산"
)
```

- ```directions``` 함수는 **스프레드 연산자** 를 사용해 인자를 받는다.
- 첫 번째 인자는 ```start``` 변수에 대입된다.
- 마지막 인자는 ```finish``` 인자에 ```Arrays.reverse``` 를 사용하여 대입된다.
- 그 후 ```args``` 배열의 ```length``` 를 사용해 얼마나 많은 도시를 지나는지 보여준다.
- 목적지로 가는 동안 들러야 하는 도시의 수는 ```args``` 배열에서 2를 뺸 것이다.

```javascript
var morning = {
  breakfast: "미역국",
  lunch: "삼치구이와 보리밥"
}

var dinner = "스테이크 정식"

var backpackingMeals = {
  ...morning,
  dinner
}

console.log(backpackingMeals)
// {breakfast: "미역국", lunch: "삼치구이와 보리밥", dinner: "스테이크 정식"}
```

- **스프레드 연산자** 를 **객체** 에 사용할 수도 있다.

## 2.5 프라미스
- **프라미스(Promise)** 는 비동기적인 동작을 잘 다루기 위한 방법이다.
- **비동기 요청** 을 보내면 모든 것이 바라는 대로 잘 작동해서 **성공** 하거나 또는 **오류** 가 생긴다.
- 여러 유형으로 성공하거나 실패할 수 있다.

### 2.5.1 API로부터 데이터를 가져오는 비동기 프라미스
- randomuser.me API로부터 데이터를 가져오는 비동기 프라미스를 하나 만들자
- 이 API에는 가짜 멤버에 대한 **전자우편 주소, 이름, 전화번호, 집주소** 등의 정보가 들어있으며, 그런 데이터를 **더미** 로 활용하기 좋다.

```javascript
const getFakeMembers = count => new Promise((resolves, rejects) => {
  const api = 'https://api.randomuser.me/?nat=US&results=${count}'
  const request = new XMLHttpRequest()
  request.open('GET', api)
  request.onload = () =>
    (request.status === 200) ?
    resolves(JSON.parse(request.response).request) :
    reject(Error(request.statusText))
  request.onerror = (err) => rejects(err)
  request.send()
})
```

- ```getFakeMembers``` 함수는 새로운 프라미스를 반환한다.
- 그 프라미스는 ```randomuser.me API```에 요청을 보낸다.
- 프라미스가 성공하면 데이터를 제대로 받아올 것이고, 실패한 경우에는 오류가 발생할 것이다.
- 이 함수로 **프라미스** 를 
