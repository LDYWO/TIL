package Sorting;

import java.util.*;

public class Number {
    public String solution(int[] numbers) {
        String answer = "";
        String[] num = new String[numbers.length];

        for (int i=0; i<numbers.length; i++) {
            num[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(num, new MyCompare());

        // 전부 0인 경우는 0을 반환한다 0, 0, 0 같은 경우
        if (num[0].equals("0")) {
            answer += "0";
        } else {
            for (int i=0; i<num.length; i++) {
                answer += num[i];
            }
        }

        return answer;
    }

    /*
        return 1 일 때 o2가 먼저 나온다.
        return 0 일 때 변함 없다.
        return -1 일 때, o1이 먼저 나온다.

     */

    // 두 개의 문자열을 합친 문자열의 크기를 비교한다.
    // 내림 차순
    class MyCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o2 + o1).compareTo(o1 + o2);
        }
    }
}

/*
    배열 numbers 내의 숫자의 조합으로 가장 큰 숫자를 반환하여라
    숫자 값이 클 수도 있으니 String 으로 변환하여 출력하라

    자릿 수가 다른 경우를 생각해야한다.

 */