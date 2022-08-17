package programmers.greedy.섬연결하기;

import java.util.*;

public class Solution {
    static int solution(int n, int[][] costs) {
        int answer = 0;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < costs.length; i++){
            list.add(costs[i]);
        }

        list.sort(Comparator.comparingInt(e -> e[2]));

        int[] parents = new int[n];
        for (int i = 0; i < n; i++){
            parents[i] = i;
        }

        while (!list.isEmpty()){
            int[] node = list.remove(0);
            if (!findParent(parents, node[0], node[1])){
                int a = getParent(parents, node[0]);
                int b = getParent(parents, node[1]);
                if (a < b) parents[b] = a;
                else parents[a] = b;
                answer += node[2];
            } else {
                continue;
            }
        }
        return answer;
    }


    static int getParent(int[] parent, int a){
        if (parent[a] == a) return a;
        return getParent(parent, parent[a]);
    }
    static boolean findParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a == b) return true;
        else return false;
    }

//    static int unionParent(int[] parent, int a, int b){
//        a = getParent(parent, a);
//        b = getParent(parent, b);
//        if (a < b) parent[b] = a;
//        else parent[a] = b;
//    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 1, 5}, {1, 2, 3}, {2, 3, 3}, {3, 1, 2}, {3, 0, 4}, {2, 4, 6}, {4, 0, 7}};
//        int[][] a = new int[][]{{0,1,1},{3,4,1},{1,2,2},{2,3,4}};
//        int[][] a = new int[][]{{0,1,5},{0,3,2},{0,4,3},{1,4,1},{3,4,10},{1,2,2},{2,4,3},{4,5,4}};


        System.out.println(solution(5, a));
    }

}
