package SWExpertAcademy;

import java.io.*;
import java.util.*;

public class Treasure {
    private static int T;
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[] N, K;
    private static String[] S;

    public static void main (String[] args) throws NumberFormatException, IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        N = new int[T+1];
        K = new int[T+1];
        S = new String[T+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=T;i++) {
            N[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
            S[i] = br.readLine();
        }

        for (int i=1; i<=T; i++) {
            System.out.println("#" + i + " " + method(S[i], N[i], K[i]));
        }
    }

    public static int method (String s, int N, int K) {
        // N개의 숫자인 s를 돌려서 나올 수 있는 모든 숫자들 중에 K번째 수를 반환
        int rotate = N/4; // 만약 12라면 3반환
        ArrayList<Integer> arrayList = new ArrayList<>();

        // rotate 횟수 만큼 회전을 하여 넣는다.
        for (int i=0; i<rotate; i++) {
            int j = 0;
            while (j<s.length()) {
                arrayList.add(Integer.parseInt(s.substring(j, j+rotate))); // j를 기점으로 rotate의 길이만큼 잘라서 넣는다.
                // 중복처리를 해주어야함.
                j += rotate;
            }

            s = s.substring(1, s.length()) + s.substring(0, 2); // rotate 시킨 것
        }

        Collections.sort(arrayList);
        Collections.reverse(arrayList);

        return arrayList.get(K-1);
    }
}
