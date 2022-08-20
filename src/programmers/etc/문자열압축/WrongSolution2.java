package programmers.etc.문자열압축;

public class WrongSolution2 {
    static int solution(String s) {
        int length = s.length();
        int answer = length;
        for (int i = 1; i <= length / 2; i++) {
            String result = "";
            for (int j = 0; j < length; j++) {
                int count = 0;
                if (j + 2 * i <= length) {
                    String slice = s.substring(j, j + i);
                    String nextSlice = s.substring(j + i, j + 2 * i);
                    if (!slice.equals(nextSlice)) {
                        if (j == 0) {
                            result = s;
                            break;
                        }
                        result += String.valueOf(s.charAt(j));
                        continue;
                    }
                    while (slice.equals(nextSlice)) {
                        count++;
                        j += i;
                        if (j + i > length) break;
                        slice = nextSlice;
                        nextSlice = s.substring(j, j + i);
                    }
                    result += count + slice;
                    j--;
                } else {
                    result += String.valueOf(s.charAt(j));
                }
            }
            answer = Math.min(answer, result.length());
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
    }
}
