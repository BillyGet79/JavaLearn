package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

	// 最多K个项目
	// W是初始资金
	// Profits[] Capital[] 一定等长
	// 返回最终最大的资金
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		//小根堆
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		//大根堆
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		//把所有项目放到小根堆里面去
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		for (int i = 0; i < K; i++) {
			while (!minCostQ.isEmpty() && minCostQ.peek().capital <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			W += maxProfitQ.poll().profit;
		}
		return W;
	}

	public static class Program {
		public int profit;
		public int capital;

		public Program(int profit, int captial) {
			this.profit = profit;
			this.capital = captial;
		}
	}

	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.capital - o2.capital;
		}

	}

	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.profit - o1.profit;
		}

	}

}
