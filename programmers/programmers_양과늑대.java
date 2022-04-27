import java.util.Deque;
import java.util.LinkedList;

class Solution {
	private static class Node {
		int idx;
		boolean isSheep;
		Node left, right;

		public Node(int idx, boolean isSheep) {
			this.idx = idx;
			this.isSheep = isSheep;
		}
	}

	private static int maxS;
	public static int solution(int[] info, int[][] edges) {
		int N = info.length;
		int M = edges.length;
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; ++i)
			nodes[i] = new Node(i, info[i] == 0 ? true : false);

		int from, to;
		for (int i = 0; i < M; ++i) {
			from = edges[i][0];
			to = edges[i][1];
			if (nodes[from].left == null) {
				nodes[from].left = nodes[to];
			} else {
				nodes[from].right = nodes[to];
			}
		}

		Deque<Node> q = new LinkedList<>();
		maxS = 0;
		dfs(nodes[0], q, 1, 0);
		return maxS;
	}
	
	private static void dfs(Node cur, Deque<Node> q, int s, int w) {
		if (maxS < s) maxS = s;
		
		if (cur == null) return;
		int addCnt = 0;
		if (cur.left != null) {
			addCnt++;
			q.addLast(cur.left);
		}
		if (cur.right != null) {
			addCnt++;
			q.addLast(cur.right);
		}
		
		int size = q.size();
		Node next;
		for (int i = 0; i < size; ++i) {
			next = q.pollFirst();
			if (next.isSheep) s++;
			else w++;
			
			if (s > w) dfs(next, q, s, w);

			if (next.isSheep) s--;
			else w--;
			q.addLast(next);
		}
		
		for (int i = 0; i < addCnt; ++i) {
			q.pollLast();
		}
		
	}
	
}