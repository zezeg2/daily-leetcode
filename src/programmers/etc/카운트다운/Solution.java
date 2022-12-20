package programmers.etc.카운트다운;

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    static int[][] table;
    public static int[] solution(int target) {
        table = new int[target + 1][2];
        IntStream.range(1, target + 1).forEach(i -> {
            if (i <= 20 || i == 50) table[i] = new int[]{1, 1};
            else if (i <= 40 && i % 2 == 0) table[i] = new int[]{1, 0};
            else if (i <= 60 && i % 3 == 0) table[i] = new int[]{1, 0};
            else if (i > 40 && i <= 50) table[i] = new int[]{2, 1};
            else if (i <= 70) table[i] = new int[]{2, 2};
            else {
                table[i] = table[i - 60][0] == table[i - 50][0] ? new int[]{table[i - 50][0] + 1, Math.max(table[i - 60][1], table[i - 50][1] + 1)}
                        : table[i - 60][0] < table[i - 50][0] ? new int[]{table[i - 60][0] + 1, table[i - 60][1]}
                        : new int[]{table[i - 50][0] + 1, table[i - 50][1] + 1};
            }
        });
        return table[target];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(101)));

    }
}