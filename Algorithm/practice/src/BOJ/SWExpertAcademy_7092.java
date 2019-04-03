import java.util.*;

public class SWExpertAcademy_7092 {

    public static int N;
    public static String[][] answerList; // 각각 아이들의 정답을 저장

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        // 테스트 케이스만큼 반복문을 돌린다.
        for (int i = 0; i < T; i++) {

            N = sc.nextInt(); // 문제의 수

            answerList = new String[3][N]; // answerList[0][1] 이면 명우의 정답 리스트의 두번째 문제의 답이다.

            for (int j = 0; j < 3; j++) {
                String[] s = sc.next().split(""); // split 으로 쪼개서 스트링 배열에 각각 할당 시킨다.
                for (int k = 0; k < s.length; k++) {
                    answerList[j][k] = s[k];
                }
            }

            System.out.println("#" + (i+1) + " " + solve(answerList));
        }

    }

    public static int solve(String[][] answerList) {

        int answer = 0;

        int[] sim = new int[3]; // 각 학생의 정답이 얼마나 유사한지 저장하는 배열

        for (int i = 0; i < N; i++) {
            if (answerList[0][i].equals(answerList[1][i]) && answerList[1][i].equals(answerList[2][i])) {
                // 전부 다 같은 경우 (같은 갯수 3개)
                sim[0]++;
                sim[1]++;
                sim[2]++;
            } else if (answerList[0][i].equals(answerList[1][i]) && !answerList[1][i].equals(answerList[2][i])) {
                // 1, 2 만 같은 경우 (같은 갯수 2개)
                sim[0]++;
                sim[1]++;
            } else if (answerList[0][i].equals(answerList[2][i]) && !answerList[1][i].equals(answerList[2][i])) {
                // 1, 3 만 같은 경우 (같은 갯수 2개)
                sim[0]++;
                sim[2]++;
            } else if (answerList[1][i].equals(answerList[2][i]) && !answerList[0][i].equals(answerList[1][i])) {
                // 2, 3 만 같은 경우 (같은 갯수 2개)
                sim[1]++;
                sim[2]++;
            }
        }

        Arrays.sort(sim);

        for (int i : sim) {
            System.out.println(i);
        }

        if (sim[2] < 1) {
            // 각 문제의 답이 모두 다른 경우
            answer = 1;
        } else {
            // 그래도 유사한 문제가 있는 경우
            answer = sim[0];
        }

        return answer;
    }
}

/*
    모두가 행복한 시험

    행복 유치원의 현주네 반에는 명우, 정우, 근우의 세 아이가 있다.
    현주네 반에 이 세명 이외의 다른 아이는 없다.
    어제 현주는 세 명의 아이들을 데리고 시험을 보았다.
    시험이라고는 했지만 거창한 것은 아니고 답도 정해진 문제들이 아니었다.
    문제는 모두 4지 선다형 문제로 A, B, C, D의 네 선택지 중 하나를 선택하는 문제들이었다.
    현주는 이 문제의 답을 정해서 아이들에게 알려줘야 한다.
    이 때, 아이들이 서로가 받은 점수로 싸우는 일이 없도록 가장 점수가 낮은 아이의 점수가 최대화 되도록 정답을 정하려고 한다.
    이를 도와주는 프로그램을 작성하라.

    [입력]

    첫 번째 줄에 테스트 케이스의 수 T 가 주어진다.
    각 테스트 케이스의 첫 번쨰 줄에는 시험의 문제 수를 나타내는 하나의 정수 N 이 주어진다.
    다음 세 줄에는 길이가 N인 문자열이 하나씩 주어진다.
    각각의 문자열은 각 학생이 어떤 식으로 시험에 답을 했는지를 나타낸다.
    i 번째 문자는 i번째 문제에 대한 답변으로 A, B, C, D 중 하나이다.

    [출력]

    각 테스트 케이스마다 '#x' 를 출력하고 한 칸을 띄운 후
    가장 점수가 낮은 아이의 점수로 가능한 최댓 값을 출력한다.
 */