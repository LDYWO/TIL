package Hash;

import java.util.HashMap;

/*
    각 부위 명을 key, 해당하는 부위에 맞는 옷 가지를 벨류로 세어준다.

    각 부위에 해당하는 숫자에 + 1을 한 값을 한 줄로 세우는 경우의 수와 같다.

    예를 들어, 머리:3, 옷: 2, 바지: 1 인 경우는
    4*3*2 - 1 이다.

    1을 더한 이유는 안 입는 경우도 체크, 마지막 1을 뺀 이유는 모두 안 입는 경우를 제외하기 때문이다.

 */

public class Comouplage {
    public int solution (String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> hm = new HashMap<>();

        for (int i=0; i<clothes.length; i++) {
            String type = clothes[i][1];
            hm.put(type, hm.getOrDefault(type,0) + 1);
        }

        for (String keys : hm.keySet()) {
            answer *= (hm.get(keys) + 1);
        }

        answer = answer - 1;

        return answer;
    }
}
