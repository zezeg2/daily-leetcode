import java.util.*;

public class Test3 {
    static int solution(int n, int m, int[][] moles){
        int max = -1;
        List<int[]> select = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            select.add(new int[m]);
            for (int j = 0; j < m; j++){
                if (j + i > n) select.get(i-1)[j] = j + i - n;
                else select.get(i-1)[j] = j + i;
            }
        }

        for (int[] e : select){
            int sum = 0;
            Map<Integer, Map<Integer, Integer>> timeMap = new HashMap<>();
            for (int[] mole: moles){
                if (!timeMap.containsKey(mole[0])) timeMap.put(mole[0], new HashMap<>());
                timeMap.get(mole[0]).put(mole[1],mole[2]);
            }
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : timeMap.entrySet()) {
                Map<Integer, Integer> v = entry.getValue();
                int score = 0;
                for (int i = 0; i < m; i++) {
                    if (v.containsKey(e[i])) score = Math.max(score, v.get(e[i]));
                }
                sum+= score;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}


