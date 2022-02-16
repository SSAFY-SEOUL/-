import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static int[][] map;
	public static int N, M, max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				map[i][j] = sc.nextInt();
			}
		}
		
		combination(0, 0, 0);
		
		System.out.println(max);

	}

	public static void combination(int cnt, int startX, int startY) {
		if (cnt == 3) {
			max = Math.max(max, getSafteyArea());
			return;
		}

		for (int i = startY; i < N; ++i) {
			for (int j = startX; j < M; ++j) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					combination(cnt + 1, startX, startY);
					map[i][j] = 0;
				}
			}
		}

	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static int getSafteyArea() {
		int area = 0;
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				tmp[i][j] = map[i][j];
			}
		}
		
		Queue<int[]> virus = new LinkedList<int[]>();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (tmp[i][j] == 2)
					virus.add(new int[] { i, j });

				while (!virus.isEmpty()) {
					int[] v = virus.poll();

					for (int k = 0; k < 4; ++k) {
						int x = v[1] + dx[k];
						int y = v[0] + dy[k];

						if (0 <= x && x < M && 0 <= y && y < N && tmp[y][x] == 0) {
							tmp[y][x] = 2;
							virus.add(new int[] { y, x });
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (tmp[i][j] == 0) area++;
			}
		}
		
		return area;
	}
}