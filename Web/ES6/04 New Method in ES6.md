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
// 원래 for in 은 Object의 순회를 나타내는 메소드이기 때문에 배열의 순환에는 적합하지 않다.
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
