import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
		int num, level;
		Node parent;
		List<Node> children;

		public Node(int num, int level, Node parent) {
			super();
			this.num = num;
			this.level = level;
			this.parent = parent;
			this.children = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] seq = br.readLine().toCharArray();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		int[] numSeq = new int[2 * N];
		Node rNode1 = null;
		Node rNode2 = null;
		
		int apple = 0;
		Node root = new Node(apple++, 0, null);
		Node cur = root;
		Node next = null;
		for (int i = 0; i < 2*N; ++i) {
			if (seq[i] == '0') {
				next = new Node(apple++, cur.level + 1, cur);
				cur.children.add(next);
				cur = next;
				
				numSeq[i] = cur.num;
				if (i + 1 == X) rNode1 = cur;
				if (i + 1 == Y) rNode2 = cur;
			} else if (seq[i] == '1') {
				numSeq[i] = cur.num;

				if (i + 1 == X) rNode1 = cur;
				if (i + 1 == Y) rNode2 = cur;
				
				cur = cur.parent;
			}
		}
		
		int removeNum = getLCA(rNode1, rNode2);
		for (int i = 0; i < 2*N; ++i) {
			if (numSeq[i] == removeNum) System.out.print((i+1) + " ");
		}
	}
	
	private static int getLCA(Node a, Node b) {
		while (a.level > 0 && b.level > 0) {
			if (a.level > b.level) {
				a = a.parent;
			} else if (a.level < b.level) {
				b = b.parent;
			} else {
				if (a.num == b.num) return a.num;
				a = a.parent;
				b = b.parent;
			}
		}
		
		return 0;
	}
}