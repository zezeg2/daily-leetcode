package programmers.etc.d220912.등산로정하기;

import java.util.*;

public class Solution {
    private static List<List<Node>> graph;

    private static class Node {
        int value, weight; // 노드, 가중치

        Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int weight = path[2];

            // 출입구일 경우 다른 곳으로만 갈 수 있는 단방향
            // 산봉우리일 경우 특정 한 곳에서 산봉우리로 가는 단방향
            if (isGate(start, gates) || isSummit(end, summits)) {
                graph.get(start).add(new Node(end, weight));
            } else if (isGate(end, gates) || isSummit(start, summits)) {
                graph.get(end).add(new Node(start, weight));
            } else {
                // 일반 등산로일 경우 양방향
                graph.get(start).add(new Node(end, weight));
                graph.get(end).add(new Node(start, weight));
            }
        }

        // 정상까지 갔을 때 최소이면 돌아올 때도 같은 경로를 선택하면 되므로
        // 정상까지 가는 경우만 고려
        return dijkstra(n, gates, summits);
    }

    private static int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<Node> queue = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 출입구 전부를 큐에 넣음
        for (int gate : gates) {
            queue.add(new Node(gate, 0));
            intensity[gate] = 0; // 시작 지점은 0
        }

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();

            if(currNode.weight < intensity[currNode.value]) continue;

            for (int i = 0; i < graph.get(currNode.value).size(); i++) {
                Node nextNode = graph.get(currNode.value).get(i);

                // 최소 갱신
                int dis = Math.max(intensity[currNode.value], nextNode.weight);
                if (intensity[nextNode.value] > dis) {
                    intensity[nextNode.value] = dis;
                    queue.add(new Node(nextNode.value, dis));
                }
            }
        }

        int minNumber = Integer.MAX_VALUE; // 산봉우리 번호
        int minWeight = Integer.MAX_VALUE; // 최솟값

        // 정렬하지 않으면 12, 14, 15, 16, 17, 25번 문제 실패
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minWeight) {
                minNumber = summit;
                minWeight = intensity[summit];
            }
        }

        return new int[]{minNumber, minWeight};
    }

    // num이 입구인지 확인
    private static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (num == gate) return true;
        }
        return false;
    }

    // num이 산봉우리인지 확인
    private static boolean isSummit(int num, int[] submits) {
        for (int submit : submits) {
            if (num == submit) return true;
        }
        return false;
    }
}