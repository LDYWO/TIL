package SWExpertAcademy;

import java.util.*;

public class ProgrammingContest_6913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        // Test Case 만큼 반복문 수행
        for (int i = 1; i <= T ; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] matrix = new int[N][M];
            int[] answer = new int[N];

            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < M; k++) {
                    int a = sc.nextInt();
                    sum +=  a;
                }
                answer[j] = sum;
            }
        }
    }
}

/*
* N 명의 사람들이 어떤 프로그래밍 대회에 참가했다.
* 대회에는 M개의 문제가 나왔다.
* 동철이는 이 프로그래밍 대회가 열렸다는 소식을 접했고,
* 간단한 웹 서핑으로 각 사람들이 문제를 풀었는지 아닌지를 나타내는 N*M 개의 값 를 구할 수 있었다.
* 사람에 1에서 N 까지의 번호를 붙이고, 문제에도 1에서 M 까지의 번호를 붙일 때,
* a ij 는 대회가 끝나고 i 번 사람이 j 번 문제를 풀었다면 1, 풀지 못했다면 0을 가지는 값이다.
* 동철이는 이 대회에는 나가지 못했지만, 다른 프로그래밍 대회에 나갈 계회깅고 목표는 우승이다
* */