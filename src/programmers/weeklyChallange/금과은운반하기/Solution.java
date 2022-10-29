package programmers.weeklyChallange.금과은운반하기;

public class Solution {
    public final boolean check(long mid, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long tot = 0L;
        long gCarry = 0L;
        long sCarry = 0L;

        for(int i = 0; i < w.length; i++) {
            long cnt = mid / (long)(t[i] * 2);
            if (mid % (long)(t[i] * 2) >= (long)t[i]) cnt++;

            long maxCarry = Math.min(cnt * (long)w[i], (long)(g[i] + s[i]));
            gCarry += Math.min((long)g[i], maxCarry);
            sCarry += Math.min((long)s[i], maxCarry);
            tot += maxCarry;
        }

        if (tot >= (long)(a + b) && gCarry >= (long)a && sCarry >= (long)b) return true;
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

