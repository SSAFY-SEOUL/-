import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Virus {
		int x;
		int y;

		public Virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static ArrayList<Virus> v = new ArrayList<Virus>();
	public static int safetyArea;
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
				if (map[i][j] == 2) {
					v.add(new Virus(j, i));
				} else if (map[i][j] == 0) {
					safetyArea++;
				}
			}
		}
		max = Integer.MIN_VALUE;
		combination(0, 0, 0);
		System.out.println(max);

	}

	public static void combination(int cnt, int startX, int startY) {
		if (cnt == 3) {
			max = Math.max(max, getSafteyArea());
			return;
		}

		int _startX = startX;
		int _startY = startY;

		for (int i = _startY; i < N; ++i) {
			for (int j = _startX; j < M; ++j) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					combination(cnt + 1, j + 1, i);
					map[i][j] = 0;
				}
			}
			_startX = 0;
		}

	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static int getSafteyArea() {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				tmp[i][j] = map[i][j];
			}
		}

		Queue<int[]> virus = new LinkedList<int[]>();
		for (Virus a : v)
			virus.add(new int[] { a.y, a.x });
		
		int infected = 0;
		while (!virus.isEmpty()) {
			int[] v = virus.poll();

			for (int k = 0; k < 4; ++k) {
				int x = v[1] + dx[k];
				int y = v[0] + dy[k];

				if (0 <= x && x < M && 0 <= y && y < N && tmp[y][x] == 0) {
					tmp[y][x] = 2;
					infected++;
					virus.add(new int[] { y, x });
				}
			}
		}

		return safetyArea - infected - 3;
	}
}