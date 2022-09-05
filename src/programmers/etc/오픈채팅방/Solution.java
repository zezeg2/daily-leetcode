package programmers.etc.오픈채팅방;

import java.util.*;

public class Solution {
    static String[] solution(String[] record) {

        List<String> ans = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        String suffix;
        int cnt = 0;

        Arrays.stream(record).forEach(r -> {
            String[] split = r.split(" ");
            if (split[0].equals("Enter") || split[0].equals("Change")){
                map.put(split[1], split[2]);
            }
        });

        for (int i = 0; i < record.length; i++){
            String[] split = record[i].split(" ");
            if (split[0].equals("Enter")) {
                suffix = "님이 들어왔습니다.";
            } else if (split[0].equals("Leave")){
                suffix = "님이 나갔습니다.";
            } else continue;
            ans.add(map.get(record[i].split(" ")[1]) + suffix);

        }
        String answer[] = ans.toArray(new String[ans.size()]);


        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] solution = solution(record);
        Arrays.toString(solution);
    }
}
