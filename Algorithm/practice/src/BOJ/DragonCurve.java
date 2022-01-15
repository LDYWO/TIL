import java.util.*;

public class DragonCurve {

    static int N;
    static int[][] Map = new int[101][101];
    static int[] Dx = {1, 0, -1, 0};
    static int[] Dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int i = 0; i < N; ++i) {
            List<Integer> curves = new ArrayList<>();
            int x, y, d, g;
            x = sc.nextInt();
            y = sc.nextInt();
            d = sc.nextInt();
            g = sc.nextInt();

            curves.add(d);
            for (int j = 0; j < g; ++j) {
                for (int k = curves.size() - 1; k >= 0; --k) {
                    curves.add((curves.get(k) + 1)%4);
                }
            }

            Map[y][x] = 1;
            for (int dir : curves) {
                x += Dx[dir];
                y += Dy[dir];
                Map[y][x] = 1;
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        int ret = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (Map[i][j] == 1 && Map[i][j+1] == 1 && Map[i+1][j] == 1 && Map[i+1][j+1] == 1) {
                    ++ret;
                }
            }
        }

        return ret;
    }
}

/*
    드래곤 커브

    드래곤 커브는 다음과 같은 세 가지 속성으로 이루어져 있으며,
    이차원 좌표 평면 위에서 정의된다.
    좌표 평면의 x축은 -> 방향, y축은 밑 방향이다.

        1. 시작 점
        2. 시작 방향
        3. 세대

    0세대 드래곤 커브는 아래 그림과 같은 길이가 1인 선분이다.
    아래 그림은 (0, 0) 에서 시작하고, 시작 방향은 오른쪽인 0세대 드래곤 커브이다.

    1세대 드래곤 커브는 0세대 드래곤 커브를 끝 점을 기준으로 시계 방향으로 90도 회전
    시킨 다음에 0 세대 드래곤 커브의 끝 저에 붙인 것이다.
    끝 점이란 시작 점에서 선부을 타고 이동했을 때, 가장 먼 거리에 있는 점을 의미한다.

    2세대 드래곤 커브도 1세대를 만든 방법을 이용해서 만들 수 있다.

    3세대 드래곤 커브도 2세대 드래곤 커브를 이용해 만들 수 있다.

    즉, K 세대 드래곤 커브는 K-1 세대 드래곤 커브를 끝 점을 기준으로 90도 시계
    방향 회전 시킨 다음 그것을 끝 점에 붙인 것이다.

 */