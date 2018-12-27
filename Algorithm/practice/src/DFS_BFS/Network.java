package DFS_BFS;

public class Network {
    static boolean[] visted = new boolean[200];
    static int ANSWER = 0;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        // 노드들을 다 검사한다 1, 2, 3 ... 갯수에 맞게
        for (int i=0; i<n; i++) {
            // 만약 해당 노드를 방문하지 않았다면
            // DFS 를 실행하여 모든 연결된 노드들을 다 방문한다. (즉, DFS 의 재귀적 실행을 통해 네트워크를 찾는다.)
            if (!visted[i]) {
                ANSWER++;
                DFS(i, n, computers);
            }
        }

        answer = ANSWER;

        return answer;
    }

    public static void DFS (int start, int n, int[][] computers) {
        visted[start] = true;

        for (int i=0; i<n; i++) {
            if (computers[start][i] == 1 && visted[i] == false) {
                DFS (i, n, computers);
            }
        }
    }

    public static void main (String[] args) {
        int[][] computers = {{1,1,0}, {1,1,1}, {0,1,1}};
        int a = solution(3, computers);

        System.out.println(a);
    }
}

/*
    네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
    예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어 있고,
    컴퓨터 B와 컴퓨터 C가 직접적을 연결되어 있을 때, 컴퓨터 A와 컴퓨터 C도 간접적으로
    연결되어 정보를 교환할 수 있습니다.
    따라서, 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

    컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers 가 매개변수로 주어질 떄,
    네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

    일단 DFS 를 사용할 것인지 DFS 를 사용할 것인지를 먼저 정해야한다.
    DFS 를 사용하려면
 */