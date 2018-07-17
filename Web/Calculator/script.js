var display = document.getElementById('display'); // 숫자가 출력되는 화면

var numStack = new Array(); // 숫자 스택
var opStack = new Array(); // 연산자 스택

var temp; // 임시로 입력 값을 받아 전달해주는 매개 변수

var result = ""; // 결과를 eval 함수로 받아서 출력해주는 변수

var opValidate = false; // 연산자 기호를 처음에 누르거나, 연속으로 누르는 것을 불허하는 진리 값

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
    opValidate = true;

    if (display.value == 0) display.value = "";

    temp = this.value;
    display.value += temp;
}

function clickOp(){
    if (opValidate == true)
    {
        opValidate = false;

        temp = this.value;

        numStack.push(display.value);
        opStack.push(temp);

        display.value = 0;
    }
}

function clickDel(){
    // Stack 초기화 및 display 초기화
    display.value = 0;
    numStack = [];
    opStack = [];
}

function clickEqual(){
    numStack.push(display.value);
    // 피 연산자가 연산자보다 무조건 1개가 더 많은 상황이어야 출력이 가능하다.
    if (numStack.length == opStack.length + 1)
    {
        // numStack, opStack 하나씩 pop
        while (numStack.length != 0){
            result += numStack.pop();
            if (opStack.length != 0) result += opStack.pop();
        }
        display.value = eval(result); // display.value에 eval 함수로 result 값 출력
        result = ""; // result 값 초기화
    } else
    {
        alert("숫자를 더 입력하세요.");
    }
}