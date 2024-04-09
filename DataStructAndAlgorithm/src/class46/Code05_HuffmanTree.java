package class46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Code05_HuffmanTree {

    //根据文章str，生成词频统计表
    public static HashMap<Character, Integer> countMap(String str) {
        HashMap<Character, Integer> ans = new HashMap<>();
        char[] s = str.toCharArray();
        for (char cha : s) {
            if (!ans.containsKey(cha)) {
                ans.put(cha, 1);
            } else {
                ans.put(cha, ans.get(cha) + 1);
            }
        }
        return ans;
    }

    //哈夫曼树节点
    public static class Node {
        public int count;
        public Node left;
        public Node right;

        public Node(int count) {
            this.count = count;
        }
    }

    //写一个Node的比较器
    public static class NodeComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.count - o2.count;
        }
    }

    //生成哈夫曼编码表
    public static HashMap<Character, String> huffmanForm(HashMap<Character, Integer> countMap) {
        HashMap<Character, String> ans = new HashMap<>();
        if (countMap.size() == 1) {
            for (char key : countMap.keySet()) {
                ans.put(key, "0");
            }
            return ans;
        }
        HashMap<Node, Character> nodes = new HashMap<>();
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComp());
        //将所有的词频建立成节点和字符的对应关系，然后存放到nodes和小根堆当中
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            Node cur = new Node(entry.getValue());
            char cha = entry.getKey();
            nodes.put(cur, cha);
            heap.add(cur);
        }
        //进行建堆操作
        while (heap.size() != 1) {
            Node a = heap.poll();
            Node b = heap.poll();
            Node h = new Node(a.count + b.count);
            h.left = a;
            h.right = b;
            heap.add(h);
        }
        //最后剩下的元素一定为最终的头节点
        Node head = heap.poll();
        //进行编码操作
        fillForm(head, "", nodes, ans);
        return ans;
    }

    //编码操作，这里用递归实现
    public static void fillForm(Node head, String pre, HashMap<Node, Character> nodes, HashMap<Character, String> ans) {
        //如果当前遍历到的节点在nodes当中存在，那么就说明遍历到头了，这个时候将pre放进ans数组当中即可
        if (nodes.containsKey(head)) {
            ans.put(nodes.get(head), pre);
        } else {
            //注意，哈夫曼树除了叶子节点以外，所有节点都是有左右两个孩子的，如果上面的if没有拦住，这里的节点一定不是叶子节点，一定有左右两个孩子，不要担心指针指向空
            //如果向左跑，那么就加“0”
            fillForm(head.left, pre + "0", nodes, ans);
            //如果向右跑，那么就加"1"
            fillForm(head.right, pre + "1", nodes, ans);
        }
    }

    //编码操作
    public static String huffmanEncode(String str, HashMap<Character, String> huffmanForm) {
        char[] s = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char cha : s) {
            builder.append(huffmanForm.get(cha));
        }
        return builder.toString();
    }

    //利用前缀树来实现哈夫曼编码的解码操作
    //前缀树结构
    public static class TireNode {
        public char value;
        public TireNode[] nexts;
        public TireNode() {
            value = 0;
            //因为每个节点向下只有0和1两种分支，所以这个定义两个单元即可
            nexts = new TireNode[2];
        }
    }

    //创建前缀树
    public static TireNode creatTire(HashMap<Character, String> huffmanForm) {
        TireNode root = new TireNode();
        for (char key : huffmanForm.keySet()) {
            //获取每个字符串编码
            char[] path = huffmanForm.get(key).toCharArray();
            //开始进行对当前字符串建树
            TireNode cur = root;
            for (int i = 0; i < path.length; i++) {
                int index = path[i] == '0' ? 0 : 1;
                //如果接下来的元素没建立，那就建立一个
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new TireNode();
                }
                //指向下一个节点
                cur = cur.nexts[index];
            }
            //到这里一定是一个触底节点了，给其附上value值
            cur.value = key;
        }
        return root;
    }

    //哈夫曼解码操作
    public static String huffmanDecode(String huffmanEncode, HashMap<Character, String> huffmanForm) {
        //先创建前缀树
        TireNode root = creatTire(huffmanForm);
        TireNode cur = root;
        char[] encode = huffmanEncode.toCharArray();
        StringBuilder builder = new StringBuilder();
        //开始遍历整个编码字符串
        for (int i = 0; i < encode.length; i++) {
            //看当前的编码是什么
            int index = encode[i] == '0' ? 0 : 1;
            //根据编码走下一个位置
            cur = cur.nexts[index];
            //如果触底了，那么这个节点的value就是解码得到的字符
            if (cur.nexts[0] == null && cur.nexts[1] == null) {
                builder.append(cur.value);
                //编码找到一个之后直接返回头节点
                cur = root;
            }
        }
        return builder.toString();
    }

    //对数器
    //根据给定的长度以及给定的字符种类，生成随机的文章
    public static String randomNumberString(int len, int range) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + 'a');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        // 根据词频表生成哈夫曼编码表
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 60);
        map.put('B', 45);
        map.put('C', 13);
        map.put('D', 69);
        map.put('E', 14);
        map.put('F', 5);
        map.put('G', 3);
        HashMap<Character, String> huffmanForm = huffmanForm(map);
        for (Map.Entry<Character, String> entry : huffmanForm.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("====================");
        // str是原始字符串
        String str = "CBBBAABBACAABDDEFBA";
        System.out.println(str);
        // countMap是根据str建立的词频表
        HashMap<Character, Integer> countMap = countMap(str);
        // hf是根据countMap生成的哈夫曼编码表
        HashMap<Character, String> hf = huffmanForm(countMap);
        // huffmanEncode是原始字符串转译后的哈夫曼编码
        String huffmanEncode = huffmanEncode(str, hf);
        System.out.println(huffmanEncode);
        // huffmanDecode是哈夫曼编码还原成的原始字符串
        String huffmanDecode = huffmanDecode(huffmanEncode, hf);
        System.out.println(huffmanDecode);
        System.out.println("====================");
        System.out.println("大样本随机测试开始");
        // 字符串最大长度
        int len = 500;
        // 所含字符种类
        int range = 26;
        // 随机测试进行的次数
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 1;
            String test = randomNumberString(N, range);
            HashMap<Character, Integer> counts = countMap(test);
            HashMap<Character, String> form = huffmanForm(counts);
            String encode = huffmanEncode(test, form);
            String decode = huffmanDecode(encode, form);
            if (!test.equals(decode)) {
                System.out.println(test);
                System.out.println(encode);
                System.out.println(decode);
                System.out.println("出错了!");
            }
        }
        System.out.println("大样本随机测试结束");
    }

}
