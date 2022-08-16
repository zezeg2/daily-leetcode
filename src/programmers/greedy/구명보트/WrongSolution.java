package programmers.greedy.구명보트;

import java.util.LinkedHashMap;
import java.util.Map;

public class WrongSolution {
    static int solution(int[] people, int limit) {
        int num = 1;

        Map<Integer, Integer> boats = new LinkedHashMap<>();
        boats.put(num, 0);

        for (int i = 0; i < people.length; i++) {
            boolean inserted = false;

            for (Map.Entry<Integer, Integer> entry : boats.entrySet()) {
                Integer k = entry.getKey();
                Integer v = entry.getValue();
                if (v + people[i] <= limit) {
                    boats.put(k, v + people[i]);
                    if (boats.get(k) == limit) boats.remove(k);
                    inserted = true;
                    break;
                }
            }
            if (!inserted) {
                num++;
                boats.put(num, people[i]);
            }
        }

        return num;
    }

    public static void main(String[] args) {
        int[] ints = {70, 50, 80};
//        int[] ints = {70, 50, 80};
        int limit = 100;
        System.out.println(solution(ints, limit));
    }
}
