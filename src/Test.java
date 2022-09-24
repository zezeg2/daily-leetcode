import java.util.*;

public class Test {
    static String[] solution(int n , String[] queries){
        Map<String, Integer> map  = new HashMap<>();
        List<String> list = new ArrayList<>();
        int usingNumber = 0;
        boolean[] using = new boolean[n+1];
        for (int i = 0; i < queries.length; i++){
            String[] split = queries[i].split(" ");
            if (split[1].equals("request")){
                if (usingNumber >= n) {
                    list.add(split[0] + " rejected");
                }
                else {
                    if (!map.containsKey(split[0])) {
                        for (int j = 1; j < using.length; j++){
                            if (!using[j]) {
                                using[j] = true;
                                map.put(split[0], j);
                                break;
                            }
                        }
                        usingNumber++;
                    }
                    list.add(split[0] + " 192.168.0." + map.get(split[0]));
                }
            } else {
                using[map.get(split[0])] = false;
                usingNumber--;
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        int n = 2;
        String[] queries = new String[] {
                "desktop1 request",
                "desktop2 request",
                "desktop3 request",
                "desktop3 request",
                "desktop1 release",
                "desktop3 request"
        };
//        System.out.println(Arrays.toString(solution(n, queries)));
    }
}
