package class03;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {

    public static class TwoStacksQueue {

        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue(){
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        private void pushToPop(){
            if (stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int value){
            stackPush.push(value);
            pushToPop();
        }
        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("队列是空的！");
            }
            pushToPop();
            return stackPop.peek();
        }
        public int poll(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("队列是空的！");
            }
            pushToPop();
            return stackPop.pop();
        }
    }


    //对数器
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
