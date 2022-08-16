package leetcode.d220816.q387;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int firstUniqChar(String s) {
        int answer = -1;
        Map<Character, Integer> n = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            n.put(s.charAt(i), n.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (n.get(s.charAt(i)) == 1) {
                answer = i;
                break;
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
