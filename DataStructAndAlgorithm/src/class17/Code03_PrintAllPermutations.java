package class17;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {


    public static List<String> permutation1(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.isEmpty()){
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char cha : str){
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);

        return ans;
    }
    //暴力递归，几乎全部遍历一遍
    public static void f(ArrayList<Character> rest, String path, List<String> ans){
        if (rest.isEmpty()){    //base case
            ans.add(path);
        }else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.remove(i);
                f(rest, path + cur, ans);
                rest.add(i, cur);   //恢复现场
            }
        }
    }

    public static List<String> permutation2(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.isEmpty()){
            return ans;
        }
        char[] str = s.toCharArray();
        g(str, 0, ans);
        return ans;
    }

    //深度优先遍历，通过交换来得到所有排列
    //针对于str字符数组，每一个交换结果都会保留在str本身
    public static void g(char[] str, int index, List<String> ans){
        //当没得换的时候，就直接加到答案里面去
        if (index == str.length){
            ans.add(String.valueOf(str));
        }else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                g(str, index + 1, ans);
                swap(str, index, i);    //恢复现场
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static List<String> permutation3(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.isEmpty()){
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    //深度优先遍历，通过交换来得到所有排列
    //针对于str字符数组，每一个交换结果都会保留在str本身
    public static void g2(char[] str, int index, List<String> ans){
        //当没得换的时候，就直接加到答案里面去
        if (index == str.length){
            ans.add(String.valueOf(str));
        }else {
            //ASCII码总共256位，所以定义256大小
            //对每一个字符进行监控，看看之前是否遇到过
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]){
                    //将其置为true之后，如果之后仍然遇到了该元素，则跳过
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);    //恢复现场
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }

}
