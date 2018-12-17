package Hash;

/*
    수많은 마라톤 선수들이 마라톤에 참여하였습니다.
    단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

    마라톤에 참여한 선수들의 이름이 담긴 배열 participant
    완주한 선수들의 이름이 담긴 배열 completion

    완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요

    제한 사항

    1. 마라톤 경기에 참여한 선수의 수는 1명 이상 10000명 이하 입니다.
    2. completion 의 길이는 participant 의 길이보다 1 작습니다.
    3. 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져있습니다.
    4. 참가자 중에는 동명이인이 있을 수 있습니다.

    레오는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

    빈코는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다

    미슬라브는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

    [1, 2, 1, 3] , [2, 3, 1]

 */

import java.util.*;

public class Marathon {

    public String Solution (String[] participant, String[] completion) {

        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();

        // 벨류 값으로 해당하는 키 값의 이름이 몇 번 들어갔느냐를 카운트 해줄 수 있다.
        for (String player : participant ) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion ) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0) {
                answer = key;
            }
        }

        return answer;
    }
}
