package Hash;

import java.util.*;

public class PhoneNumList {

    public boolean solution(String[] phone_book) {

        boolean answer = true;

        HashMap<Integer, String> m = new HashMap<>();

        for (int i=0; i<phone_book.length; i++) {
            m.put(i, phone_book[i]);
        }

        Iterator<Integer> iter = m.keySet().iterator();

        while(iter.hasNext()) {

            Integer iterator = iter.next();

            HashMap<Integer, String> cloneM = (HashMap<Integer, String>)m.clone();

            cloneM.remove(iterator);

            for (String value: cloneM.values()) {
                if (value.startsWith(m.get(iterator)))
                    return false;
            }
        }

        return answer;
    }
}

/*
    전화번호 부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있느지 확인하려 합니다.
    전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사 입니다.

    구조대: 119
    박준영: 97 674 223
    지영석: 11 9552 4421

    전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로
    주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false 를 그렇지 않으면 true 를 return
    하도록 solution 함수를 작성해주세요.

    제한 사항
    - phone_book 의 길이는 1 이상, 1,000,000 이하 입니다.
    - 각 전화번호의 길이는 1 이상 20 이하입니다.

    1. 해시맵 두 개를 만든다.
    2. 배열 값을 넣는다.
    3. 하나의 해시맵으로 아이터레이터를 돌면서 나머지 하나의 해시맵에 그 문자로 시작하는 값을 찾는다.
 */