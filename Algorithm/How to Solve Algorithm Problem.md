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