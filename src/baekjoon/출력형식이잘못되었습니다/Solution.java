package baekjoon.출력형식이잘못되었습니다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Solution {
    static int k;
    static String[] transform(String s) {
        return s.replaceAll("\\{", "(")
                .replaceAll("\\[", "(")
                .replaceAll(";", ",")
                .replaceAll("\\s+", " ")
                .replaceAll("\\s\\(\\:\\;\\,\\s", " ").toLowerCase(Locale.ROOT).split(" ");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for (int i = 1; i <= k; i++) {
            String[] s1 = transform(br.readLine());
            String[] s2 = transform(br.readLine());
        }
    }
}
