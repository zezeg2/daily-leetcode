package programmers.etc.순위검색;

import java.util.*;
import java.util.stream.Collectors;

public class Sol {
    static Map<Integer, Map<String, Set<Integer>>> filters = new HashMap<>();
    static Map<Integer, Integer> scoreMap = new HashMap<>();
    static Map<String, Set<Integer>> allCase = new HashMap<>();

    static int[] solution(String[] info, String[] query) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            filters.put(i, new HashMap<>());
            filters.get(i).put("-", new HashSet<>());
        }
        for (int i = 0; i < info.length; i++) {
            String[] sp = info[i].split(" ");
            scoreMap.put(i, Integer.parseInt(sp[4]));
            for (int j = 0; j < 4; j++) {
                if (!filters.get(j).containsKey(sp[j])) filters.get(j).put(sp[j], new HashSet<>());
                filters.get(j).get(sp[j]).add(i);
            }
        }
        makeCase(0, "");
        List<Integer> scoreInCase;
        for (String q : query) {
            q = q.replaceAll(" and ", " ");
            String[] sp = q.split(" ");
            String c = "";
            for (int i = 0; i < filters.size(); i++) c += sp[i] + " ";
            scoreInCase = allCase.get(c).stream().mapToInt(o -> scoreMap.get(o)).sorted().boxed().collect(Collectors.toList());
            int count = binarySearch(scoreInCase, Integer.parseInt(sp[4]));
            result.add(count);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static void makeCase(int depth, String c) {
        if (depth == filters.size()) {
            Queue<Set<Integer>> queue = new LinkedList<>();
            String[] sp = c.split(" ");
            for (int i = 0; i < sp.length; i++) if (!sp[i].equals("-")) queue.add(new HashSet<>(filters.get(i).get(sp[i])));
            Set<Integer> p;
            if (queue.isEmpty()) p = scoreMap.keySet();
            else p = queue.poll();
            while (!queue.isEmpty()) p.retainAll(queue.poll());
            allCase.put(c, new HashSet<>(p));
            return;
        }
        for (String f : filters.get(depth).keySet()) {
            c += f + " ";
            makeCase(depth + 1, c);
            c = c.substring(0, c.length() - (f.length() + 1));
        }
    }

    private static int binarySearch(List<Integer> scores, int score) {
        int start = 0, end = scores.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) < score) start = mid + 1;
            else end = mid - 1;
        }
        return scores.size() - start;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(solution(info, query)));
    }
}
