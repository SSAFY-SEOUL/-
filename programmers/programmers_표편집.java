import java.util.Stack;

class Solution {
	private static class Node {
		int idx;
		Node prev, next;

		public Node(int idx, Node prev, Node next) {
			this.idx = idx;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public String solution(int n, int k, String[] cmd) {
		Stack<Node> deleted = new Stack<>();
		Node[] list = createList(n);
		Node cur = list[k];
		
		String[] tok;
		int X;
		Node revive;
		for (String c : cmd) {
			tok = c.split(" ");

			switch (tok[0].charAt(0)) {
			case 'U':
				X = Integer.parseInt(tok[1]);
				for (int i = 0; i < X; ++i) cur = cur.prev;
				break;
			case 'D':
				X = Integer.parseInt(tok[1]);
				for (int i = 0; i < X; ++i) cur = cur.next;
				break;
			case 'C':
				if (cur.prev != null) cur.prev.next = cur.next;
				if (cur.next != null) cur.next.prev = cur.prev;

				deleted.add(cur);
				if (cur.next == null) cur = cur.prev;
				else cur = cur.next;
				
				break;
			case 'Z':
				revive = deleted.pop();
				if (revive.prev != null) revive.prev.next = revive;
				if (revive.next != null) revive.next.prev = revive;
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			sb.append('X');
		}
		
		sb.setCharAt(cur.idx, 'O');
		Node left = cur.prev;
		while (left != null) {
			sb.setCharAt(left.idx, 'O');
			left = left.prev;
		}
		Node right = cur.next;
		while (right != null) {
			sb.setCharAt(right.idx, 'O');
			right = right.next;
		}
		
		return sb.toString();
	}
	
	public static Node[] createList(int n) {
		Node[] nodes = new Node[n];
		
		nodes[0] = new Node(0, null, null);
		for (int i = 1; i < n; ++i) {
			nodes[i] = new Node(i, nodes[i - 1], null);
			nodes[i - 1].next = nodes[i];
		}
		
		return nodes;
	}
}