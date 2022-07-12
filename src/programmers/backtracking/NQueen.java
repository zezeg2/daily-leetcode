package programmers.backtracking;

public class NQueen {
    boolean[] visited;
    int[] pick; //뽑은 위치를 저장. 0번인덱스는 첫 번째 줄에서 퀸의 위치를 의미한다.
    int count;

    public int solution(int n) {
        visited = new boolean[n];
        pick = new int[n];
        count = 0;

        permutation(n, 0);
        return count;
    }

    public void permutation(int n, int idx) {
        if(n == idx) {
            count++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                pick[idx] = i;
                if(check(idx)) permutation(n, idx + 1);
                visited[i] = false;
            }
        }
    }

    public boolean check(int idx) {
        for(int i = 0; i < idx; i++) {
            if(pick[i] == pick[idx]) return false; //같은 행에 퀸이 존재하면 false
            if(Math.abs(idx - i) == Math.abs(pick[idx] - pick[i])) return false; //대각선에 퀸이 존재한다면 false
        }
        return true;
    }
}
