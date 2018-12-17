package Stack;

import java.util.*;

public class Tower {

    public static void main(String[] args) {
        int[] progresses = {3, 9, 9, 3, 5, 7, 2};

        int[] answer = solution(progresses);

        for (int i=0; i<answer.length; i++){
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution (int[] heights) {
        int[] answer = {};

        Stack<Integer> towerStack = new Stack<>();
        Stack<Integer> answerStack = new Stack<>();
        Stack<Integer> towerStackClone = new Stack<>();

        for (int i : heights) {
            towerStack.push(i);
        }

        while (!towerStack.isEmpty()) {
            int num = towerStack.pop();
            int count = 0;
            boolean is = false;
            // 나머지 타워 스택을 반복문으로 돌면서 검사한다.
            // 만약에 검사 기준 높이보다 높은 타워가 있다면 그 타워의 위치를 반환한다.

            towerStackClone = (Stack<Integer>) towerStack.clone();
            Collections.reverse(towerStackClone);

            // 거꾸로 검사하는 방법?
            for (int i : towerStackClone) {
                count++;
                if (i > num) {
                    is = true;
                    break;
                }
            }

            if (is) {
                answerStack.push(towerStack.size() - count + 1);
            } else {
                answerStack.push(0);
            }
        }

        answer = new int[answerStack.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = answerStack.pop();
        }

        return answer;
    }
}

/*
    수평 직선에 높이가 서로 다른 탑 N대를 세웠습니다.
    모든 탑의 꼭대기에는 신호를 송/수신하는 장치를 설치했습니다.

    발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다.
    또한, 한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.

    예를 들어, 높이가 6, 9, 5, 7, 4인 다섯 탑이 왼쪽으로 동시에 레이저 신호를 발사합니다.
    그러면, 탑은 다음과 같이 신호를 주고 받습니다.
    높이가 4인 다섯 번쨰 탑에서 발사한 신호는 높이가 7인 네 번째 탑이 수신하고, 높이가 7인 네 번쨰
    탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신합니다.
    높이가 9인 두 번쨰 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신할 수 있습니다.

 */
