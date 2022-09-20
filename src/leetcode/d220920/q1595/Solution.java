package leetcode.d220920.q1595;

import java.util.*;

public class Solution {
    static int size1, size2;
    static int minCost = Integer.MAX_VALUE;

    static int connectTwoGroups(List<List<Integer>> cost) {
        Set<Integer> check1 = new HashSet<>();
        Set<Integer> check2 = new HashSet<>();
        size1 = cost.size();
        size2 = cost.get(0).size();
        Queue<int[]> normalizedQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < size1; i++)
            for (int j = 0; j < size2; j++) normalizedQueue.add(new int[]{cost.get(i).get(j), i, j});
        int result =  backTracking(check1, check2, normalizedQueue, 0, 0);
        minCost = Integer.MAX_VALUE;
        return result;
    }

    static int backTracking(Set<Integer> check1, Set<Integer> check2, Queue<int[]> pq, int min, int depth) {
        if (depth > size1 + size2) return Integer.MAX_VALUE;
        while (!(check1.size() == size1 && check2.size() == size2) && !pq.isEmpty()) {
            int[] polled = pq.poll();
            int value = polled[0];
            int e1 = polled[1];
            int e2 = polled[2];

            if ((check1.contains(e1) && check2.contains(e2)) || min > minCost) continue;
            else {
                HashSet<Integer> tempCheck1 = new HashSet<>(check1);
                HashSet<Integer> tempCheck2 = new HashSet<>(check2);
                if (!check1.contains(e1)) tempCheck1.add(e1);
                if (!check2.contains(e2)) tempCheck2.add(e2);
                int pickCase = backTracking(tempCheck1, tempCheck2, new PriorityQueue<>(pq), min + value, depth + 1);
                int passCase = backTracking(new HashSet<>(check1), new HashSet<>(check2), new PriorityQueue<>(pq), min, depth + 1);
                min = Math.min(passCase, pickCase);
                return min;
            }
        }
        if (check1.size() == size1 && check2.size() == size2) {
            minCost = min;
            return min;
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {

        List<List<Integer>> cost = new ArrayList<>();
        List<Integer> e1 = new ArrayList<>();
        e1.add(55);
        e1.add(96);
        e1.add(15);
        e1.add(52);
        e1.add(56);
        List<Integer> e2 = new ArrayList<>();
        e2.add(15);
        e2.add(13);
        e2.add(46);
        e2.add(100);
        e2.add(27);
        List<Integer> e3 = new ArrayList<>();
        e3.add(30);
        e3.add(34);
        e3.add(23);
        e3.add(97);
        e3.add(4);
        List<Integer> e4 = new ArrayList<>();
        e4.add(70);
        e4.add(12);
        e4.add(89);
        e4.add(15);
        e4.add(60);
        List<Integer> e5 = new ArrayList<>();
        e5.add(58);
        e5.add(91);
        e5.add(85);
        e5.add(60);
        e5.add(86);
        cost.add(e1);
        cost.add(e2);
        cost.add(e3);
        cost.add(e4);
        cost.add(e5);


        System.out.println(connectTwoGroups(cost));
    }
}
