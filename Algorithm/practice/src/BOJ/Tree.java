import java.util.*;

public class Tree {
    static int N, M, K;
    static int[][] A = new int[10][10];
    static int[][] F = new int[10][10];
    static ArrayList<TREE> Trees = new ArrayList<>();
    static int[] Dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] Dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                A[i][j] = sc.nextInt();
                F[i][j] = 5;
            }
        }

        int x, y, z;
        for (int i = 0; i < M; ++i) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();

            Trees.add(new TREE(x-1, y-1, z, true));
        }

    }

    static int solve() {
        for (int k = 0; k < K; ++k) {

            // 봄
            for (TREE t : Trees) {
                if (t.age <= F[t.r][t.c]) {
                    F[t.r][t.c] -= t.age;
                    t.age++;
                } else {
                    t.alive = false;
                }
            }

            // 여름
            for (Iterator<TREE> it = Trees.iterator(); it.hasNext();) {
                TREE t = it.next();

                if (!t.alive) {
                    F[t.r][t.c] += t.age / 2;
                    it.remove();
                }
            }

            // 가을
            ArrayList<TREE> newTrees = new ArrayList<>();
            for (TREE t : Trees) {
                if (t.age % 5 == 0) {

                    for (int i = 0; i < 8; ++i) {
                        int nr = t.r + Dr[i], nc = t.c + Dc[i];

                        if (nr < 0 || nr > N -1 || nc < 0 || nc > N-1) continue;

                        newTrees.add(new TREE(nr, nc, 1, true));
                    }
                }
            }
            Trees.addAll(0, newTrees);

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    F[i][j] += A[i][j];
                }
            }
        }

        return Trees.size();
    }

    static class TREE {
        int r, c, age;
        boolean alive;

        public TREE (int r, int c, int age, boolean alive) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.alive = alive;
        }
    }
}

/*
public static int N, M, K;
    public static int[][] Map = new int[10][10]; // 상도가 구매한 땅, 그리고 그 좌표에 있는 양분
    public static int[][] A = new int[10][10]; // 겨울에 로봇이 뿌릴 양분
    public static ArrayList<TreeInfo> T = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // N 은 상도가 가진 땅의 가로, 세로 넓이 (정사각형)
        M = sc.nextInt(); // 상도가 심을 나무의 갯수 (이만큼 정보를 입력해야함)
        K = sc.nextInt(); // K 년

        // N 개의 줄 만큼 로봇이 뿌릴 양분 값을 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // 심을 나무의 좌표와 처음 나이를 입력하는 반복문
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt(); // 나무의 r 좌표
            int y = sc.nextInt(); // 나무의 c 좌표
            int z = sc.nextInt(); // 나무의 나이

            T.add(new TreeInfo(x-1, y-1, z));
        }

        init(Map); // 땅의 양분을 5로 초기화

        // 이제 K년이 지난 후 살아있는 나무의 개수를 출력해주면 된다.
        System.out.println(solve(K));
    }

    public static void init(int[][] Map) {
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[i].length; j++) {
                Map[i][j] = 5;
            }
        }
    }

    public static int solve(int K) {

        int ret = 0;

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();

            ret = T.size();
        }

        return ret;
    }

    public static void spring() {

        // 나무의 나이 순으로 정렬
        Collections.sort(T, new Comparator<TreeInfo>() {
            @Override
            public int compare(TreeInfo o1, TreeInfo o2) {
                return o1.age - o2.age; // 내림차순 정의 (작은 수부터)
            }
        });

        for (int i = 0; i < T.size(); i++) {

            System.out.println(T.get(i).age);
            int r = T.get(i).r;
            int c = T.get(i).c;

            // 양분을 먹을 수 있다면
            if (Map[r][c] >= T.get(i).age) {
                Map[r][c] -= T.get(i).age; // 나무의 나이만큼 양분을 먹고
                T.get(i).age++; // 나이 증가
            } else {
                // 양분을 먹을 수 없다면
                T.get(i).alive = false; // 죽는다.
            }
        }
    }

    public static void summer() {

        ArrayList<Integer> dead = new ArrayList<>();

        for (int i = 0; i < T.size(); i++) {
            if (!T.get(i).alive) {
                // 만약 죽은 나무가 있다면
                Map[T.get(i).r][T.get(i).c] += Math.floor((double)(T.get(i).age/2)); // 그 나무의 나이를 2로 나눈 버린 값의 양분을 추가

                dead.add(i);
            }
        }

        for (int i = 0; i < dead.size(); i++) {
            T.remove(dead.get(i));
        }
    }

    public static void fall() {

        for (int i = 0; i < T.size(); i++) {
            // 만약 나무의 나이가 5의 배수라면
            if (T.get(i).age%5 == 0) {
                int r = T.get(i).r;
                int c = T.get(i).c;

                // 주변에 나이가 1인 나무를 생성
                breeding(r-1, c);
                breeding(r-1, c-1);
                breeding(r-1, c+1);
                breeding(r+1, c);
                breeding(r+1, c-1);
                breeding(r+1, c+1);
                breeding(r, c-1);
                breeding(r, c+1);
            }
        }
    }

    public static void winter() {
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[i].length; j++) {
                Map[i][j] += A[i][j];
            }
        }
    }

    // 나무 번식 (가을)
    public static void breeding (int r, int c) {
        // 범위를 벗어났다면 그냥 반환
        if (r < 0 || r > N-1 || c < 0 || c > N-1) return;

        T.add(new TreeInfo(r, c, 1));
    }


    static class TreeInfo {
        int r;
        int c;
        int age;
        boolean alive;

        public TreeInfo (int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;

            this.alive = true; // 나무가 아직 살아있다.
        }
    }
}
 */

/*
    삼성 기출

    나무 재테크

    부동산 투자로 억대의 돈을 번 상도는 최근 N*N 크기의 땅을 구매했다.
    상도는 손쉬운 땅 관리를 위해 땅을 1*1 크기의 칸으로 나누어 놓았다.
    각각의 칸은 (r, c) 로 나타내며, r은 가장 위에서부터 떨어진 칸의 개수,
    c는 가장 왼쪽으로부터 떨어진 칸의 개수이다. r과 c는 1부터 시작한다.

    상도는 전자통신공학과 출신답게 땅의 양분을 조사하는 로봇 S2D2를 만들었다.
    이 로봇은 1*1 크기의 칸에 들어있는 양분을 조사해 상도에게 전송하고, 모든 칸에 대해 조사한다.
    가장 처음에 양분은 모든 칸에 5만큼 들어있다.

    나무 재테크를 하자!

    나무 재테크란 작은 묘목을 구매해 어느정도 키운 후 팔아서 수익을 얻는 재테크이다.
    상도는 나무 재테크로 더 큰 돈을 벌기 위해 M개의 나무를 구매해 땅에 심었다.

    같은 1*1 크기의 칸에 여러 개의 나무가 심어져 있을 수도 있다.

    이 나무는 사계절을 보내며, 아래와 같은 과정을 반복한다.

    봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
    각각의 나무는 나무가 있는 1*1 크기의 칸에 있는 양분만 먹을 수 있다.

    하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을
    먹지 못하고 즉시 죽는다.

    여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
    각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
    소수점 아래는 버린다.

    가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며,
    인접한 8개의 칸에 나이가 1인 나무가 생긴다.

    어떤 칸과 인접한 칸은 둘러싸고 있는 8개이며 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.

    겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
    각 칸에 추가되는 양분의 양은 A[r][c] 이고, 입력으로 주어진다.

    K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.

    입력

    첫째 줄에 N, M, K 가 주어진다.

    둘째 줄부터 N개의 줄에 A 배열의 값이 주어진다.

    r번째 줄의 c번째 값은 A[r][c] 이다.

    다음 M개의 줄에는 상도가 심은 나무의 정보를 나타내는 세 정수 x, y, z 가 주어진다.
    처음 두 개의 정수는 나무의 위치 (x, y)를 의미하고, 마지막 정수는 그 나무의 나이를 의미한다.

 */