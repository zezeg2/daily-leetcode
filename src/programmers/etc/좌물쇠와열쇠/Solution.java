package programmers.etc.좌물쇠와열쇠;

import java.util.Arrays;

public class Solution {
    static int keyLength;
    static int lockLength;

    public static boolean solution(int[][] key, int[][] lock) {
        keyLength = key.length;
        lockLength = lock.length;
        int[][] expanded = new int[keyLength * 2 + lockLength - 2][keyLength * 2 + lockLength - 2];
        for (int i = keyLength - 1; i < keyLength + lockLength - 1; i++)
            for (int j = keyLength - 1; j < keyLength + lockLength - 1; j++) {
                expanded[i][j] = lock[i - keyLength + 1][j - keyLength + 1];
            }

        for (int i = 0; i + keyLength <= expanded.length; i++)
            for (int j = 0; j + keyLength <= expanded.length; j++) {
                for (int ri = 0; ri < 4; ri++) {
                    int[][] temp = copyMatrix(expanded);
                    boolean check = check(temp, key, i, j);
                    if (check) return true;
                    key = rotate(key);
                }
            }
        return false;
    }

    public static int[][] copyMatrix(int[][] origin) {
        int[][] copied = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) copied[i] = Arrays.copyOf(origin[i], origin[i].length);
        return copied;
    }

    public static boolean check(int[][] expanded, int[][] key, int sr, int sc) {
        boolean flag = true;
        for (int i = 0; i < key.length; i++) for (int j = 0; j < key.length; j++) {
            expanded[sr + i][sc + j] += key[i][j];
        }
        for (int x = keyLength - 1; x < keyLength + lockLength - 1; x++) {
            for (int y = keyLength - 1; y < keyLength + lockLength - 1; y++) {
                if (expanded[x][y] != 1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }
        return flag;
    }

    public static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n - 1 - j][i];
            }
        }
        return rotate;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1}}, new int[][]{{0}}));
    }
}
