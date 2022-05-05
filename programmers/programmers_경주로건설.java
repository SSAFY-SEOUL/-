class Solution {
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[][] map;
	private static int[][][] visit;
	private static int N, answer;
	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, INF = 600 * 25 * 25 + 1;

	public int solution(int[][] board) {
		map = board;
		N = map.length;
		visit = new int[N][N][4];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < 4; ++k) {
					visit[i][j][k] = INF;
				}
			}
		}

		answer = INF;

		dfs(0, 0, RIGHT, 0);
		dfs(0, 0, DOWN, 0);

		return answer;
	}

	public static void dfs(int x, int y, int dir, int cost) {
		visit[y][x][dir] = cost;

		if (x == N - 1 && y == N - 1) {
			answer = Math.min(answer, cost);
			return;
		}

		int nx, ny, ncost;
		for (int d = 0; d < 4; ++d) {
			nx = x + dx[d];
			ny = y + dy[d];

			if (0 <= nx && nx < N && 0 <= ny && ny < N && map[ny][nx] == 0) {
				if (d == dir)
					ncost = cost + 100;
				else
					ncost = cost + 600;

				if (visit[ny][nx][d] > ncost)
					dfs(nx, ny, d, ncost);
			}
		}
	}
}