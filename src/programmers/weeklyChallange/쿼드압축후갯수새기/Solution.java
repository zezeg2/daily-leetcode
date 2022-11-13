package programmers.weeklyChallange.쿼드압축후갯수새기;

public class Solution {
    static int[] answer = new int[2];

    static int[] solution(int[][] arr) {

        int arrLength = arr.length;
        divisionAndConquest(arr, 0, arrLength, 0, arrLength);
        return answer;
    }

    static boolean isSqueezable(int[][] arr, int fr, int tr, int fc, int tc) {
        int initialValue = arr[fr][fc];
        for (int i = fr; i < tr; i++)
            for (int j = fc; j < tc; j++) {
                if (arr[i][j] != initialValue) return false;
            }
        return true;
    }

    static void divisionAndConquest(int[][] arr, int fr, int tr, int fc, int tc) {
        if (tr - fr == 1 || isSqueezable(arr, fr, tr, fc, tc)) {
            answer[arr[fr][fc]]++;
        } else {
            int mr = (fr + tr) / 2;
            int mc = (fc + tc) / 2;
            divisionAndConquest(arr, fr, mr, fc, mc);
            divisionAndConquest(arr, mr, tr, fc, mc);
            divisionAndConquest(arr, fr, mr, mc, tc);
            divisionAndConquest(arr, mr, tr, mc, tc);
        }
    }
}
