package programmers.etc.수식최대화;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static String[] ops = new String[]{"*", "+", "-"};
    static boolean[] visited = new boolean[ops.length];
    static long[] numbers;
    static String[] operators;
    static long max = 0;
    static long solution(String expression) {
        numbers = Arrays.stream(expression.replaceAll("[*+-]", " ").split(" ")).mapToLong(Long::parseLong).toArray();
        operators = expression.replaceAll("[0-9]", "").split("");
        permutation(ops, visited, 3, "");
        return max;
    }
    public static void permutation(String[] arr, boolean[] visited, int r, String s) {
        if (r == 0) {
            List<Long> nl = new ArrayList<>(Arrays.stream(numbers).boxed().collect(Collectors.toList()));
            List<String> ol = new ArrayList<>(Arrays.asList(operators));
            for (int i = 0; i < s.length(); i++) {
                String op = Character.toString(s.charAt(i));
                while (ol.contains(op)) {
                    int idx = ol.indexOf(op);
                    nl.set(idx + 1, operation(op, nl.get(idx), nl.get(idx + 1)));
                    nl.remove(idx);
                    ol.remove(idx);
                }
            }
            max = Math.max(max, Math.abs(nl.get(0)));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            s += arr[i];
            permutation(arr, visited, r - 1, s);
            visited[i] = false;
            s = s.substring(0, s.length() - 1);
        }
    }

    static long operation(String op, long n1, long n2) {
        switch (op) {
            case "*":
                return n1 * n2;
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
        }
        return -1;
    }
}
