package CodingTest;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        ArrayList<Integer> resultList = new ArrayList<Integer>();

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                final String str = br.readLine();

                Calculator cal = new Calculator();
                Queue<String> que = cal.transformPostfix(str);
                Double resultVal = cal.calculatePostfix(que);
                int result = Integer.parseInt(String.valueOf(Math.round(resultVal)));

                resultList.add(result);
            }
        }

        for (int i=0; i<resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
    }

    private Map<String,Integer> hashMap = new HashMap<String, Integer>();

    public Calculator() {
        hashMap.put("+", 0);
        hashMap.put("-", 0);
        hashMap.put("*", 1);
        hashMap.put("/", 1);
        hashMap.put("(", -1);
    }


    /**
     * @param String
     * @return Queue
     */
    public Queue<String> transformPostfix(String param) {
        if(param == null || param.trim().equals(""))
            return null;

        Stack<String> stack  = new Stack<String>();

        StringBuilder postfixStr = new StringBuilder();

        Queue<String> postfixQue = new LinkedList<String>();

        Pattern p = Pattern.compile("[0-9]+|\\(|\\)|\\+|\\-|\\*|\\/");
        Matcher m = p.matcher(param);

        while(m.find()){
            String word = m.group();
            if(word.equals("(")) {
                stack.push(word);
            }
            else if(hashMap.containsKey(word)) {
                while(true) {
                    if(stack.isEmpty() || hashMap.get(stack.peek()) < hashMap.get(word)) {
                        stack.push(word);
                        break;
                    } else {
                        String popStr = stack.pop();
                        postfixStr.append(popStr);
                        postfixQue.add(popStr);
                    }
                }
            }
            else if(word.equals(")")) {
                while(true) {
                    String popStr = stack.pop();
                    if(popStr.equals("(")) {
                        break;
                    } else {
                        postfixStr.append(popStr);
                        postfixQue.add(popStr);
                    }
                }
            }
            else {
                postfixStr.append(word);
                postfixQue.add(word);
            }
        }

        while(stack.isEmpty() == false) {
            String popStr = stack.pop();
            postfixStr.append(popStr);
            postfixQue.add(popStr);
        }

        return postfixQue;
    }


    /**
     * @param Queue
     * @return String
     */
    public Double calculatePostfix(Queue<String> param) {
        Stack<Double> stack = new Stack<Double>();

        Double firstVal;
        Double secondVal;

        while(param.isEmpty() == false) {
            String word = param.remove();

            if(hashMap.containsKey(word)) {
                secondVal= stack.pop();
                firstVal = stack.pop();

                switch(word.charAt(0)) {
                    case '+' : stack.push(firstVal + secondVal);
                        break;
                    case '-' : stack.push(firstVal - secondVal);
                        break;
                    case '*' : stack.push(firstVal * secondVal);
                        break;
                    case '/' : stack.push(firstVal / secondVal);
                        break;
                    default :
                        break;
                }

            } else {
                stack.push(Double.valueOf(word));
            }
        }
        return stack.pop();

    }

}
