import java.util.Scanner;
import java.util.Stack;

public class Solution {

    // 여러 개의 쇠 막대기를 레이저로 절단
    // 효율적인 작업을 위해 쇠 막대기를 겹쳐놓고 절단하려 한다.
    // 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있다.
    // 쇠 막대기를 다른 쇠 막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝 점은 겹치지 않도록 놓는다.
    // 각 쇠 막대기를 자르는 레이저는 적어도 하나 존재한다.
    // 레이저는 어떤 쇠 막대기의 양 끝점과도 겹치지 않는다.

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();

        for (int i=1; i<=count; i++) {
            String TC;
            String[] output = new String[count];

            TC = sc.next();
            // ()(((()())(())()))(())

        }
    }
}
