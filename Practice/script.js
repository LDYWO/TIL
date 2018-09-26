function Person(name) {
    this.name = name;
}

console.log(new Person('foo')); // Person(foo)

function Animal(name) {
    this.name = name;
    return new Person('bar'); // 명시적으로 다른 객체 리턴
}

console.log(new Animal('cat')); // Person(bar) --> Animal 객체가 아닌 명시적 값이 리턴된다.

// 이 때, 생성자 함수에서 명시적으로 리턴한 값이 객체가 아닌 Boolean, 숫자, 문자열 등 기본 타입인 경우 이러한 리턴을 무시하고 this 로 바인딩된 객체가 리턴된다.