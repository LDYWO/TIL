package DP;

public class WayToSchool {
    private static int[][] MAP;

    public int solution (int m, int n, int[][] puddles) {
        int answer = 0;

        /*
            문제 푸는 순서

            1. 2차원 배열 생성
            2. puddles set up
            3. 맵 순회
            4. 1,0000000007로 나누기
         */

        MAP = new int[m+1][n+1];

        for (int i=0; i<puddles.length; i++) {
            MAP[puddles[i][0]][puddles[i][1]] = -1;
        }

        MAP[1][1] = 1;

        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (MAP[i][j] == -1) {
                    MAP[i][j] = 0;
                    continue;
                }
                if (i != 1 || j != 1) MAP[i][j] = (MAP[i-1][j] + MAP[i][j-1])%1000000007;
            }
        }

        answer = MAP[m][n]%1000000007;

        return answer;
    }
}

/*
    등굣길

    계속되는 폭우로 일부 지역이 물에 잠겼습니다.
    물에 잠기지 않은 지역을 통해 학교를 가려고 합니다.
    집에서 학교까지 가는 길은 m*n 크기의 격자모양으로 나타낼 수 있습니다.
    아래 그림은 m = 4, n = 3 인 경우 입니다.

    가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1,1)로 나타내고 가장 오른쪽 아래,
    즉 학교가 있는 곳의 좌표는 (m, n) 으로 나타냅니다.

    격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles 이
    매개변수로 주어질 떄, 학교에서 집까지 갈 수 있는 최단 경로의 개수를
    1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    - 격자의 크기 M, N 은 1 이상 100 이하인 자연수입니다.
    - 물에 잠긴 지역은 0 개 이상 10 개 이하입니다.
    - 집과 학교는 물에 잠기지 않았습니다.

 */