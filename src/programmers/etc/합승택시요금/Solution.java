package programmers.etc.합승택시요금;

import java.util.*;

public class Solution {
    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int nodeNum;
    static Map<Integer, List<Edge>> map = new HashMap<>();

    static int solution(int n, int s, int a, int b, int[][] fares) {
        int minFare = Integer.MAX_VALUE;
        nodeNum = n;
        for (int[] f : fares) {
            if (!map.containsKey(f[0])) map.put(f[0], new ArrayList<>());
            if (!map.containsKey(f[1])) map.put(f[1], new ArrayList<>());
            map.get(f[0]).add(new Edge(f[1], f[2]));
            map.get(f[1]).add(new Edge(f[0], f[2]));
        }
        int[] interValues = dijkstra(s);
        for (int i = 1; i < interValues.length; i++) {
            int[] from = dijkstra(i);
            minFare = Math.min(minFare, interValues[i] + from[a] + from[b]);
        }
        return minFare;
    }

    static int[] dijkstra(int start) {
        int[] fares = new int[nodeNum + 1];
        Arrays.fill(fares, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        fares[start] = 0;
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (poll.weight > fares[poll.node]) continue;
            fares[poll.node] = poll.weight;
            for (Edge e : map.getOrDefault(poll.node, new ArrayList<>())) {
                Edge next = new Edge(e.node, poll.weight + e.weight);
                pq.add(next);
            }
        }
        return fares;
    }

    public static void main(String[] args) {
        int n, s, a, b;
        n = 6;
        s = 4;
        a = 5;
        b = 6;
        String val = "{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}";
        val = val.replaceAll("]", "}");
        val = val.replaceAll("\\[", "{");
        System.out.println(val);

        System.out.println(solution(n,s,a,b, new int[][] {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}));

    }
}
