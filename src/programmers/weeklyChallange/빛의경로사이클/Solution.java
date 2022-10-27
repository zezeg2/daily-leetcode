package programmers.weeklyChallange.빛의경로사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static class Light {
        public Light(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
        int col;
        int row;
        int cycleLength = 0;
        int direction;
        public void nextLoc(String[] grid) {
            if (this.direction == 0) this.row = this.row == 0 ? grid.length - 1 : this.row - 1;
            else if (this.direction == 1) this.row = this.row == grid.length - 1 ? 0 : this.row + 1;
            else if (this.direction == 2) this.col = this.col == 0 ? grid[0].length() - 1 : this.col - 1;
            else if (this.direction == 3) this.col = this.col == grid[0].length() - 1 ? 0 : this.col + 1;
        }
    }

    static int[] solution(String[] grid) {
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
        List<Light> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                for (int d = 0; d < 4; d++) {
                    Light light = new Light(i, j, d);
                    if (visited[i][j][d]) continue;
                    while (true) {
                        if (visited[light.row][light.col][light.direction]) {
                            list.add(light);
                            break;
                        }
                        visited[light.row][light.col][light.direction] = true;
                        light.cycleLength++;
                        if (grid[light.row].charAt(light.col) == 'S') {
                            light.nextLoc(grid);
                        } else if (grid[light.row].charAt(light.col) == 'R') {
                            if (light.direction == 0) light.direction = 3;
                            else if (light.direction == 1) light.direction = 2;
                            else if (light.direction == 2) light.direction = 0;
                            else light.direction = 1;
                            light.nextLoc(grid);
                        } else if (grid[light.row].charAt(light.col) == 'L') {
                            if (light.direction == 0) light.direction = 2;
                            else if (light.direction == 1) light.direction = 3;
                            else if (light.direction == 2) light.direction = 1;
                            else light.direction = 0;
                            light.nextLoc(grid);
                        }
                    }
                }
            }
        }
        int[] answer = list.stream().mapToInt(o -> o.cycleLength).toArray();
        Arrays.sort(answer);
        return answer;
    }

    public static void main(String[] args) {
        String[] grid = {"SL", "LR"};
        System.out.println(Arrays.toString(solution(grid)));
    }
}
