package DP;

public class Theft {

    public int solution (int[] money) {

        int[] dp1 = new int[1000000]; // dp를 두개씩 만들어준다. 첫 번째 집을 털 때와
        int[] dp2 = new int[1000000]; // 두 번째 집부터 털 때
        int N = money.length-1;

        dp1[0] = money[0];
        dp1[1] = dp1[0];
        dp2[1] = money[1];

        // 마지막 집 전까지 반복문 순회 (마지막 집은 첫 번째 집을 터냐 안터냐에 상관있기 때문에
        for (int i=2; i<N; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }

        dp1[N] = dp1[N-1];
        dp2[N] = Math.max(dp2[N-1], dp2[N-2] + money[N]);

        return Math.max(dp1[N], dp2[N]);
    }
}

/*
    도둑질

    도둑이 어느 마을을 털 계획을 하고 있습니다.
    이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.
    각 집들은 서로 인접한 집들과 방범 장치가 연결되어 있기 떄문에 인접한 두 집을 털면
    경보가 울립니다.

    각 집에 있는 돈이 담긴 배열 money 가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓 값을
    return 하도록 solution 함수를 작성하세요.

    제한 사항
    - 이 마을에 있는 집은 3 개 이상 1,000,000개 이하 입니다.
    - money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.

    입출력 예
    [1, 2, 3, 1] = 4
 */