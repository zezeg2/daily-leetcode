package programmers.etc.숫자문자열과영단어;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        map.put("zero","0");
        map.put("one","1");
        map.put("two","2");
        map.put("three","3");
        map.put("four","4");
        map.put("five","5");
        map.put("six","6");
        map.put("seven","7");
        map.put("eight","8");
        map.put("nine","9");
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            sb.append(c);
            if (!Character.isDigit(c)){
                if (map.containsKey(sb.toString())){
                    answer+= map.get(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                answer += c;
                sb = new StringBuilder();
            }

        }
        return Integer.parseInt(answer);
    }

    public static void main(String[] args) {

        System.out.println(solution("one4seveneight"));
    }
}
