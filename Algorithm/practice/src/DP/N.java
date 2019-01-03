package DP;

import java.util.*;

public class N {
    int answer = -1;

    public int solution (int N, int number) {
        dfs(N, 0, 0, number, "");
        return answer;
    }

    public void dfs (int n, int pos, int num, int number, String s) {
        // n -> N, pos = N을 사용한 횟수, num = number 를 만들기 위한 중간 과정
        // number = number, s = 수식
        if (pos > 8) // pos 가 8을 넘었으면 원래대로 answer 를 그대로 -1 로 반환한다.
            return;

        if (num == number) {
            // answer 가 pos 보다 작은 경우, 즉 N이 그대로 number 인 경우 그대로 반환한다.
            if (pos < answer || answer == -1) {
                answer = pos;
            }

            return;
        }

        // nn = 0;
        int nn = 0;
        // 0 부터 8까지 반복문을 돌려본다.
        for (int i = 0; i < 8; i++) {
            nn = nn*10 + n;
            // 5면 i=0일 때, 5, 1 일 때, 55, 등...
            // 그럼 이 DFS 를 거치면 5인 경우 55 인 경우 모든 사칙연산을 조합한 경우의 수가 다 나온다.
            dfs(n, pos + 1+i, num + nn, number, s + "+");
            dfs(n, pos + 1+i, num - nn, number, s + "-");
            dfs(n, pos + 1+i, num * nn, number, s +"*");
            dfs(n, pos + 1+i, num / nn, number, s + "/");
        }
    }
}

/*
    아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

    12 = 5 + 5 + (5/5) + (5/5)
    12 = 55/5 + 5/5
    12 = (55 + 5) / 5

    5를 사용한 횟수는 각각 6, 5, 4 입니다.
    그리고, 이중 가장 작은 경우는 4 입니다.
    이처럼 숫자 N과 number 가 주어질 떄, N과 사칙연산만을 사용해서 표현할 수 있는 방법 중
    N의 사용횟수의 최솟 값을 return 하도록 solution 함수를 작성하시오.

    제한 사항
    1. N 은 1 이상 9 이하 입니다.
    2. number 는 1 이상 32,000 이하입니다.
    3. 수식에는 괄호와 사칙연산만 가능하며, 나누기 연산에서 나머지는 무시합니다.
    4. 최솟 값이 8보다 크면 -1을 return 합니다.

 */
