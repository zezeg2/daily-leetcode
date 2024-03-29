package programmers.etc.두큐합같게만들기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();

        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            que1.offer(queue1[i]);
            sum2 += queue2[i];
            que2.offer(queue2[i]);
        }

        int count = 0;
        while (sum1 != sum2) {
            count++;

            if (sum1 > sum2) {
                int value = que1.poll();
                sum1 -= value;
                sum2 += value;
                que2.offer(value);
            } else {
                int value = que2.poll();
                sum1 += value;
                sum2 -= value;
                que1.offer(value);
            }

            if (count > (queue1.length + queue2.length) * 2) return -1;
        }

        return count;
    }
}

class WrongSolution {

    public static int solution(int[] queue1, int[] queue2) {
        int length = queue1.length;
        long s1 = 0;
        long s2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            s1 += queue1[i];
            s2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        long target = (s1 + s2) / 2;
        int count = 0;

        while (s1 != s2) {
            int polled;
            if (s1 < s2) {
                polled = q2.poll();
                s1 += polled;
                s2 -= polled;
                q1.add(polled);
            } else {
                polled = q1.poll();
                s1 -= polled;
                s2 += polled;
                q2.add(polled);
            }
            count++;
            if (count > (queue1.length + queue2.length) * 2) return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] queue1 = {1, 1};
        int[] queue2 = {1, 5};
        System.out.println(solution(queue1, queue2));
    }
}
