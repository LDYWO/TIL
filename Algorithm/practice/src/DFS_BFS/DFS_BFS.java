package DFS_BFS;

import java.util.*;

public class DFS_BFS {
    public static int N, M, V;
    public static int x, y;

    public static int[][] graph = new int[1001][1001];
    public static boolean visted[] = new boolean[10001];

    public static void DFS (int V) {
        // 시작하는 정점을 방문했다고 표시 boolean 값으로
        System.out.print(V + " ");
        visted[V] = true;

        // 정점의 갯수 만큼 반복문을 돌린다.
        for (int i=1; i<=N; i++) {
            if (graph[V][i] == 1 && visted[i] == false) {
                DFS(i);
            }
        }
    }

    public static void BFS (int V) {
        Deque<Integer> deque = new ArrayDeque<>();
        int out;

        // 큐에 시작점 추가
        deque.offer(V);
        visted[V] = true;
        System.out.print(V + " ");

        // 큐에 값이 없을 때까지 실행
        while (!deque.isEmpty()) {
            // 큐에서 값 가져옴
            out = deque.poll();

            for (int i=1; i<=N; i++) {
                if (graph[out][i] == 1 && visted[i] == false) {
                    // 방문 안한 점을 찾으면, 큐에 저장
                    deque.offer(i);
                    visted[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        // 두 정점을 2차원 배열에 저장
        // 양 방향이므로 graph[x][y] = graph[y][x] = 1 로 저장
        for (int i=1; i<=M; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            graph[x][y] = graph[y][x] = 1;
        }

        DFS(V);

        // reset visted
        for (int i=1; i<=N; i++) {
            visted[i] =false;
        }

        System.out.println();
        BFS(V);

        sc.close();
    }
}

/*
    그래프는 정점과 간선으로 이루어져 있다.
    간선을 통해 모든 정점을 방문하는 것을 그래프의 탐색이라고 한다.
    그래프를 탐색하는 방법에는 DFS 와 BFS 방식이 있다.

    먼저 그래프는 표현하는 방법으로 인접행렬, 인접리스트에 대해 알아보도록 하자.

    1. 인접 행렬과 인접 리스트
    ***
    1.1 인접 행렬
        - 그래프의 상태를 나타내는 정사각행렬이다.
        - 그래프의 정점이 n개 일 때, 그래프의 표현을 n*n 의 이차원 배열로 나타낼 수 있다.
        - 정점간의 간선이 존재하면 값을 1로 하고, 간선이 없으면 0으로 나타낸다.
        - 인접 행렬을 JAVA 언어로 나타내면 다음과 같다.

       ```java
        int[][] matrix = new int[n+1][n+1];

        for (int i=0; i<n; i++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();

            a[v1][v2] = 1;
            a[v2][v1] = 1;
        }
        ```

    1.2 인접 리스트
        - 한 정점과 연결되어 있는 모든 정점들을 하나의 연결리스트로 표현하는 방식이다.
        - 그래프의 각 정점마다 해당 정점에서 나가는 간선의 목록을 저장하여 표현한다.
        - 인접행렬에 비해 변이 적은 그래프에 유용하다.
        - 인접리스트를 JAVA 언어로 나타내면 다음과 같다.

        ```java
        ArrayList<Integer>[] a = (ArrayList<Integer[]) new ArrayList[n+1];

        for (int i=0; i<=n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();

            a[v1].add(v2);
            a[v2].add(v1);
        }
      ```

  2. 깊이 우선 탐색 (DFS)
    - 깊이 우선 탐색은 트리나 그래프에서 한 루트로 탐색하다가 최대한 깊숙히 들어가 확인 후 다시 돌아가
    다른 루트를 탐색하는 방식이다.
    여기서 부모 노드로 되돌아오는 과정을 백트래킹이라고 한다.
    일반적으로 인접행렬을 이용한 재귀호출을 사용하여 구현하지만, 단순한 스택 배열로 구현하기도 한다.

    장점
        - 현 경로상의 노드만 기억하면 되므로 저장공간의 수요가 적다.
        - 목표 노드가 깊은 단계에 있을 경우 해를 빨리 구할 수 있다.

    단점
        - 해가 없는 경로에 깊이 빠질 위험이 있다. 따라서 깊이제한을 지정하여 해당 깊이까지만 탐색하고,
        목표 노드를 발견하지 못하면 다음의 경로를 따라 탐색하도록 하는 것이 좋다.
        - 얻어진 해가 최단 경로가 된다는 보장이 없다. 목표까지의 경로가 다수인 경우 깊이 우선 탐색은
        해에 다다르면 탐색을 끝내버리기 때문이다.

    소스코드로의 구현
        - DFS 는 인접 행렬로 구현한다.

        ```java
        public static void dfs (int v) {
            System.out.print(v);
            check[v] = true;
            for (int i=1; i<1001; i++) {
                if (node[v][i] == 1 && !check[i]) {
                    System.out.print(" ");
                    dfs(i);
                }
            }
        }
        ```
        // 정점의 개수가 1000개인 경우, DFS 탐색 수행 JAVA 소스 코드


    3. 너비 우선 탐색 (BFS)
       - 너비 우선 탐색은 시작 정점을 방문한 후 시작 정점에 인접한 모든 정점을 방문한 후 시작 정점에 인접한
       모든 정점들을 웃헌 방문하는 방법이다. 더이상 방문하지 않은 정점이 없을 때까지 방문하지 않은 모든 정점들에
       대해서 너비우선탐색을 적용한다.

       장점
           - 출발 노드에서 목표 노드까지의 최단 길이 경로를 보장한다.

       단점
           - 경로가 매우 길 경우 탐색 가지가 급격히 증가하여, 많은 기억 공간을 필요로한다.
           - 해가 존재하지 않는 유한 그래프의 경우 모든 그래프를 탐색한 후 실패로 끝난다.
           - 무한 그래프의 경우 끝낼 수 없다.

       소스 코드로의 구현
            - BFS 는 자료구조 Queue 를 사용하여 구현하는 경우가 일반적이다.

            ```java
            public static void bfs (int v) {
                Deque<Integer> q = new ArrayDeque<>();
                q.add(v);
                check[v] = true;

                while (!q.isEmpty()) {
                    int tmp = q.poll();
                    System.out.print(tmp + " ");
                    for (int i=1; i<1001; i++) {
                        if (node[tmp][i] == 1 && !check[i]) {
                            check[i] = true;
                            q.add(i);
                        }
                    }
                }
            }
            ```
            // 정점의 개수가 1000개인 경우, BFS 탐색 수행 JAVA 소스 코드


 */