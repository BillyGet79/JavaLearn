package class08;

public class Code01_Trie {
	// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
	// 提交Trie类可以直接通过
	// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
	// 这个链接上要求实现的功能和课上讲的完全一样
	// 该前缀树的路用数组实现
	class Trie {
		class Node {
			public int pass;
			public int end;
			public Node[] nexts;
			public Node() {
				pass = 0;
				end = 0;
				//将i位置看为i方向上的路是否存在
				//例如0位置看为a方向的路是否存在
				nexts = new Node[26];
			}
		}
		//这一步封装很关键
		private Node root;
		//对外构造器来初始化定义的前缀树对象
		public Trie() {
			root = new Node();
		}
		//后续通过对外的方法来操作前缀树
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}
		public void erase(String word) {
			if (countWordsEqualTo(word) != 0) {
				char[] chs = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					//如果这个分支的第一个节点的pass为0了，直接把这个分支拆掉
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}
		public int countWordsEqualTo(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				//如果走着走着没路了，就说明这个单词没有出现过
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}
		public int countWordsStartingWith(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

}
