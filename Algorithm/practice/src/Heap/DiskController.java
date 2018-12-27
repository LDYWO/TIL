package Heap;

import java.util.*;

public class DiskController {

    public static int solution (int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        PriorityQueue<Job> pq = new PriorityQueue<>((a,b) -> a.requiredTime - b.requiredTime);

        int cnt = jobs[0][0] + jobs[0][1];
        int s = 1;
        int complete = 1;
        answer = cnt - jobs[0][0];

        while (complete != jobs.length) {
            for (int i=s; i<jobs.length; i++) {
                if (cnt >= jobs[i][0]) {
                    pq.add(new Job(jobs[i][0], jobs[i][1]));
                    if (i == jobs.length -1)
                        s=jobs.length;
                } else {
                    s=i;
                    break;
                }
            }

            if (!pq.isEmpty()) {
                Job tmp = pq.poll();
                cnt += tmp.requiredTime;
                answer += cnt - tmp.startTime;
                complete++;
            } else
                cnt = jobs[s][0];
        }

        answer = answer/jobs.length;

        return answer;
    }

    static class Job {
        int startTime;
        int requiredTime;

        public Job(int startTime, int requiredTime) {
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
