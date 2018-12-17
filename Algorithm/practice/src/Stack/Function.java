package Stack;

import java.util.*;

public class Function {

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] answer = solution(progresses, speeds);

        for (int i=0; i<answer.length; i++){
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        Deque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i=0; i<progresses.length; i++) {
            int num = (int)Math.ceil(((100 - (double)progresses[i])/(double)speeds[i])); // double 과 double 로 나누어주어야 값이 절삭되지 않는다. // 7 3 9
            deque.add(num);
        }

        while (deque.size() > 0) {
            int num = deque.poll(); // poll 은 큐의 맨 앞 요소를 제거하여 반환한다.
            int count = 1;

            while (deque.size() > 0 && num >= deque.peek()) { // peek()은 큐의 맨 앞 요소를 반환하고 제거한다. 만일 비어있다면 null 을 반환한다.
                deque.poll();
                count++;
            }

            arrayList.add(count);
        }

        answer = new int[arrayList.size()];

        for (int i=0; i<arrayList.size(); i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }

}

/*

    기능 개선 작업을 수행 중이다.
    각 기능은 진도가 100% 일 때 서비스에 반영할 수 있다.

    또, 각 기능의 개발 속도는 모두 다르기 떄문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고,
    이 때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포 된다.

    먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses 와 각 작업의 개발 속도가 적힌
    정수 배열 speeds 가 주어질 떄 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 하여라

 */
