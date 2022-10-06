package programmers.etc.키패드누르기;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        Map<Integer, Location> map = new HashMap<>();
        map.put(0, new Location(3,1));
        for (int i = 1; i < 10; i++) map.put(i, new Location((i - 1) / 3, (i-1) % 3));
        Location left = new Location(3,0);
        Location right = new Location(3,2);

        for (int n : numbers) {
            String s = "";
            Location sel = map.get(n);
            if (n == 1 || n == 4 || n == 7) s = "L";
            else if (n == 3 || n == 6 || n == 9) s = "R";
            else {
                int ld = getDistance(left, sel);
                int rd = getDistance(right, sel);
                if (ld < rd) s = "L";
                else if (ld > rd) s = "R";
                else {
                    if (hand == "left") s = "L";
                    if (hand == "right") s = "R";
                }
            }
            if (s.equals("L")) left = sel;
            if (s.equals("R")) right = sel;
            answer += s;
        }
        return answer;
    }

    static class Location {
        int r, c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int getDistance(Location l1, Location l2){
        return Math.abs(l1.r - l2.r) + Math.abs(l1.c - l2.c);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
    }
}
