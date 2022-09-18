package programmers.etc.다단계칫솔판매;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

    static class Member {
        Member upper = null;
        int benefits = 0;

        public Member(Member upper) {
            this.upper = upper;
        }
    }

    static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        Map<String, Member> memberMap = new LinkedHashMap<>();
        memberMap.put("center", new Member(null));
        for (int i = 0; i < enroll.length; i++) {
            if (referral[i].equals("-")) {
                memberMap.put(enroll[i], new Member(memberMap.get("center")));
            }
            memberMap.put(enroll[i], new Member(memberMap.get(referral[i])));
        }

        for (int i = 0; i < seller.length; i++) {
            Member member = memberMap.get(seller[i]);
            int totalSale = amount[i] * 100;
            while (member != null) {
                int reserve = (int) Math.ceil(totalSale * 0.9);
                member.benefits += reserve;
                totalSale = (totalSale) - reserve;
                member = member.upper;
            }
        }
        memberMap.remove("center");
        answer = memberMap.values().stream().mapToInt(v -> v.benefits).toArray();
        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = new String[]{"young", "john", "tod", "emily", "mary"};
        int[] amount = new int[]{12, 4, 2, 5, 10};
        Arrays.toString(solution(enroll, referral, seller, amount));
    }
}
