package CodingTest;

import java.util.ArrayList;
import java.util.Scanner;

public class IP {

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            String ipAddressString = scanner.nextLine();

            String str1 = ipAddressString.substring(0,3);
            String str2 = ipAddressString.substring(3,6);
            String str3 = ipAddressString.substring(6,9);
            String str4 = ipAddressString.substring(9);

            if (Integer.parseInt(str1) > 255) {
                str1 = ipAddressString.substring(0,2);
                str2 = ipAddressString.substring(2,5);
                str3 = ipAddressString.substring(5,8);
                str4 = ipAddressString.substring(8);
            }

            if (Integer.parseInt(str2) > 255) {

            }

            System.out.print(str4);
        }
    }
}

/*
    Sally 는 자신의 컴퓨터와 통신하는 IP 주소 목록을 체크
    IP 주소의 포맷이 망가져 숫자만 볼 수 있었다.

    다음과 같이 숫자로만 이루어진 String 으로부터 유효한 IP 주소를 출력하는 프로그램을 작성하시오

    단, 유효 범위는 0.0.0.0 ~ 255.255.255.255 이다.

    25525512235 의 유효한 IP는 255.255.12.235, 255.255.122.35 이다.

    - 표준 입력을 통해 입력 받는다.
    - 숫자로만 구성된 String 이 입력된다.
    - 단, 유효한 IP 주소가 없는 입력은 존재하지 않는다.

    - 유효한 IP 주소를 출력한다.
    - 각 IP 주소는 개행 문자로 구분한다.
    - 출력 순서는 OP 주소 기준 첫 번째 자리부터 오름 차순으로 출력한다.

 */

/*
    1. 입력받은 문자열에 "." 3개를 넣어 가능한 모든 경우의 수를 뽑아낸다.
    2. 가능한 경우의 수를 모두 유효성 검사를 진행한다.
    3. 오름 차순으로 정렬하여 출력한다.


 */