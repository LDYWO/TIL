package SWExpertAcademy;

import java.util.*;

// 완료
public class String_5949 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            char[] start = sc.next().toCharArray(); // charArray 로 변환해서 대입해주는 함수
            char[] target = sc.next().toCharArray();

            ArrayList<Integer> sl = new ArrayList<>();
            ArrayList<Integer> tl = new ArrayList<>();

            long count = 0;

            for (int j = 0; j < start.length; j++) {
                if (start[j] == 'a') sl.add(j);
                if (target[j] == 'a') tl.add(j);
            }

            for (int j = 0; j < sl.size(); j++) {
                count += (long)Math.abs(sl.get(j) - tl.get(j));
            }

            System.out.println("#" + i + " " + count);
        }
    }
}

/*
    문자 'a' 와 'b' 로만 이루어진 문자열 S가 있다.
    이 문자열의 인접한 두 문자를 바꾸는 연산을 계속할 수 있다.
    이 때, S를 T로 변경할 수 있는가? 가능하다면 필요한 연산의 최솟 값은 몇 번인가?
 */