package class17;

import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		//拿到栈底元素
		int i = f(stack);
		//递归去拿
		reverse(stack);
		stack.push(i);
	}

	// 栈底元素移除掉
	// 上面的元素盖下来
	// 返回移除掉的栈底元素
	public static int f(Stack<Integer> stack) {
		//用result将弹出栈的元素保存
		int result = stack.pop();
		if (stack.isEmpty()) {
			//栈为空则说明result为栈底元素
			return result;
		} else {
			//如果没到栈底元素，就递归找到栈底元素
			int last = f(stack);
			//把当前元素入栈
			stack.push(result);
			//将栈底元素向上返回
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
