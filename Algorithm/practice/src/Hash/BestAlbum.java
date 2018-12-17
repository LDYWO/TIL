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

    public static int[] solution(String[] genres, int[] plays) {

        int[] answer = {};

        HashMap<String, Integer> hm = new HashMap<>();
        HashMap<Integer, Integer> hm2 = new HashMap<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        // 각 장르 별로 몇 회 재생되었는지 정보를 가지고 있는 해시맵
        for (int i=0; i<genres.length; i++) {
            hm.put(genres[i], hm.getOrDefault(genres[i], 0) + plays[i]);
            hm2.put(i,plays[i]);
        }

        // hm, hm2 정렬
        Map<Object, Object> valueSort = sortByValue(hm, true);
        Map<Object, Object> complexSort = sortByValue(sortByKey(hm2, false), true);

        for (Object key : valueSort.keySet()) {
            String genre = (String)key;
            int count = 0;
            for (Object keys : complexSort.keySet()) {
                if (count == 2) {
                    break;
                } else {
                    Integer keyInt = (Integer)keys;
                    if (genres[keyInt].equals(genre)) {
                        answerList.add(keyInt);
                        count++;
                    }
                }
            }
        }

        answer = new int[answerList.size()];

        for (int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static Map<Object, Object> sortByKey(final Map map, boolean isASC) {
        if (isASC) {
            return new TreeMap<Object, Object>(map);
        } else {
            TreeMap<Object, Object> tree = new TreeMap<>(Collections.reverseOrder());
            tree.putAll(map);
            return tree;
        }
    }

    public static Map<Object, Object> sortByValue(final Map map, boolean isASC) {
        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare (Object o1, Object o2) {
                return ((Comparable) ((Map.Entry)(o1)).getValue()).compareTo((((Map.Entry)(o2)).getValue()));
            }
        });

        if (isASC) {
            Collections.reverse(list);
        }

        HashMap sortedHashMap = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
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