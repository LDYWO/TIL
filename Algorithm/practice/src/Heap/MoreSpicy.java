package Heap;

import java.util.*;

public class MoreSpicy {
    public int solution (int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> pq = new PriorityQueue<>();

        for (int integer : scoville) {
            pq.offer(integer);
        }

        while (pq.size() > 1) {
            if (pq.peek() >= K) {
                break;
            }

            int s1 = pq.poll();
            int s2 = pq.poll();
            int addOn = s1 + s2*2;

            pq.offer(addOn);

            answer++;
        }

        if (pq.peek() < K) answer = -1;

        return answer;
    }
}
