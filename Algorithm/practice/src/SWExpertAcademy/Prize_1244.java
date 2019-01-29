package SWExpertAcademy;

import java.util.*;

public class Prize_1244 {
 /*   private static PriorityQueue<Integer> pq;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int C = sc.nextInt();
            int N = sc.nextInt();

            String S = String.valueOf(C);

            pq = new PriorityQueue<>((a,b) -> b-a); // 오름 차순으로 정렬하는 PQ

            DFS(S, N, 0);
        }
    }

    public static String swap (String S, int start, int target) {

        // s의 start 와 target 의 인덱스 값을 바꿔주는 코드
        S = S.substring(0, start) + S.charAt(target) + S.substring(start + 1, target) + S.charAt(start) + S.substring(target, S.length());

        return S;
    }

    public static void DFS (String S, int N, int cnt) {

        // 스왑 횟수만큼 DFS 를 진행했다면 DFS 를 종료한다.
        if (N == cnt) {
            return;
        }

        // 1. 스왑 할 두개의 요소를 뽑는다.
        for (int i = 0; i < )
        // 2. 스왑

        DFS(S, N, cnt + 1);

    }*/
    static int num[];
    static int count;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            String str = sc.next();
            count = sc.nextInt();
            num = new int[str.length()];

            for (int j = 0; j < str.length(); j++) {
                num[j] = (int)str.charAt(j) - 48;
            }

            max = 0;

            DFS(0, 0);
            System.out.println("#" + i + " " + (max));
        }
    }

    public static void swap (int x, int y) {
        int temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }

    public static void max() {
        String str = "";

        for (int i = 0; i < num.length; i++) {
            str = str + num[i];
        }

        int temp = Integer.parseInt(str);

        max = Math.max(temp, max);
    }

    public static void DFS (int n, int cnt) {
        if (cnt >= count) {
            max();
            return;
        }

        int sw = 0;
        for (int i = n; n < num.length-1; i++) {
            for (int j = i+1; j < num.length; j++) {
                if (num[i] = )
            }
        }
    }
}

/*
* 최대 상금
* 퀴즈 대회에 참가해서 우승을 하게 되면 보너스 상금을 획득할 수 있는 기회를 부여받는다.
* 우승자는 주어진 숫자판들 중에 두 개를 선택에서 정해진 횟수만큼 서로의 자리를 위치를 교환할 수 있다.
* 예를 들어, 다음 그림과 3 2 8 8 8 의 5 개의 숫자판들이 주어지고 교환 횟수는 2회라고 하자*/
