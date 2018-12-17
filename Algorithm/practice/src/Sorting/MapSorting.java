package Sorting;

import java.util.*;

public class MapSorting {

    public static void main(String[] args) {

        Map<Object, Object> data = new HashMap<>();

        data.put("A", 50);
        data.put("F", 10);
        data.put("C", 40);
        data.put("E", 100);
        data.put("W", 90);
        data.put("D", 100);

        Map<Object, Object> keySort = sortByKey(data, true);
        Map<Object, Object> valSort = sortByValue(data, true);

        Map<Object, Object> complexSort = sortByKey(sortByValue(keySort, true), true);


        for (Object key : keySort.keySet()) {
            System.out.println(key + ": " + keySort.get(key));
        }
    }

    /**
     * 맵(Map)을 키(key)를 기준으로 정렬하는 메소드
     * @param map 정렬하고자 하는 맵
     * @param isASC 오름차순 true / 내림차순 false
     * @return 정렬된 맵
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<Object, Object> sortByKey(final Map map, boolean isASC) {
        if (isASC) {
            return new TreeMap<Object, Object>(map);
        } else {
            TreeMap<Object, Object> tree = new TreeMap<Object, Object>(Collections.reverseOrder());
            tree.putAll(map);
            return tree;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<Object, Object> sortByValue(final Map map, boolean isASC) {
        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo((((Map.Entry) (o2)).getValue()));
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
