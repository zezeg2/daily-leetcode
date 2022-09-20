package leetcode.d220920.q718;

public class Solution {
    static int findLength(int[] nums1, int[] nums2) {
        int moveNums1 = findMaxLength(nums1, nums2);
        int moveNums2 = findMaxLength(nums2, nums1);
        return Math.max(moveNums1, moveNums2);
    }

    static int findMaxLength(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        int ln1 = nums1.length;
        int ln2 = nums2.length;
        for (int i = 0; i < ln1 && max <= ln1; i++) {
            for (int j = 0; j < ln1 - i && j < ln2; j++) {
                if (nums1[j + i] == nums2[j]) {
                    count++;
                    max = Math.max(max, count);
                }
                else count = 0;
            }
            max = Math.max(max, count);
            count = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println(findLength(nums1,nums2));
    }
}
