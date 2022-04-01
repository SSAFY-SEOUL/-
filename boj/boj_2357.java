import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int MIN = 0;
	public static int MAX = 1000000001;
	
	static class Node {
		int min, max;

		public Node(int min, int max) {
			super();
			this.min = min;
			this.max = max;
		}
	}
	
	static class SegmentTree {
		Node[] tree;
		
		public SegmentTree(int size) {
			tree = new Node[1 << ((int)Math.ceil(Math.log10(size) / Math.log10(2)) + 1 )];
		}
		
		public Node init(int []arr, int cur, int start, int end) {
			if (start == end) return tree[cur] = new Node(arr[start], arr[start]);
			
			int mid = (start + end) / 2;

			Node leftNode = init(arr, cur * 2, start, mid);
			Node rightNode = init(arr, cur * 2 + 1, mid + 1, end);
			
			return tree[cur] = new Node(Math.min(leftNode.min, rightNode.min), Math.max(leftNode.max, rightNode.max));
		}
		
		public Node getMinMax(int cur, int start, int end, int left, int right) {
			
			if (left > end || right < start) return new Node(MAX, MIN);
			if (left <= start && end <= right) return tree[cur];
			
			int mid = (start + end) / 2;
			Node leftNode = getMinMax(cur * 2, start, mid, left, right);
			Node rightNode = getMinMax(cur * 2 + 1, mid + 1, end, left, right);
			
			return new Node(Math.min(leftNode.min, rightNode.min), Math.max(leftNode.max, rightNode.max));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];
		
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		int a, b;
		Node node;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			node = tree.getMinMax(1, 1, N, a, b);
			sb.append(node.min).append(" ").append(node.max).append("\n");
		}
		System.out.println(sb.toString());
	}
}
