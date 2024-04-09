package class19;

import java.util.HashMap;

public class Code03_StickersToSpellWord {

    //本题测试链接：https://leetcode.cn/problems/stickers-to-spell-word
    public static int minStickers1(String[] stickers, String target){
        int[][] sticker = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            //将每一个字符串数组化
            char[] cha = stickers[i].toCharArray();
            for (int j = 0; j < 26; j++) {
                sticker[i][cha[j] - 'a']++;
            }
        }
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //所有贴纸stickers，每一种贴纸都有无穷张
    //target
    //最少张数
    public static int process1(String[] stickers, String target) {
        if (target.length() == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers){
            String rest = minus(target, first);
            if (rest.length() != target.length()){
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String first) {
        char[] str1 = target.toCharArray();
        char[] str2 = first.toCharArray();
        int[] count = new int[26];
        for (char cha : str1){
            count[cha - 'a']++;
        }
        for (char cha : str2){
            count[cha - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++){
            if (count[i] > 0){
                for (int j = 0; j < count[i]; j++){
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //这个二维数组的含义：
    //字符串：aaa   [3,0,0,...]
    //字符串：bbc   [0,2,1,...]
    //字符串：abb   [1,2,0,...]
    //即stickers[i]是一个数组，当初i号贴纸的字符统计
    public static int process2(int[][] stickers, String t){
        if (t.length() == 0){
            return 0;
        }
        //target做出词频统计
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target){
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        //在上一个暴力递归算法中，每次递归我们都拿所有的字符串去尝试
        //但是这一次，我们进行一步剪枝，让字符串中包含目标字符串的第一个字符的去尝试
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            //最关键的优化（重要的剪枝！这一步也是贪心！）
            //拥有目标字符串的第一个字符的字符串参与到尝试
            if (sticker[target[0] - 'a'] > 0){
                //将剪的行为用词频表来实现
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++){
                    if (tcounts[j] > 0){
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minSticker3(String[] stickers, String target){
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str){
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = processr3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int processr3(int[][] stickers, String t, HashMap<String, Integer> dp){
        if (dp.containsKey(t)){
            return dp.get(t);
        }
        //target做出词频统计
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target){
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        //在上一个暴力递归算法中，每次递归我们都拿所有的字符串去尝试
        //但是这一次，我们进行一步剪枝，让字符串中包含目标字符串的第一个字符的去尝试
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            //最关键的优化（重要的剪枝！这一步也是贪心！）
            //拥有目标字符串的第一个字符的字符串参与到尝试
            if (sticker[target[0] - 'a'] > 0){
                //将剪的行为用词频表来实现
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++){
                    if (tcounts[j] > 0){
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }
}
