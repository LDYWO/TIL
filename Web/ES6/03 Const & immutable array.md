# Const & Immutable Array

## 1. Const
```javascript
function home() {
  const list = ['apple', 'orange', 'watermelon'];
  list = "adasdasd";
}

home();
```
- 이와 같은 경우는 값의 재 할당이 이루어지지 않는다.

```javascript
function home() {
  const list = ['apple', 'orange', 'watermelon'];
  list.push("banana");
  console.log(list);
}

home();
```
- ```const```를 쓴다고 해서 수정할 수 없다는 것을 의미하지 않는다.
- ```const```를 사용하더라도 배열과 오브젝트의 값을 변경하는 것은 가능하다.
- ```const```는 불변을 의미하지 않는다.

## 2. Immutable Array
```javascript
function home() {
  const list = ['apple', 'orange', 'watermelon'];
  list.push("banana");
  console.log(list);
}

// immutable array를 어떻게 만들지?
const list = ["apple", "orange", "watermelon"];
list2 = [].concat(list, "banana");
// ["apple", "orange", "watermelon", "banana"];
console.log(list === list2);
```
