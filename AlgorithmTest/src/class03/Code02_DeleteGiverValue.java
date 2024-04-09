package class03;

//删除对应的节点
public class Code02_DeleteGiverValue {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static Node removeValue(Node head, int num){
        while (head != null){
            if (head.value == num){
                head = head.next;
            }
        }
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}

