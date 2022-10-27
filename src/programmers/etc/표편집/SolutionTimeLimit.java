package programmers.etc.표편집;

import java.util.*;

// arraylist add, remove 시간복잡도로 인한 시간초과로 예상됨
// linkedlist 사용해도 get 시간복잡도로 인한  시간초과 발생
public class SolutionTimeLimit {
    private static int pointer;
    private static int N;
    private static List<Integer> table = new LinkedList<>();
    private static Stack<Integer> latest = new Stack<>();
    private static Set<Integer> deletedSet = new HashSet<>();

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) table.add(i);
        N = n;
        pointer = k;
        for (int i = 0; i < cmd.length; i++){
            String[] c = cmd[i].split(" ");
            switch (c[0]){
                case "U": up(Integer.parseInt(c[1])); break;
                case "D": down(Integer.parseInt(c[1])); break;
                case "C": delete(); break;
                case "Z": undo(); break;
            }
        }
        for (int i = 0; i < N; i++){
            if (deletedSet.contains(i)) {
                sb.append("X");
                deletedSet.remove(i);
                continue;
            }
            sb.append("O");
        }
        return sb.toString();

    }

    public static void up(int x) {
        pointer = pointer - x >= 0 ? pointer - x : 0;
    }

    public static void down(int x) {
        pointer = pointer + x <= table.size() - 1 ? pointer + x : table.size() - 1;
    }

    public static void delete() {
        latest.add(table.get(pointer));
        deletedSet.add(table.get(pointer));
        table.remove(pointer);
        if (pointer >= table.size()) pointer -= 1;
    }

    public static void undo() {
        Integer restored = latest.pop();
        deletedSet.remove(restored);
        int left = 0;
        int right = table.size() - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (table.get(mid) > restored) right = mid - 1;
            else left = mid + 1;
        }
        int idx = Math.max(left,right);
        if (idx <= pointer) pointer++;
        table.add(idx, restored);
    }

    public static void main(String[] args) {
        System.out.println(solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
