package programmers.weeklyChallange.모두0으로만들기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static class Node {
        int num;
        long weight;
        List<Node> adjacent = new ArrayList<>();

        public Node(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }
        public long childSum() {
            visited[this.num] = true;
            long sum = weight;
            if (adjacent.size() == 1) {
                if (visited[adjacent.get(0).num]) {
                    answer += Math.abs(weight);
                    return this.weight;
                }
            }
            for (Node node : adjacent) {
                if (!visited[node.num]) sum += node.childSum();
            }
            answer += Math.abs(sum);
            return sum;
        }
    }

    static boolean[] visited;
    static long answer = 0;

    static long solution(int[] a, int[][] edges) {
        long[] longArray = Arrays.stream(a).mapToLong(i -> i).toArray();
        visited = new boolean[longArray.length];

        if (Arrays.stream(longArray).sum() != 0) return -1;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < longArray.length; i++) {
            Node node = new Node(i, longArray[i]);
            list.add(node);
        }
        List<Node> nodeList = new ArrayList<>(list);

        for (int[] e : edges) {
            nodeList.get(e[0]).adjacent.add(nodeList.get(e[1]));
            nodeList.get(e[1]).adjacent.add(nodeList.get(e[0]));
        }
        nodeList.get(0).childSum();
        return answer;
    }

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
        System.out.println(solution(a, edges));
    }
}
