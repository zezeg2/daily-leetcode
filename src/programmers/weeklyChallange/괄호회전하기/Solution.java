package programmers.weeklyChallange.괄호회전하기;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    static int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            String rs = rotateString(s, i);
            if (isCorrect(rs)) answer++;
        }
        return answer;
    }

    static String rotateString(String s, int i) {
        String prefix = s.substring(i);
        String suffix = s.substring(0, i);
        return prefix + suffix;
    }

    static boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char poll = stack.pop();
                if (c == ')') {
                    if (poll != '(') return false;
                } else if (c == '}') {
                    if (poll != '{') return false;
                } else if (c == ']') {
                    if (poll != '[') return false;
                }
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
    }
}
