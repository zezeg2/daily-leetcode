package programmers.dfs.퍼즐조각맞추기;

import java.util.*;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        List<int[][]> finalList = new ArrayList<>();
        int length = game_board.length;


        List<int[][]> leftList = new ArrayList<>();
        List<int[][]> puzzleList = new ArrayList<>();

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (game_board[x][y] == 1) continue;
                int[][] left = new int[length][length];
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    int px = poll[0];
                    int py = poll[1];

                    game_board[px][py] = 1;
                    left[px][py] = 1;
                    for (int i = 0; i < 4; i++) {
                        if (px + dx[i] < 0 || py + dy[i] < 0 || px + dx[i] > length - 1 || py + dy[i] > length - 1)
                            continue;
                        if (game_board[px + dx[i]][py + dy[i]] == 0) {
                            queue.add(new int[]{px + dx[i], py + dy[i]});
                        }
                    }
                }
                left = split(left);
                left = rotate(left);
                left = split(left);
                leftList.add(left);
            }
        }

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (table[x][y] == 0) continue;
                int[][] puzzle = new int[length][length];
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    int px = poll[0];
                    int py = poll[1];

                    table[px][py] = 0;
                    puzzle[px][py] = 1;
                    for (int i = 0; i < 4; i++) {
                        if (px + dx[i] < 0 || py + dy[i] < 0 || px + dx[i] > length - 1 || py + dy[i] > length - 1)
                            continue;
                        if (table[px + dx[i]][py + dy[i]] == 1) {
                            queue.add(new int[]{px + dx[i], py + dy[i]});
                        }
                    }
                }
                puzzle = split(puzzle);
                puzzle = rotate(puzzle);
                puzzle = split(puzzle);
                puzzleList.add(puzzle);
            }
        }

        for (int[][] left : leftList) {
            for (int i = 0; i < puzzleList.size(); i++) {
                int[][] p = puzzleList.get(i);
                int[][] r1 = rotate(left);
                int[][] r2 = rotate(r1);
                int[][] r3 = rotate(r2);

                if (isEqual(left, p) || isEqual(r1, p) || isEqual(r2, p) || isEqual(r3, p)) {
                    finalList.add(p);
                    puzzleList.remove(i);
                    break;
                }
            }
        }
        for (int[][] f : finalList) {
            for (int i = 0; i < f.length; i++) {
                for (int j = 0; j < f[0].length; j++) {
                    if (f[i][j] == 1) answer++;
                }
            }
        }
        return answer;
    }

    public static boolean isEqual(int[][] first, int[][] second) {
        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        if (first.length != second.length) {
            return false;
        }

        for (int i = 0; i < first.length; i++) {
            if (first[i].length != second[i].length) {
                return false;
            }

            for (int j = 0; j < first[i].length; j++) {
                if (first[i][j] != second[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static int[][] split(int[][] arr) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Arrays.stream(arr[i]).sum() != 0) {
                start = i;
                break;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (Arrays.stream(arr[i]).sum() != 0) {
                end = i;
                break;
            }
        }
        return Arrays.copyOfRange(arr, start, end + 1);
    }

    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n - 1 - j][i];
            }
        }

        return rotate;
    }

    public static void main(String[] args) {
        int[][] game_board = new int[][]{{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table = new int[][]{{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

        System.out.println(solution(game_board, table));
    }
}
