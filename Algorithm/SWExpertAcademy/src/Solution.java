
// 민기 - 햄버거 덕후
// 민기 - 다이어트를 결심
// 칼로리 제한된 햄버거를 먹기로 결심
// 재료에 점수가 매겨져 있
// 맛잇으면서 칼로리가 낮은 조합을 찾아라

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    // 첫 번째 줄에는 재료의 수 N (1<= n <= 20)
    // 제한 칼로리 L (1 <= L <= 10000)

    public static void main(String arg[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        // 여러 개의 테스트 케이스가 주어지는 것을 차례대로 처리

        for (int test_case = 1; test_case <= T; test_case++) {

            int ingredientNum; // 1 ~ 20
            int calLimit; // 1 ~ 10000

            // HashMap<Integer, Integer> ingredientList = new HashMap<>();
            ArrayList<Integer> tasteList = new ArrayList<Integer>(); // 1 ~ 1000
            ArrayList<Integer> calList = new ArrayList<Integer>();   // 1 ~ 1000

            ingredientNum = sc.nextInt();
            calLimit = sc.nextInt();

            for (int i = 0; i < ingredientNum; i++) {
                int taste = sc.nextInt();
                int cal = sc.nextInt();

                tasteList.add(taste);
                calList.add(cal);
            }

            System.out.println("#" +  test_case + " " + burgerScore(tasteList, calList, calLimit));
        }
    }

    public static int burgerScore(ArrayList<Integer> tasteList, ArrayList<Integer> calList, int limit) {

        int Score = 0;

        if (tasteList.size() == 0 || calList.size() == 0)
            return 0;
        else {
            for (int i = 1; i <= calList.size(); i++ ) {
                    
            }

            return Score;
        }

    }

}
