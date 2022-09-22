package programmers.etc.괄호변환;

public class Solution {
    static String solution(String p) {
        if (p == "") return p;
        int obNum = 0;
        int cbNum = 0;
        String u = "";
        String v = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') obNum++;
            if (c == ')') cbNum++;
            sb.append(c);
            if (obNum == cbNum) {
                u = sb.toString();
                v = p.substring(i + 1);
                break;
            }
        }
        if (isCorrect(u)) return u + solution(v);
        String patched = "(" + solution(v) + ")";
        String tail = "";
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') tail += ")";
            if (u.charAt(i) == ')') tail += "(";
        }
        tail = tail.substring(1, tail.length() - 1);
        patched += tail;
        return patched;
    }

    static boolean isCorrect(String s) {
        int obNum = 0;
        int cbNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') obNum++;
            if (c == ')') cbNum++;
            if (cbNum > obNum) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }
}
