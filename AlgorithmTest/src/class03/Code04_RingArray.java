package class03;

//实现循环队列
public class Code04_RingArray {
    public static class MyQueue{
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value){
            if (size == limit){
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[polli];
            pushi = nextIndex(polli);
            return ans;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private int nextIndex(int number) {
            return number < limit - 1 ? number + 1 : 0;
        }
    }
}
