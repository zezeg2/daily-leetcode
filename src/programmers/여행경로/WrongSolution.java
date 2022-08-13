package programmers.여행경로;

import java.util.*;

public class WrongSolution {
    static String[] solution(String[][] tickets) {

        List<String> ansList = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (String[] t : tickets){
            if (!map.keySet().contains(t[0])){
                map.put(t[0], new PriorityQueue<>());
            }
            map.get(t[0]).add(t[1]);
        }

        String curr;
        String next;

        stack.add("ICN");
        while(!stack.isEmpty()){
            curr = stack.pop();
            ansList.add(curr);
            if (!map.keySet().contains(curr)) next = null;
            else next = map.get(curr).poll();

            if (next == null) break;
            stack.add(next);
        }

        System.out.println(ansList);
        return ansList.toArray(new String[ansList.size()]);
    }
}
