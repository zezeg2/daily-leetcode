package programmers.etc.행렬과연산;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MySolution {
    static int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = rc;
        for (String s : operations) {
            if (s.equals("ShiftRow")) answer = shiftRow(answer);
            if (s.equals("Rotate")) answer = rotate(answer);
        }
        return answer;
    }

    static int[][] rotate(int[][] rc) {
        int[][] expanded = new int[rc.length + 2][rc[0].length + 2];
        int[][] result = new int[rc.length][rc[0].length];
        int[] rf = getRowFirst(rc);
        int[] rl = getRowLast(rc);
        int[] cf = getColFirst(rc);
        int[] cl = getColLast(rc);

        for (int i = 2; i < Math.max(expanded[0].length, expanded.length); i++) {
            if (i < expanded[0].length) {
                expanded[1][i] = rf[i - 2];
                expanded[expanded.length - 2][i - 2] = rl[i - 2];
            }
            if (i < expanded.length) {
                expanded[i - 2][1] = cf[i - 2];
                expanded[i][expanded[0].length - 2] = cl[i - 2];
            }
            if (i < expanded.length - 2) {
                for (int j = 2; j < expanded[0].length - 2; j++) {
                    expanded[i][j] = rc[i - 1][j - 1];
                }
            }
        }

        for (int i = 1; i < expanded.length - 1; i++) {
            result[i - 1] = Arrays.copyOfRange(expanded[i], 1, expanded[0].length - 1);
        }
        return result;

    }

    static int[][] shiftRow(int[][] rc) {
        int[][] temp = rc.clone();
        for (int i = 0; i < rc.length - 1; i++) {
            rc[i + 1] = temp[i];
        }
        rc[0] = temp[temp.length - 1];

        return rc;
    }

    static int[] getRowFirst(int[][] rc) {
        return rc[0];
    }

    static int[] getRowLast(int[][] rc) {
        return rc[rc.length - 1];
    }

    static int[] getColFirst(int[][] rc) {
        int[] result = new int[rc.length];
        for (int i = 0; i < rc.length; i++) {
            result[i] = rc[i][0];
        }
        return result;
    }

    static int[] getColLast(int[][] rc) {
        int[] result = new int[rc.length];
        for (int i = 0; i < rc.length; i++) {
            result[i] = rc[i][rc[0].length - 1];
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] x = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] s = {"Rotate", "ShiftRow"};
//        int[] y = new int[5];

//        int[][] rotate = rotate(x);
//        int[][] shift = shiftRow(x);
        int[][] result = solution(x, s);
        System.out.println(result);
    }
}

class BestSolution {

    static Deque<Integer> cf = new ArrayDeque<>();
    static Deque<Integer> cl = new ArrayDeque<>();
    static Deque<Deque<Integer>> rows = new ArrayDeque<>();
    static int r, c;

    static int[][] solution(int[][] rc, String[] operations) {
        r = rc.length;
        c = rc[0].length;

        for (int i = 0; i < r; i++){
            cf.add(rc[i][0]);
            cl.add(rc[i][c-1]);
            Deque<Integer> row = new ArrayDeque<>();
            for (int j = 1; j < c-1; j++){
                row.add(rc[i][j]);
            }
            rows.add(row);
        }

        for (String s : operations) {
            if (s.equals("ShiftRow")) shift();
            if (s.equals("Rotate")) rotate();
        }
        return getAnswer();
    }

    public static void rotate() {
        if (c == 2) {
            cl.addFirst(cf.pollFirst());
            cf.addLast(cf.pollLast());
        } else {
            rows.getFirst().addFirst(cf.pollFirst());
            cl.addFirst(rows.getFirst().pollLast());
            rows.getLast().addLast(cl.pollLast());
            cf.addLast(rows.getLast().pollFirst());
        }
    }

    public static void shift() {
        cf.addFirst(cf.pollLast());
        cl.addFirst(cl.pollLast());
        rows.addFirst(rows.pollLast());
    }

    public static int[][] getAnswer() {
        int [][]ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            if (i < r) {
                ans[i][0] = cf.pollFirst();
                ans[i][c - 1] = cl.pollFirst();
            }
            Deque<Integer> temp = rows.pollFirst();
            for (int j = 1; j < c - 1; j++){
                ans[i][j] = temp.pollFirst();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] operations = {"Rotate", "ShiftRow"};

        int[][] result = solution(rc, operations);
        System.out.println(result);
    }
}





