package SWExpertAcademy;

import java.util.*;

public class Picnic_5695 {
    private static int N; // 학생의 수
    private static int[] R; // 일방적인 관계를 나타내는 것
    private static int Count; // 정답의 최댓 값을 출력하는 것
    private static ArrayList<Integer> arrayList; // 하나의 링크드 리스트를 만들어주는 것

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T ; i++) {
            N = sc.nextInt();
            R = new int[N];

            for (int j = 0; j < R.length; j++) {
                R[j] = sc.nextInt();
            }

            arrayList = new ArrayList<>();

            Count = 0;

            DFS(0, R, arrayList);

            System.out.println("#" + i + " " + Count);
        }
    }

    // ArrayList 에 각 인덱스를 담고 마지막에 처음 인덱스와 마지막 인덱스가 같다면 전체 사이즈 - 1 을 출력하는 프로그램을 만들자
    // 각각을 DFS 로 진행한다.

    public static void DFS (int index, int[] R, ArrayList<Integer> arrayList) {

        arrayList.add(index);

        if (arrayList.size() >= 2 && arrayList.get(0) == arrayList.get(arrayList.size()-1)) {
            Count = Math.max(Count, arrayList.size());
            return;
        }

        for (int i = 0; i < arrayList.size(); i++) {
            DFS(0,R,arrayList);
            DFS(index, R, arrayList);
        }
    }
}

/*
    삼성 초등학교의 홍 선생님이 가르치는 학급엔 N명의 학생이 있다.
    각 학생은 정확히 한 명의 절친이 있다.
    학생의 번호를 1번에서 N 번까지 붙이면, i번 학생의 절친은 Fi 이다.
    이 관계는 일방적이기 때문에, i번 학생의 절친이 j번 학생이더라도 j번 학생의 절친은 i가 아닐 수 있다.
    홍선생님은 내일 학생들을 데리고 가을 소풍을 가기로 했다.
    소풍을 가서는 여러 활동을 할 것인데, 이중 몇 가지 활동을 하기 위해선 학생들이 반드시 원을 이루며 앉아야 한다.
    학생들이 원을 이루어 앉을 때, 자신의 오른쪽 혹은 왼쪽에 절친이 있어야만 안심하고 앉을 수 있다.
    만약 그렇지 않다면 해당 학생은 원을 이뤄 앉지 않고 다른 학생들이 활동 하는 것을 일어 서서 지켜 보기만 할 것이다.
    원을 이뤄 앉을 수 있는 학생 수의 최대치를 구하는 프로그램을 작성하라
 */