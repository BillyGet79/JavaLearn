package Test49;

import java.util.*;

//https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0){
            return null;
        }
        List<List<String>> ans = new ArrayList<>();
        //用来保存排好序的字符序列
        HashMap<String, Integer> temp = new HashMap<>();
        for (String str : strs) {
            String s = stringSort(str);
            if (temp.containsKey(s)){
                ans.get(temp.get(s)).add(str);
            } else {
                temp.put(s, ans.size());
                ans.add(new ArrayList<>());
                ans.getLast().add(str);
            }
        }
        return ans;
    }
    private String stringSort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ans = solution.groupAnagrams(strs);
        for (List<String> an : ans) {
            for (String s : an) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
