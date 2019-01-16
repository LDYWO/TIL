package Heap;

import java.util.*;

public class PQ {
    public static boolean[] visited;
    public static int[] solution (String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> b.num-a.num);
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b) -> a.num-b.num);

        visited = new boolean[operations.length];

        for (int i=0; i<operations.length; i++) {
            String[] op = operations[i].split(" ");

            if (op[0].equals("I")) {
                // 숫자 삽입
                maxHeap.offer(new Pair(Integer.parseInt(op[1]), i));
                minHeap.offer(new Pair(Integer.parseInt(op[1]), i));
            } else if (op[1].equals("1")) {
                // 최댓 값 삭제
                pqMethod(maxHeap);
            } else {
                // 최솟 값 삭제
                pqMethod(minHeap);
            }
        }

        answer[0] = MaxOrMin(maxHeap);
        answer[1] = MaxOrMin(minHeap);

        return answer;
    }

    public static void pqMethod(PriorityQueue<Pair> pq) {
        while (!pq.isEmpty()) {
            if (visited[pq.peek().index]) {
                pq.poll();
            } else {
                Pair pair = pq.poll();
                visited[pair.index] = true;
                break;
            }
        }
    }

    public static int MaxOrMin (PriorityQueue<Pair> pq) {

        int answer = 0;

        while (!pq.isEmpty()) {
            if (visited[pq.peek().index]) {
                pq.poll();
            } else {
                answer = pq.poll().num;
                break;
            }
        }

        return answer;
    }

    static class Pair {
        int num;
        int index;

        public Pair (int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static void main (String[] args) {
        // String[] operations = {"I 16", "D 1"};
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] answer = solution(operations);

        for (int i=0; i<answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}

/*
    이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말 합니다.
    명령어
    I 숫자 : 큐에 주어진 숫자를 삽입합니다.
    D 1 : 큐에서 최댓 값을 삭제합니다.
    D -1 : 큐에서 최솟 값을 삭제합니다.

 */
