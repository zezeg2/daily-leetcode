package programmers.etc.메뉴리뉴얼;

import java.util.*;

public class Solution {
    static Map<Integer, Map<String, Integer>> map = new HashMap<>();
    static String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for (String o : orders) {
            String[] split = o.split("");
            Arrays.sort(split);
            for (int i = 0; i < course.length; i++) {
                boolean[] visited = new boolean[split.length];
                combination(split, visited, 0, course[i], "");
            }
        }
        for (Map<String, Integer> inner : map.values()) {
            Integer maxCount = inner.values().stream().max(Integer::compareTo).orElse(null);
            if (maxCount == null) continue;
            for (Map.Entry<String, Integer> entry : inner.entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (v == maxCount && v >= 2) answer.add(k);
            }
        }
        String[] result = answer.toArray(new String[answer.size()]);
        Arrays.sort(result);
        return result;
    }

    static void combination(String[] arr, boolean[] visited, int start, int r, String course) {
        if (r == 0) {
            if (!map.containsKey(course.length())) map.put(course.length(), new HashMap<>());
            Map<String, Integer> selected = map.get(course.length());
            selected.put(course, selected.getOrDefault(course, 0) + 1);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            course += arr[i];
            combination(arr, visited, i + 1, r - 1, course);
            visited[i] = false;
            course = course.substring(0, course.length() - 1);
        }
    }
//
//    static void combination(List<String> list, boolean[] visited, int r, String course) {
//        if (r == 0) {
//            map.put(course, map.getOrDefault(course, 0) + 1);
//            return;
//        }
//        for (int i = 0; i < list.size(); i++) {
//            visited[i] = true;
//            course += list.get(i);
//            combination(list, visited, r - 1, course);
//            visited[i] = false;
//        }
//    }

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders, course)));
    }
}
