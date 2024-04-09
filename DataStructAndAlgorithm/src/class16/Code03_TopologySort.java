package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopologySort {

	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		// key 某个节点   value 剩余的入度
		HashMap<Node, Integer> inMap = new HashMap<>();
		// 只有剩余入度为0的点，才进入这个队列
		Queue<Node> zeroInQueue = new LinkedList<>();
		for (Node node : graph.nodes.values()) {
			//将所有节点的入度保存
			inMap.put(node, node.in);
			//只要发现入度为0的点，就直接加入队列
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		List<Node> result = new ArrayList<>();
		while (!zeroInQueue.isEmpty()) {
			//弹出队列
			Node cur = zeroInQueue.poll();
			result.add(cur);
			//遍历所有的相邻节点，让所有的相邻节点的入度-1
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				//如果减完后入度变为0，则入队
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
