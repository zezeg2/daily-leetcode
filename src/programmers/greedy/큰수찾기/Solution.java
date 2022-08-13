package programmers.greedy.큰수찾기;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static String solution(String number, int k) {
        String answer = "";
        List<Integer> chkList = new ArrayList<>();
        for (int i = 0; i < 10; i++) chkList.add(i);
        chkList.sort(Collections.reverseOrder());
        List<Integer> split = Arrays.stream(Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray()).boxed().collect(Collectors.toList());
        while(k != 0){
            List<Integer> temp = new ArrayList<>(chkList);
            for (int i : temp){
                int loc = split.indexOf(i);
                if (loc == -1) {
                    chkList.remove(chkList.indexOf(i));
                    continue;
                }
                if (loc <= k) {
                    answer += String.valueOf(split.get(loc));
                    split.remove(loc);
                    split = split.subList(loc, split.size());
                    k -= loc;
                    break;
                }
            }
        }

        for (int i = 0; i < split.size(); i++) answer += String.valueOf(split.get(i));
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("4177252841", 4));
    }
}
