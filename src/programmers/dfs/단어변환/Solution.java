package programmers.dfs.단어변환;

import java.util.*;

public class Solution {
    static int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) return 0;

        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        wordList.add(begin);

        Map<String, Integer> resultMap = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.add(target);

        while (!q.isEmpty()) {
            Queue<String> temp = new LinkedList<>(q);
            while(!temp.isEmpty()){
                wordList.remove(temp.poll());
            }
//            wordList.removeAll(q.stream().toList());
            String curr = q.poll();
            if (curr == begin) {
                break;
            }
            for (String word : wordList) {
                int cnt = 0;
                for (int i = 0; i < curr.length(); i++) {
                    if (curr.charAt(i) == word.charAt(i)) cnt++;
                }

                if (cnt == curr.length() - 1) {
                    q.add(word);
                    resultMap.put(word, resultMap.getOrDefault(curr, 0) + 1);
                }
            }

        }
        if (!resultMap.keySet().contains(begin)) {
            return 0;
        }

        return resultMap.get(begin);
    }

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));
    }
}
