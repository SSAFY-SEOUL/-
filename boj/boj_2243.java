import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	private static class SegmentTree {
		int[] tree;
		
		public SegmentTree(int size) {
			tree = new int[1 << ((int)Math.ceil(Math.log10(size) / Math.log10(2)) + 1 )];
		}
		
		public void update(int cur, int left, int right, int target, int val) {
			if (target < left || right < target) return;
			
			tree[cur] += val;
			if (left == right) return;
			
			int mid = (left + right) / 2;
			update(cur * 2, left, mid, target, val);
			update(cur * 2 + 1, mid + 1, right, target, val);
		}
		
		public int query(int cur, int left, int right, int target) {
			tree[cur] --;
			if (left == right) return left;
			
			int mid = (left + right) / 2;
			if (tree[cur * 2] >= target) return query(cur * 2, left, mid, target);
			else return query(cur * 2 + 1, mid + 1, right, target - tree[cur * 2]);
		}
	}

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		final int MAX = 1000000;
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		SegmentTree segmentTree = new SegmentTree(MAX);
		int A, B, C;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			if (A == 1) {
				sb.append(segmentTree.query(1, 1, MAX, B)).append("\n");
			} else {
				C = Integer.parseInt(st.nextToken());
				segmentTree.update(1, 1, MAX, B, C);
			}
		}
		
		System.out.println(sb.toString());
	}
}