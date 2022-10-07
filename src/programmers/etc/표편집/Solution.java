package programmers.etc.표편집;

import java.util.*;

public class Solution {
    private static int pointer;
    private static int rl;
    private static Set<Integer> set = new TreeSet<>();
    private static Stack<Integer> latest = new Stack<>();
    private static Set<Integer> deletedSet = new HashSet<>();


    public static String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        rl = n;
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
        for (int i = 0; i < rl; i++){
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
        int count = 0;
        int temp = pointer;
        while (count < x) {
            if (temp == 0) break;
            if (!deletedSet.contains(temp - 1)){
                count++;
            }
            temp--;
        }
        pointer = temp;
    }

    public static void down(int x) {
        int count = 0;
        int temp = pointer;
        while (count < x) {
            if (temp == rl-1) break;
            if (!deletedSet.contains(temp + 1)){
                count++;
            }
            temp++;
        }
        pointer = temp;
    }

    public static void delete() {
        latest.add(pointer);
        deletedSet.add(pointer);
        int temp = pointer;
        down(1);
        if (temp == pointer) {
            up(1);
        }
    }

    public static void undo() {
        deletedSet.remove(latest.pop());
    }

    public static void main(String[] args) {
        System.out.println(solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));

    }
}
