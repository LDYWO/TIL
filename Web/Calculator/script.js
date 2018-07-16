var display = document.getElementById('display'); // 숫자가 출력되는 화면
var num = document.getElementsByClassName('num'); // 숫자 키, 배열 (ClassName)
var op = document.getElementsByClassName('op'); // 연산 키, 배열 (ClassName)

var numStack = new Array(); // 숫자 스택
var opStack = new Array(); // 연산자 스택

var temp; // 임시로 입력 값을 받아 전달해주는 매개체

var firstValidate = false;

// 숫자 id 초기화
var one = document.getElementById('one');
var two = document.getElementById('two');
var three = document.getElementById('three');
var four = document.getElementById('four');
var five = document.getElementById('five');
var six = document.getElementById('six');
var seven = document.getElementById('seven');
var eight = document.getElementById('eight');
var nine = document.getElementById('nine');
var zero = document.getElementById('zero');

// 연산자 id 초기화
var plus = document.getElementById('plus');
var min = document.getElementById('min');
var multi = document.getElementById('multi');
var div = document.getElementById('div');
var equal = document.getElementById('equal');
var del = document.getElementById('del');

// 처음 들어오는 입력 값은 숫자, 숫자 다음은 숫자, 기호 가능, 기호 다음은 무조건 숫자.
// 마지막에 Equal을 누르면 해당 계산 값이 다 나오도록 출력
// Del 버튼을 누르면 초기화 되도록 출력
// stack을 총 2개 생성 (num, op)

one.addEventListener('click',clickNum);
two.addEventListener('click',clickNum);
three.addEventListener('click',clickNum);
four.addEventListener('click',clickNum);
five.addEventListener('click',clickNum);
six.addEventListener('click',clickNum);
seven.addEventListener('click',clickNum);
eight.addEventListener('click',clickNum);
nine.addEventListener('click',clickNum);
zero.addEventListener('click',clickNum);

plus.addEventListener('click',clickOp);
min.addEventListener('click',clickOp);
multi.addEventListener('click',clickOp);
div.addEventListener('click',clickOp);

del.addEventListener('click',clickDel);
equal.addEventListener('click',clickEqual);

function clickNum(){
    console.log("ClickNum");
}

function clickOp(){
    console.log("ClickOp");
}

function clickDel(){
    console.log("clickDel");
}

function clickEqual(){
    console.log("clickEqual");
}

function Error(){
    // 초기 조건, 처음에는 숫자만 입력 가능
    if (numStack.length == 0){

    }
}