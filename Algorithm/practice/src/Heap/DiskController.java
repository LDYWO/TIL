package Heap;

import java.util.*;

public class DiskController {

    public static int solution (int[][] jobs) {
        int answer = 0;

        // jobs 배열 정렬
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) // 만약에 호출하는 시간이 같지않다면
                    return o1[0] - o2[0]; // 호출 시간이 빠른 순서대로 정렬
                return o1[1] - o2[1]; // 만약에 호출 시간이 같다면 걸리는 시간이 짧은 순서대로 정렬
            }
        });

        // Job 객체를 담는 우선순위 큐 선언
        // 우선 순위는 람다식으로 선언
        PriorityQueue<Job> pq = new PriorityQueue<>((a,b) -> a.requiredTime - b.requiredTime);

        // 현재 시간 선언, 처음 호출된 작업의 시간 + 그 작업이 걸리는 시간 = 즉, 처음 호출된 작업이 끝나는 시간
        int cnt = jobs[0][0] + jobs[0][1];
        int s = 1; // 다음 작업부터 반복문을 돌리기 위한 인덱스
        int complete = 1;
        answer = cnt - jobs[0][0]; // answer 값은 현재 시간 - 처음 작업이 불린 시간

        // complete 변수 - 완료된 작업의 갯수가 꽉차면 반복문 종료
        while (complete != jobs.length) {
            for (int i=s; i<jobs.length; i++) {
                if (cnt >= jobs[i][0]) {
                    // 만약 작업 목록 중 작업의 시작 시간이 현재 시간보다 길다면 (즉, 바로 실행할 수 있다면)
                    pq.add(new Job(jobs[i][0], jobs[i][1])); // 우선순위 큐에 작업 객체를 생성하여 넣는다.
                    if (i == jobs.length-1)
                        // 만약 인덱스 끝까지
                        s=jobs.length;
                } else {
                    // 만약 바로 실행할 수 없다면 해당 인덱스에서 멈춘다.
                    s=i;
                    break;
                }
            }

            // 만약 pq 가 비어있지 않다면
            // 하나를 실행한다.
            if (!pq.isEmpty()) {
                Job out = pq.poll();
                cnt += out.requiredTime;
                answer += cnt - out.startTime;
                complete++;
            } else {
                // 바로 실행할 작업 목록이 없다면, 현재 시간을 다음 시작할 작업의 시작 시간으로 옮기고 루프를 하나 종료한다.
                cnt = jobs[s][0];
            }
        }

        answer = answer/jobs.length;

        return answer;
    }

    // Job 클래스 선언
    static class Job {
        int startTime;
        int requiredTime;

        public Job (int startTime, int requiredTime) {
            this.startTime = startTime;
            this.requiredTime = requiredTime;
        }
    }
    public static void main (String[] args) {
        int[][] jobs = {{0,3}, {1,9}, {2,6}};
        // int[][] jobs = {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}};
        int a = solution(jobs);

        System.out.println(a);
    }
}

/*
    하드 디스크는 한번에 하나의 작업만 수행할 수 있습니다.
    디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다.
    가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것 입니다.

    예를 들어.
    - 0ms 시점에 3ms 가 소요되는 A 작업 요청
    - 1ms 시점에 9ms 가 소요되는 B 작업 요청
    - 2ms 시점에 6ms 가 소요되는 C 작업 요청

    가장 평균 수행처리 속도가 낮게 나올 수 있는 순서대로 처리를 한다.
 */
