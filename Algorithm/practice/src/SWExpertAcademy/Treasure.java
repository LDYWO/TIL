package SWExpertAcademy;

import java.util.*;

public class Treasure {
    public static final int MAX_N = 45;
    static int N, K;
    // 입력 값을 저장할 배열
    static int[] Digits;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int tcCnt = sc.nextInt();
        char[] temp = new char[MAX_N + 1];

        for (int t = 1; t <= tcCnt; ++t) {
            N = sc.nextInt();
            K = sc.nextInt();
            // 연속된 16 진수 숫자 입력
            String s = sc.nextLine();
            s = sc.nextLine();

            for (int i1 = 0; i1 < s.length(); i1++) temp[i1] = s.charAt(i1);
            Digits = new int[MAX_N];

            for (int i = 0; i < N; ++i) {
                // i 번쨰 숫자가 10 보다 작을 경우
                if (temp[i] < 'A') Digits[i] = temp[i] - '0';
                // i 번째 숫자가 10 보다 같거나 큰 경우
                else Digits[i] = temp[i] - 'A' + 10;
            }

            // 배열 앞의 N/4 개의 데이터를 맨 뒤에 추가해줌
            for (int a = 0; a < N / 4; ++a)
                Digits[N + a] = Digits[a];

            System.out.println(t + ": " + solve());
        }

    }

    public static int solve() {
        // 16 진수를 10 진수로 변환한 숫자들을 저장할 배열
        int[] nums = new int[MAX_N];
        int cnt = 0;
        // 한 변에 들어갈 숫자의 개수
        int len = N / 4;

        // 배열의 시작 index
        for (int i = 0; i < len; ++i) {
            // 마름모 4개의 변
            for (int j = 0; j < 4; ++j) {
                int tmp = 0;
                // 마름모 한 개의 변에 있는 숫자들
                for (int k = 0; k < len; ++k) {
                    // 숫자들의 자리수 증가
                    tmp *= 16;
                    // j 구간의 k 번 째 숫자를 더함
                    tmp += Digits[i + j * len + k];
                }
                // 만들어진 N/4 자리 수 저장
                nums[cnt++] = tmp;
            }
        }

        // 내림차순으로 정렬
            for (int i = 0; i < cnt - 1; ++i) {
                for (int j = i + 1; j < cnt; ++j) {
                    // 현재 위치의 값(앞의 값) 보다 뒤의 값이 작으면 자리 변환
                    if (nums[i] < nums[j]) {
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                    }
                }
            }

            int k;
            for (k = 0; k < K; k++) {
                // 중복된 수일 경우, K++ 번째 수를 찾는 것으로 변경
                if (k > 0 && nums[K] == nums[k-1]) K++;
            }

            return nums[k-1];
    }
}
