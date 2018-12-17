package DFS_BFS;

import java.util.*;

public class WordTransformation {

    static boolean[] visited = new boolean[50];

    public static int solution(String begin, String target, String[] word) {
        int answer = 0;

        Deque<Word> deque = new ArrayDeque<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        Word out;
        deque.offer(new Word(begin, 0, false));

        while (!deque.isEmpty()) {
            out = deque.poll();

            for (int i=0; i<word.length; i++) {

                int count = 0;

                for (int j=0; j<word[i].length(); j++) {
                    if (word[i].charAt(j) == out.string.charAt(j)) count++;
                }

                if (count == word[i].length() - 1 && !visited[i]) {
                    visited[i] = true;
                    if (word[i].equals(target)) {
                        deque.offer(new Word(word[i], out.cnt + 1, true));
                    } else {
                        deque.offer(new Word(word[i], out.cnt + 1, false));
                    }
                }

                if (out.target == true) {
                    answerList.add(out.cnt);
                }
            }
        }

        Collections.sort(answerList);

        if (!answerList.isEmpty()) answer = answerList.get(0);

        return answer;
    }

    // BFS 를 실행한다.
    // 처음에 begin 단어에서 1개의 단어만 바꿔서 고칠 수 있는 단어를 모두 찾는다.
    // 차례로 계속 BFS 실행

    static class Word {
        int cnt;
        String string;
        boolean target = false;

        public Word (String string, int cnt, boolean target) {
            this.string = string;
            this.cnt = cnt;
            this.target = target;
        }
    }

    public static void main (String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int a = solution("hit", "cog", words);

        System.out.println(a);
    }
}

/*
    두 개의 단어 begin, target 과 단어의 집합 words 가 있다.
    아래와 같은 규칙을 이용하여 begin 에서 target 으로 변환하는 가장 짧은 변환 과정을 찾으려고 한다.

    1. 한 번에 한 개의 알파벳만 바꿀 수 있다.
    2. words 에 있는 단어로만 변환할 수 있다.

    예를 들어, begin 이 hit, target 이 cog words 가 ... 라면 hit > hot > dot > dog > cog 와 같이 4단계를 거칠 수 있다.

    최소 몇 단계의 과정을 거치는지 return 하도록 solution 함수를 작성하여라.

    제한 사항
    1. 각 단어는 알파벳 소문자로만 이루어져 있습니다.
    2. 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
    3. words 에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
    4. begin 과 target 은 같지 않습니다.
    5. 변환할 수 없는 경우에는 0을 return 합니다.
 */