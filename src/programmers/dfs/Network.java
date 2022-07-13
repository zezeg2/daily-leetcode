package programmers.dfs;

import java.util.Set;
import java.util.TreeSet;

public class Network {

    //    static int answer = 0;
//
//    static int solution(int n, int[][] computers) {
//        Set<Integer> set = new TreeSet<>();
//        dfs(computers, set,0);
//        return answer;
//    }
//
//    static void dfs(int[][] computers, Set set, Integer index) {
//        set.add(index);
//
//
//        int currentSize = set.size();
//
//        for (int i = 0; i < computers.length; i++) {
//            if (computers[index][i] == 1 && !set.contains(i)) {
//                set.add(i);
//                if (currentSize == set.size()) continue;
//                dfs(computers, set, i);
//            }
//        }
//
//        if (currentSize != set.size()) {
//            return;
//        }
//        answer++;
//
//        if (set.size() == computers.length) {
//            return;
//        }
//
//        index = 0;
//        while (set.contains(index)) {
//            index++;
//        }
//        dfs(computers, set, index);
//    }
    static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                answer++;
                dfs(i, visited, computers);
            }
        }
        return answer;
    }

    static void dfs(int node, boolean[] visited, int[][] computers){
        visited[node] = true;
        for (int i = 0; i < computers.length; i++){
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, visited, computers);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}, {0, 0, 1, 1, 0}, {0, 0, 1, 0, 1}}));
    }
}
