package programmers.greedy.단속카메라;

import java.util.*;

class Solution {
    static int solution(int[][] routes) {
        int ans = 0;
        List<int[]> list = new ArrayList<>();
        for (int[] r : routes) list.add(r);
        list.sort(Comparator.comparingInt(r -> r[1]));
        while (!list.isEmpty()){
            int position = list.get(0)[1];
            for (int i = 0; i < list.size(); i++){
                if (list.get(i)[0] <= position){
                    list.remove(i);
                    i--;
                }
            }
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) {

        int[][] routes = { {2,2},{0,1},{-10,9} };
        System.out.println(solution(routes));
    }
}
