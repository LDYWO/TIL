import java.util.*;

public class Immigration {
    static int N, L, R;
    static int Map[][] = new int[50][50];
    static int Visited[][] = new int[50][50]; // 값이 0이면 방문한 적이 없는 것, 1이면 연합을 이루는 국가, 2면 이미 방문한 국가
    static int Cnt; // 연합을 이루는 총 국가의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Map[i][j] = sc.nextInt();
            }
        }

        System.out.println(solve());
    }

    static void move(int r, int c, int value) {
        if (r < 0 || r > N-1 || c < 0 || c > N-1) return; // 2차원 배열의 범위를 벗어났다면 예외 처리
        if (Visited[r][c] != 1) return; // 연합을 이루고 있지 않은 나라라면 예외 처리

        Visited[r][c] = 2; // 이미 방문한 국가라고 처리 해준다.

        Map[r][c] = value; // 그 나라의 인구수를 value 값으로 바꿔준다.
        move(r-1, c, value);
        move(r+1, c, value);
        move(r, c-1, value);
        move(r, c+1, value);
    }

    // 하나를 기준으로 좌 우 위 아래 순서로 범위 안에 들어오는 국경선을 개방해야 하는 나라를 찾는 함수
    static int find (int r, int c, int value) {
        if (r < 0 || r > N-1 || c < 0 || c > N-1) return 0; // 2차원 배열의 범위를 벗어났다면 예외 처리
        if (Visited[r][c] != 0) return 0; // 이미 방문 했다면 예외 처리

        if (value != -1) {
            // 초기 방문이 아니라면 차이를 구한다
            int diff = Math.abs(value - Map[r][c]); // 음수가 나올 수도 있기 때문에 절댓값 처리
            if (diff  < L || diff > R) return 0; // 만약에 범위 안에 있는 것이 아니라면 종료한다. (예외 처리)
        }

        Visited[r][c] = 1; // 방문한 것으로 1로 처리
        ++Cnt; // 연합의 갯수 추가

        // 차례대로 검사하여 sum 에 더한다.
        int sum = Map[r][c];
        sum += find(r-1, c, Map[r][c]);
        sum += find(r+1, c, Map[r][c]);
        sum += find(r, c-1, Map[r][c]);
        sum += find(r, c+1, Map[r][c]);

        return sum;
    }

    static int solve() {
        int ret = 0;
        boolean flag;

        // 인구 이동이 만약에 일어나면 flag 를 통해서 do while 반복을 한다.
        do {
            // flag 초기화
            flag = false;

            // 방문을 초기화
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    Visited[i][j] = 0;
                }
            }

            // 만약 방문을 하지 않았다면
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (Visited[i][j] == 0) {
                        // Cnt = 연합의 갯수
                        Cnt = 0;
                        int sum = find(i, j, -1);

                        if (Cnt > 1) {
                            // 연합의 갯수가 1보다 크다면 move 함수를 실행한다.
                            flag = true;
                            move (i, j, sum / Cnt);
                        } else {
                            // 연합을 이루고 있는 국가가 아니라면 2로 처리하여 다시 방문하지 않도록 한다.
                            // 결국에 if 절에 걸리지 않고 else 절에 모두 걸려 flag 값이 true 로 바뀌지 않으면 인구이동이 더이상 일어나지 않는 것이다.
                            Visited[i][j] = 2;
                        }
                    }
                }
            }
            if (flag) ++ret; // 인구 이동의 횟수를 증가시킨다.
        } while (flag);

        return ret;
    }
}

// 삼성 소프트웨어 역량 평가
// 인구 이동 문제