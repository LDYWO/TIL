package BruteForce;

import java.util.*;

public class Test {
    public int[] solution (int[] answers) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        int[] answer1 = {1, 2, 3, 4, 5};
        int[] answer2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] answer3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for (int i=0; i<answers.length; i++) {
            int index1 = i%5;
            int index2 = i%8;
            int index3 = i%10;

            if (answers[i] == answer1[index1]) count1++;
            if (answers[i] == answer2[index2]) count2++;
            if (answers[i] == answer3[index3]) count3++;
        }

        int maxCount = Math.max(count1, Math.max(count2, count3));

        if (maxCount == count1) arrayList.add(1);
        if (maxCount == count2) arrayList.add(2);
        if (maxCount == count3) arrayList.add(3);

        int[] answer = new int[arrayList.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }
}
