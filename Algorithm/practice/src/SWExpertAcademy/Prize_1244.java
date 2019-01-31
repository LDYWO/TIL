package SWExpertAcademy;

import java.util.*;

// 완료
public class Prize_1244 {
    static int num[];
    static int count;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            String str = sc.next(); // 입력된 숫자 값을 스트링으로 저장한다.
            count = sc.nextInt(); // 스왑을 할 횟수
            num = new int[str.length()]; // 스트링으로 받은 숫자를 정수 배열에 대입

            // 스트링 값을 하나하나 쪼개서 대입
            for (int j = 0; j < str.length() ; j++) {
                num[j] = (int)str.charAt(j) - 48;
            }

            // 최댓 값을 저장하는 정수
            max = 0;

            // DFS 를 실행한다
            DFS(0, 0);

            System.out.println("#" + (i + 1) + " " + (max));
        }
    }

    // 자릿 수를 바꾸는 함수
    public static void swap(int x, int y) {
        int temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }

    // 최댓 값을 갱신하는 함수
    public static void max() {
        String str = "";

        for (int i = 0; i < num.length; i++) {
            str = str + num[i];
        }

        int temp = Integer.parseInt(str);

        max = Math.max(max, temp);
    }

    public static void DFS (int n, int cnt) {
        if (cnt >= count) {
            // DFS 를 진행한 횟수가 전체 스왑 횟수 제한보다 크거나 같아지는 순간 (모든 경우의 수에서 가장 큰 경우)
            max();
            return; // max() 함수를 실행하여 최댓 값을 저장한 뒤 DFS 를 종료한다
        }

        // 앞부터 차례대로 백트래킹 (DFS 를 실행하는 횟수만큼 인덱스가 이동된다
        for (int i = n; i < num.length; i++) {
            // 해당 인덱스보다 뒤에 있는 애들을 다 검사한다.
            for (int j = i + 1; j < num.length; j++) {
                // 만약 맨 앞에 있는 인덱스의 값보다 큰 것이 있다면 교환한다
                if (num[i] <= num[j]) {
                    swap(j, i);
                    DFS(j, cnt + 1);
                    // 교환한 뒤 다시 되돌려준다. (모든 경우의 수를 고려해야 하기 때문에)
                    swap(i, j);
                }

            }
        }
    }
}

/*
* 최대 상금
* 퀴즈 대회에 참가해서 우승을 하게 되면 보너스 상금을 획득할 수 있는 기회를 부여받는다.
* 우승자는 주어진 숫자판들 중에 두 개를 선택에서 정해진 횟수만큼 서로의 자리를 위치를 교환할 수 있다.
* 예를 들어, 다음 그림과 3 2 8 8 8 의 5 개의 숫자판들이 주어지고 교환 횟수는 2회라고 하자*/
