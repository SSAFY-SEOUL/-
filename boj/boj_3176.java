import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Edge {
		int to, dist;

		public Edge(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}
	}

	public static List<Edge>[] adjList;
	public static int[] level;
	public static boolean[] used;
	public static int[][] parent, min, max;

	public static int N, K;
	public static final int INF = 1000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = 17;
		adjList = new ArrayList[N + 1];

		level = new int[N + 1];
		used = new boolean[N + 1];

		parent = new int[N + 1][K + 1];
		min = new int[N + 1][K + 1];
		max = new int[N + 1][K + 1];

		for (int i = 1; i <= N; ++i) {
			adjList[i] = new ArrayList<>();
		}

		StringTokenizer st;
		int A, B, C;
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			adjList[A].add(new Edge(B, C));
			adjList[B].add(new Edge(A, C));
		}

		updateLevel(1, 1);
		updateParent();

		int K = Integer.parseInt(br.readLine());
		int D, E;
		StringBuilder sb = new StringBuilder();
		int[] minmax;
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			minmax = getLCA(D, E);
			sb.append(minmax[0]).append(" ").append(minmax[1]).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void updateLevel(int cur, int lv) {
		used[cur] = true;
		level[cur] = lv;

		for (Edge next : adjList[cur]) {
			if (!used[next.to]) {
				updateLevel(next.to, lv + 1);
				parent[next.to][0] = cur;

				min[next.to][0] = next.dist;
				max[next.to][0] = next.dist;
			}
		}
	}

	public static void updateParent() {
		for (int i = 1; i < K; ++i) {
			for (int j = 1; j <= N; ++j) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];

				min[j][i] = Math.min(min[j][i - 1], min[parent[j][i - 1]][i - 1]);
				max[j][i] = Math.max(max[j][i - 1], max[parent[j][i - 1]][i - 1]);
			}
		}
	}

	public static int[] getLCA(int a, int b) {
		if (level[a] < level[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		int minDist = INF;
		int maxDist = 0;

		int diff = level[a] - level[b];
		for (int i = 0; diff > 0; ++i) {
			if ((diff & 1) == 1) {
				minDist = Math.min(minDist, min[a][i]);
				maxDist = Math.max(maxDist, max[a][i]);
				a = parent[a][i];
			}
			diff >>= 1;
		}
		if (a == b) return new int[] { minDist, maxDist };

		for (int i = K; i >= 0; --i) {
			if (parent[a][i] != parent[b][i]) {
				minDist = Math.min(minDist, Math.min(min[a][i], min[b][i]));
				maxDist = Math.max(maxDist, Math.max(max[a][i], max[b][i]));

				a = parent[a][i];
				b = parent[b][i];
			}
		}

		minDist = Math.min(minDist, Math.min(min[a][0], min[b][0]));
		maxDist = Math.max(maxDist, Math.max(max[a][0], max[b][0]));

		return new int[] { minDist, maxDist };
	}

}
