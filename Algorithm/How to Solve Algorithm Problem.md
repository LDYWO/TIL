# 유형 별 알고리즘 문제 풀이법

## 1. Hash (해시)
### 1.1. 해시맵 선언

```java
  import java.util.*;

  HashMap<String, Integer> hm = new HashMap<>(); // *Java 8 이후 "=" 이하 오토 박싱
```

### 1.2. 해시맵에 값 대입
```java
  hm.put("abc", 1); // "abc" 라는 문자열 고유한 키 값과 1이라는 벨류 값 대입
```

### 1.3. 해시맵 순회
```Java
  for (String key : hm.keySet()) {
    System.out.println(hm.get(key)); // 해시맵을 차례로 순회하면서 벨류 값을 출력
  }

  for (Integer value : hm.values()) {
    System.out.println(value); // 해시맵을 차례로 순회하면서 벨류 값을 출력
  }
```

### 1.4. 응용
- 해시맵의 특성을 이용하여 특정 유형의 문제를 풀이할 수 있다.
- 예를 들어, 키 값이 중복되지 않기 때문에 같은 키 값이 들어오면 중복이 되지 않아 값이 덮일 수 있다.
- 해당 특성을 다룰 수 있는 함수가 존재한다. (```getOrDefault```)

```Java
  hm.put(type, hm.getOrDefault(type, 0) + 1);
  // 이렇게 해주면 type이라는 중복되지 않는 키에 해당하는 벨류 값이 처음에 들어간 이후
  // 같은 키 값을 대입하면 원래 존재하는 값에 + 1을 하여 업데이트 해준다.
```

### 1.5. 정렬
- 해시맵은 키와 값의 엔트리를 인자로 가지고 있는 자료구조이다.
- 해당 키와 값을 기준으로 다르게 정렬을 수행해줄 수 있다.

#### 1.5.1. Key를 기준으로 정렬
- 기본 개념은 트리맵과 맵을 이용한다.
- 트리 맵에 맵을 그대로 삽입하면 내림차순으로 정렬된다.
- 오름차순으로 정렬하려면 트리맵을 역순으로 선언하고 그대로 맵의 엔트리를 대입해주면 된다.

```Java
  Map<Object, Object> keySort = sortByKey(hm, true);

  public static Map<Object, Object> sortByKey (final Map map, boolean isASC) {
    if (isASC) {
      // 해시맵은 트리맵에 그대로 대입해주면 내림차순으로 정렬된다.
      return new TreeMap<Object, Object>(map);
    } else {
      // 오름차순으로 정렬하려면 트리맵 객체를 역순으로 선언하고 기존 맵의 모든 요소를 그대로 넣는다.
      TreeMap<Object, Object> tree = new TreeMap<Collections.reverseOrder());
      tree.putAll(map);
      return tree;
    }
  }
```

#### 1.5.2. Value를 기준으로 정렬
- 기본 개념은 **LinkedList** 와 맵을 이용한다.
- **LinkedList** 를 선언하고 ```Collections.sort``` 를 이용하여 ```Map.Entry``` 요소들을 비교한다.
- 구체적인 내부 구현은 ```new Comparator()``` 를 활용한다.
- 내림차순 정렬은 역순으로 한번 더 선언한다.
- 최종적으로 **List** 를 순회하면서 각 엔트리에 해당하는 키와 값을 새로운 해시맵에 대입하여 반환한다.

```Java
  Map<Object, Object> valueSort = sortByValue(hm, true);

  public static Map<Object, Object> sortByValue (final Map map, boolean isASC) {
    List list = new LinkedList(map.entrySet());

    Collections.sort(list, new Comparator(){
      public int compare (Object o1, Object o2) {
        return ((Comparable) ((Map.Entry)(o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue());
      }
    });

    if (isASC) {
      Collections.reverse(list);
    }

    HashMap sortedHashMap = new LinkedHashMap();

    for (Object obj : list) {
      Map.Entry entry = (Map.Entry)obj;
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }

    return sortedHashMap;
  }
```

## 2. Stack (스택) & Queue (큐)
### 2.1. 스택 및 큐 선언
- 자바에서의 큐는 인터페이스 구현이기 때문에 ```offer``` 와 ```poll``` 과 같은 기본 메소드를 구현해주어야 한다.
- 따라서 ```Deque``` 를 선언해 ```ArrayDeque``` 로 인스턴스를 생성한다.

```java
import java.util.*;

Stack st = new Stack(); // 기본적인 스택 선언
Stack<Integer> Integerst = new Stack<>(); // 정수형 값을 담는 스택 선언
Deque<Integer> deque = new ArrayDeque<>(); // 정수형 값을 담는 큐 선언
```

### 2.2. 요소 삽입 및 반환
- **스택** 은 ```push``` 와 ```pop```을 통해 삽입과 반환을 구현한다.
- **큐** 는 ```offer``` 와 ```poll```을 통해 삽입과 반환을 구현한다.

```Java
st.push(1); // Stack st 에 1을 삽입
st.pop(); // st라는 스택에 들어간 자료형 중 가장 앞에있는 요소를 제거하고 반환한다.

deque.offer(1); //  deque에 1을 삽입
System.out.println(deque.peek()); // deque의 가장 앞 요소를 확인하여 반환한다.
deque.poll(); // deque의 가장 앞에 있는 요소를 제거하고 반환한다.
```

### 2.2. 응용
- **스택** 과 **큐** 는 딱히 응용이랄 것이 없다.
- 기본적인 ```FIFO```, ```FILO``` 구조에 맞추어 적당히 구현해주면 된다.

## 3. 힙 (Heap)
- 힙은 데이터에서 최대 값 (혹은 최소 값)을 빠르게 찾을 수 있는 자료구조이다.
- 힙은 완전 이진 트리이다.
- 우선 순위가 가장 높은 데이터가 제일 앞(루트)에 위치한다.
- 부모는 자식보다 우선 순위가 더 높은 데이터가 배치된다.
- 대표적으로 최대 힙과 최소 힙이 있다.

### 3.1. 힙 (우선순위 큐) 선언
- 자바에서의 힙은 ```PriorityQueue``` 로 선언될 수 있다.

```java
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b-a); // 람다식을 이용하여 최대 힙을 구현할 수 있다.
  PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a-b); // 최소 힙은 디폴트 값으로 설정되어 있다.   
```

### 3.2. 요소 삽입 및 반환
- 힙은 우선순위 큐로써 큐와 같은 구조를 가지고 있다.
- 따라서, 삽입은 ```offer()``` 메소드를 이용하고, 반환은 ```poll()``` 메소드를 이용하면 된다.

### 3.3. 응용
- 기본적인 객체를 담을 수도 있고, 또한 객체의 요소를 람다식을 이용하여 비교하는 방식으로 정렬할 수도 있다.
```Java
  Arrays.sort(jobs, new Comparator<int[]>() {
    @Override
    public int compare (int[] o1, int[] o2) {
      if (o1[0] != o2[0]) // 만약에 호출하는 시간이 같지 않다면
      return o1[0] - o2[0]; // 호출 시간이 빠른 순서대로 정렬
      return o1[1] - o2[1]; // 만약에 호출 시간이 같다면 걸리는 시간이 짧은 순서대로 정렬
    }
  });

  // 별개의 영역이지만 힙 문제 중 디스크 컨트롤러 문제에서
  // 기본 숫자 배열 정렬하는 방법을 구현한 코드이다.
```

## 4. 정렬 (Sorting)
- 정렬을 하는 방법은 크게 3가지가 있다.
  1. Arrays.sort();
  2. Collections.sort();
  3. new Comparator<>();

- 단순한 오름차순 정렬은 1, 2를 이용한다.
- 내림차순 정렬은 2번을 이용하고 ```Collections.reverse()``` 를 이용한다.
- 그 외 조건부 정렬을 구현하려고 할 때는 Comparator를 이용한다.

### 4.1. 정렬의 구현
```Java
  Arrays.sort(jobs); // 오름차순 정렬
  Collections.sort(arrayList); // 오름차순 정렬
  Collections.reverse(arrayList); // 역순, 즉 내림차순 정렬
```

### 4.2. 응용
```Java
  Arrays.sort(num, new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
      return (o2 + o1).compareTo(o1 + o2);
    }
  });
  // 문자열 두 개를 이어 붙인 것 중에 큰 순서대로 정렬
  // 내림차순 (디폴트가 오름차순이기 때문에) return 을 역순으로!
```

## 5. 완전 탐색 (BruteForce)
- 모든 케이스를 하나하나 다 탐색하는 방법이다.
- 주로 반복문으로 순회를 한다.

### 5.1. 응용
- 완전 탐색은 응용 예제를 중심으로 살펴보자

#### 5.1.1 모의고사
```Java
  int[] answer1 = {1, 2, 3, 4, 5}; // 찍는 순서의 배열을 넣는다.
  int index1 = i%5 // 0 - 4 까지의 나머지가 나오기 때문에 이것을 위의 정수 배열의 인덱스로 접근한다.
```

#### 5.1.2 소수 찾기
```Java
  // 모든 숫자의 조합을 찾기 위해서 DFS를 활용하여 순열을 구현한다.

  public static void func (String s, String number, ArrayList<String> stringArrayList) {
    if (number.length() == 0) {
      if (!s.equals(""))
        stringArrayList.add(s):
    } else {
      for (int i=0; i<number.length(); i++) {
        func(s + number.charAt(i), number.substring(0, i) + number.substring(i+1, number.length()), stringArrayList);
        for (int i=0; i<number.length(); i++) {
          func(s, number.substring(0, i) + number.substring(i+1, number.length()), stringArrayList);
        }
      }
    }
  }

  // 그 후에 중복된 값을 HashSet을 이용하여 거르고
  // 순회를 하면서 소수를 찾는다.
```

#### 5.1.3 숫자 야구
- 숫자 야구는 중복되지 않는 0 - 9까지의 숫자의 조합이기 떄문에 3중 반복문을 돌린다.
- 중간에 중복되지 않는 경우이기 때문에 각 자리 숫자가 같아지는 경우는 ```continue``` 키워드를 사용하여 넘긴다.

```Java
for (int i=1; i<10; i++) {
   for (int j=1; j<10; j++) {
       if (j==i) continue;
       for (int k=1; k<10; k++) {
           if (k==j || k==i) continue;

           boolean isTrue = true;

           for (int t=0; t<baseball.length; t++) {

               String num = String.valueOf(baseball[t][0]);
               int strike = 0;
               int ball = 0;

               if (i == Integer.parseInt(String.valueOf(num.charAt(0)))) {
                   strike++;
               } else if (i == Integer.parseInt(String.valueOf(num.charAt(1))) || i == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                   ball++;
               }

               if (j == Integer.parseInt(String.valueOf(num.charAt(1)))) {
                   strike++;
               } else if (j == Integer.parseInt(String.valueOf(num.charAt(0))) || j == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                   ball++;
               }

               if (k == Integer.parseInt(String.valueOf(num.charAt(2)))) {
                   strike++;
               } else if (k == Integer.parseInt(String.valueOf(num.charAt(0))) || k == Integer.parseInt(String.valueOf(num.charAt(1)))) {
                   ball++;
               }

               if (strike != baseball[t][1] || ball != baseball[t][2]) isTrue = false;

               if (!isTrue) break;
           }

           if (isTrue) answer++;
       }
   }
 }
```
