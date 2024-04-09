package class03;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
//1) pop、push、getMin操作的时间复杂都都是O(1)
//2) 设计的栈类型可以使用现成的栈结构
public class Code05_GetMinStack {

    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack(){
            stackData = new Stack<Integer>();
            stackMin = new Stack<Integer>();
        }

        public void push(int newNum){
            if (stackMin.isEmpty() || newNum <= this.getmin()){
                stackMin.push(newNum);
            }
            stackData.push(newNum);
        }

        public int pop(){
            if (stackMin.isEmpty()){
                throw new RuntimeException("栈空了");
            }
            int value = stackData.pop();
            if (value == stackMin.peek()){
                stackMin.pop();
            }
            return value;
        }

        private int getmin() {
            if (stackMin.isEmpty()){
                throw new RuntimeException("栈空了");
            }
            return stackMin.peek();
        }
    }


    //对数器
    public static void main(String[] args) {

        MyStack stack2 = new MyStack();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }
}
