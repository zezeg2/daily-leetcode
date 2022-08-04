package programmers.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class AnotherSolution {
    public int solution(int[][] maps) {
        int answer = 0;
        int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Position> q = new LinkedList();
        int[][] count = new int[maps.length][maps[0].length];

        q.add(new Position(0, 0));
        count[0][0] = 1;

        while (!q.isEmpty()) {
            Position cur = q.poll();
            int currentCount = count[cur.y][cur.x];
            for (int i = 0; i < 4; i++) {
                Position p = new Position(cur.x + move[i][0], cur.y + move[i][1]);
                //맵밖으로 나갈때
                if (p.x < 0 || p.y < 0 || p.x == maps[0].length || p.y == maps.length) {
                    continue;
                }
                //벽에 부딪힐때
                if (maps[p.y][p.x] == 0) {
                    continue;
                }
                count[p.y][p.x] = currentCount + 1;
                //이미 지난길은 벽으로 만들어버리기
                maps[p.y][p.x] = 0;
                q.add(p);
            }
        }
        answer = count[maps.length - 1][maps[0].length - 1];
        if (answer == 0) {
            return -1;
        }
        return answer;
    }
}

class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
