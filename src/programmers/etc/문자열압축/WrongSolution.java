package programmers.etc.문자열압축;

import java.util.HashMap;
import java.util.Map;

public class WrongSolution {
    static int solution(String s) {
        int l = s.length();
        int answer = l;
        String piece;
        Map<String, Integer> overlapMap = new HashMap<>();
        Map<Integer, Integer> lastMap = new HashMap<>();

        for (int i = 2; i < l / 2; i++) {
            for (int j = 0; j < l; j++) {
                if (j + i > l) continue;
                piece = s.substring(j, j + i);
                overlapMap.put(piece, overlapMap.getOrDefault(piece, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : overlapMap.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if (v > 1) {
                lastMap.put(k.length(), lastMap.getOrDefault(k.length(), 0) + v);
            }
        }

        for (Map.Entry<Integer, Integer> entry : lastMap.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            answer = Math.min(answer, l - k * (v - 1) + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("abcabcdede"));
    }
}


//
//        for (Map.Entry<String, Integer> entry : overlapMap.entrySet()) {
//            String k = entry.getKey();
//            Integer v = entry.getValue();
//            if (k.length() == 1) overlapMap.remove(k);
//        }


//        Set<Integer> candidate = overlapMap.keySet().stream().mapToInt(String::length).boxed().collect(Collectors.toSet());
//        for (int c : candidate) {
//            int num = 0;
//            overlapMap.forEach((k, v) -> {
//                if (k.length() == c) {
//
//                }
//            });
//        }
