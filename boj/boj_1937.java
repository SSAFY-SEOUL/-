import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] forest;
	public static int[][] memo;
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };
	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		memo = new int[N][N];
		forest = new int[N][N];
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0, cur = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				cur = dfs(j, i, 1);
				if (max < cur) max = cur;
			}
		}
		
		System.out.println(max);
	}

	private static int dfs(int x, int y, int depth) {
		if (memo[y][x] > 0) return memo[y][x];
		
		int move = 0;
		int nx, ny;
		for (int dir = 0; dir < 4; ++dir) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (0 <= nx && nx < N && 0 <= ny && ny < N && forest[ny][nx] > forest[y][x]) {
				move++;
				memo[y][x] = Math.max(dfs(nx, ny, depth + 1) + 1, memo[y][x]);
			}
		}
		
		if (move == 0) return memo[y][x] = 1;
		return memo[y][x];
	}
}
