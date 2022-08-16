package programmers.greedy.구명보트;

import java.util.Arrays;

public class Solution {

    static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int min = 0;

        for (int max = people.length - 1; min <= max; max--) {
            if (people[min] + people[max] <= limit) min++;
            answer++;
        }

        return answer;
    }
    public static void main(String[] args) {
        int[] ints = {70, 50, 80};
//        int[] ints = {70, 50, 80};
        int limit = 100;
        System.out.println(solution(ints, limit));
    }

}
