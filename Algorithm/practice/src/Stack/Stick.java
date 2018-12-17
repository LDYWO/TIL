package Stack;

import java.util.*;

public class Stick {
    public int solution (String arrangement) {
        int answer = 0;

        Stack st = new Stack();

        for (int i=0; i<arrangement.length(); i++) {
            if (arrangement.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();
                if (arrangement.charAt(i-1) == '(') {
                    answer += st.size(); // 레이저일 때
                } else {
                    answer += 1; // 쇠 막대기의 끝 부분일 때
                }
            }
        }

        return answer;
    }
}

/*
    여러 개의 쇠 막대기 절단
    효율적인 작업을 위해 쇠 막대기를 겹쳐놓고 자른다

    배치 조건
    1. 쇠 막대기는 자신보다 긴 쇠 막대기 위에만 놓일 수 있다.
    2. 쇠 막대기를 다른 쇠 막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝 점은 겹치지 않도록 놓는다.
    3. 각 쇠 막대기를 자르는 레이저는 적어도 하나 존재한다.
    4. 레이저는 어떤 쇠 막대기의 양 끝 점과도 겹치지 않는다.

 */
