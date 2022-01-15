import java.util.*;

/*
    전체적인 아이디어는 처음에 solve 라는 BFS 를 실행한다.
    그 BFS 내에서 상어 객체를 기준으로 가장 가까운 위치의 좌표를 찾는다.
    그 좌표를 먹을 수 있다면 거리 값을 초기화 해주고 먹는다.
 */
public class BabyShark {
    static int N;
    static int INF = 987654321;
    static int[][] Map = new int[20][20];
    static int[] Dr = {-1, 0, 0, 1};
    static int[] Dc = {0, -1, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 상어의 좌표
        int srow = 0, scol = 0;

        // 공간 초기화
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Map[i][j] = sc.nextInt();

                // 입력된 값이 9라면 상어로 인식하고 저장 후 값을 0으로 바꾼다.
                if (Map[i][j] == 9) {
                    srow = i;
                    scol = j;
                    Map[i][j] = 0;
                }
            }
        }

        System.out.println(solve(srow, scol)); // solve 라는 함수를 생성하여
    }

    static int solve (int r, int c) {
        // 지나간 시간을 출력하는 값
        int ret = 0;
        int size = 2, cnt = 2; // 상어의 사이즈, cnt 값은 상어의 크기를 증가시켜주기위한 카운트

        // 큐에 대입하기 위한 객체를 생성해준다. (상어 객체)
        Point minPt = new Point (r, c, 0);

        // do - while 문으로 진행한다.
        // 왜냐하면, 정확히 얼마나 BFS 가 실행되는지 모르기 때문이다.
        do {
            boolean visited[][] = new boolean[20][20]; // 방문을 체크할 2차 행렬을 생성한다.
            Queue<Point> myqueue = new LinkedList<>(); // 큐는 링크드 리스트로 구현을 해준다.

            visited[minPt.r][minPt.c] = true;  // 해당 객체의 위치는 방문한 것이다. (처음은 상어)
            myqueue.offer(new Point(minPt.r, minPt.c, 0)); // 큐에 대입해준다.
            minPt.d = INF; // 충분히 큰 값으로 초기화 해준다.

            // 큐에 더이상 노드가 존재하지 않을 때까지 반복문 실행
            while (!myqueue.isEmpty()) {
                // 큐의 가장 위 노드를 뽑아서 비교해준다.
                Point curr = myqueue.poll();

                // 만약에 뽑은 위치보다 minPt.d 의 값이 더 작다면 반복문을 멈춘다. (왜냐면 그 위치는 가장 최소의 거리가 아니기 때문에 더 볼 필요도 없다.)
                if (curr.d > minPt.d) break;

                // 만약에 해당 좌표의 값이 현재 상어가 먹을 수 없는 위치라면 다음 노드로 넘어간다. (먹을 수 없기 때문에)
                if (Map[curr.r][curr.c] > size) continue;

                // 만약에 그 좌표가 0이 아니고 (빈 공간이 아니고) 상어보다 크기가 작다면 먹을 수 있는 위치로 찾는다. 그리고 다음 노드로 넘어간다. (이미 먹을 좌표로 초기화를 했기 때문에 종착, 탐색할 필요 없음)
                if (Map[curr.r][curr.c] != 0 && Map[curr.r][curr.c] < size) {
                    if (curr.d < minPt.d) {
                        // 그 중에서도 minPt.d 보다 크기가 작다면 초기화 해준다. (주변에 가장 작은 거리를 찾기 위함)
                        minPt = curr;
                    } else if (curr.d == minPt.d) {
                        // 만약에 거리가 같다면
                        // 그 때 우선순위는 위, 상, 좌, 우, 하 순
                        if (curr.r < minPt.r) {
                            // r 값이 작은 것, 즉 더 위에 있는 것
                            minPt = curr;
                        } else if (curr.r == minPt.r && curr.c < minPt.c)
                            // 그 중에서 행이 같다면 더 왼쪽에 있는 것
                            minPt = curr;
                    }
                    continue;
                }

                // 위의 조건문에 해당되지 않는 노드는 단순히 이동을 하기 위해 사용된 노드이다.

                // 해당 노드를 기준으로 위, 왼쪽, 오른쪽, 아래 순으로 탐색을 진행한다.
                for (int i = 0; i < 4; ++i) {
                    int nr = curr.r + Dr[i], nc = curr.c + Dc[i]; // 해당 좌표는 Dr, Dc 배열을 만들어 반복문으로 정한다.

                    if (nr < 0 || nr > N-1 || nc < 0 || nc > N-1) continue; // 만약에 공간의 범위를 벗어났다면 해당 차례를 종료
                    if (visited[nr][nc]) continue; // 만약에 이미 방문한 위치라면 해당 차례를 종료

                    // 그렇지 않은 경우라면
                    visited[nr][nc] = true; // 방문을 하고
                    myqueue.offer(new Point(nr, nc, curr.d + 1)); // 해당 좌표의 노드를 큐에 대입한다. 이 때, dist 의 값은 이동했으니 1 증가시켜준다.
                }
            }

            // 만약에 dist 의 값이 INF 가 아니라면, 즉 minPt = 상어의 dist 값이 초기화 되어 먹었다면
            if (minPt.d != INF) {
                ret += minPt.d; // 해당 좌표까지의 거리를 시간에 증가시켜준다.
                --cnt; // 물고기를 먹었으니 그만큼 cnt 카운트를 감소시켜준다.

                // 만약 cnt 값이 0이 되었다면 그 상어의 크기만큼 물고기를 먹은 것이니 사이즈를 증가시켜준다.
                if (cnt == 0) {
                    ++size;
                    cnt = size; // 그리고 그 카운트의 값을 그 상어의 크기로 다시 초기화해준다.
                }

                Map[minPt.r][minPt.c] = 0; // 그리고 해당 좌표의 값을 0으로 초기화한다.
            }

        } while (minPt.d != INF); // minPt.d != INF 라는 사실은 먹을 물고기가 있어서 최소 거리 값이 초기화 되었다는 뜻이다.

        return ret;
    }

    // 이 해당 클래스는 상어로부터의 거리 값 (시간을 계산해주기 위함), 그리고 좌표의 행, 렬 값이 들어있다.
    static class Point {
        int r, c, d;

        Point (int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}

/*
    아기 상어

    N*N 크기의 공간에 물고기 M 마리와 아기 상어 1 마리가 있다.
    공간은 1*1 크기의 정사각형 칸으로 나누어져 있다.
    한 칸에는 물고기가 최대 1마리 존재한다.
    아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
    가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
    아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
    아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
    따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
    아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.
        - 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
        - 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
        - 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
            - 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최소값이다.
            - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.

    아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다.
    즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다
    물고기를 먹으면, 그 칸은 빈 칸이 된다.
    아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
    예를 들어, 크기가 2인 아기 상어는 물고기를 먹으면 크기가 3이 된다.
    공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

    입력
        첫째 줄에 공간의 크기 N (2 <= N <= 20) 이 주어진다.
        둘째 줄부터 N개의 줄에 공간의 상태가 주어진다.
        공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9 로 이루어져있고 아래와 같은 의미를 가진다.
        0 : 빈칸
        1, 2, 3, 4, 5, 6 : 칸에 있는 물고기의 크기
        9 : 아기 상어의 위치

    아기 상어는 공간에 한 마리 있다.

 */
