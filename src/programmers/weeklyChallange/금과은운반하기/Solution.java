package programmers.weeklyChallange.금과은운반하기;

public class Solution {
    public final boolean check(long mid, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long total = 0L;
        long gMaxCarry = 0L;
        long sMacCarry = 0L;

        for(int i = 0; i < w.length; i++) {
            //전체 시간을 한 번 왕복하는 시간으로 나누면 운반 가능 횟수
            long cnt = mid / (long)(t[i] * 2);
            //편도로 한 번 더 갈 수 있는 경우 1추가
            if (mid % (long)(t[i] * 2) >= (long)t[i]) cnt++;
            //금+은 한 번에 이동 가능한 최대 운반량은 트럭 한 번 운반 무게 * 운반 횟수 vs 금 + 은 최대량
            long maxCarry = Math.min(cnt * (long)w[i], (long)(g[i] + s[i]));

            //금 운반 최대량은 각 도시의 (금 최대량 vs 최대 운반량) 누적
            //은 운반 최대량은 각 도시의 (은 최대량 vs 최대 운반량) 누적
            gMaxCarry += Math.min((long)g[i], maxCarry);
            sMacCarry += Math.min((long)s[i], maxCarry);
            //금+은 최대 운반량
            total += maxCarry;
        }

        if (total >= (long)(a + b) && gMaxCarry >= (long)a && sMacCarry >= (long)b) return true;
        return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 1L;
        long end = 400000000000000L;

        while(start < end) {
            long mid = (start + end) / 2L;
            if (this.check(mid, a, b, g, s, w, t)) end = mid;
            else start = mid + 1L;
        }
        return end;
    }
}

