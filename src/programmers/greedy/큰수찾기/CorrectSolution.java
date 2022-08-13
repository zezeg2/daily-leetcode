package programmers.greedy.큰수찾기;

public class CorrectSolution {
    static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder("");
        int len = number.length() - k;
        int start = 0;

        while(start < number.length() && answer.length() != len) {
            int leftNum = k + answer.length() + 1;
            int max = 0;
            for (int j = start; j < leftNum; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    start = j + 1;
                }
            }
            answer.append(Integer.toString(max));
        }
        return answer.toString();
    }

    public static void main(String[] args) {
//        System.out.println(solution("4177252841", 4));
//        System.out.println(solution("1231234", 3));

        String s = "123984";
        int i = s.charAt(3) - '0';
        System.out.println(i);
    }
}
