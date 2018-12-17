package Sorting;

import java.util.*;

public class KNumber {

    public static void main(String[] args) {
        int[] progresses = {1, 5, 2, 6, 3, 7, 4};
        int[][] speeds = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] answer = solution(progresses, speeds);

        for (int i=0; i<answer.length; i++){
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution (int[] array, int[][] commands) {
        int[] answer = {};

        ArrayList<Integer> answerList = new ArrayList<>();

        for (int i=0; i<commands.length; i++) {

            ArrayList<Integer> arrayList = new ArrayList<>();

            int a = commands[i][0]; // i 번째 숫자부터
            int b = commands[i][1]; // j 번쨰 숫자까지
            int c = commands[i][2]; // k 번째 있는 수

            // a 부터 b 까지 자르기
            for (int j=a; j<b+1; j++) {
                arrayList.add(array[j-1]);
            }

            Collections.sort(arrayList);

            for (int k : arrayList) {
                System.out.print(k);
            }

            answerList.add(arrayList.get(c-1));
        }

        answer = new int[answerList.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

/*
    배열 array 의 i 번째 숫자부터 j 번째 숫자까지 자르고 정렬했을 떄, k 번째 있는 수를 구하려 한다.
    예를 들어, array 가 [1, 5, 2, 6, 3, 7, 4] i=2, j=5, k=3 이라면
    2 - 5 까지 짤라 [5, 2, 6, 3]
    정렬하면 [2, 3, 5, 6]
    나온 배열 중 3번째 숫자는 5이다.
 */
