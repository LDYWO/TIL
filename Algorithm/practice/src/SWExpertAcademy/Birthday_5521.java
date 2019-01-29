package SWExpertAcademy;

import java.util.*;

public class Birthday_5521 {
    private static boolean[] visit;
    public static void main(String[] args) {
        /*
            일단 풀이 순서
            1. N과 M을 입력 받는다.
            2. DFS 로 풀어보자
            3. 상원이와 네트워크를 형성하고 있는 아이들을 모두 출력해주면 된다.
         */
        Scanner sc = new Scanner(System.in);
        int T =  sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
/*
            int[][] rel = new int[M+1][2];
            visit = new boolean[N+1];

            for (int i=1; i<=M; i++) {
                rel[i][0] = sc.nextInt();
                rel[i][1] = sc.nextInt();
            }

            int cnt = 0;

            for (boolean chk : visit) {
                if (chk) cnt++;
            }

            System.out.println("#" + test_case + " " + cnt);*/

            int arr[][] = new int[N][N];

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                arr[a-1][b-1] = 1;
                arr[b-1][a-1] = 1;
            }

            int check[] = new int[N];

            int count = 0;

            for (int i = 0; i < check.length; i++) {
                if (check[i] == 1) {
                    check[i] = 1;
                    count++;
                }
            }
            for (int i = 1; i < check.length; i++) {
                if (check[i] == 1) {
                    for (int j = 1; j < check.length; j++) {
                        if (arr[i][j] == 1 && check[j] == 0) {
                            check[j] = 2;
                            count++;
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + count);
        }
    }
/*
    public static void DFS (int[][] rel, int start) {

        boolean isEnd = true;
        visit[start] = true; // start 인덱스로 시작하는 노드를 방문

        for (int i=1; i<=rel.length; i++) {
            if (rel[i][0] == start && !visit[start]) {
                // 만약 start 와 같고 한번도 방문하지 않았다면 방문한다.
                isEnd = false;
                DFS(rel, rel[i][1]);
            }
        }

        if (isEnd) visit[rel[start][1]] = true;
    }*/
}

/*
* 상원이의 생일 파티
* 상원이의 생일 파티가 곧 열린다.
* 그러나 파티가 어색하게 되는 것을 원치 않는 상원이는
* 모든 친구들에게 초대장을 줄 생각이 없다.
* 같은 반에 있지만, 서로 친한 친구가 아닐 수도 있기 때문이다.
* 상원이는 우선 자신과 친한 친구들에게 모두 초대장을 주기로 했다.
* 총 몇 명의 친구들에게 초대장을 주어야 하는지 구하는 프로그램을 작성하라
* 상원이의 반에는 N명이 있으며, 각 학생들은 1번에서 N 번까지의 번호가 붙어있다.
* 여기서 1번 학생이 상원이다.
*/