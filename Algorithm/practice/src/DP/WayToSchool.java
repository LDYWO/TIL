package DP;

public class WayToSchool {
    private static int[][] WAY;
    public int solution (int m, int n, int[][] puddles) {
        int answer = 0;

        WAY = new int[m][n];

        return answer;
    }

    public static int DP (int[][] puddles) {

        WAY[0][0] = 0;

        for (int i=0; i<puddles.length; i++) {
            WAY[puddles[i][0]][puddles[i][1]] = -1;
        }

        for (int i=0; i<WAY.length; i++) {
            if (WAY[i][0] != -1) WAY[i][0] = 1;
        }

        for (int i=0; i<WAY[0].length; i++) {
            if (WAY[0][i] != -1) WAY[0][i] = 1;
        }

        for (int i=1; i<WAY.length; i++) {
            for (int j=1; j<WAY[i].length; j++) {
                
            }
        }
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