import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] map, memo;
	public static boolean[][] visit;
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };
	public static int ans, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		memo = new int[M][N];
		visit = new boolean[M][N];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) return 1;
		
		if (visit[y][x]) return memo[y][x];
		else
		{
			visit[y][x] = true;
			int h = map[y][x];
			int nx, ny;
			for (int i = 0; i < 4; ++i) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < M && h > map[ny][nx]) {
					memo[y][x] += dfs(nx, ny);
				}
			}
		}
		
		return memo[y][x];
	}
}