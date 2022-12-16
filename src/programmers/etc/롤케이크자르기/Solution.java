package programmers.etc.롤케이크자르기;

import java.util.*;

public class Solution {
    public static int solution(int[] topping) {
//        int numOfToppings = (int) Arrays.stream(topping).distinct().count();
//        int count = 0;
//        int startIdx=0;
//        Map<Integer, Integer> f_map = new HashMap<>();
//        Map<Integer, Integer> s_map = new HashSet<>();
//        for (int i = 0; i < topping.length; i++) {
//            if (f_map.size() < ((numOfToppings / 2) == 0 ? numOfToppings / 2 : numOfToppings / 2 + 1)) {
//                f_map.add(topping[i]);
//                startIdx++;
//            }
//            s_map.add(topping[i]);
//        }
//        for (int i = startIdx; i < topping.length; i++){
//            int t = topping[i];
//            f_map.add(t);
//            if (s_map.contains(t))
//        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2}));
    }
}