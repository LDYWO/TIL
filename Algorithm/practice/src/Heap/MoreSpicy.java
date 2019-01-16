package Heap;

import java.util.*;

public class MoreSpicy {
    public int solution (int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a-b); // a-b 인 경우가 minHeap 이다.

        for (int i : scoville) {
            pq.offer(i);
        }

        while (!pq.isEmpty() && pq.peek() < K) {

            if (pq.size() == 1) {
                return -1;
            }

            int a = pq.poll();
            int b = pq.poll();

            pq.offer(a + b*2);
            answer++;
        }

        return answer;
    }
}
