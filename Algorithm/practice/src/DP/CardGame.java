package DP;

public class CardGame {
    private static int[][] DP;
    private static int N;
    private static int ANSWER;

    public static int solution(int[] left, int[] right) {

        // 최대 카드 뭉치의 값 설정
        N = left.length;

        // DP 메모이제이션을 할 2차원 배열 선언 DP[i][j] 가 의미하는 바는 왼쪽 카드가 i이고 오른쪽 카드가 j일 떄 얻을 수 있는 최대 값
        DP = new int[2001][2001];

        solve(left, right, 0, 0);

        return ANSWER;
    }

    public static int solve (int[] left, int[] right, int i, int j) {

        if (i >= N || j>= N) {
            DP[i][j] = 0;
            return DP[i][j];
        }

        if (left[i] > right[j]) {
            DP[i][j] = Math.max(DP[i][j], solve(left, right, i, j+1) + right[j]);
        } else {
            DP[i][j] = Math.max(solve(left, right, i+1, j), solve(left, right, i+1, j+1));
        }

        ANSWER = DP[i][j];

        return DP[i][j];
    }

    public static void main (String[] args) {
        int[] left = {3, 2, 5};
        int[] right = {2, 4, 1};

        int i = solution(left, right);

        System.out.println(i);
    }
}

/*
    카드 게임이 있다.
    게임에 사용하는 각 카드에는 양의 정수 하나가 적혀있고,
    같은 숫자가 적힌 카드는 여러 장 있을 수 있다.
    게임 방법은 우선 짝수 개의 카드를 무작위로 섞은 뒤 같은개수의 두 더미로 나누어
    하나는 왼쪽에 다른 하나는 오른쪽에 둔다.

    각 더미의 제일 위에 있는 카드끼리 서로 비교하며 게임을 한다.
    게임 규칙은 다음과 같다.
    지금부터 왼쪽 더미의 제일 위 카드를 왼쪽 카드로, 오른쪽 더미의 제일 위 카드를 오른쪽 카드로 부르겠다.

    1. 언제든지 왼쪽 카드만 통에 버릴 수도 있고, 왼쪽 카드와 오른쪽 카드를 둘 다 통에 버릴 수 있다.
    이 떄 얻는 점수는 없다.
    2. 오른쪽 카드에 적힌 수가 왼쪽 카드에 적힌 수보다 작은 경우에는 오른쪽 카드만 통에 버릴 수도 있다.
    오른쪽 카드를 버리는 경우에는 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
    3. (1)과 (2)의 규칙에 따라 게임을 진행하다가 어느 쪽 더미든 남은 카드가 없다면 게임이 끝나며
    그 떄까지 얻은 점수의 합이 최종 점수가 된다.

    왼쪽 더미의 카드에 적힌 정수가 담긴 배열 left 와 오른쪽 더미의 카드에 적힌 정수가 담긴 배열 right 가
    매개 변수로 주어질 떄, 얻을 수 있는 최종 점수의 최대 값을 return 하도록 solution 함수를 작성하시오.

    제한 사항
    - 한 더미에는 1장 이상 2,000 장 이하의 카드가 있다.
    - 각 카드에 적힌 정수는 1 이상 2,000 이하이다.
 */
