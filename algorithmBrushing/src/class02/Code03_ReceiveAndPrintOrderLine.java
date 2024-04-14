package class02;

import java.util.HashMap;

/**
 * 题目3
 * 已知一个消息流会不断地吐出正数1~N
 * 但不一定按照顺序依次吐出
 * 如果上次打印的序号为i，那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数
 * 直到1~N全部接收并且打印完
 * 请设计这种接收并打印的结构
 */
public class Code03_ReceiveAndPrintOrderLine {
    /**
     * 这个问题与现实问题中信号的接收顺序有相同的意思
     * 由于现实中信号传输的不稳定性，有可能在传输过程中会产生丢包的问题，而由于传输中的滑动窗口机制，在超时之后就会进行重传
     * 所以每一个信息到达的顺序很有可能与发送的顺序不一样
     * 就比如按序发送0~9个信息，到达的时候不一定0~9号信息按序到达，中间的顺序有可能是混乱的
     * 但是这个时候我们将这个接收到的信息序列打印出来时又必须按照发送顺序打印
     * 这个数据结构解决的就是这样一类问题
     * 我们要实现的是，每接收一个数据都要尝试打印
     * 但是如果到达的数据不是按照既定顺序到达的就不能打印
     * 如果到达的数据是既定的数据的话，那么打印当前数据以及这个数据之后的顺序数据
     */

    //自己写的方法，虽然hashMap的get以及containsKey方法时间复杂度都是O(1)，但是其常数时间很大，所以用链表来解决打印问题能够优化效率
    /*public static class MessageBox {
        private int time;   //记录当前要接收的数据
        private HashMap<Integer, String> map;   //记录到达的数据

        public MessageBox() {
            this.time = 1;
            map = new HashMap<>();
        }

        public void receive(int time, String s) {
            map.put(time, s);
            if (this.time == time) {
                System.out.print(s + " ");
                this.time++;
                while (map.containsKey(this.time)) {
                    System.out.print(map.get(this.time++) + " ");
                }
                System.out.println();
            }
        }
    }*/


    public static class Node {
        public String info;
        public Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    /**
     * 设计思路：
     * 当我们接收到一个数据的时候，使用Node类结构来存储当前接收到的信息，其中info存储接收到的字符串，next指向的是当前接收到的字符串的下一个接收到的字符串的Node
     * 定义两个哈希表，其中key表示接收到的Node是第几个信息，Node则存储用Node封装后的信息
     * headMap存储当前存在链串的头节点，tailMap存储当前存在链串的尾节点
     * 当接收到一个消息并将其打包成Node之后，我们将接收到的信息次序以及Node节点存放到两个哈希表中
     * 然后我们在tailMap中找到当前传入节点的前一个节点，就是当前存在的某一个链串的尾节点的下一个节点刚好是传入的节点
     * 如果找到了，则将其在tailMap中移除，并且headMap中移除当前传入节点
     * 然后我们在headMap中找到当前传入节点的后一个节点，就是当前存在的某一个链串的头节点的前一个节点刚好是传入的节点
     * 如果找到了，则将其在headMap中移除，并且tailMap中移除当前传入节点
     * 使用waitPoint表示当前等待的节点
     * 如果当前等待的节点传入，那么就找到该节点，让该节点顺着链串向下打印
     * 打印前将headMap中的该节点移除
     * 打印结束后在tailMap中删除最后打印的节点
     */
    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            //封装成节点
            Node cur = new Node(info);
            //将其放入两个哈希表中
            headMap.put(num, cur);
            tailMap.put(num, cur);
            //从tailMap中找其前一个节点
            if (tailMap.containsKey(num - 1)) {
                //将找到的节点的next指针指向当前封装后的节点
                tailMap.get(num - 1).next = cur;
                //然后在tailMap和headMap中删除两个节点
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            //从headMap中找其后一个节点
            if (headMap.containsKey(num + 1)) {
                //将封装好的节点的next指针指向找到的节点
                cur.next = headMap.get(num + 1);
                //然后在tailMap和headMap中删除两个节点
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
            //如果当前传入的消息次序与等待的点相同，则打印
            if (num == waitPoint) {
                print();
            }
        }
        private void print() {
            //找到当前的节点
            Node node = headMap.get(waitPoint);
            //将这个节点在headMap中删除
            headMap.remove(waitPoint);
            //打印
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }

    }


    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        System.out.println("这是2来到的时候");
        box.receive(2, "B");

        System.out.println("这是1来到的时候");
        box.receive(1, "A");

        box.receive(4, "D");
        box.receive(5, "E");
        box.receive(7, "G");
        box.receive(8, "H");
        box.receive(6, "F");
        box.receive(3, "C");
        box.receive(9, "I");
        box.receive(10, "J");
        box.receive(12, "L");
        box.receive(13, "M");
        box.receive(11, "K");

    }
}
