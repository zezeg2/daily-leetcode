import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheetMethods {
    //조합
    static void combination(int[] arr, boolean[] visited, int start, int r, String s) {
        if (r == 0) {
            // TODO: 2022/09/27 조합식 이후로 할 동작
            System.out.println(s);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            s += String.valueOf(arr[i]);
            combination(arr, visited, i + 1, r - 1, s);
            visited[i] = false;
            s = s.substring(0, s.length() - 1);
        }
    }

    //순열
    static void permutation(int[] arr, boolean[] visited, int r, String s) {
        if (r == 0) {
            // TODO: 2022/09/27 순열식 이후로 할 동작
            System.out.println(s);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            s += String.valueOf(arr[i]);
            permutation(arr, visited, r - 1, s);
            visited[i] = false;
            s = s.substring(0, s.length() - 1);
        }
    }


    // 다익스트라 알고리즘
    static class Edge {
        int node, weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int nodeNum;
    static Map<Integer, List<Edge>> graph = new HashMap<>(); // TODO: 2022/09/27 문제 조건에 따라 그래프 만들기

    static int[] dijkstra(int start) {
        int[] totalCost = new int[nodeNum + 1];
        Arrays.fill(totalCost, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        totalCost[start] = 0;
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (poll.weight > totalCost[poll.node]) continue; // TODO: 2022/09/27 weight 비교하는 메커니즘 이해
            totalCost[poll.node] = poll.weight;
            for (Edge e : graph.getOrDefault(poll.node, new ArrayList<>())) {
                Edge next = new Edge(e.node, poll.weight + e.weight);
                pq.add(next);
            }
        }
        return totalCost;
    }

    // 시간 변환 관련 메서드
    static SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

    static int toSec(String time) throws ParseException {
        return (int) (f.parse(time).getTime() / 1000);
    }

    static String secondToTime(int second) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i >= 0; i--) {
            int t = second / (int) Math.pow(60, i);
            second %= (int) Math.pow(60, i);
            if (t < 10) sb.append(0).append(t);
            else sb.append(t);
            if (i != 0) sb.append(":");
        }
        return sb.toString();
    }




    public static void main(String[] args) {

    }

}
