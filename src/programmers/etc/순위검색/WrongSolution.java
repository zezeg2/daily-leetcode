package programmers.etc.순위검색;

import java.util.*;

public class WrongSolution {
    static int[] solution(String[] info, String[] query) {
        List<Integer> result = new ArrayList<>();
        Map<String, Set<Integer>> filter1 = new HashMap<>();
        Map<String, Set<Integer>> filter2 = new HashMap<>();
        Map<String, Set<Integer>> filter3 = new HashMap<>();
        Map<String, Set<Integer>> filter4 = new HashMap<>();
        Map<Integer, Integer> scoreMap = new HashMap<>();

        for (int i = 0; i < info.length; i++) {
            String[] sp = info[i].split(" ");
            scoreMap.put(i, Integer.parseInt(sp[4]));
            if (!filter1.containsKey(sp[0])) filter1.put(sp[0], new HashSet<>());
            if (!filter2.containsKey(sp[1])) filter2.put(sp[1], new HashSet<>());
            if (!filter3.containsKey(sp[2])) filter3.put(sp[2], new HashSet<>());
            if (!filter4.containsKey(sp[3])) filter4.put(sp[3], new HashSet<>());

            filter1.get(sp[0]).add(i);
            filter2.get(sp[1]).add(i);
            filter3.get(sp[2]).add(i);
            filter4.get(sp[3]).add(i);
        }
        Queue<Set<Integer>> queue = new LinkedList<>();

        for (String q : query) {
            q = q.replaceAll(" and ", " ");
            String[] sp = q.split(" ");
            if (!sp[0].equals("-")) queue.add(new HashSet<>(filter1.get(sp[0])));
            if (!sp[1].equals("-")) queue.add(new HashSet<>(filter2.get(sp[1])));
            if (!sp[2].equals("-")) queue.add(new HashSet<>(filter3.get(sp[2])));
            if (!sp[3].equals("-")) queue.add(new HashSet<>(filter4.get(sp[3])));
            Set<Integer> p;
            if (queue.isEmpty()) {
                 p = scoreMap.keySet();
            } else p = queue.poll();
            while (!queue.isEmpty()) p.retainAll(queue.poll());
            int count = (int) p.stream().filter(i -> scoreMap.get(i) >= Integer.parseInt(sp[4])).count();
            result.add(count);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(solution(info, query)));
    }
}
