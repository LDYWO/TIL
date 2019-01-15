package Hash;

import java.util.*;

public class BestAlbum {

    public static void main(String[] args) {
        String[] genres = {"classic","pop","classic","pop","classic","classic"};
        int[] plays = {400,600,150,2500,500,500};

        int[] answer = solution(genres, plays);

        for (int i=0; i<answer.length; i++){
            System.out.print(answer[i] + ", ");
        }
    }

    public static int[] solution (String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, Integer> hm1 = new HashMap<>();
        HashMap<Integer, Integer> hm2 = new HashMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        // 가장 많이 재생된 장르순서대로 정렬하기 위한 해시맵 추가
        for (int i=0; i<genres.length; i++) {
            hm1.put(genres[i], hm1.getOrDefault(genres[i], 0) + plays[i]);
            hm2.put(i, plays[i]);
        }

        Map<Object, Object> valueSort = sortByValue(hm1, true); // isASC = 내림차순 정렬
        Map<Object, Object> complexSort = sortByValue(sortByKey(hm2, false), true);

        for (Object key : valueSort.keySet()) {
            String genre = (String)key;
            int count = 0;

            for (Object comkey : complexSort.keySet()) {
                if (count == 2) break;

                Integer id = (Integer)comkey;
                if (genres[id].equals(genre)) {
                    // 재생 횟수 순으로 정렬, 같으면 아이디가 작은 순으로 정렬한 맵을 순회하면서
                    // 해당 아이디와 총 재생횟수가 많은 장르 순으로 일치한다면 재생 목록에 순서대로 추가한다.
                    arrayList.add(id);
                    count++;
                }
            }
        }

        answer = new int[arrayList.size()];

        for (int i=0; i<arrayList.size(); i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }

    public static Map<Object, Object> sortByKey (final Map map, boolean isASC) {
        if (isASC) {
            return new TreeMap<Object, Object>(map);
        } else {
            TreeMap<Object, Object> tree = new TreeMap<>(Collections.reverseOrder());
            tree.putAll(map);
            return tree;
        }
    }

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
}

/*

    스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려고 한다.

    노래는 고유번호로 구분한다.

    노래를 수록하는 기준은 다음과 같다.

    1. 속한 노래가 많이 재생된 장르를 먼저 수록한다.
    2. 장르 내에서 많이 재생된 노래를 먼저 수록한다.
    3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록한다.

    1. 재생 횟수 토탈 순으로 장르 정렬
    2. 장르 별로 재생 횟수에 따라 정렬
    3. 장르 내에서 재생 횟수가 같으면 고유 번호가 낮은 순으로 정렬한다는 조건 추가
    4. 정렬된 자료구조 내에서 앞의 두개 선택

 */