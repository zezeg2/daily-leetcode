import java.util.*;

public class Test4 {

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static List<Integer> answer;

    static int[] solution(int n, int[] win_cnt) {
        answer = new ArrayList<>();
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));
        for (int i = 0; i < win_cnt.length; i++) {
            pq.add(new int[]{i + 1, win_cnt[i]});
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

}


