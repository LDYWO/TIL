import java.util.Arrays;

public class Number124 {

    static public String solution(String s) {
        String answer = "";

        String[] arrays = s.split(" ");
        int[] answers = new int[arrays.length];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = Integer.valueOf(arrays[i]);
        }

        Arrays.sort(answers);

        answer = answers[0] + " " + answers[answers.length-1];

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("-1 -2 -3 -4"));
    }
}
