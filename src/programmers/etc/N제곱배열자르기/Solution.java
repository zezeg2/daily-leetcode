package programmers.etc.N제곱배열자르기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Solution {
    public static int[] solution(int n, long left, long right) {
        List<Integer> ans = new ArrayList<>();
        int lr, lc, rr, rc;
        lr = (int) (left / (long) n);
        lc = (int) (left % (long) n);
        rr = (int) (right / (long) n);
        rc = (int) (right % (long) n);
        for (int i = lr; i <= rr; i++) {
            int s = i == lr ? lc : 0;
            int e = i == rr ? rc : n - 1;
            for (int j = s; j <= e; j++) ans.add(Math.max(i + 1, j + 1));
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] solution2(int n, long left, long right) {
        return LongStream.rangeClosed(left, right).mapToInt(value -> (int) (Math.max(value / n, value % n) + 1)).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution2(3, 2, 5)));
    }
}
