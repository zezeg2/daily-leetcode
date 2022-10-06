package programmers.etc.무지의먹방라이브;

import java.util.Arrays;

public class Solution {
    static int solution(int[] food_times, long k) {
        int answer = 0;
        int maxRotate = Arrays.stream(food_times).max().getAsInt();
        int nof = food_times.length;
        long[] timePerRotate = new long[maxRotate + 1];
        timePerRotate[0] = nof;
        for (int i = 0; i < nof; i++) {
            timePerRotate[food_times[i]]--;
            timePerRotate[timePerRotate.length - 1]++;
        }
        for (int i = 1; i < timePerRotate.length; i++) {
            timePerRotate[i] += timePerRotate[i - 1];
        }
        int n = 0;
        long curr = 0;
        long prev = 0;

        for (int i = 0; i < timePerRotate.length; i++) {
            if (i == 0) {
                curr = timePerRotate[1];
            } else {
                timePerRotate[i] += timePerRotate[i - 1];
                curr = timePerRotate[i];
                prev = timePerRotate[i - 1];
            }
            if (k <= curr) {
                n = k == curr ? i + 1 : i;
                break;
            }
        }
        long currTime = k == curr ? curr : prev;
        for (int i = 0; i < nof; i++) {
            if (food_times[i] - (n + 1) < 0) continue;
            currTime++;
            if (currTime > k) {
                answer = i + 1;
                break;
            }
        }
        if (currTime <= k) return -1;
        return answer;
    }

    public static void main(String[] args) {
//        int[] food_times = {1, 1, 1, 1, 1};
        int[] food_times = {5,4,2,7,4,3,1};
        long k = 14;
        System.out.println(solution(food_times, k));
    }
}
