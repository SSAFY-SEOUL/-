import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static class SegmentTree {
		int N;
		int[] H, tree;

		public SegmentTree(int N, int[] H) {
			this.N = N;
			this.H = H;
			this.tree = new int[1 << ((int)Math.ceil(Math.log10(N) / Math.log10(2)) + 1 )];
		}
		
		public int init(int cur, int start, int end) {
			if (start == end) return tree[cur] = start;
			
			int mid = (start + end) / 2;
			int left = init(cur * 2, start, mid);
			int right = init(cur * 2 + 1, mid + 1, end);
			
			return tree[cur] = H[left] < H[right] ? left : right;
		}
		
		public int getMinIdx(int cur, int start, int end, int left, int right) {
			if (left > end || right < start) return -1;
			if (left <= start && end <= right) return tree[cur];
			
			int mid = (start + end) / 2;
			int leftMinIdx = getMinIdx(cur * 2, start, mid, left, right);
			int rightMinIdx = getMinIdx(cur * 2 + 1, mid + 1, end, left, right);
			
			if (leftMinIdx == -1) return rightMinIdx;
			else if (rightMinIdx == -1) return leftMinIdx;
			else return H[leftMinIdx] < H[rightMinIdx] ? leftMinIdx : rightMinIdx;
		}
		
		public int getMaxArea(int left, int right) {
			int minIdx = getMinIdx(1, 1, N, left, right); // 주어진 범위(left ~ right)내에서 가장 높이가 낮은 막대의 인덱스 찾기
			int maxArea = H[minIdx] * (right - left + 1); // 주어진 범위의 최대 넓이(주어진 범위 * 가장 낮은 막대 높이)
			
			// 주어진 범위 내에서 가장 낮은 막대를 기준으로 왼쪽과 오른쪽 더 살펴보기
			if (left < minIdx) maxArea = Math.max(maxArea, getMaxArea(left, minIdx - 1));
			if (right > minIdx) maxArea = Math.max(maxArea, getMaxArea(minIdx + 1, right));
			
			return maxArea;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] H = new int[N + 1];
		for (int i = 1; i <= N; ++i) H[i] = Integer.parseInt(br.readLine());
		
		SegmentTree segmentTree = new SegmentTree(N, H);
		segmentTree.init(1, 1, N);
		System.out.println(segmentTree.getMaxArea(1, N));
	}
}