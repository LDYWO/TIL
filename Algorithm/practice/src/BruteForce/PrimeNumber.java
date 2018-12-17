package BruteForce;

import java.util.*;

public class PrimeNumber {
    public static void main(String[] args) {

        int a = solution("17");

        System.out.print(a);

    }

    public static int solution (String numbers) {
        int answer = 0;

        Permutation ex = new Permutation();
        String[] s = numbers.split("");
        ArrayList<String> stringList = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i=0; i<s.length; i++) {
            String[] combArr = new String[i+1];
            ex.doCombination(stringList, combArr, s, s.length, i+1, 0, 0);
        }

        // 모든 숫자 조합 String 값을 대입한 stringList 의 String 값을 ParseInt 함수를 통해 바꿔준다. 이렇게 하면 011 과 같은 값이 처리된다.
        for (String string : stringList) {
            int i = Integer.parseInt(string);
            hm.put(i, i);
        }

        // 해시맵에 대입하여 중복된 값을 처리해준다.
        // 에라토스테네스의 체를 사용한다.
        // 주어진 자연수 N이 소수이기 위한 필요충분조건은 N이 N의 제곱근보다 크지 않은 어떤 소수로도 나뉘지 않는다는 것이다.
        for (int num : hm.keySet()) {
            boolean isPrime = true;

            if (num == 0 || num == 1 || num == 2) {
                isPrime = false;
            }

            for (int j=2; j<num; j++) {

                if (num%j == 0) {
                    isPrime = false;
                    break;
                }
            }

            System.out.println(num);
            System.out.println(isPrime);
            if (isPrime) answer++;
        }

        return answer;
    }

    static class Permutation {
        public void doCombination (ArrayList<String> t, String[] combArr, String[] arr, int n, int r, int index, int target) {
            if (r == 0) {
                // to-do : combArr permutation
                doPermutation(t, combArr, 0);
            } else if (target == n) return;
            else {
                combArr[index] = arr[target];
                doCombination(t, combArr, arr, n, r-1, index+1, target+1);
                doCombination(t, combArr, arr, n, r, index, target+1);
            }
        }

        public void doPermutation (ArrayList<String> t, String[] arr, int startIdx) {
            int length = arr.length;
            if (startIdx == length) {
                String temp = "";
                for (int i=0; i<arr.length; i++) {
                    temp += arr[i];
                }
                t.add(temp);
                return;
            }
            for (int i=startIdx; i<length; i++) {
                swap(arr, startIdx, i);
                doPermutation(t, arr, startIdx+1);
                swap(arr, startIdx, i);
            }
        }

        public void swap (String[] arr, int n1, int n2) {
            String temp = arr[n1];
            arr[n1] = arr[n2];
            arr[n2] = temp;
        }
    }
}

/*
    한 자리 숫자가 적힌 종이 조각이 흩어져있다.
    흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 한다.

    각 종이 조각에 적힌 숫자가 적힌 문자열 numbers 가 주어졌을 때,
    종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    1. numbers 는 길이 1 이상 7 이하인 문자열입니다.
    2. numbers 는 0 - 9 까지 숫자만으로 이루어져있습니다.
    3. "013" 은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져 있다는 의미입니다.

    numbers 가 예를 들어 013 이면 0 1 3 숫자가 적힌 종이 조각이 흩어져 있다는 의미이다.
 */
