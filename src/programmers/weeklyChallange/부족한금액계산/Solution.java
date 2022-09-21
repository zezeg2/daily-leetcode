package programmers.weeklyChallange.부족한금액계산;

public class Solution {
    public long solution(int price, int money, int count) {
        long longMoney = Long.valueOf(money);
        longMoney -= (long) price * (count * (count + 1)) / 2;
        return longMoney >= 0 ? 0 : -longMoney;
    }
}
