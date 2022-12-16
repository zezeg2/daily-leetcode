package codingtest.starters_bootcamp;

import java.util.*;

public class Test2 {
    static int solution(int[] cards) {
        if (cards.length < 5) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int card : cards) {
            if (!map.containsKey(card)) {
                map.put(card, 0);
            }
            map.put(card, map.get(card) + 1);
        }
        int numOfJoker = map.get(0);
        if (numOfJoker >= 3) return 1;
        else {
            List<Integer> list = new ArrayList<>(map.values());
            int count = 0;
            list.sort(Collections.reverseOrder());
            for (Integer integer : list) {
                if (count >= 2) return 1;
                if (integer >= 3) count++;
                else {
                    if ((count == 1 && numOfJoker >= 1) || (count == 1 && integer == 2)) return 1;
                }
            }
        }
        return 0;
    }
}


