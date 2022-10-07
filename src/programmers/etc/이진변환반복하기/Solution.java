package programmers.etc.이진변환반복하기;

import java.util.Arrays;

public class Solution {
    static int[] solution(String s) {
        String temp = s;
        int zeroCount = 0;
        int count = 0;
        while (!s.equals("1")){
            s = s.replaceAll("0", "");
            zeroCount += temp.length() - s.length();
            s = Integer.toBinaryString(s.length());
            temp = s;
            count++;
        }

        return new int[]{count, zeroCount};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("110010101001")));
    }


}
