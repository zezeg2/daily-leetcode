package leetcode.d220712.q473;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution3 {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        int sum = 0;
        for (int m : matchsticks) sum += m;
        if (sum % 4 != 0) return false;
        List<Integer> list = Arrays.stream(matchsticks).boxed().collect(Collectors.toList());
        Collections.sort(list, Collections.reverseOrder());
        // optimization: check whether there are some matchsticks are longer than target. if so, return false.
        // if (list.get(0) > sum / 4) return false;
        int[] sidesLen = new int[4];
        return backtracking(list, sidesLen, 0, sum / 4);
    }

    private boolean backtracking(List<Integer> matchsticks, int[] sidesLen, int index, int target) {
        // if (index == matchsticks.length) {
        if (index == matchsticks.size()) {
            return sidesLen[0] == target && sidesLen[1] == target && sidesLen[2] == target && sidesLen[3] == target;
        }
        int m = matchsticks.get(index);
        for (int i = 0; i < 4; i++) {
            // pruning
            // if (sidesLen[i] + matchsticks[index] > target) continue;
            if (sidesLen[i] + m > target) continue;
            int j = i - 1;
            while (j >= 0) {
                if (sidesLen[j] == sidesLen[i]) break;
                j--;
            }
            // we have encountered the same sideLen before
            if (j >= 0) continue;
            sidesLen[i] += m;
            if (backtracking(matchsticks, sidesLen, index + 1, target)) return true;
            sidesLen[i] -= m;
        }
        return false;
    }
}
