package DP;

import java.io.*;
import java.util.*;

public class rodCutting {
    private static int N;
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] partPrice;
    private static int[] maxPricePerLength;

    public static void main (String[] args) throws NumberFormatException, IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        partPrice = new int[N+1];
        maxPricePerLength = new int[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=N; i++) {
            partPrice[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        for (int i=1; i<=N; i++) {
            int max = 0;
            for (int j=1; j<=i; j++) {
                max = Math.max(max, partPrice[j] + maxPricePerLength[i-j]);
            }
            maxPricePerLength[i] = max;
        }

        System.out.println(maxPricePerLength[N]);
    }
}

/*
    길이가 N 인 막대의 각 잘린 조각마다 최대 값이 정해져 있을 때,
    가장 최대의 가격으로 쪼개는 경우를 계산하는 방법

    예를 들어, 길이 4인 막대의 최댓값은 아래 구해지는 값들 중 최대를 취하면 될 것이다.
    길이 3의 막대로 받을 수 있는 최대 값에 길이 1 막대의 값을 더한 값
    길이 2의 막대로 받을 수 있는 최대 값에 길이 2 막대의 값을 더한 값
    ...
    길이 4 막대 자체의 값

    길이 4의 막대의 최댓 값을 구하기 위해서 길이 1,2,3 막대의 최댓 값을 먼저 구해 이를 사용하게 된다.
    이렇게 작은 문제의 해답으로 큰 문제를 해결하는 것이 동적 계획법의 기본 개념이다.
 */