package programmers.dfs.게임맵최단거리;

public class Solution {
    static int solution(int[][] maps) {
        int[][] visited = new int[maps.length + 2][maps[0].length + 2];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                visited[i + 1][j + 1] = maps[i][j];
            }
        }
        return dfs(visited, 1, 1, 1);
    }

    static int dfs(int[][] visited, int x, int y, int count) {
        if (visited[y][x] == 0) return -1;
        if (x == visited[0].length - 2 && y == visited.length - 2) {
            return count;
        }
        visited[y][x] = 0;

        int result = dfs(visited, x, y + 1, count + 1);
        if (result == -1) result = dfs(visited, x + 1, y, count + 1);
        if (result == -1) result = dfs(visited, x, y - 1, count + 1);
        if (result == -1) result = dfs(visited, x - 1, y, count + 1);

        return result;
    }

}
