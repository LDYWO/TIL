package BruteForce;
import java.util.*;

// DFS 탐색 문제
/*
    기억해야할 포인트 (프레임)

    1. 재귀(DFS)
        - 2차원 배열에서 상하좌우 탐색하면서, 상하좌우 탐색
    2. 재귀 시 2가지 2차원 배열 활용
        - 방문 여부 배열 (값 0 , 1 , 2 - 3가지)
        - 값 배열 (2차원 배열 값)
    3. 반복문 활용
        - 이중 for 문
        - do - while 문
    4. 예외 처리
        - 배열 범위
        - 방문 여부
        - 초기 값

 */

public class HumanMoving {
    static int N, L, R;
    static int Map[][] = new int[50][50];
    static int Visited[][] = new int[50][50];
    static int Cnt; // 연합을 이루는 국가의 수

    // 연합을 이루는 국가를 찾는 함수
    static int find(int r, int c, int value) {
        // 범위를 벗어난 경우 예외 처리
        if (r < 0 || r > N-1 || c < 0 || c > N-1) return 0;
        // 방문한 국가면 0으로 반환
        if (Visited[r][c] != 0) return 0;

        // 인구 수가 최초가 아닌 경우 (-1은 최초 값인 경우)
        if (value != -1) {
            int diff = Math.abs(value - Map[r][c]); // 차이 (절댓값)
            if (diff < L || diff > R) return 0; // 범위를 벗어나면 0 반환
        }

        ++Cnt;
        Visited[r][c] = 1;

        int sum = Map[r][c];
        sum += find(r-1, c, Map[r][c]); // 상
        sum += find(r+1, c, Map[r][c]); // 하
        sum += find(r, c-1, Map[r][c]); // 좌
        sum += find(r, c+1, Map[r][c]); // 우

        return sum;
    }

    static void move(int r, int c, int value) {
        // 범위를 벗어난 경우 예외 처리
        if (r < 0 || r > N-1 || c < 0 || c > N-1) return;
        // 방문한 국가면 0으로 반환
        if (Visited[r][c] != 1) return;

        Visited[r][c] = 2;

        // 인구이동 -> value 값으로 전체 초기화
        Map[r][c] = value;
        move(r-1, c, value);
        move(r+1, c, value);
        move(r, c-1, value);
        move(r, c+1, value);

    }

    static int solve() {
        int ret = 0;
        boolean flag;

        // 끝날 때까지 멈추지 않는 탐색 = do while
        do {
            flag = false;

            // 방문한 국가 0으로 초기화
            for (int i = 0; i<N; ++i)
                for (int j = 0; j<N; ++j)
                    Visited[i][j] = 0;


            for (int i=0; i<N; ++i ) {
                for (int j=0; j<N; ++j) {
                    // 방문한 적이 없는 국가일 경우
                    if(Visited[i][j] == 0) {
                        Cnt = 0; // 연합을 이루는 국가의 개수
                        // 연합을 이루는 국가의 전체 인구수를 구하는 함수
                        int sum = find(i, j, -1);

                        // 연합을 이루는 국가의 개수가 1 이상이라면 인구이동
                        if(Cnt > 1) {
                            flag = true; // do를 해야하는 것
                            move(i, j, sum / Cnt); // 파라미터 의미 -> i, j 위치, value = Cnt
                        } else {
                            Visited[i][j] = 2;
                        }
                    }
                }
            }

            if(flag) ++ret;
        } while (flag);

        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        for (int i=0; i < N; ++i) {
            for (int j=0; j<N; ++j) {
                Map[i][j] = sc.nextInt();
            }
        };

    }
}

