import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class SegmentTree {
		int size;
		long[] tree;
		
		public SegmentTree(int N) {
			tree = new long[4 * N];
		}
		
		public long init(int[] arr, int cur, int start, int end) {
			if (start == end) return tree[cur] = arr[start];
			
			int mid = (start + end) / 2;
			return tree[cur] = init(arr, cur * 2, start, mid) + init(arr, cur * 2 + 1, mid + 1, end);
		}
		
		public long getSum(int cur, int start, int end, int left, int right) {
			if (left > end || right < start) {
				return 0;
			}
			if (left <= start && end <= right) {
				return tree[cur];
			}
			
			int mid = (start + end) / 2;
			return getSum(cur * 2, start, mid, left, right) + getSum(cur * 2 + 1, mid + 1, end, left, right);
		}
		
		public void update(int cur, int start, int end, int target, long diff) {
			if (target < start || end < target) return;
			tree[cur] = tree[cur] + diff;
			if (start != end) {
				int mid = (start + end) / 2;
				update(cur * 2, start, mid, target, diff);
				update(cur * 2 + 1, mid + 1, end, target, diff);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= N; ++i) arr[i] = Integer.parseInt(st.nextToken());

		SegmentTree t = new SegmentTree(N);
		t.init(arr, 1, 1, N);

		StringBuilder sb = new StringBuilder();
		int x, y, a, b;
		for (int i = 0; i < Q; ++i) {
			st = new StringTokenizer(br.readLine()," ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if (x > y) {
				int tmp = y;
				y = x;
				x = tmp;
			}
			sb.append(t.getSum(1, 1, N, x, y)).append("\n");
			t.update(1, 1, N, a, (long)b - arr[a]);
			arr[a] = b;
		}
		
		System.out.println(sb.toString());
	}
}