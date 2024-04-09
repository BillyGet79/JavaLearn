package class46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class huffman {

    //哈夫曼树节点
    public static class Node{
        public String name;
        public int count;
        public Node left;
        public Node right;

        public Node(int count, String name) {
            this.count = count;
            this.name = name;
        }

        public Node(int count) {
            this.count = count;
        }
    }

    //比较器
    public static class NodeComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.count - o2.count;
        }
    }

    //根据离散无记忆信源生成扩展信源
    //直接生成优先级队列方便下面操作
    public static PriorityQueue<Node> sourceGeneration(double[] pro) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComp());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3 ;k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(i + 1).append(j + 1).append(k + 1);
                    String name = sb.toString();
                    priorityQueue.add(new Node((int)(pro[i]*pro[j]*pro[k]*1000), name));
                }
            }
        }
        return priorityQueue;
    }

    //进行哈夫曼编码
    public static HashMap<String, String> huffmanForm(PriorityQueue<Node> heap) {
        HashMap<String, String> ans = new HashMap<>();
        while (heap.size() != 1) {
            //注意，这里a是更小的那一个，所以放在右树上
            Node a = heap.poll();
            Node b = heap.poll();
            Node h = new Node(a.count + b.count);
            h.right = a;
            h.left = b;
            heap.add(h);
        }
        //最后剩下的元素一定为最终的头节点
        Node head  = heap.poll();
        //编码操作
        fillForm(head, "", ans);
        return ans;
    }

    //递归实现编码
    public static void fillForm(Node head, String pre, HashMap<String, String> ans) {
        //如果左树右树双空，那么这个节点一定是有name的
        if (head.left == null || head.right == null) {
            ans.put(head.name, pre);
        } else {
            fillForm(head.left, pre + "0", ans);
            fillForm(head.right, pre +  "1", ans);
        }
    }

    //测试，得出结果
    public static void main(String[] args) {
        double[] pro = new double[]{0.5, 0.3, 0.2};
        HashMap<String, String> ans = huffmanForm(sourceGeneration(pro));
        //记录平均码长
        double n  = 0;
        //计算平均码长
        for (Map.Entry<String, String> entry : ans.entrySet()) {
            String name = entry.getKey();
            //通过name计算概率
            double d = 1.0;
            for (char c : name.toCharArray()) {
                d = d * pro[c - 49];
            }
            //将概率与自己本身的码长相乘，与平均码长相加
            n += d * entry.getValue().length();
        }
        System.out.println(n);
    }
}








