package DFS_BFS;

import java.util.*;

public class TargetNumber {
    static int ANSWER = 0;

    public int solution(int[] numbers, int target) {
        int answer = 0;

        makeOperator(numbers, 0, target);

        answer = ANSWER;

        return answer;
    }

    public static void makeOperator (int[] numbers, int index, int target) {
        if (index < numbers.length) {
            numbers[index] *= 1;
            makeOperator(numbers, index + 1, target);

            numbers[index] *= -1;
            makeOperator(numbers, index + 1, target);
        } else {
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            if (sum == target) ANSWER++;
        }
    }
}

/*
    n 개의 음이 아닌 정수가 있습니다.
    이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
    예를 들어 [1,1,1,1,1] 로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
    사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target 이 매개변수로 주어질 때
    숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

    재귀를 사용하여 DFS 를 구현하였다.
 */
