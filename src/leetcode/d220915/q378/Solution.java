package leetcode.d220915.q378;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution{
    static int[][]  move =  {{1,0}, {0,1}};

    static class Node implements Comparable {
        int row;
        int col;
        int val;
        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(Object o) {
            return this.val - ((Node) o).val;
        }
    }
    static int kthSmallest(int[][] matrix, int k) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Node poll = null;
        int count = 1;
        queue.add(new Node(0,0, matrix[0][0]));
        while (count <= k && !queue.isEmpty()){
            poll = queue.poll();
            if (count == k) break;
            for (int[] d : move){
                int dr = d[0];
                int dc = d[1];
                int nr = poll.row + dr;
                int nc = poll.col + dc;
                if (isPossible(nr, nc, visited)){
                    visited[nr][nc] = true;
                    queue.add(new Node(nr, nc, matrix[nr][nc]));
                }
            }
            count++;
        }
        return poll.val;
    }

    static boolean isPossible (int row, int col, boolean[][] visited){
        if (row < visited.length && col < visited[0].length && row >= 0 && col >= 0){
            if (!visited[row][col]) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;
        System.out.println(kthSmallest(matrix, k));
    }
}
