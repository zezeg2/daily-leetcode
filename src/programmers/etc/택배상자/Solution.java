package programmers.etc.택배상자;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solution {
    public static int solution(int[] order) {
        int answer = 0;
        Queue<Integer> orderQ = new LinkedList<>(Arrays.stream(order).boxed().collect(Collectors.toList()));
        Stack<Integer> stack = new Stack<>();
        int index = 1;
        while (true) {
            if (orderQ.isEmpty()) break;
            Integer target = orderQ.poll();
            if (!stack.empty() && stack.peek() == target) {
                answer++;
                stack.pop();
                continue;
            }
            if (index > target) break;
            for (int i = index; i <= order.length; i++) {
                if (target == i) {
                    answer++;
                    index = i + 1;
                    break;
                } else stack.push(i);
            }
        }
        return answer;
    }

    public static int solution2(int[] order) {
        int answer = 0;
        Queue<Integer> orderQ = new LinkedList<>(Arrays.stream(order).boxed().collect(Collectors.toList()));
        Stack<Integer> stack = new Stack<>();
        int index = 1;
        int target = orderQ.poll();

        while (true) {
            if (!stack.isEmpty() && target == stack.peek()) {
                answer++;
                stack.pop();
                if (orderQ.isEmpty()) break;
                target = orderQ.poll();
                continue;
            }
            if (index > target) break;
            if (target == index) {
                answer++;
                index++;
                if (orderQ.isEmpty()) break;
                target = orderQ.poll();
                continue;
            }
            stack.push(index++);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 6, 7, 5, 8, 4, 9, 3, 10}));
    }
}
