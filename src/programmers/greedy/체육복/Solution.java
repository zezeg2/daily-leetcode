package programmers.greedy.체육복;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        Arrays.sort(lost);
        List<Integer> amend = Arrays.stream(lost).boxed().collect(Collectors.toList());
        Collections.sort(amend);

        List<Integer> collect = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        Collections.sort(collect);

        for (int i = 0; i < lost.length; i++){
            if (collect.contains(lost[i])) {
                amend.remove(amend.indexOf(lost[i]));
                collect.remove(collect.indexOf(lost[i]));
                answer++;
            }
        }

        for (int i = 0; i < amend.size(); i++) {
            if (collect.contains(amend.get(i) - 1)) collect.remove(collect.indexOf(amend.get(i) - 1));
            else if (collect.contains(amend.get(i) + 1)) collect.remove(collect.indexOf(amend.get(i) + 1));
            else {
                continue;
            }
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] lost = { 1,2 };
        int[] reserve = { 2,3 };
        System.out.println(solution(n,lost,reserve));
    }
}
