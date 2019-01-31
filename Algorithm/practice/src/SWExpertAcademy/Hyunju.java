package SWExpertAcademy;

import java.util.*;

// 완료
public class Hyunju {
    static int solve(int N) {
        int cnt = 0;

        if (N == 2) return 0;
        else {
            while (N != 2) {
                // N 이라는 숫자가 2가 될 때까지 계속 반복문을 돌린다.
                if (Math.sqrt(N)%1 == 0 ) {
                    // 만약 N의 제곱근이 정수라면 N을 제곱근으로 만든다
                    N = (int)Math.sqrt(N);
                } else {
                    // 아니라면 1을 대입한다
                    N += 1;
                }

                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            System.out.println("#" + i + " " + solve(N));
        }

    }
}

/*
* 현주가 좋아하는 제곱근 놀이
* 어느날 현주는 제곱근 놀이를 만들기로 했는데, 룰은 다음과 같다.
* 2 이상의 어떤 정수 N이 있다
* N 을 N + 1로 바꿀 수 있다
* 루트 N이 정수일 때, N을 루트 N으로 바꿀 수 있다
*
* 게임의 목표는 N을 2로 만드는 것이다
*
* 입력 첫 번째 줄에 테스트 케이스의 수 T가 주어진다
* 각 테스트 케이스의 첫 번째 줄에는 하나의 정수가 주어진다*/