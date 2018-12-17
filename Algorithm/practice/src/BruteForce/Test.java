package BruteForce;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] progresses = {1, 2, 3, 4, 5};
        int[] speeds = {1, 30, 5};

        int[] answer = solution(progresses);

        for (int i=0; i<answer.length; i++){
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution (int[] answers) {
        int[] answer = {};

        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        int s1 = 0;
        int s2 = 0;
        int s3 = 0;

        // 1번 수포자 검사
        for (int i=1; i<=answers.length; i++) {
            int j = i%5;

            switch (j) {
                case 1 :
                    if (answers[i-1] == 1)
                        s1++;
                    break;
                case 2 :
                    if (answers[i-1] == 2)
                        s1++;
                    break;
                case 3 :
                    if (answers[i-1] == 3)
                        s1++;
                    break;
                case 4 :
                    if (answers[i-1] == 4)
                        s1++;
                    break;
                case 0 :
                    if (answers[i-1] == 5)
                        s1++;
                    break;
            }
        }

        // 2번 수포자 검사
        for (int i=1; i<=answers.length; i++) {
            int j = i%8;

            switch (j) {
                case 1:
                case 3:
                case 5:
                case 7:
                    if (answers[i-1] == 2)
                        s2++;
                    break;
                case 2:
                    if (answers[i-1] == 1)
                        s2++;
                    break;
                case 4:
                    if (answers[i-1] == 3)
                        s2++;
                    break;
                case 6:
                    if (answers[i-1] == 4)
                        s2++;
                    break;
                case 0:
                    if (answers[i-1] == 5)
                        s2++;
                    break;
            }
        }

        // 3번 수포자 검사
        for (int i=1; i<=answers.length; i++) {
            int j = i%10;

            switch (j) {
                case 1:
                case 2:
                    if (answers[i-1] == 3)
                        s3++;
                    break;
                case 3:
                case 4:
                    if (answers[i-1] == 1)
                        s3++;
                    break;
                case 5:
                case 6:
                    if (answers[i-1] == 2)
                        s3++;
                    break;
                case 7:
                case 8:
                    if (answers[i-1] == 4)
                        s3++;
                    break;
                case 9:
                case 0:
                    if (answers[i-1] == 5)
                        s3++;
                    break;
            }
        }

        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);

        Collections.sort(arrayList);

        if (s1 == arrayList.get(2)) {
            answerList.add(1);
        }

        if (s2 == arrayList.get(2)) {
            answerList.add(2);
        }

        if (s3 == arrayList.get(2)) {
            answerList.add(3);
        }

        answer = new int[answerList.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

/*
    수포자는 수학을 포기한 사람의 준말이다.
    수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 한다.

    1번 수포자가 찍는 방식: 1, 2, 3, 4, 5
    2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5,
    3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 ...

    제한 조건
    1. 시험은 최대 10,000 문제로 구성되어 있다.
    2. 문제의 정답은 1, 2, 3, 4, 5 중 하나 이다.
    3. 가장 높은 점수를 받은 사람이 여럿일 경우, return 하는 값을 오름 차순으로 정렬해주세요.
 */
