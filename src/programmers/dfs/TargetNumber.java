package programmers.dfs;

public class TargetNumber {

    static int answer = 0;

    static int solution(int[] numbers, int target) {
        int depth = 0;
        dfs(0, numbers, target, depth);
        return answer;
    }

    static void dfs(int prev, int[] numbers, int target, int depth){
        if (depth == numbers.length){
            if (prev == target) answer++;
            return;
        } else{
            dfs(prev + numbers[depth], numbers, target, depth+1);
            dfs(prev - numbers[depth], numbers, target, depth+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,1,1,1,1}, 3));
    }
}
