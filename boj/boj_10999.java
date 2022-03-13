import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class SegmentTree {
		int size;
		long[] tree;
		long[] lazy;
		
		public SegmentTree(int N) {
			tree = new long[4 * N];
			lazy = new long[4 * N];
		}
		
		public long init(long[] arr, int cur, int start, int end) {
			if (start == end) return tree[cur] = arr[start];
			
			int mid = (start + end) / 2;
			return tree[cur] = init(arr, cur * 2, start, mid) + init(arr, cur * 2 + 1, mid + 1, end);
		}
		
		public long getSum(int cur, int start, int end, int left, int right) {
			lazyUpdate(cur, start, end);
			
			if (left > end || right < start) return 0;
			if (left <= start && end <= right) return tree[cur];
			
			int mid = (start + end) / 2;
			return getSum(cur * 2, start, mid, left, right) + getSum(cur * 2 + 1, mid + 1, end, left, right);
		}
		
		public void update(int cur, int start, int end, int left, int right, long value) {
			lazyUpdate(cur, start, end);
			if (left > end || right < start) return;
			if (left <= start && end <= right) {
				tree[cur] += (end - start + 1) * value;
				if (start != end) {
					lazy[cur * 2] += value;
					lazy[cur * 2 + 1] += value;
				}
				return;
			}
			
			int mid = (start + end) / 2;
			update(cur * 2, start, mid, left, right, value);
			update(cur * 2 + 1, mid + 1, end, left, right, value);
			tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
		}
		
		public void lazyUpdate(int cur, int start, int end) {
			if (lazy[cur] != 0) {
				tree[cur] += (end - start + 1) * lazy[cur];
				if (start != end) {
					lazy[cur * 2] += lazy[cur];
					lazy[cur * 2 + 1] += lazy[cur];
				}
				
				lazy[cur] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] arr = new long[N + 1];
		
		for (int i = 1; i <= N; ++i) {
			arr[i] = Long.parseLong(br.readLine());
		}

		SegmentTree t = new SegmentTree(N);
		t.init(arr, 1, 1, N);

		StringBuilder sb = new StringBuilder();
		int a, b, c;
		long d;
		for (int i = 0; i < M + K; ++i) {
			st = new StringTokenizer(br.readLine()," ");
			a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				t.update(1, 1, N, b, c, d);
			} else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				sb.append(t.getSum(1, 1, N, b, c)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}