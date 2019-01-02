package BruteForce;

import java.util.*;

public class Carpet {
    public int[] solution (int brown, int red) {
        int[] answer = {};

        ArrayList<Integer> arrayList = new ArrayList<>();

        int all = brown + red;

        int width = 1;

        // 약수를 찾기 위한 반복문
        while (width<all) {
            if (all%width == 0) {
                int height = all/width;
                if (width >= height && (width-2)*(height-2) == red) {
                    arrayList.add(width);
                    arrayList.add(height);
                    break;
                }
            }
            width++;
        }

        answer = new int[arrayList.size()];

        for (int i=0; i<answer.length; i++) {
            answer[i] = arrayList.get(i);
        }

        return answer;
    }
}

/*
    Leo 는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 빨간색으로 칠해져 있고
    모사리는 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

    Leo 는 집으로 돌아와서 아까 본 카펫의 빨간색과 갈색으로 색칠된 격자의 개수는 기억했지만,
    전체 카펫의 크기는 기어갛지 못했습니다.

    Leo 가 본 카펫에서 갈색 격자의 수 brown, 빨간색 격자의 수 red 가 매개 변수로
    주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작서앻주세요.

    제한 사항
    1. 갈색 격자의 수 brown 은 8 이상 5,000 이하인 자연수 입니다.
    2. 빨간색 격자의 수 red 는 1 이상 2,000,000 이하인 자연수입니다.
    3. 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.

    입출력 예
    10 2 [4, 3]
    8 1 [3, 3]
    24 24 [8, 6]
 */
