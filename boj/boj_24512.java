import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] map;
	public static boolean[] visited;
	public static int[] currentPath, bestPath;
	public static int N, M, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		bestPath = new int[N];
		currentPath = new int[N];

		int u, v, c;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken()) - 1;
			v = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			map[u][v] = c;
		}

		C = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			visited[i] = true;
			currentPath[0] = i;
			dfs(1, i, i, 0);
			visited[i] = false;
		}

		if (C == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(C).append("\n");
			for (int i = 0; i < N; ++i) {
				sb.append(bestPath[i] + 1).append(" ");
			}
			
			sb.setLength(sb.length() - 1);
			System.out.print(sb.toString());
		}
	}

	public static void dfs(int cnt, int start, int current, int maxCost) {
		if (maxCost > C) return;
		if (cnt == N) {
			if (map[current][start] > 0) {
				int realMaxCost = maxCost > map[current][start] ? maxCost : map[current][start];
				if (realMaxCost < C) {
					C = realMaxCost;
					bestPath = currentPath.clone();
				}
			}
			return;
		}

		for (int i = 0; i < N; ++i) {
			if (!visited[i] && map[current][i] > 0) {
				visited[i] = true;
				currentPath[cnt] = i;
				dfs(cnt + 1, start, i, map[current][i] > maxCost ? map[current][i] : maxCost);
				visited[i] = false;
			}
		}
	}
}