package Stack;

import java.util.*;

public class Truck {

    public static void main(String[] args) {

        int[] trucks = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        int answer = solution(100, 100, trucks);

        System.out.println(answer);
    }

    public static int solution (int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Deque<truck> readyQueue = new ArrayDeque<>();
        Deque<truck> bridgeQueue = new ArrayDeque<>();

        // 대기열 큐에 다리를 지날 트럭의 무게 값을 차례로 대입
        for (int i : truck_weights) {
            readyQueue.offer(new truck(i));
        }

        // 대기열의 트럭을 다리에 넣는다.
        answer++;
        readyQueue.peek().position++;
        bridgeQueue.offer(readyQueue.poll());

        while (!bridgeQueue.isEmpty()) {
            answer++;

            // position 하나씩 이동
            for (truck t : bridgeQueue) {
                t.position++;
            }

            if (bridgeQueue.peek().position > bridge_length) {
                bridgeQueue.poll();
            }

            if (!readyQueue.isEmpty() && bridgeWeight(bridgeQueue) + readyQueue.peek().weight <= weight) {
                readyQueue.peek().position++;
                bridgeQueue.offer(readyQueue.poll());
            }
        }

        return answer;
    }

    // 다리 위 무게의 합을 계산해주는 함수
    public static int bridgeWeight(Deque<truck> deque) {
        int sum = 0;

        for (truck t : deque) {
            sum += t.weight;
        }

        return sum;
    }

    // truck 객체, 무게 정보와 위치 정보를 가지고 있다.
    static class truck {
        int weight;
        int position;

        // Constructor
        public truck(int weight) {
            this.weight = weight;
            this.position = 0;
        }
    }
}

/*
    1. 트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 한다.
    2. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 한다.
    3. 다리 길이는 weight_length 이고 다리는 무게 weight 까지 버틴다.

    매개 변수로 다리 길이, 다리가 견딜 수 있는 무게, 트럭별 무게가 주어진다.
    이 때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하라.

 */
