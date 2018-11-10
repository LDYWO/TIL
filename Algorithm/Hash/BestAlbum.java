package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {

        int[] answer = {};

        return answer;
    }
}


/*
    장르 별로 가장 많이 재생된 음악을 두 개씩 모아 베스트 앨범을 출시하려 한다.

    노래는 고유 번호로 구분

    - 노래를 수록하는 기준
        1. 속한 노래가 많이 재생된 장르를 먼저 수록
        2. 장르 내에서 많이 재생된 노래를 먼저 수록
        3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록

    - 노래의 장르를 나타내는 문자열 배열 genres
    - 노래 별 재생 횟수를 나타내는 정수 배열 plays

    두 배열이 주어질 때 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
    
 */
