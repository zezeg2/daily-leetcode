package d220705.q128;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {
    static int longestConsecutive(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);

        int max = 0;
        for(int num : set) {
            if(!set.contains(num-1)) {
                int cur = num;
                while(set.contains(cur+1)) {
                    cur++;
                }
                max = Math.max(max, cur-num+1);
            }
        }
        return max;
    }
}
