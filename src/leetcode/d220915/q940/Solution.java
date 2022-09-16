package leetcode.d220915.q940;

import java.util.HashMap;

public class Solution {
    public int distinctSubseqII(String s) {
        HashMap<Character , Long> lastOcc = new HashMap<>();
        long lastCount = 1;
        long mod = (long)1e9+7;
        for(int index = 0 ; index < s.length() ; index++){
            char current = s.charAt(index);
            long temp = lastCount % mod;
            long value = (2*lastCount) % mod;
            if(lastOcc.containsKey(current)){
                long prev = lastOcc.get(current);
                lastCount = (value - prev % mod + mod) % mod;
            }else{
                lastCount = value;
            }
            lastOcc.put(current , temp);
        }
        return (int)(lastCount-1);
    }
}
