package DP;

public class SeoulToKS {
    private static int[][] DP;
    private static int TIME;

    public static int solution (int K, int[][] travel) {

        TIME = K;

        DP = new int[K+1][travel.length + 1];

        return DFS(travel, 0, 0);
    }

    private static int DFS (int[][] travel, int time, int cnt) {

        if (cnt == travel.length) {
            return 0;
        }

        if (DP[time][cnt] != 0) {
            return DP[time][cnt];
        }

        if (time + travel[cnt][0] <= TIME) {
            DP[time][cnt] = DFS(travel, time + travel[cnt][0], cnt + 1) + travel[cnt][1];
        }

        if (time + travel[cnt][2] <= TIME) {
            DP[time][cnt] = Math.max(DP[time][cnt], DFS(travel, time + travel[cnt][2], cnt + 1) + travel[cnt][3]);
        }

        return DP[time][cnt];
    }

    public static void main (String[] args) {

        int K = 1650;
        int[][] travel = {{500, 200, 200, 100}, {800, 370, 300, 120}, {700, 250, 300, 90}};

        int a = solution(K, travel);

        System.out.println(a);
    }
}

/*
    서울에서 경산까지

    문제 설명

    서울에서 경산까지 여행을 하면서 모금 활동을 하려고 합니다.
    여행은 서울에서 출발해 다른 도시를 정해진 순서대로 딱 한번 방문한 후 경산으로 도착할
    예정입니다.
    도시를 이동할 때에는 도보 혹은 자전거를 이용합니다.
    이 때 도보 이동에 걸리는 시간, 도보 이동 시 얻을 모금액, 자전거 이동에 걸리는 시간,
    자전거 이동 시 얻을 모금액이 정해져 있습니다.
    K 시간 이내로 여행할 때 모을 수 있는 최대 모금액을 알아보려고 합니다.

    예를 들어 여행 루트가 다음과 같고 K = 1650 일 떄,

    1, 2번 구간은 도보로 3번 구간은 자전거로 이동해 모금액을 660으로 하는 것이 가장
    좋은 방법입니다. 이 때, 시간은 1,600 시간이 소요됩니다

    도보, 자전거 - 시간, 모금액

    행은 3 이상 100 이하 // 즉 도시 구간이 100개 이하
    각 열은 4씩 이루어진다

 */