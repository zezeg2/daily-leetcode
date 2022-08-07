package leetcode.d220807.q1220;

import java.util.Arrays;

public class Solution {
    static int countVowelPermutation(int n) {
        int a = 0, e = 1, i = 2, o = 3, u = 4;
        int MOD = (int) (1e9 + 7);

        long[] elements = new long[5];
        long[] prevElements = new long[5];
        Arrays.fill(prevElements, 1L);

        while (n-- > 1){
            elements[a] = (prevElements[e] + prevElements[i] + prevElements[u]) % MOD;
            elements[e] = (prevElements[a] + prevElements[i]) % MOD;
            elements[i] = (prevElements[e] + prevElements[o]) % MOD;
            elements[o] = (prevElements[i]) % MOD;
            elements[u] = (prevElements[i] + prevElements[o]) % MOD;

            prevElements = elements;
            elements = new long[5];
        }

        return (int) ((prevElements[a] + prevElements[e] + prevElements[i] + prevElements[o] + prevElements[u]) % MOD);

    }

    public static void main(String[] args) {
        System.out.println(countVowelPermutation(5));
    }
}
