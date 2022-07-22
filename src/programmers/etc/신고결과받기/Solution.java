package programmers.etc.신고결과받기;

import java.util.*;

public class Solution {
    static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        List<Integer> answerList = new ArrayList<>();

        HashSet<String> hashSet = new HashSet<>(Arrays.asList(report));
        String[] refined = hashSet.toArray(new String[0]);
        Set<String> reportedSet = new HashSet<>();

        Map<String, HashSet<String>> reportListMap = new LinkedHashMap<>();
        Map<String, Integer> reportedNumMap = new LinkedHashMap<>();

        for (String id : id_list) {
            reportListMap.put(id, new HashSet<>());
            reportedNumMap.put(id, 0);
        }

        String[] s;

        for (int i = 0; i < refined.length; i++) {
            s = refined[i].split(" ");
            reportListMap.get(s[0]).add(s[1]);
            reportedNumMap.put(s[1], reportedNumMap.get(s[1]) + 1);
        }

        reportedNumMap.forEach((key, val) -> {
            if (val >= k) {
                reportedSet.add(key);
            }
        });

        reportListMap.forEach((key, val) -> {
            Set<String> criteria = new HashSet<>(reportedSet);
            criteria.retainAll(val);
            answerList.add(criteria.size());
        });

        answer = answerList.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = new String[]{"muzi", "frodo", "apeach", "neo"};

        String[] reported = new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};

        int k = 2;

        System.out.println(solution(id_list,reported,k).toString());
    }
}
