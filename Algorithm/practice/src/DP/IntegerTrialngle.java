package DP;

import java.util.*;

public class IntegerTrialngle {
    private static int[][] COST;
    public static int solution (int[][] triangle) {
        int answer = 0;

        COST = new int[501][501];

        answer = DP(triangle);

        return answer;
    }

    public static int DP (int[][] triangle) {

        COST[0][0] = triangle[0][0];

        for (int i=1; i<triangle.length; i++) {
            COST[i][0] = COST[i-1][0] + triangle[i][0]; // 각 층의 첫 번째 노드
            COST[i][i] = COST[i-1][i] + triangle[i][i]; // 각 층의 마지막 노드
        }

        for (int i=1; i<triangle.length; i++) {
            for (int j=1; j<triangle[i].length; j++) {
                COST[i][j] = triangle[i][j] + Math.max(COST[i-1][j], COST[i-1][j-1]);
            }
        }

        int max = 0;
        for (int i=0; i<triangle.length; i++) {
            max = Math.max(max, COST[triangle.length-1][i]);
        }

        return max;
    }

    public static void main (String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int a = solution(triangle);

        System.out.println(a);
    }
}

/*
    정수 삼각형

    위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이
    가장 큰 경우를 찾아보려고 한다.
    아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다.
    삼각형의 정보가 담긴배열 triangle 이 매개변수로 주어질 때, 거쳐간 숫자의 최댓 값을
    return 하도록 solution 함수를 완성하시오.

    제한 사항
    - 삼각형의 높이는 1 이상 500 이하입니다.
    - 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.

    DP를 풀 때는 들어온 배열과 같은 크기의 배열을 선언해준 후에
    하나 하나 차근히 문제를 확장해나가면서 풀어주면 된다.

 */
