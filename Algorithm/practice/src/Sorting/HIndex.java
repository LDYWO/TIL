package Sorting;

import java.util.*;

public class HIndex {
    public int solution (int[] citations) {
        int answer = 0;

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i : citations) {
            arrayList.add(i);
        }

        Collections.sort(arrayList);
        Collections.reverse(arrayList);

        // 6 5 3 1 0
        while (answer < arrayList.size() && arrayList.get(answer) >= answer + 1) {
            answer++;
        }

        return answer;
    }
}

/*
    H-Index 는 과학자의 생산성과 영향력을 나타내는 지표이다.

    어떤 과학자가 발표한 논문 n 편 중, h 번 이상 인용된 논문이 h 편 이상이고
    나머지 논문이 h 번 이하 인용되었다면 h 가 이 과학자의 H-Index 입니다.

    어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations 가 매개변수로 주어질 때,
    이 과학자의 H-Index 를 return 하도록 solution 함수를 작성해주세요.

    [3, 0, 6, 1, 5] -> 발표한 논문이 총 5개

    그 중에 3편의 논문은 3회 이상 인용되었다.
    그리고 나머지 2 편의 논문은 3회 이하 인용되었다.

    문제를 이해하는게 어려웠다...
 */