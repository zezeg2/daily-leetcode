package programmers.etc.롤케이크자르기;

import java.util.*;

public class Solution {
    public static int solution(int[] topping) {
        int numOfToppings = (int) Arrays.stream(topping).distinct().count();
        int count = 0;
        int startIdx=0;
        Map<Integer, Integer> f_map = new HashMap<>();
        Map<Integer, Integer> s_map = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            int t = topping[i];
            if (f_map.size() < ((numOfToppings % 2) == 0 ? numOfToppings / 2 : numOfToppings / 2 + 1)) {
                if (!f_map.containsKey(t)) f_map.put(t, 1);
                else f_map.put(t, f_map.get(t) + 1);
                startIdx++;
                continue;
            }
            if (!s_map.containsKey(topping[i])) s_map.put(t, 1);
            else s_map.put(t, s_map.get(t) + 1);
        }
        for (int i = startIdx; i < topping.length; i++){
            if (f_map.size() == s_map.size()) count++;
            int t = topping[i];
            f_map.put(t, 1);
            s_map.put(t, s_map.get(t) - 1);
            if (s_map.get(t) == 0) s_map.remove(t);
        }
        return count;
    }

    public int solution2(int[] topping) {
        int answer = 0;
        int[] left = new int[10001], right = new int[10001];
        int ls = 0, rs = 0;
        for(var i : topping) right[i]++;
        for(var i : right) rs += i > 0 ? 1 : 0;
        for(var i : topping) {
            right[i]--;
            if (right[i] == 0) rs--;
            if (left[i] == 0) ls++;
            left[i]++;
            if (rs == ls) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,1,3,1,4,1,2}));
    }
}