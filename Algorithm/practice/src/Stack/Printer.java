package Stack;

import java.util.*;

public class Printer {

    public static void main(String[] args) {
        int[] a = {1, 1, 9, 1, 1, 1};
        int b = 0;

        int answer = solution(a, b);

        System.out.println(answer);
    }

    public static int solution (int[] priorities, int location) {
        int answer = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i=0; i<priorities.length; i++) {
            deque.offer(priorities[i]);
        }

        //TODO! 주의해야할 사항은 큐는 동적으로 빼면서 추가하는 사항이 불가능하다. 아래와 같은 경우는 deque 에서 poll 을 루프문 내에서 1번만 실행이 가능하다.
        while (!deque.isEmpty()) {
            int num = deque.poll(); // 큐의 맨 첫 번째 인자를 정수 값에 저장
            boolean check = true; // 비교 대상보다 우선 순위가 큰 것이 있는지 체크하기 위한 값 (뒤로 보내기 위해서)

            for (int i: deque) {
                if (num < i) {
                    check = false; // 만약 비교 대상보다 우선 순위가 큰 것이 있다면 체크
                }
            }

            if (check) {
                answer++; // 무사히 통과되었다면 출력 (출력할 때마다 카운트 추가)
                if (location == 0) return answer;
            } else {
                deque.add(num); // 그렇지 않다면 맨 뒷 열에 추가
            }

            location--; // 만일 바로 출력되지 않았다면 뒤로 갔거나 앞으로 땡겨졌을테니까 location 값을 하나 줄인다.
            if (location<0) location+=deque.size(); // 만약에 location 즉, index 값이 - 라면 전체 사이즈 값을 더해주면 제대로 된 인덱스 값이 나온다.
        }

        return answer;
    }

}

/*
    일반적인 프린터는 인쇄 요청이 들어온 순서대로 처리한다.
    그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있다.

    따라서 중요한 문서를 먼저 인쇄할 수 있도록 새로운 프린터가 개발되었다.

    1. 인쇄 대기 목록의 가장 앞에 있는 문서 (J)를 대기 목록에서 꺼낸다.
    2. 나머지 안쇄 대기 목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기 목록의 가장 마지막에
    3. 그렇지 않으면 J를 인쇄한다.

    A B C D E F

    1 1 9 1 1 1

    C D E F A B

 */
