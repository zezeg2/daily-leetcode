package programmers.etc.코딩테스트공부;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        Arrays.sort(problems);
        int maxAlp = Integer.MIN_VALUE;
        int maxCop = Integer.MIN_VALUE;
        List<Problem> problemList = new ArrayList<>();
        List<Problem> cleared = new ArrayList<>();
        for (int[] p : problems){
            Problem problem = new Problem(p[0],p[1],p[2],p[3],p[4]);
            problemList.add(problem);
            if (alp > p[0] && cop > p[1]) {
                cleared.add(problem);
                problemList.remove(problem);
            }
            maxAlp = Math.max(maxAlp, problem.alpReq);
            maxCop = Math.max(maxCop, problem.copReq);
        }

        while (alp < maxAlp && cop < maxCop){

        }



        int answer = 0;
        return answer;
    }

    static class Problem {

        public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int reqTime) {
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.reqTime = reqTime;
        }

        int alpReq;
        int copReq;
        int alpRwd;
        int copRwd;
        int reqTime;
    }
}
