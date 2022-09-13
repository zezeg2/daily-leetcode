package programmers.etc.주차요금계산;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    static SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);

    static int[] solution(int[] fees, String[] records) throws ParseException {
        int[] answer = {};
        Map<String, Integer> accumFees = new TreeMap<>();
        Map<String, String> inCars = new HashMap<>();

        for (String r : records) {
            String[] split = r.split(" ");
            String ioTime = split[0];
            String carNum = split[1];
            String io = split[2];

            if (@accumFees.containsKey(carNum))accumFees.put()

            if (io.equals("IN")) inCars.put(carNum, ioTime);
            else if (io.equals("OUT")) {
                int bill = fees[1];
                int td = timeDiff(ioTime, inCars.get(carNum));
                if (td > fees[0]) bill += ((td - fees[0]) / fees[2]) * fees[3];
                accumFees.put(carNum, accumFees.getOrDefault(carNum, 0) + bill);
                inCars.remove(carNum);
            }
        }

        for (Map.Entry<String, String> entry : inCars.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            accumFees.put(k, accumFees.getOrDefault(k, 0) + fees[1] + ((timeDiff("23:59", v) - fees[0]) / fees[2]) * fees[3]);
        }
        int[] result = accumFees.values().stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    static int timeDiff(String t1, String t2) throws ParseException {
        return (int)((f.parse(t1).getTime() - f.parse(t2).getTime()) / (60 * 1000));
    }

    public static void main(String[] args) throws ParseException {
        int[] fees = new int[] {180, 5000, 10, 600};
        String[] records = new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(fees, records)));
    }
}
