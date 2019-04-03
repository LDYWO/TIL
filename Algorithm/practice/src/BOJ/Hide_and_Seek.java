import java.util.*;

class Hide_and_seek {

    public static int[][] dist = new int[2][500001]; // 해당 좌표에 몇 초에 방문했는지 기록하는 배열, 앞 자리는 홀, 짝 여부
    public static int ANSWER; // 정답

    public static void main(String[]args)
    {
        ANSWER = -1;

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 수빈이가 서있는 위치 좌표
        int K = sc.nextInt(); // 동생이 서있는 위치 좌표

        reset(dist);

        BFS(N, 0);

        int t = 0;

        // 수빈이가 방문한 좌표를 기준으로 동생이 방문 가능한지 검사를 한다.
        while (K <= 500000) {
            if (dist[t%2][K] != -1 && dist[t%2][K] <= t) {
                ANSWER = t;
                break;
            }

            t++;
            K += t;
        }

        System.out.println(ANSWER);
    }

    // 수빈이가 방문할 수 있는 모든 경우의 수를 배열에 저장하는 함수 (BFS)
    public static void BFS (int N, int cnt) {

        Queue<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(N, cnt));

        dist[cnt%2][N] = cnt; // 해당 노드 방문하면 그 좌표를 방문한 시간을 저장 // 방문하지 않았으면 -1 로 초기화

        Node out;

        while (!deque.isEmpty()) {

            out = deque.poll();

            if (out.pos+1 <= 500000 && out.pos+1 >= 0 && dist[(out.cnt+1)%2][out.pos+1] == -1) {
                deque.offer(new Node(out.pos+1, out.cnt + 1));
                dist[(out.cnt+1)%2][out.pos+1] = out.cnt + 1;
            }

            if (out.pos-1 <= 500000 && out.pos-1 >= 0 && dist[(out.cnt+1)%2][out.pos-1] == -1) {
                deque.offer(new Node(out.pos-1, out.cnt + 1));
                dist[(out.cnt+1)%2][out.pos-1] = out.cnt + 1;
            }

            if (out.pos*2 <= 500000 && out.pos*2 >= 0 && dist[(out.cnt+1)%2][out.pos*2] == -1) {
                deque.offer(new Node(out.pos*2, out.cnt + 1));
                dist[(out.cnt+1)%2][out.pos*2] = out.cnt + 1;
            }
        }
    }

    public static void reset (int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = -1;
            }
        }
    }

    static class Node {
        int pos;
        int cnt;

        public Node (int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}

// 그냥 방문이 아니라 짝수초에 방문한 적이 있고 동생이 그 점을 짝수초에 지나간다면 찾은 것으로 칠 수 있고, 마찬가지로
// 홀수 초에 방문한 적이 있고 동생이 그 점을 홀수초에 지나간다면 찾은 것으로 칠 수 있다.
// 그럼 고려해주어야할 것이 두가지이다.
// 방문 시간이 홀수인가 짝수인가, 이전에 방문한 적이 있는가?

/*
    숨바꼭질 5

    수빈이는 동생과 숨바꼭질을 하고 있다.
    수빈이는 현재 점 N에 있고, 동생은 점 K에 있다.
    수빈이는 걷거나 순간이동을 할 수 있다.
    만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
    순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
    동생은 항상 걷기만 한다.
    동생은 항상 매초마다 이동읋 하며, 이동은 가속이 붙는다.
    동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다.
    즉, 동생의 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1, K+1+2... 이다.
    수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
    동생을 찾는 위치는 정수 좌표이어야 하고, 수빈이가 0보다 작은 좌표로 50만보다 큰 좌표로 이동하는 것은 불가능하다.
 */