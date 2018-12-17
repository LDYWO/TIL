package DFS_BFS;

import java.util.*;

public class BabyShark {
    static boolean[][] visited = new boolean[20][20];
    static int[][] map = new int[20][20];
    static int sharkX, sharkY, sharkSize, eat, time, N;
    // 아기 상어의 X, Y 좌표, 아기상어의 현재 사이즈, 한번 큐가 돌 때 움직이는 칸의 수 (상태), 전체 시간, 입력되는 맵의 가로, 세로 길이
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    // 상 좌 우 하 (y, x) 우선순위 순서대로

    public static int solution (int N, int[][] M) {

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[i].length; j++) {
                if (map[i][j] == 9) {
                    time = 0;
                    eat = 0;
                    sharkSize = 2;
                    sharkX = j;
                    sharkY = i;
                }
            }
        }

        init(); // 방문 배열 초기화

        Deque<Item> deque = new ArrayDeque<>();

        deque.offer(new Item(sharkX, sharkY, sharkSize, 0, false)); // 큐에 대입
        visited[sharkY][sharkX] = true; // 해당 좌표 방문
        Item fish;

        while (!deque.isEmpty()) {

            fish = deque.poll();

            // 현재 위치에서 상하좌우를 살펴보기 위한 반복문
            for (int i=0; i<4; i++) {
                int dx, dy;
                dx = fish.x + dir[i][1];
                dy = fish.y + dir[i][0];

                if (dx<0 || dy<0 || dx>=N || dy>= N) continue; // 즉, 현재 상어의 위치에서 상하좌우가 없다면 반복문을 계속한다.
                if (visited[dy][dx] == true) continue;  // 만약, 보는 위치가 이미 방문을 했다면 반복문을 계속한다.
                if (map[dy][dx] > fish.size) continue; // 만약, 보는 위치의 크기가 현재 상어의 크기보다 크다면 지나가지 못한다.


                // 위의 예외적인 케이스에 모두 걸리지 않는다면
                visited[dy][dx] = true; // 해당 칸을 방문한다.

                if (map[dy][dx] != 0 && map[dy][dx] < fish.size) {
                    // 만약에 먹을 수 있는 물고기가 있는 칸이라면, 체크한다.
                    deque.offer(new Item(dx, dy, sharkSize, fish.cnt + 1, true));
                } else if (map[dy][dx] == 0 || map[dy][dx] == fish.size){
                    // 먹지 못한다면 그냥 이동만 한다.
                    deque.offer(new Item (dx, dy, sharkSize, fish.cnt + 1, false));
                }
            }

            // 해당 큐가 먹을 수 있다면 먹고 eat 카운트가 증가한다.
            // eat 카운트가 아기상어의 현재 사이즈와 같다면 크기를 증가시키고 eat 카운트를 초기화한다.
            if (fish.eatable) {
                eat++;
                if (eat == sharkSize) {
                    sharkSize++;
                    eat = 0;
                }

                // 먹고나서 해당 위치로 아기 상어를 이동시키고
                // BFS 를 종료시킨다.
                sharkX = fish.x;
                sharkY = fish.y;
                map[fish.y][fish.x] = 0;
                time += fish.cnt;
                deque.clear();

                init();

                deque.offer(new Item(sharkX, sharkY, sharkSize, 0, false));
                visited[sharkY][sharkX] = true;
            }
        }

        return time;
    }

    public static void init() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                visited[i][j] = false;
            }
        }
    }

    static class Item {
        int x;
        int y;
        int size;
        int cnt;
        boolean eatable = false;

        public Item (int x, int y, int size, int cnt, boolean eatable) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.cnt = cnt;
            this.eatable = eatable;
        }
    }

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int time = solution(N, map);

        System.out.println(time);
    }
}

/*
   1. N*N 크기의 공간에 물고기 M 마리와 아기 상어 1마리가 있다.
   2. 공간은 1*1 크기의 정사각형 칸으로 나누어져 있다.
   3. 한 칸에 물고기가 최대 1마리 존재한다.
   4. 아기 상어와 물고기가 최대 1마리 존재한다.
   5. 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
   6. 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 지나갈 수 있다.
   7. 자신의 크기보다 작은 물고기만 먹을 수 있다.
   8. 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸으로는 이동할 수 있다.

   아기 상어가 어디로 이동할지 결정하는 방법
   1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다. (BFS 가 끝나는 조건)
   2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
   3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
   4. 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최소 값이다.
   5. 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
   6. 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다.
   7. 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
   8. 즉, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
 */