package SWExpertAcademy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class String_5949 {
/*    int map[],res[];
    long cnt=0;
    PriorityQueue<node> o1;
    PriorityQueue<node> o2;

    Solution(){
        Scanner sc= new Scanner (System.in);
        int t =sc.nextInt();
        o1 = new PriorityQueue();
        o2 = new PriorityQueue();
        for(int k =1; k<t+1;k++) {
            cnt=0;
            String a =  sc.next();
            String b = sc.next();
            map=new int[a.length()];
            res=new int[b.length()];
            for(int i =0;i <a.length();i++) {
                if(a.charAt(i)=='a') {
                    map[i] =0;
                    o1.add(new node(i));
                }
                else {
                    map[i]=1;
                    o2.add(new node(i));
                }
                if(b.charAt(i)=='a')
                    res[i] =0;
                else
                    res[i]=1;
            }
            for(int i =0;i <b.length();i++) {
                if(b.charAt(i)=='a')
                    res[i] =0;
                else
                    res[i]=1;
            }
            for(int i = 0 ; i < a.length();i++) {
                if(map[i]==res[i]) {
                    if(map[i]==0)
                        o1.poll();
                    else
                        o2.poll();
                    continue;
                }
                int index1  = o1.poll().index;
                int index2  = o2.poll().index;
                cnt+=Math.abs(index1-index2);
                int tmp = map[index1];
                map[index1] = map[index2];
                map[index2] = tmp;
                if(i==index1)
                    o1.add(new node(index2));
                else
                    o2.add(new node (index1));
            }
            System.out.println("#"+k+" "+cnt);
        }
    }

    class node implements Comparable<node>{
        int index;
        node(int index){
            this.index= index;
        }
        @Override
        public int compareTo(node arg0) {
            // TODO Auto-generated method stub
            if(this.index <arg0.index)
                return -1;
            return 1;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Solution();
    }*/
}

/*
* 문자열 변경하기
* 문자 'a'와 'b'로만 이루어진 문자열 S가 있다.
* 이 문자열의 인접한 두 문자를 바꾸는 연산을 계속할 수 있다.
* 이 때, S를 T로 변경할 수 있는가? 가능하다면 필요한 연산 횟수의 최솟 값은 몇 번인가?*/