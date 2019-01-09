# Red Black Tree
- RBT는 BST를 기반으로 하는 트리 형식의 자료구조이다.
- 결론부터 말하자면 Red-Black Tree에 데이터를 저장하게 되면 Search, Insert, Delete에 O(log n)의 시간 복잡도가 소요된다.
- 동일한 노드의 개수일 때, depth를 최소화하여 시간 복잡도를 줄이는 것이 핵심 아이디어이다.

## 1. Red-Black Tree의 정의
- RBT는 다음의 성질을 만족하는 BST이다.
  1. 각 노드는 Red or Black 이라는 색깔을 갖는다.
  2. Root node의 색깔은 Black 이다.
  3. 각 leaf node의 색깔은 Black 이다.
  4. 어떤 노드의 색깔이 Red라면 두 개의 Children 색깔은 모두 Black이다.
  5. 각 노드에 대해서 노드로부터 descendant leaves 까지의 단수 경로는 모두 같은 수 의 Black nodes 들을 포함하고 있다.
  6. 이를 해당 노드의 Black-Height 라고 한다.

## 2. Red-Black Tree의 특징
  1. Binary Search Tree 이므로 BST의 특징을 모두 갖는다.
  2. Root Node 부터 Leaf Node 까지의 모든 경로 중 최소 경로와 최대 경로의 크기 비율은 2보다 크지 않다.
  3. 이러한 상태를 **Balanced** 라고 한다.
  4. 노드의 자식이 없을 경우 자식을 가리키는 포인터는 NIL 값을 저장한다.

### 2.1 삽입
- 우선 BST의 특징을 유지하면서 노드를 삽입한다.
- 그리고 삽입된 노드의 색깔을 Red로 지정한다.
- Red로 지정하는 이유는 Black-Height 변경을 최소화하기 위해서이다.
- 삽입 결과 RBT의 특성 위배시 노드의 색깔을 조정하고, Black-Height가 위배되었다면 Rotation을 통해 Height을 조정한다.
- 이러한 과정을 통해 RBT의 동일한 Height에 존재하는 Internal Node들의 Black-Height가 같아지게 되고 최소 경로와 최대 경로의 크기 비율이 2미만으로 유지된다.

### 2.2 삭제
- 삭제도 삽입과 마찬가지로 BST의 특성을 유지하면서 해당 노드를 삭제한다.
- 삭제될 노드의 Child 개수에 따라 Rotation 방법이 달라지게 된다.
- 그리고 만약 지워진 노드의 색깔이 Black이라면 Black-Height가 1 감소한 경로에 Black Node가 1개 추가되도록 Rotation하고 노드의 색깔을 조정한다.
- 지워진 노드의 색깔이 Red라면 Violation이 발생하지 않으므로 RBT가 유지된다.

**Java Collection** 에서 ArrayList도 내부적으로 RBT로 이루어져 있고, HashMap에서 Separate Chaining에서도 사용된다.
그만큼 효율이 좋고 중요한 자료구조이다.
