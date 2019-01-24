package SWExpertAcademy;

import java.util.*;

public class Brick {

    static int N, W, H, ANSWER;
    // Data: 초기 벽돌의 위치를 저장하는 배열
    static int[][] Data;
    // Map: 벽돌이 폭결을 당한 이 후의 상태를 저장할 배열
    static int[][] Map;

    public static void init() {
        ANSWER = 987654321;
    }

    // 폭격 이전의 벽돌의 상태를 Map 에 저장
    public static void block_init() {
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++) Map[i][j] = Data[i][j];
    }

    public static boolean bomb_1() {
        for (int i = 0; i < H ; i++) {
            for (int j = 0; j < W; j++) {
                // 지워야할 벽돌일 경우
                if (Map[i][j] > 10) {
                    // d : 벽돌의 숫자 (폭발할 벽돌 표시를 위해 +10 했던 값을 다시 지워줌)
                    int d = Map[i][j] - 10;
                    // 현재 위치의 벽돌을 제거
                    Map[i][j] = 0;

                    int sx = j - d + 1; // sx : 제거할 벽돌의 왼쪽 경계선
                    int ex = j + d - 1; // ex : 제거할 벽돌의 오른쪽 경계선
                    if (sx < 0) sx = 0; // 왼쪽 경계선의 최솟 값은 0
                    if (ex >= W) ex = W - 1; // 오른쪽 경계선의 최댓 값은 W-1

                    boolean flag = false;
                    // 좌, 우에 제거할 수 있는 범위 내에 있는 벽돌 탐색
                    for (int x = sx; x <= ex; x++)
                        // 제거할 벽들의 숫자가 1이면 해당 벽돌을 지워줌
                        if (Map[i][x] == 1) Map[i][x] = 0;
                        // 제거할 벽돌의 숫자가 1보다 크면 지워야할 벽돌로 표사 (+10)
                        else if (Map[i][x] > 1 && Map[i][x] < 10) {
                            Map[i][x] += 10;
                            flag = true;
                        }

                    int sy = i - d + 1; // sy : 제거할 벽돌의 위쪽 경계선
                    int ey = i + d - 1; // ey : 제거할 벽돌의 아래쪽 경계선
                    if (sy < 0) sy = 0; // 위쪽 경계선의 최솟 값은 0
                    if (ey >= H) ey = H - 1; // 아래쪽 경계선의 최솟 값은 H-1
                    // 위, 아래에 제거할 수 있는 범위 내에 있는 벽돌 탐색
                    for (int y = sy; y <= ey ; y++)
                        // 제거할 벽돌의 숫자가 1이면 해당 벽돌을 지워줌
                        if (Map[y][j] == 1) Map[y][j] = 0;
                        // 제거할 벽돌의 숫자가 1보다 크면 지워야할 벽돌로 표시 (+10)
                        else if (Map[y][j] > 1 && Map[y][j] < 10) { Map[y][j] += 10; flag = true; }


                    // 지워야 할 벽돌이 남아 있을 경우 return 1
                    if (flag) return true;
                }
           }
        }

        // 더이상 폭발할 벽돌이 없을 경우 return 0
        return false;
    }

    // 구슬이 명중될 때 벽돌을 지움
    static void bomb (int x) {
        // +10의 의미는 지워야 하는 애를 표시해주는거임
        for (int y = 0; y < H; y++)
            // 벽돌의 숫자가 1일 경우, 해당 벽돌을 지워줌
            if (Map[y][x] == 1) {Map[y][x] = 0; return;}

            // 벽돌의 숫자가 1이면 지워야할 벽돌로 표시 (+10)
            else if (Map[y][x] > 1) { Map[y][x] += 10; break;}

        boolean flag = true;
        // 벽돌에 더 이상 폭발할 벽돌이 없을 떄까지 벽돌을 지워줌
        while (flag) flag = bomb_1();
    }

    // 벽돌의 빈 공간을 제거
    static void down() {
        for (int i = 0; i < W; i++) {
            for (int j =H - 1; j >= 0 ; j--) {
                // 빈 공간이 있을 때
                if (Map[j][i] == 0) {
                    // 빈 공간보다 위에
                    for (int y = i -1; y >= 0; y--) {
                        // 폭발되지 않은 벽돌이 있으면
                        if (Map[y][i] != 0) {
                            // 벽돌을 아래로 밀어준다.
                            Map[j][i] = Map[y][i];
                            Map[y][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    static void block_check() {
        int cnt = 0;
        for (int i = 0; i < H ; i++) {
            for (int j = 0; j < W; j++) {
                if (Map[i][j] > 0) cnt++;
            }
        }

        // 이전 경우에 남아 있던 벽돌보다 더 적은 벽돌이 남아 있을 경우 저장
        if (ANSWER > cnt) ANSWER = cnt;
    }

    static void solve() {
        // B : 구슬을 투하할 열의 순서
        int[] B = new int[4];
        for (B[0] = 0; B[0] < W; B[0]++) // 첫 번째
            for (B[1] = 0; B[1] < W; B[1]++) { // 두 번째
                for (B[2] = 0; B[2] < W; B[2]++) { // 세 번째
                    for (B[3] = 0; B[3] < W; B[3]++) { // 네 번째
                        // 처음 벽돌이 있던 상태로 초기화
                        block_init();
                        for (int i = 0; i < N; i++) {
                            // 구슬 투하
                            bomb(B[i]);
                            // 빈 공간 제거
                            down();
                        }
                        // 남은 벽돌의 수 count;
                        block_check();
                        // 남은 벽돌이 0 일 경우, 더 최적의 값을 구할 수 없으므로 종료
                        if (ANSWER == 0) return;
                        // N이 4보다 작을 경우.
                        // 네 번째 값의 변화는 유효하지 않은 값이므로 더 이상 진행하지 않음
                        if (N < 4) break;
                    }
                    if (N < 3) break;
                }
                if (N < 2) break;
            }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Data = new int[15][12];
        Map = new int[15][12];

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            for (int h = 0; h < H; h++) {
                for (int w = 0; w < W; w++) {
                    Data[h][w] = sc.nextInt();
                }
            }

            init();
            solve();

            System.out.printf("#" + tc + " " + ANSWER);
        }
    }

}

/*
* 벽돌 깨기
* 구슬을 쏘아 벽돌을 깨뜨리는 게임을 하려고 한다.
* 구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W * H 배열로 주어진다.
* 0 은 빈 공간을 의미하며, 그 외의 숫자는 벽돌을 의미한다.
*
* 게임의 규칙은 다음과 같다.
* 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨뜨릴 수 있다.
* 2. 벽돌은 숫자 1- 9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로 벽돌에 적힌 숫자 -1 칸만큼 같이 제거된다.
* N 개의 벽돌을 떨어뜨려 최대한 많은 벽돌을 제거하려고 한다.
* N, W, H 그리고 벽돌들의 정보가 주어질 때, 남은 벽돌의 개수를 구하라!
*
* 제약 사항
* 1. 1 <= N <= 4
* 2. 2 <= W <= 12
* 3. 2 <= H <= 15
*
* 입력
* 가장 첫 줄에는 총 테스트 케이스의 개수 T가 주어지고,
* 그 다음 줄부터 T 개의 테스트 케이스가 주어진다.
* 각 테스트 케이스의 첫 번째 줄에는 N, W, H 가 순서대로 공백을 사이에 두고 주어지고,
* 다음 H 줄에 걸쳐 벽돌들의 정보가 1 줄에 W 개씩 주어진다.
*
* 출력
* 출력은 #t 를 찍고 한 칸 띄운 다음 정답을 출력한다.
* (t는 테스트 케이스의 번호를 의미하며 1 부터 시작한다.)
*
* 입력
* 5
* 3 10 10 // #1, N = 3, W = 10, H = 10
* 0 0 0 0 0 0 0 0 0 0
* 1 0 1 0 1 0 0 0 0 0
* ...
* */
