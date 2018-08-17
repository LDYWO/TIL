## New Method in ES6
## 1. String
```javascript
// ES2015 string에 추가된 새로운 메소드
let str = "hello world ! ^^ ~~";
let matchstr = "^ ~~";

console.log(str.startsWith(matchstr));
// 특정 문자열로 시작하는지 확인

console.log(str.endsWith(matchstr));
// 특정 문자열로 끝나는지 확인

console.log(str.includes(matchstr));
// 특정 문자열이 포함 되는지 확인
```

## 2. iterator
### 2.1 기존의 방법
```javascript
var data = [1, 2, undefined, NaN, null, ""];

data.forEach(function(value)) {
  console.log(value);
}

for (let idx in data) {
  console.log(data[idx]);
}

// 이 경우 문제가 있다.
// 원래 for in 은 Object의 순회를 나타내는 메소드이기 때문에
// 배열의 순환에는 적합하지 않다.
// 따라서, 배열의 상위 프로토 타입에 대한 변화 값도 출력할 수 있다.
// 예를 들어, data.prototype.fucntion = function() {}; 이라는 문장을 추가하면
// function(){} 값도 함께 출력된다.
```

### 2.2 for of
```javascript
for (let value of data) {
  console.log(value);
}

var str = "hello world!!";
for (let value of str) {
  console.log(value);
}

// str 순회도 가능하다.
// 문자열을 문자 단위로 돌면서 순회한다.
```


## 3. Spread Operator
```javascript
let pre = ["apple", "orange", 100];
let newData = [...pre];
console.log(pre === newData);
// false 얕은 복사를 하여 같은 값을 참조하지 않는다.
```
```javascript
let pre = [100, 200,  "hello", null];

let newData = [0, 1, 2, 3, ...pre, 4];

console.log(newData);
```
```javascript
function sum(a, b, c) {
  return a+b+c;
}

let pre = [100, 200, 300];

console.log(sum.apply(null, pre));
// 기존의 방법 (컨텍스트 초기화 후 삽입)

console.log(sum(...pre));
// 새로운 방법 (펼쳐서 간단히 구현)
```

## 4. from Method
### 4.1 for 문 + arguments
```javascript
function addmark() {
  let NewData = [];

  for (let i=0;  i<arguments.length; i++) {
    newData.push(arguments[i] + "!");
  }

  console.log(newData);
}

addMark(1, 2, 3, 4, 5);
```

### 4.2 map + arguments
```javascript
function addMark() {
  let NewData = arguments.map(function(value) {
    // arguments는 배열이 아니기 때문에 오류가 뜬다.
    // 배열 같지만 배열이 아니여서 가짜 배열이라고 부른다.
    return value + "!";
  });

  console.log(newData);
}

addMark(1, 2, 3, 4, 5, ,6 ,7, 8 ,9);
```

### 4.3 from + arguments
```javascript
function addMark() {
  let NewArray = Array.from(arguments);
  // 가짜 배열을 Array.from을 통해 진짜 배열로 만들어준다.
  let NewData = newArray.map(function(value){
    return value + "!";
  });

  console.log(newData);
}

addMark(1, 2, 3, 4, 5, 6, 7, 8, 9);
```

### 5. 실습

```javascript
function print() {
  /*
  filter, includes, from을 사용해서 문자열 'e'가 포함된
  노드로 구성된 배열을 만들어서 반환하기.
  */

  // 노드는 가짜 배열이기 때문에 Array.from을 써야함

  let list = document.querySelectorAll("li");
  let listArray = Array.from(list);
  let eArray = listArray.filter( function(value){
    return value.innerText.includes("e");
  });

  return eArray;
}

print();
```

### 6. Object
- 간단히 객체 생성하기

#### 6.1 Destructuring

#### 6.2 Set & WeakSet

#### 6.3 Map & WeakMap
