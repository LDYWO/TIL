function A (arg) {
    if (!(this instanceof A))
        return new A (arg);
    this.value = arg? arg : 0; // arg가 유의미하다면 arg, 아니면 0 할당
}

var a = new A (100);
var b = A(10);

console.log(a.value);
console.log(b.value);
// console.log(window.value);

// if (!(this instanceof A)) 이 부분은 if(!(this instanceof arguments.callee))로 대체할 수 있다.

