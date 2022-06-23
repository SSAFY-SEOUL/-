import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static class DisjointSet {
		int[] parents;

		public DisjointSet(int N) {
			this.parents = new int[N];
			for (int i = 0; i < N; ++i) parents[i] = i;
		}
		
		public int findSet(int a) {
			if (a == this.parents[a]) return a;
			return parents[a] = findSet(parents[a]);
		}
		
		public boolean union(int a, int b) {
			int aRoot = findSet(a);
			int bRoot = findSet(b);
			if (aRoot == bRoot) return false;
			
			parents[bRoot] = aRoot;
			return true;
		}
		
	}

	private static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		DisjointSet ds = new DisjointSet(N);
		StringTokenizer st;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					ds.union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int set = -1;
		for (int i = 0; i < M; ++i) {
			int dest = Integer.parseInt(st.nextToken()) - 1;
			if (set == -1) {
				set = ds.findSet(dest);
			} else if (set != ds.findSet(dest)) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");
		
	}

}