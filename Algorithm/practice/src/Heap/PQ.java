package Heap;

import java.util.*;

public class PQ {
    public static int[] solution (String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> b.num - a.num);

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b) -> a.num - b.num);

        boolean[] visited = new boolean[operations.length];

        for (int i=0; i<operations.length; i++) {
            String[] op = operations[i].split(" ");

            if (op[0].equals("I")) {
                // maxHeap 과 minHeap 에 주어진 숫자를 삽입
                maxHeap.offer(new Pair (i, Integer.parseInt(op[1])));
                minHeap.offer(new Pair (i, Integer.parseInt(op[1])));
            } else if (op[1].equals("1")) {
                while (!maxHeap.isEmpty()) {
                    if (visited[maxHeap.peek().index]) {
                        maxHeap.poll();
                    } else {
                        Pair pair = maxHeap.poll();
                        visited[pair.index] = true;
                        break;
                    }
                }
            } else if (op[1].equals("-1")){
                while (!minHeap.isEmpty()) {
                    if (visited[minHeap.peek().index]) {
                        minHeap.poll();
                    } else {
                        Pair pair = minHeap.poll();
                        visited[pair.index] = true;
                        break;
                    }
                }
            }
        }

        while (!maxHeap.isEmpty()) {
            if (visited[maxHeap.peek().index]) {
                maxHeap.poll();
            } else {
                answer[0] = maxHeap.poll().num;
                break;
            }
        }

        while (!minHeap.isEmpty()) {
            if (visited[minHeap.peek().index]) {
                minHeap.poll();
            } else {
                answer[1] = minHeap.poll().num;
                break;
            }
        }

        if (minHeap.isEmpty() || maxHeap.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        }

        return answer;
    }

    static class Pair {
        int index;
        int num;

        public Pair (int index, int num) {
            this.index = index;
            this.num = num;
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
