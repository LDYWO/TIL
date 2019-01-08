# Graph
## 정점과 간선의 집합, Graph

cf) 트리 또한 그래프이며, 그 중 사이클이 허용되지 않는 그래프를 말한다.

### 1. 그래프 관련 용어 정리

#### 1.1. **Undirected Grpah** 와 **Directed Graph(Digraph)**

말 그대로 정점과 간선의 연결 관계에 있어서 방향성이 없는 그래프를 **Undirected Graph** 라 하고, 간선에 방향성이 포함되어 있는 그래프를 **Directed Graph(Digraph)** 라고 한다.

- Directed Graph(Digraph)
```
V = {1, 2, 3, 4, 5, 6}
E = {(1, 4), (2, 1), (3, 4), (3, 4), (5, 6)}
(u, v) = vertex u에서 vertex v로 가는 edge
```
- Undirected Digraph
```
V = {1, 2, 3, 4, 5, 6}
E = {(1, 4), (2, 1), (3, 4), (3, 4), (5, 6)}
(u, v) = vertex u와 vertex v를 연결하는 edge
```

##### 1.2. Degree
Undirected Graph 에서 각 정점(Vertex)에 연결된 Edge의 개수를 Degree라 한다.
Directed Graph에서는 간선에 방향성이 존재하기 때문에 Degree가 두 개로 나뉘게 된다.
각 정점으로부터 나가는 개수를 Outdegree라 하고, 들어오는 간선의 개수를 Indegree라 한다.

##### 1.3. 가중치 그래프(Weight Grpah)와 부분 그래프 (Sub Graph)
가중치 그래프란 간선에 가중치 정보를 두어서 구성한 그래프를 말한다. 반대의 개념인 비가중치 그래프 즉, 모든 간선의 가중치가 동일한 그래프도 물론 존재한다. 부분 집합과 유사한 개념으로 부분 그래프라는 것이 있다. 부분 그래프는 본래의 그래프의 일부 정점 및 간선으로 이루어지는 그래프를 말한다.

### 2. 그래프를 구현하는 두 방법
#### 2.1. 인접 행렬(adjacent matrix): 정방 행렬을 사용하는 방법
해당하는 위치의 value 값을 통해서 vertex 간의 연결 관계를 O(1)으로 파악할 수 있다. Edge 개수와는 무관하게 V^2의 공간 복잡도를 가진다. **Dense Graph** 를 표현할 때 적절할 방법이다.

#### 2.2. 인접 리스트(adjacent list): 연결 리스트를 사용하는 방법
vertex의 adjacent list를 확인해봐야 하므로 vertex간 연결되어있는지 확인하는데 오래 걸린다. Space Complexity는 O(E + V) 이다.
**Sparse Graph** 를 표현하는데 적당한 방법이다.

### 3. 그래프의 탐색
그래프는 정점의 구성 뿐만 아니라 간선의연결에도 규칙이 존재하지 않기 때문에 탐새이 복잡하다. 따라서 그래프의 모든 정점을 탐색하는 방법은 다음의 두 가지 알고리즘을 기반으로 한다.

#### 3.1. 깊이 우선 탐색 (Depth First Search: DFS)
그래프 상에 존재하는 임의의 한 정점으로부터 연결되어 있는 한 정점으로만 나아간다라는 방법을 우선으로 탐색한다.
일단 연결된 정점으로탐색하는 것이다. 연결할 수 있는 정점이 있을 때까지 계속 연결하다가 더 이상 연결되지 않은 정점이 없으면 바로 그 전 단계의 정점으로 돌아가서 연결할 수 있는 정점이 있는지 살펴봐야 할 것 이다. 갔던 길을 되돌아 오는 상황이 존재하는 미로찾기처럼 구성하면 되는 것이다. 어떤 자료구조를 사용해야할까? 바로 **Stack** 이다.
- Time Complexity: O(V + E) ... vertex 개수 + edge 개수

#### 3.2. 너비 우선 탐색 (Breadth First Search: BFS)
그래프 상에 존재하는 임의의 한 정점으로부터 연결되어 있는 모든 정점으로 나아간다. Tree에서의 Level Order Traversal 형식으로 진행되는 것이다. BFS에서는 자료구조로 Queue를 사용한다. 연락을 취할 정점의 순서를 기록하기 위한 것이다. 우선, 탐색을 시작하는 정점을 Queue에 넣는다. 그리고 dequeue를 하면서 dequeue 하는 정점과 간선으로 연결되어 있는 정점들을 enqueue 한다. 즉 vertex들을 방문한 순서대로 queue에 저장하는 방법을 사용하는 것이다.
- Time Complexity: O(V + E) ... vertex 개수 + edge 개수 ! BFS로 구한 경로는 최단 경로이다.

### 4. Minimum Spanning Tree
그래프 G의 spanning tree 중 edge weight의 합이 최소인 spanning tree를 말한다. 여기서 말하는 spanning tree란 그래프 G의 모든 vertex가 cycle이 없이 연결된 형태를 말한다.

#### 4.1. Kruskal Algorithm
초기화 작업으로 edge 없이 vertex 들 만으로 그래프를 구성한다. 그리고 weight가 가장 작은 edge부터 검토한다. 그러기 위해선 Edge Set을 non-decreasing 으로 sorting 해야 한다. 그리고 가장 작은 weight에 해당하는 edge를 추가하는데 추가할 때 그래프에 cycle이 생기지 않는 경우에만 추가한다. spanning tree가 완성되면 모든 vertex들이 연결된 상태로 종료가 되고 완성될 수 없는 그래프에 대해서는 모든 edge에 대해 판단이 이루어지면 종료된다.

##### 어떻게 cycle 생성 여부를 판단하는가?
Graph의 각 vertex에 set-id라는 것을 추가적으로 부여한다. 그리고 초기화 과정에서 모두 1~n까지의 값으로 각각의 vertex들을 초기화 한다. 여기서 0은 어떠한 edge 와도 연결되지 않았음을 의미하게 된다. 그리고 연결할 때마다 set-id를 하나로 통일시키는데, 값이 동일한 set-id 개수가 많은 set-id 값으로 통일시킨다.

##### Time Complexity
  1. Edge의 Weight를 기준으로 sorting - O(E log E)
  2. cycle 생성 여부를 검사하고 set-id 를 통일 - O(E + V log V) => 전체 시간 복잡도: O(E log E)

#### 4.2 Prim Algorithm
초기화 과정에서 한 개의 vertex로 이루어진 초기 그래프 A를 구성한다. 그리고나서 그래프 A 내부에 있는 vertex로부터 외부에 있는 vertex 사이의 edge를 연결하는데 그 중 가장 작은 weight의 edge를 통해 연결되는 vertex를 추가한다. 어떤 vertex건 간에 상관없이 edge의 weight를 기준으로 연결하는 것이다. 이렇게 연결된 vertex는 그래프 A에 포함된다. 위 과정을 반복하고 모든 vertex들이 연결되면 종료한다.

##### Time Complexity
- 전체 시간 복잡도: O(E log V)
