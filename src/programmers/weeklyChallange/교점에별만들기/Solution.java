package programmers.weeklyChallange.교점에별만들기;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution {
    static long minX = Long.MAX_VALUE;
    static long minY = Long.MAX_VALUE;
    static long maxX = Long.MIN_VALUE;
    static long maxY = Long.MIN_VALUE;
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static String[] solution(int[][] line) {

        Set<Point> set = new HashSet<>();
        for (int[] l1 : line)
            for (int[] l2 : line) {
                Point meet = findSol(l1, l2);
                if (meet != null) {
                    minX = Math.min(minX, meet.x);
                    minY = Math.min(minY, meet.y);
                    maxX = Math.max(maxX, meet.x);
                    maxY = Math.max(maxY, meet.y);
                    set.add(meet);
                }
            }
        boolean[][] matrix = new boolean[(int)(maxY - minY + 1)][(int)(maxX - minX + 1)];
        String[] answer = new String[matrix.length];
        set.forEach(p -> matrix[(int)(matrix.length - (p.y -  minY) - 1)][((int)(p.x - minX))] = true);
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                if (!matrix[i][j]) sb.append(".");
                else sb.append("*");
            }
            System.out.println(sb);
            answer[i] = sb.toString();
        }
        return answer;
    }

    static Point findSol(int[] arr1, int[] arr2) {
        long[] l1 = new long[3];
        long[] l2 = new long[3];
        for (int i = 0; i < 3; i++){
            l1[i] = arr1[i];
            l2[i] = arr2[i];
        }
        long x, y;
        long denominator = Long.valueOf(l1[0] * l2[1] - l1[1] * l2[0]);
        long numeratorX = Long.valueOf(l1[1] * l2[2] - l1[2] * l2[1]);
        long numeratorY = Long.valueOf(l1[2] * l2[0] - l1[0] * l2[2]);
        if (denominator == 0) return null;
        if (numeratorX % denominator != 0 || numeratorY % denominator != 0) return null;
        x = numeratorX / denominator;
        y = numeratorY / denominator;
        return new Point(x, y);
    }

    public static void main(String[] args) {
        solution(new int[][]{{-2, -1, 4}, {2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}});
    }
}
