package Stack;

import java.util.*;

public class StockPrice {
    public int[] solution(int[] prices) {
        int[] answer = {};

        Deque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i : prices) {
            deque.offer(i);
        }

        while (!deque.isEmpty()) {
            int num = deque.poll();
            int count = 0;

            for (int i : deque) {
                count++;
                if (num > i) {
                    break;
                }
            }

            arrayList.add(count);
        }

        answer = new int[arrayList.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }
}

/*
    초 단위로 기록된 주식 가격이 담긴 배열 prices 가 매개 변수로 주어질 때,
    가격이 유지된 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

    prices 의 각 가격은 1 이상 10,000 이하인 자연수 입니다.
    prices 의 길이는 2 이상 100,000 이하 입니다.

    [498, 501, 470, 489]
 */
