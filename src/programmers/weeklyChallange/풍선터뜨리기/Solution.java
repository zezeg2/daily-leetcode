package programmers.weeklyChallange.풍선터뜨리기;

import java.util.*;

public class Solution {
    static int solution(int[] a) {
        int length = a.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> fromStartMinMap = new HashMap<>();
        Map<Integer, Integer> fromEndMinMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int ei = length - 1 - i;
            fromStartMinMap.put(i, Math.min(a[i], fromStartMinMap.getOrDefault(i - 1, Integer.MAX_VALUE)));
            fromEndMinMap.put(ei, Math.min(a[ei], fromEndMinMap.getOrDefault(ei + 1, Integer.MAX_VALUE)));
        }
        for (int i = 0; i < length; i++) {
            int value = a[i];
            if (value > fromStartMinMap.getOrDefault(i-1, Integer.MAX_VALUE) && value > fromEndMinMap.getOrDefault(i+1, Integer.MAX_VALUE)) continue;
            result.add(value);
        }
        return result.size();
    }

    public static void main(String[] args) {
        int[] a = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
        System.out.println(solution(a));
    }
}
