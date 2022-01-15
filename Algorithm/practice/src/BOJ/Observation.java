import java.util.*;

public class Observation {

    static int INF = 987654321;
    static int N, M;
    static int[][] Map = new int[8][8];
    static int CamCnt;
    static int[] CamType = new int[8];
    static int[] CamRow = new int[8];
    static int[] CamCol = new int[8];
    static int[] Dr = {0, -1, 0, 1}; // 0 = 우, 1 = 상, 2 = 좌, 3 = 하
    static int[] Dc = {1, 0, -1, 0};
    static int[][] Dcam = {
            {1, 0, 0, 0, 4},
            {1, 0, 1, 0, 2},
            {1, 1, 0, 0, 4},
            {1, 1, 1, 0, 4},
            {1, 1, 1, 1, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        CamCnt = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                Map[i][j] = sc.nextInt();
                if (Map[i][j] >= 1 && Map[i][j] <= 5) {
                    CamType[CamCnt] = Map[i][j];
                    CamRow[CamCnt] = i;
                    CamCol[CamCnt++] = j;
                }
            }
        }

        System.out.println(solve(0));
    }

    static int solve (int pos) {
        if (pos == CamCnt) {
            int sum = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (Map[i][j] == 0) ++sum;
                }
            }

            return sum;
        }

        int[][] backup = new int[8][8];
        int ret = INF;

        int type = CamType[pos] - 1;

        for (int i = 0; i < Dcam[type][4]; ++i) {

            copymap(backup, Map);

            for (int j = 0; j < 4; ++j) {
                if (Dcam[type][j] == 1)
                    watch(CamRow[pos], CamCol[pos], (i + j)%4);
            }

            ret = Math.min(ret, solve(pos + 1)); // 해당 케이스에서 다음 케이스로 넘어가는 부분 (백트래킹)

            copymap(Map, backup);
        }

        return ret;
    }

    static void copymap(int[][] dst, int[][] src) {
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j)
                dst[i][j] = src[i][j];
    }

    static void watch (int r, int c, int dir) {
        int srow = r, scol = c;

        for (;;) {
            int nr = srow + Dr[dir], nc = scol + Dc[dir];
            if (nr < 0 || nr > N-1 || nc < 0 || nc > M-1) return;
            if (Map[nr][nc] == 6) return;
            Map[nr][nc] = 7;

            srow = nr;
            scol = nc;
        }
    }
}

/*
    감시

    스타트링크의 사무실은 1*1 크기의 정사각형으로 나누어져 있는 N*M 크기의 직사각형으로 나타낼 수 있다.
    사무실에는 총 K 개의 CCTV 가 설치되어져 있는데, CCTV 는 5가지 종류가 있다.
    각 CCTV 가 감시할 수 있는 방법은 다음과 같다.

    1번 CCTV 는 한 쪽 방향만 감시할 수 있다.
    2번과 3번은 두 방향을 감시할 수 있는데,
    2번은 감시하는 방향이 서로 반대 방향이어야 하고,
    3번은 직각 방향 이어야 한다.
    4번은 세 방향
    5번은 네 방향을 감시할 수 있다.

    CCTV 는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.
    사무실에는 벽이 있는데, CCTV 는 벽을 통과할 수 없다.
    CCTV 가 감시할 수 있는 영역은 사각지대라고 한다.

    CCTV 는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.

    지도에서 0은 빈 칸
    6은 벽, 1 ~ 5 는 CCTV 의 번호이다.
    위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 # 로 나타내면 아래와 같다.

    사무실의 크기와 상태, 그리고 CCTV 의 정보가 주어졌을 때, CCTV 의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는
    프로그램을 작성하시오.

    입력

    첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. ( 1 <= N, M <= 8)
    둘째 줄부터 N개의 줄에는 사무실의 각 칸의 정보가 주어진다.
    0은 빈 칸, 6은 벽, 1 ~ 5는 CCTV 를 나타내고, 문제에서 설명한 CCTV 의 종류이다.

    CCTV 의 최대 개수는 8개를 넘지 않는다.

    1번 가지수 4
    2번 가지수 2
    3번 가지수 4
    4번 가지수 4
    5번 가지수 1
 */