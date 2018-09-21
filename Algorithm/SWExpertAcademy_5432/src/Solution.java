// 레이저 절단
// Stack을 이용

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static int countTotalNum(String s) {
        char[] inputSymbol = s.toCharArray();
        int[] laserCountArray = new int[10000];
        int laserCount = 0;
        int blockCount = 0;

        Stack stack = new Stack();

        // 스택에 넣고 빼는 반복문
        for (int i=0; i<inputSymbol.length; i++){

            switch (inputSymbol[i]){
                case '(':
                        stack.push(inputSymbol[i]);

                    break;

                case ')':
                    if (!stack.isEmpty() && inputSymbol[i-1] == '(') {
                        // 블록 안에 있는 laser 인 경우
                        laserCount++;
                        stack.pop();
                    }
                    // block 인 경우
                    // 이제 각 블록마다 안에 레이저를 몇 개씩 가지고 있는지 확인하는 조건문 작성
                    // 21441
                    else if (!stack.isEmpty() && inputSymbol[i-1] == ')') {
                        laserCountArray[blockCount] = laserCount + arraySum(laserCountArray);
                        blockCount++;
                        stack.pop();
                    }
                    else
                    laserCountArray[blockCount] = laserCount;
                    blockCount++;
                    // stack.pop();

                    break;
            }
        }

        int sum = arraySum(laserCountArray);

        return sum;
    }

    public static int arraySum(int[] array) {
        int sum = 0;

        for (int i=0; i<array.length; i++) {
            sum += array[i];
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();

        for (int i=1; i<=count; i++) {
            String TC;
            String[] output = new String[count];

            TC = sc.next();

            System.out.println("#" + i + " " + countTotalNum(TC));
        }
    }

}
