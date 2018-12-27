package DFS_BFS;

import java.util.*;

public class TravelPath {

    public static ArrayList<String> ANSWERLIST = new ArrayList<>();

    public static String[] solution (String[][] tickets) {

        String[] answer = {};

        ArrayList<Ticket> ticketList = new ArrayList<>();
        boolean[] visited = new boolean[tickets.length];

        for (int i=0; i<tickets.length; i++) {
            ticketList.add(new Ticket(tickets[i][0], tickets[i][1]));
        }

        DFS (0, "","ICN", ticketList, visited);
        Collections.sort(ANSWERLIST);

        answer = new String[ANSWERLIST.size()];

        answer = ANSWERLIST.get(0).split(" ");

        return answer;
    }

    public static void DFS (int index, String s, String country, ArrayList<Ticket> ticketList, boolean[] visited) {

        s = s + country + " ";

        for (int i=0; i<ticketList.size(); i++) {
            if (!visited[i] && ticketList.get(i).departure.equals(country)) {
                visited[i] = true;
                DFS(index + 1, s, ticketList.get(i).destination, ticketList, visited);
                // DFS 를 진행 (여기서 끝날 떄 까지 재귀), 만약 모든 방문지를 방문하지 못한 경우를 대비하여 index 설정
                visited[i] = false;
            }
        }

        if (index == ticketList.size()) {
            ANSWERLIST.add(s);
        }
    }

    static class Ticket {
        String departure;
        String destination;

        public Ticket (String departure, String destination) {
            this.departure = departure;
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