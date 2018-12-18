package Heap;

import java.util.*;

public class RamenFactory {
    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;

        return answer;
    }

    public static void main (String[] args) {
        int a = 0;
        int[] dates = {4, 10, 15};
        int[] supplies = {20, 5, 10};
        a = solution(4, dates, supplies, 30);

        System.out.println(a);
    }
}

/*
    라면 공장에서는 하루에 밀가루를 1톤씩 사용한다.
    원래 밀가루를 공급받던 공장의 고장으로 앞으로 k일 이후에야 밀가루를 공급받을 수 있기 때문에
    해외 공장에서 밀가루를 수입해야 한다.

    해외 공장에서는 향후 밀가루를 공급할 수 있는 날짜와 수량을 알려주었고, 라면 공장에서는
    운송비를 줄이기 위해 최소한 횟수로 밀가루를 공급받고 싶습니다.

    현재 공장에 남아있는 밀가루 수량 stock
    밀가루 공급 일정 (dates)
    해당 시점에 공급 가능한 밀가루 수량 (supplies)
    원래 공장으로부터 공급받을 수 있는 시점 k 가 주어질 때, 밀가루가 떨어지지 않고 공장을 운영하기 위해서
    최소한 몇 번 해외 공장으로부터 밀가루를 공급받아야 하는지를 return 하도록 solution 함수를 완성하세요.

    data[i] 에는 i 번째 공급 가능일이 들어있으며, amounts[i] 에는 date[i] 날짜에 공급 가능한 밀가루 수량이 들어 있습니다.

    제한 사항
    1. stock 에 있는 밀가루는 오늘 이후부터 사용됩니다.
    2. stock 과 k 는 2 이상 100,000 이하 입니다.
    3. dates 의 각 원소는 1 이상 k 이하 입니다.
    4. supplies 의 각 원소는 1 이상 1,000 이하 입니다.
    5. dates 와 supplies 의 길이는 1 이상 20,000 이하 입니다.
    6. k 일 째에는 밀가루가 충분히 공급되기 때문에 k-1 일에 사용할 수량까지만 확보하면 됩니다.
    7. dates 에 들어있는 날짜는 오름차순 정렬되어 있습니다.

    ...
 */