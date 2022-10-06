package programmers.보석쇼핑;

import java.util.*;

public class Solution {
    static int[] solution(String[] gems) {
        int[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        Set<String> gemSet = new HashSet<>();
        gemSet.addAll(Arrays.asList(gems));
        int min = 0;
        int max = Integer.MIN_VALUE;
        int gap = Integer.MAX_VALUE;
        String s = null;

        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], i);
            max = i;

            if (gemSet.size() == map.size()) {
                if (s == null || s.equals(gems[i])) {
                    Map.Entry<String, Integer> e = map.entrySet().stream().min(Comparator.comparingInt(o -> o.getValue())).get();
                    s = e.getKey();
                    min = e.getValue();
                }

                int prevGap = gap;
                gap = Math.min(gap, max - min);
                if (gap != prevGap) answer = new int[]{min + 1, max + 1};
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] gems = {"DIA", "EM", "EM", "RUB", "DIA"};
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));
    }
}
