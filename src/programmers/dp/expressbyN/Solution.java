package programmers.dp.expressbyN;

import java.util.*;

public class Solution {
    static int solution(int N, int number) {
        Map<Integer, HashSet<Integer>> candidate = new HashMap<>();
        Set<Integer> container = new HashSet<>();
        container.add(N);
        candidate.put(1, new HashSet<>(container));
        container.clear();
        int count = 1;
        while (!candidate.get(count).contains(number)) {
            if (count > 8) return -1;
            count++;

            for (int i = 1 , j = count - 1; i < count; ++i, j--){
                int finalJ = j;
                candidate.get(i).forEach(o -> {
                    candidate.get(finalJ).forEach(s -> {
                        container.add(o + s);
                        container.add(Math.abs(o - s));
                        container.add(o * s);
                        if (!o.equals(0) && !s.equals(0)) {
                            container.add(o / s);
                        }
                    });
                });
            }

            if (count < 6) {
                container.add(Integer.parseInt(String.valueOf(N).repeat(count)));
            }

            container.removeIf(o -> (o < 0 || o > 32000));
            candidate.put(count, new HashSet<>(container));
            container.clear();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 53));
//        System.out.println(55/5);
    }

}
