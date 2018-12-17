package BruteForce;

import java.util.*;

public class PrimeNumbers {
    public static void main(String[] args) {

        int a = solution("17");

        System.out.print(a);
    }

    public static int solution (String numbers) {
        int answer = 0;

        ArrayList<String> stringList = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();

        func("", numbers, stringList);

        // 모든 숫자 조합 String 값을 대입한 stringList 의 String 값을 ParseInt 함수를 통해 바꿔준다. 이렇게 하면 011 과 같은 값이 처리된다.
        for (String string : stringList) {
            hs.add(Integer.parseInt(string));
        }

        // 해시맵에 대입하여 중복된 값을 처리해준다.
        // 에라토스테네스의 체를 사용한다.
        // 주어진 자연수 N이 소수이기 위한 필요충분조건은 N이 N의 제곱근보다 크지 않은 어떤 소수로도 나뉘지 않는다는 것이다.
        for (int num : hs) {
            boolean isPrime = true;

            if (num == 0 || num == 1) {
                isPrime = false;
            } else if (num == 2) {
                isPrime = true;
            } else {
                for (int j=2; j<num; j++) {

                    if (num%j == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            if (isPrime) answer++;
        }

        return answer;
    }

    public static void func (String s, String number, ArrayList<String> stringArrayList) {
        if (number.length() == 0) {
            if (!s.equals(""))
                stringArrayList.add(s);
        } else {
            for (int i=0; i<number.length(); i++)
                func(s + number.charAt(i), number.substring(0, i) + number.substring(i+1, number.length()), stringArrayList);
            for (int i=0; i<number.length(); i++)
                func(s, number.substring(0, i) + number.substring(i+1, number.length()), stringArrayList);
        }
    }
}

// 정답 코드