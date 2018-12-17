package DFS_BFS;

import java.util.*;

public class TravelPath {

    static boolean[] visited = new boolean[10000];
    static ArrayList<String> answerList = new ArrayList<>();
    static int CNT;

    public static String[] solution (String[][] tickets) {
        String[] answer = {};

        ArrayList<Ticket> ticketList = new ArrayList<>();

        for (int i=0; i<tickets.length; i++) {
            String starting = tickets[i][0];
            String destination = tickets[i][1];

            ticketList.add(new Ticket (starting, destination));
        }

        // destination 의 알파벳 순서대로 정렬
        Collections.sort(ticketList, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                int i = o1.destination.compareTo(o2.destination);
                return i;
            }
        });

        for (int i=0; i<ticketList.size(); i++) {
            if (ticketList.get(i).starting.equals("ICN")) {
                DFS (i, 1, ticketList);

                // 만약 모든 티켓을 다 사용한 경우라면 그대로 반환한다.
                if (CNT == ticketList.size() && answerList.size() == ticketList.size() + 1) break;
                else { // 아니라면 계속 DFS 실행
                    init();
                    answerList.clear();
                    CNT = 0;
                }
            }
        }

        answer = new String[answerList.size()];

        for (int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static void DFS (int index, int cnt, ArrayList<Ticket> ticketList) {

        CNT = cnt;
        visited[index] = true;
        boolean isTrue = false;

        answerList.add(ticketList.get(index).starting);

        String destination = ticketList.get(index).destination;

        for (int i=0; i<ticketList.size(); i++) {
            if (ticketList.get(i).starting.equals(destination) && !visited[i]) {
                DFS(i, CNT + 1, ticketList);
                isTrue = true;
            }
        }

        if (!isTrue) answerList.add(destination);
    }

    public static void init() {
        for (int i=0; i<visited.length; i++) {
            visited[i] = false;
        }
    }

    // DFS 로 해야할지 BFS 로 해야할지 고민을 조금 해봐야겠다.

    static class Ticket {

        String starting;
        String destination;

        public Ticket (String starting, String destination) {
            this.starting = starting;
            this.destination = destination;
        }

    }

    public static void main (String[] args) {
        // String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        // String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] tickets = {{"ICN", "ATL"}, {"ICN", "SFO"}, {"SFO", "ICN"}};
        String[] a = solution(tickets);

        for (String string : a) {
            System.out.println(string);
        }

    }
}

/*
    주어진 항공권을 모두 이용하여 여행 경로를 짜려고 합니다.
    항상 인천 공항에서 출발합니다. ("ICN")

    항공권 정보가 담긴 2차원 배열 tickets 가 매개변수로 주어질 때, 방문하는 공항 경로를
    배열에 담아 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    - 모든 공항은 알파벳 대문자 3 글자로 이루어집니다.
    - 주어진 공항 수는 3 개 이상 10,000 개 이하입니다.
    - tickets 의 각 행 [a, b] 는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
    - 주어진 항공권은 모두 사용해야 합니다.
    - 만일 가능한 경로가 2 개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
    - 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
 */