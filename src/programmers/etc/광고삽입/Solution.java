package programmers.etc.광고삽입;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    static SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
    static int criteria;

    static String solution(String play_time, String adv_time, String[] logs) throws ParseException {
        String answer = "00:00:00";
        criteria = toSec("00:00:00");
        int playRunningTime = toSec(play_time) - criteria;
        int advRunningTime = toSec(adv_time) - criteria;
        long max = Long.MIN_VALUE;

        long[] timeLineBySec = new long[playRunningTime + 1];

        for (String log : logs) {
            String[] split = log.split("-");
            int s = toSec(split[0]) - criteria;
            int e = toSec(split[1]) - criteria;
            timeLineBySec[s] += 1;
            timeLineBySec[e] -= 1;
        }
        for (int i = 1; i < timeLineBySec.length; i++) {
            timeLineBySec[i] += timeLineBySec[i - 1];
        }

        long[] sum = timeLineBySec.clone();
        for (int i = 1; i < sum.length; i++) {
            sum[i] += sum[i - 1];
        }

        for (int i = 0; i < playRunningTime - advRunningTime; i++) {
            if (i == 0) {
                max = Math.max(max, sum[i + advRunningTime - 1]);
                continue;
            }
            if (max < Math.max(max, sum[i + advRunningTime - 1] - sum[i - 1])) {
                max = Math.max(max, sum[i + advRunningTime - 1] - sum[i - 1]);
                answer = secondToTime((i));
            }
        }

        return answer;
    }

    static int toSec(String time) throws ParseException {
        return (int) (f.parse(time).getTime() / 1000);
    }

    static String secondToTime(int second) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i >= 0; i--) {
            int t = second / (int) Math.pow(60, i);
            second %= (int) Math.pow(60, i);
            if (t < 10) sb.append(0).append(t);
            else sb.append(t);
            if (i != 0) sb.append(":");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws ParseException {
        String play_time = "00:02:00";
        String adv_time = "00:00:15";
        String[] logs = {"00:00:00-00:00:15","00:00:10-00:00:25", "00:00:11-00:00:16", "00:00:56-00:01:30", "00:01:25-00:02:00"};

//        String play_time = "00:00:10";
//        String adv_time = "00:00:05";
//        String[] logs = {"00:00:08-00:00:10"};

//        String play_time = "02:03:55";
//        String adv_time = "00:14:15";
//        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(solution(play_time, adv_time, logs));

    }
}
