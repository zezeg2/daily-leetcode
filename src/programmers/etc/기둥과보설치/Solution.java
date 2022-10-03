package programmers.etc.기둥과보설치;

public class Solution {
    private int n;
    private boolean[][] pillars, beams; // 기둥, 보
    private final int PILLAR = 0, BEAM = 1, REMOVE = 0, ADD = 1;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        int count = 0;
        pillars = new boolean[n + 3][n + 3];
        beams = new boolean[n + 3][n + 3];

        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            if (frame[2] == PILLAR) { // 기둥
                if (frame[3] == ADD && canConstructPillar(x, y)) { // 해당 위치에 기둥을 세울 수 있으면
                    pillars[x][y] = true;
                    count++;
                }
                if (frame[3] == REMOVE && canDestruct(x, y, PILLAR)) { // 삭제할 수 있으면
                    pillars[x][y] = false;
                    count--;
                }
            } else { // 보
                if (frame[3] == ADD && canConstructBeam(x, y)) {
                    beams[x][y] = true;
                    count++;
                }
                if (frame[3] == REMOVE && canDestruct(x, y, BEAM)) {
                    beams[x][y] = false;
                    count--;
                }
            }
        }

        int[][] answer = new int[count][3];
        int index = 0;
        for (int i = 1; i <= n + 1; i++) { // 남아 있는 기둥과 보 배열에 저장
            for (int j = 1; j <= n + 1; j++) {
                if (pillars[i][j]) answer[index++] = new int[]{i - 1, j - 1, PILLAR};
                if (beams[i][j]) answer[index++] = new int[]{i - 1, j - 1, BEAM};
            }
        }
        return answer;
    }

    private boolean canConstructPillar(int x, int y) { // x, y 위치에 존재할 수 있는지
        return y == 1 || pillars[x][y - 1] || beams[x][y] || beams[x - 1][y];
    }

    private boolean canConstructBeam(int x, int y) {
        return pillars[x][y - 1] || pillars[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
    }

    private boolean canDestruct(int x, int y, int type) {
        boolean result = true;

        if (type == PILLAR) pillars[x][y] = false; // 임시로 삭제
        else beams[x][y] = false;

        loop:
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (pillars[i][j] && !canConstructPillar(i, j)) {
                    result = false;
                    break loop;
                }
                if (beams[i][j] && !canConstructBeam(i, j)) {
                    result = false;
                    break loop;
                }
            }
        }

        if (type == PILLAR) pillars[x][y] = true; // 삭제했던 구조물 원상복구
        else beams[x][y] = true;

        return result;
    }

    public static void main(String[] args) {
//        int[][] solution = solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});
//        for (int[] e : solution) System.out.println(Arrays.toString(e));
    }
}
