package class32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code04_AC2 {
    //前缀树的节点
    public static class Node {
        // 如果一个node，end为空，不是结尾
        // 如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        //只有在上面的end变量不为空的时候，endUse才有意义
        // 表示，这个字符串之前有没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;
        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }
    public static class ACAutomation {
        private Node root;
        public ACAutomation() {
            root = new Node();
        }
        //前缀树的构建，有路就复用，没有路就新建
        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()){
                //当前节点弹出
                //当前节点的所有后代加入到队列里去
                //当前节点给它的子去设置fail指针
                //cur -> 父亲
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {  //所有的路
                    //i号儿子，必须把i号儿子的fail指针设置好！
                    if (cur.nexts[i] != null) { //如果真的有i号儿子
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            //注意next[i]表示的就是对应的路径，如果路径存在，则fail指向这个next[i]
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            //如果路径不存在，即next[i] == null,则继续找
                            cfail = cfail.fail;
                        }
                        //如果当前的儿子设置好了，就把他加入到队列中去
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }
        //大文章遍历
        public List<String> containWords(String content){
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                //当前来到的“路”
                index = str[i] - 'a';
                //如果当前字符串在这条路上没配出来，就随着fail方向走向下条路径
                while (cur.nexts[index] == null && cur != root){
                    cur = cur.fail;
                }
                //1.现在来到的路径，是可以继续匹配的
                //2.现在来到的节点，就是前缀树的根节点
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    //如果之前收集过这个字符串了，那就不用管了
                    if (follow.endUse) {
                        break;
                    }
                    //不同的需求，在这一段之间修改
                    //这里代表找到了一个可以收集的字符串
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    //不同的需求，在这一段之间修改
                    //把所有的fail全部跑一遍
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }
    //对数器
    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
