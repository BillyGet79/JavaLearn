package class03;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
//1) pop、push、getMin操作的时间复杂都都是O(1)
//2) 设计的栈类型可以使用现成的栈结构

public class Code05_GetMinStack {

	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack1() {
			stackData = new Stack<Integer>();
			stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (stackMin.isEmpty() || newNum <= this.getmin()) {
				stackMin.push(newNum);
			}
			stackData.push(newNum);
		}

		public int pop() {
			if (stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			int value = stackData.pop();
			if (value == getmin()) {
				stackMin.pop();
			}
			return value;
		}

		public int getmin() {
			if (stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return stackMin.peek();
		}
	}


	//定义两个栈，一个正常存储，一个根据当前的输入值判断直接存入或者只存入栈顶
	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			stackData = new Stack<Integer>();
			stackMin = new Stack<Integer>();
		}

		//如果当前值不是最小值，则stackMin中压入栈顶元素
		//否则，将当前值也压入栈顶
		public void push(int newNum) {
			if (stackMin.isEmpty() || newNum < getmin()) {
				stackMin.push(newNum);
			} else {
				stackMin.push(stackMin.peek());
			}
			stackData.push(newNum);
		}

		public int pop() {
			if (stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			stackMin.pop();
			return stackData.pop();
		}

		public int getmin() {
			if (stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack2 stack2 = new MyStack2();
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
