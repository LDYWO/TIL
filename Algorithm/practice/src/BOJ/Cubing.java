import java.util.*;

public class Cubing {
    static int T;
    static String[] M = new String[1000];
    static String[][] Cube = new String[6][9]; // 위, 아래, 앞, 뒤, 좌, 우 순서
    static String[][] ANSWER = new String[100][9]; // 테스트 케이스마다 답을 담는 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int i = 0; i < T; ++i) {
            int num = sc.nextInt();

            init();

            for (int j = 0; j < num; ++j) {
                M[j] = sc.next();
            }

            solve(M);

            for (int j = 0; j < 9; ++j) {
                ANSWER[i][j] = Cube[0][j];
            }
        }

        for (int i = 0; i < T; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(ANSWER[i][j]);
                if ((j+1)%3 == 0 ) System.out.println();
            }
        }
    }

    static void solve(String[] M) {
        for (int i = 0; i < M.length; ++i) {
            rotate(M[i]);
        }
    }

    static void rotate (String m) {
        char rot = m.charAt(0);
        char dir = m.charAt(1);

        // U 부터 시작해보자
        switch (rot) {
            case 'U' :
                if (dir == '+') {
                    clockwise(0);
                } else {
                    // 반 시계 방향은 시계 방향을 3번 돌리면 됨
                    clockwise(0);
                    clockwise(0);
                    clockwise(0);
                }

            case 'D' :
                if (dir == '+') {
                    clockwise(1);
                } else {
                    clockwise(1);
                    clockwise(1);
                    clockwise(1);
                }

            case 'F' :
                if (dir == '+') {
                    clockwise(2);
                } else {
                    clockwise(2);
                    clockwise(2);
                    clockwise(2);
                }

            case 'B' :
                if (dir == '+') {
                    clockwise(3);
                } else {
                    clockwise(3);
                    clockwise(3);
                    clockwise(3);
                }

            case 'L' :
                if (dir == '+') {
                    clockwise(4);
                } else {
                    clockwise(4);
                    clockwise(4);
                    clockwise(4);
                }

            case 'R' :
                if (dir == '+') {
                    clockwise(5);
                } else {
                    clockwise(5);
                    clockwise(5);
                    clockwise(5);
                }
        }
    }

    static void clockwise (int cnt) {
        // cnt = 돌리는 면의 방향

        String[][] CCube = new String[6][9];

        CCube[cnt][0] = Cube[cnt][6];
        CCube[cnt][1] = Cube[cnt][3];
        CCube[cnt][2] = Cube[cnt][0];
        CCube[cnt][3] = Cube[cnt][7];
        CCube[cnt][5] = Cube[cnt][1];
        CCube[cnt][6] = Cube[cnt][8];
        CCube[cnt][7] = Cube[cnt][5];
        CCube[cnt][8] = Cube[cnt][2];

        switch (cnt) {
            case 0 :
                // 아래 면 빼고 다
                CCube[3][2] = Cube[4][2];
                CCube[3][1] = Cube[4][1];
                CCube[3][0] = Cube[4][0];

                CCube[5][2] = Cube[3][2];
                CCube[5][1] = Cube[3][1];
                CCube[5][0] = Cube[3][0];

                CCube[2][2] = Cube[5][2];
                CCube[2][1] = Cube[5][1];
                CCube[2][0] = Cube[5][0];

                CCube[4][2] = Cube[2][2];
                CCube[4][1] = Cube[2][1];
                CCube[4][0] = Cube[2][0];

                Cube[3][2] = CCube[3][2]; // ... 노가다
            case 1 :
                // 윗 면 빼고 다
                CCube[3][7] = Cube[5][7];
                CCube[3][8] = Cube[5][8];
                CCube[3][9] = Cube[5][9];

                CCube[4][7] = Cube[3][7];
                CCube[4][8] = Cube[3][8];
                CCube[4][9] = Cube[3][9];

                CCube[2][7] = Cube[4][7];
                CCube[2][8] = Cube[4][8];
                CCube[2][9] = Cube[4][9];

                CCube[5][7] = Cube[2][7];
                CCube[5][8] = Cube[2][8];
                CCube[5][9] = Cube[2][9];

            case 2 :
                // 뒷 면 빼고 다
                CCube[0][7] = Cube[4][8];
                CCube[0][8] = Cube[4][5];
                CCube[0][9] = Cube[4][2];

                CCube[5][0] = Cube[0][7];
                CCube[5][3] = Cube[0][8];
                CCube[5][5] = Cube[0][9];

                CCube[2][7] = Cube[5][0];
                CCube[2][8] = Cube[5][3];
                CCube[2][9] = Cube[5][5];

                CCube[4][8] = Cube[2][7];
                CCube[4][5] = Cube[2][8];
                CCube[4][2] = Cube[2][9];

            case 3 :

                Cube[3][2] = Cube[4][2];
                Cube[3][1] = Cube[4][1];
                Cube[3][0] = Cube[4][0];

                Cube[5][2] = Cube[3][2];
                Cube[5][1] = Cube[3][1];
                Cube[5][0] = Cube[3][0];

                Cube[2][2] = Cube[5][2];
                Cube[2][1] = Cube[5][1];
                Cube[2][0] = Cube[5][0];

                Cube[4][2] = Cube[2][2];
                Cube[4][1] = Cube[2][1];
                Cube[4][0] = Cube[2][0];

            case 4 :

                Cube[3][2] = Cube[4][2];
                Cube[3][1] = Cube[4][1];
                Cube[3][0] = Cube[4][0];

                Cube[5][2] = Cube[3][2];
                Cube[5][1] = Cube[3][1];
                Cube[5][0] = Cube[3][0];

                Cube[2][2] = Cube[5][2];
                Cube[2][1] = Cube[5][1];
                Cube[2][0] = Cube[5][0];

                Cube[4][2] = Cube[2][2];
                Cube[4][1] = Cube[2][1];
                Cube[4][0] = Cube[2][0];

            case 5 :

                Cube[3][2] = Cube[4][2];
                Cube[3][1] = Cube[4][1];
                Cube[3][0] = Cube[4][0];

                Cube[5][2] = Cube[3][2];
                Cube[5][1] = Cube[3][1];
                Cube[5][0] = Cube[3][0];

                Cube[2][2] = Cube[5][2];
                Cube[2][1] = Cube[5][1];
                Cube[2][0] = Cube[5][0];

                Cube[4][2] = Cube[2][2];
                Cube[4][1] = Cube[2][1];
                Cube[4][0] = Cube[2][0];
        }

    }

    static void init () {
        for (int i = 0; i < Cube.length; ++i) {
            for (int j = 0; j < Cube[i].length; ++j) {
                switch (i) {
                    case 0 :
                        Cube[i][j] = "w";
                    case 1 :
                        Cube[i][j] = "y";
                    case 2 :
                        Cube[i][j] = "r";
                    case 3 :
                        Cube[i][j] = "o";
                    case 4 :
                        Cube[i][j] = "g";
                    case 5 :
                        Cube[i][j] = "b";
                }
            }
        }
    }
}

/*
    큐빙

    루빅스 큐브는 삼차원 퍼즐이다.
    보통 루빅스 큐브는 3*3*3 개의 작은 정육면체로 이루어져 있다.
    퍼즐을 풀려면 각 면에 있는 아홉 개의 작은 정육면체의 색이 동일해야 한다.

    큐브는 각 면을 양방향으로 90도 만큼 돌릴 수 있도록 만들어져 있다.
    회전이 마친 이후에는, 다른 면을 돌릴 수 있다.

    이렇게 큐브의 서로 다른 면을 돌리다 보면, 색을 섞을 수 있다.

    이 문제에서는 루빅스 큐브가 모두 풀린 상태에서 시작한다.

    윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색이다.

    루빅스 큐브를 돌린 방법이 순서대로 주어진다.
    이 때, 모두 돌린 다음에 가장 윗 면의 색상을 구하는 프로그램을 작성하시오.

    입력
        - 첫째 줄에 큐브를 돌린 횟수 n이 주어진다.
        - 둘째 줄에는 큐브를 돌린 방법이 주어진다.
            - 각 방법은 공백으로 구분되어져 있으며, 첫 번째 문자는 돌린 면이다.
            - U : 윗 면, D : 아랫 면, F : 앞 면, B : 뒷 면, L : 왼쪽 면, R : 오른쪽 면이다.
            - 두번 째는 돌린 방향이다.
            - +인 경우에는 시계 방향, - 인 경우에는 반시계 방향이다.

    출력
        각 테스트 케이스에 대해서 큐브를 모두 돌린 후의 윗 면의 색상을 출력한다.
        첫 번째 줄에는 뒷 면과 접하는 칸의 색을 출력하고,
        두 번째, 세 번째 줄은 순서대로 출력하면 된다.
        흰색은 w, 노란색은 y, 빨간색은 r, 오랜지색은 o, 초록색은 g, 파란색은 b
 */