package Test100097;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        HashMap<Integer, Integer> maps = new HashMap<>();
        //将所有的相同值加入到map当中
        for (int num : nums) {
            if (maps.containsKey(num)) {
                maps.put(num, maps.get(num) + 1);
            } else {
                maps.put(num, 1);
            }
        }
        int[] numsSet = new int[maps.size()];
        int N = 0;
        int mins = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> map : maps.entrySet()){
            numsSet[N] = map.getValue();
            mins = Math.min(mins, numsSet[N]);
            N++;
        }
        int ans = Integer.MAX_VALUE;
        for (int k = 1; k <= mins; k++){
            int cnt = 0;
            for (int s : numsSet){
                int d = s / (k + 1);
                int r = s % (k + 1);
                if (r == 0){
                    cnt += d;
                } else if (d + r >= k){
                    cnt += d + 1;
                } else {
                    d = s / k;
                    r = s % k;
                    if (r > d) {
                        cnt = -1;
                        break;
                    }
                    cnt += d;
                }
            }
            if (cnt >= 0){
                ans = Math.min(ans, cnt);
            }
        }
        return ans;
    }
}
