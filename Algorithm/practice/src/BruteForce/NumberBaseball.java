package BruteForce;

import java.util.*;

public class NumberBaseball {
    public static void main(String[] args) {
        int[][] progresses = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};

        int answer = solution(progresses);

        System.out.print(answer);
    }

    public static int solution (int[][] baseball) {
        int answer = 0;

        for (int i=1; i<10; i++) {
            for (int j=1; j<10; j++) {
                if (j==i) continue;
                for (int k=1; k<10; k++) {
                    if (k==j || k==i) continue;

                    boolean isTrue = true;

                    for (int t=0; t<baseball.length; t++) {

                        String num = String.valueOf(baseball[t][0]);
                        int strike = 0;
                        int ball = 0;

                        if (i == Integer.parseInt(String.valueOf(num.charAt(0)))) {
                            strike++;
                        } else if (i == Integer.parseInt(String.valueOf(num.charAt(1))) || i == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                            ball++;
                        }

                        if (j == Integer.parseInt(String.valueOf(num.charAt(1)))) {
                            strike++;
                        } else if (j == Integer.parseInt(String.valueOf(num.charAt(0))) || j == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                            ball++;
                        }

                        if (k == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                            strike++;
                        } else if (k == Integer.parseInt(String.valueOf(num.charAt(0))) || k == Integer.parseInt(String.valueOf(num.charAt(1)))) {
                            ball++;
                        }

                        if (strike != baseball[t][1] || ball != baseball[t][2]) isTrue = false;

                        if (!isTrue) break;
                    }

                    if (isTrue) answer++;
                }
            }
        }

        return answer;
    }
}

/*
    숫자 야구 게임이란 2명이 서로가 생각한 숫자를 맞추는 게임입니다.

    각자 서로 다른 1 - 9 까지 3자리 임의의 숫자를 정한 뒤 서로에게
    3 자리의 숫자를 불러서 결과를 확인합니다.

    그리고, 그 결과를 토대로 상대가 정한 숫자를 예상한 뒤 맞힙니다.

    숫자는 맞지만, 위치가 틀렸을 때는 볼
    숫자와 위치가 모두 맞을 때는 스트라이크
    숫자와 위치가 모두 틀렸을 때는 아웃

    123 - 1 스트라이크 1볼
    356 - 1 스트라이크 0볼
    327 - 2 스트라이크 0볼
    489 - 0 스트라이크 1볼

    이 때, 가능한 답은 324, 328 두 가지 이다.

    질문한 세 자리의 수, 스트라이크의 수, 볼의 수를 담은 2차원 배열
    baseball 이 매개변수로 주어질 때, 가능한 답의 개수를 return 하라

    제한 사항
    1. 질문의 수는 1 이상 100 이하의 자연수
    2. baseball 의 각 행은 [세 자리의 수, 스트라이크, 볼] 을 담고있다.

 */
