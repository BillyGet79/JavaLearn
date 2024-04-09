package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//队列书写栈，两个队列互相倒
public class Code07_TwoQueueImplementStack {

	public static class TwoQueueStack<T> {
		//注意，本质上是两个队列互相倒，为了调用方便，统一用queue来保存入栈的数据
		public Queue<T> queue;
		public Queue<T> help;

		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}

		public void push(T value) {
			//向链表尾部添加数据
			queue.offer(value);
		}

		public T poll() {
			//将非弹出栈的数据转移到另一个队列当中去
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			//这个为要弹出栈的数据
			T ans = queue.poll();
			//将queue和help队列交换
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public T peek() {
			//将非出栈的数据转移到另一个队列当中
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			//这个数据就是要找到的数据
			T ans = queue.poll();
			//将该数据返回到队列中
			help.offer(ans);
			//将queue和help队列交换
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

	public static void main(String[] args) {
		System.out.println("test begin");
		TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
		Stack<Integer> test = new Stack<>();
		int testTime = 1000000;
		int max = 1000000;
		for (int i = 0; i < testTime; i++) {
			if (myStack.isEmpty()) {
				if (!test.isEmpty()) {
					System.out.println("Oops");
				}
				int num = (int) (Math.random() * max);
				myStack.push(num);
				test.push(num);
			} else {
				if (Math.random() < 0.25) {
					int num = (int) (Math.random() * max);
					myStack.push(num);
					test.push(num);
				} else if (Math.random() < 0.5) {
					if (!myStack.peek().equals(test.peek())) {
						System.out.println("Oops");
					}
				} else if (Math.random() < 0.75) {
					if (!myStack.poll().equals(test.pop())) {
						System.out.println("Oops");
					}
				} else {
					if (myStack.isEmpty() != test.isEmpty()) {
						System.out.println("Oops");
					}
				}
			}
		}

		System.out.println("test finish!");

	}

}
