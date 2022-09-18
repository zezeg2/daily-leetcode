package programmers.etc.행렬테두리회전하기;

import java.util.Arrays;

public class Solution {
    static int[][] matrix;

    static int[] solution(int rows, int columns, int[][] queries) {
        matrix = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) for (int j = 0; j < columns; j++) matrix[i][j] = ++count;


        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            answer[i] = rotate(queries[i][0],queries[i][1],queries[i][2],queries[i][3]);
        }
        return answer;
    }

    static int rotate(int r1, int c1, int r2, int c2) {
        r1 -= 1; r2 -= 1; c1 -= 1; c2 -= 1;
        int tmp = matrix[r1][c1];
        int min = tmp;

        for (int i = r1 + 1; i <= r2; i++){
            min = Math.min(min, matrix[i][c1]);
            matrix[i-1][c1] = matrix[i][c1];
        }

        for (int i = c1 + 1; i <= c2; i++){
            min = Math.min(min, matrix[r2][i]);
            matrix[r2][i-1] = matrix[r2][i];
        }

        for (int i = r2 - 1; i >= r1; i--){
            min = Math.min(min, matrix[i][c2]);
            matrix[i+1][c2] = matrix[i][c2];
        }

        for (int i = c2 - 1; i >= c1; i--){
            min = Math.min(min, matrix[r1][i]);
            matrix[r1][i+1] = matrix[r1][i];
        }

        matrix[r1][c1+1] = tmp;
        return min;
    }

    public static void main(String[] args) {
        int r = 6;
        int c =  6;
        int[][] q = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

        Arrays.toString(solution(r,c,q));
    }
}
