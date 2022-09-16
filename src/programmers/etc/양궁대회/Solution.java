package programmers.etc.양궁대회;

import java.util.*;

public class Solution {

    static Map<Integer, int[]> map = new TreeMap<>((o1, o2) -> o2 - o1);

    static int[] solution(int n, int[] info) {
        int[] ryanInfo = new int[info.length];
        recur(info, ryanInfo, 0, n, map);
        if (map.size() == 0) return new int[]{-1};
        int maxKey = map.keySet().stream().findFirst().get();
        int[] result = Arrays.copyOf(map.get(maxKey), info.length);
        map.clear();
        return result;
    }

    static void recur(int[] apeachInfo, int[] ryanInfo, int depth, int arrow, Map<Integer, int[]> map) {
        if (arrow == 0) {
            int diff = getDiff(apeachInfo, ryanInfo);
            if (diff <= 0) return;
            if (map.containsKey(diff)){
                int[] prev = map.get(diff);
                for (int i = ryanInfo.length - 1; i >= 0; i--){
                    if (prev[i] < ryanInfo[i]) {
                        map.put(diff, Arrays.copyOf(ryanInfo, ryanInfo.length));
                        break;
                    }
                    else if(prev[i] > ryanInfo[i]) break;
                    else continue;
                }
                return;
            }
            map.put(diff, Arrays.copyOf(ryanInfo, ryanInfo.length));
            return;
        }
        if (depth == apeachInfo.length) {
            ryanInfo[depth - 1] = arrow;
            recur(apeachInfo,ryanInfo, depth, 0, map);
            ryanInfo[depth - 1] = 0;
            return;
        }
        if (apeachInfo[depth] < arrow) {
            ryanInfo[depth] = apeachInfo[depth] + 1;
            recur(apeachInfo, ryanInfo, depth + 1, arrow - (apeachInfo[depth] + 1), map);
            ryanInfo[depth] = 0;
            recur(apeachInfo,ryanInfo, depth + 1, arrow, map);
        }
        recur(apeachInfo,ryanInfo, depth + 1, arrow, map);
    }

    static int getDiff(int[] infoA, int[] infoB) {
        int plyrA = 0;
        int plyrB = 0;
        for (int i = 0; i < infoA.length; i++) {
            if (infoB[i] - infoA[i] > 0) plyrB += 10 - i;
            else {
                if (infoA[i] == 0 && infoB[i] == 0) continue;
                plyrA += 10 - i;
            }
        }
        return plyrB - plyrA;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] info = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        int [] result = solution(n, info);
        Arrays.toString(result);
    }
}
