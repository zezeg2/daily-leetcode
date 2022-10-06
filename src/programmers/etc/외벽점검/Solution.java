package programmers.etc.외벽점검;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int N;
    static List<Integer> weakList;
    public static int solution(int n, int[] weak, int[] dist) {
        N = n;
        weakList = Arrays.stream(weak).boxed().collect(Collectors.toList());
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int e : dist) pq.add(e);

        while (!pq.isEmpty()){
            if (weakList.isEmpty()) break;
            int maxWeakNum = -1;
            int maxIndex = -1;
            Integer poll = pq.poll();
            for (int i = 0; i < weakList.size(); i++) {
                int prevMax = maxWeakNum;
                maxWeakNum = Math.max(maxWeakNum, getWeakNum(poll, weakList.get(i)));
                if (prevMax!=maxWeakNum) maxIndex = i;
            }
            inspectWeak(poll, weakList.get(maxIndex));
        }
        if (!weakList.isEmpty()) return -1;



        return dist.length - pq.size();
    }

    private static int getWeakNum(int d, int loc) {
        int maxRange = d + loc;
        int count = maxRange > N ? (int) weakList.stream().filter(e -> (e >= loc && e < N) || N <= maxRange - N).count() : (int) weakList.stream().filter(e -> e >= loc && e <= maxRange).count();
        return count;
    }

    private static void inspectWeak(int d, int loc) {
        int maxRange = d + loc;
        if (maxRange > N) {
            for (int i = 0; i < weakList.size(); i++){
                if ((weakList.get(i) >= loc && weakList.get(i) < N) || N <= maxRange - N) {
                    weakList.remove(i);
                    i--;
                }
            }
        } else {
            for (int i = 0; i < weakList.size(); i++){
                if ((weakList.get(i) >= loc && weakList.get(i) < maxRange)) {
                    weakList.remove(i);
                    i--;
                }
            }
        }
    }

    private static int ret;
    public static int solution2(int n, int[] weak, int[] dist) {
        ret = n+1;
        int[] w = new int[weak.length*2];
        int idx = 0;
        // 역방향 계산하기 짜증나서 만들어 버림.
        while(idx<w.length) {
            if(idx<weak.length) w[idx] = weak[idx];
            else w[idx] = n+weak[idx-weak.length];
            idx++;
        }
        for(int i=0 ; i<weak.length ; i++) {
            dfs(w, dist, new boolean[dist.length], n, i, weak.length+i, 0);
        }
        return ret>n?-1:ret;
    }

    public static void dfs(int[] weak, int[] dist, boolean[] visit, int n, int start, int end, int sum) {
        if(start>=end) {
            ret = Math.min(ret, sum);
            return;
        }
        for(int i=0; i<dist.length ; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            int idx = start+1;
            while(idx<end && (weak[idx]-weak[start])<=dist[i] ) {
                idx++;
            }
            dfs(weak, dist, visit, n, idx, end, sum+1);
            visit[i] = false;
        }
        return;
    }



    public static void main(String[] args) {
        System.out.println(solution2(12, new int[]{1, 5, 6, 10},new int[]{1, 2, 3, 4}));
    }

}
