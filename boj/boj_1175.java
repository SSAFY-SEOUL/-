import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int x, y, prevDir, step;

		public Node(int x, int y, int prevDir, int step) {
			super();
			this.x = x;
			this.y = y;
			this.prevDir = prevDir;
			this.step = step;
		}
	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };
	public static char[][] map;
	public static boolean[][][] visit;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][4];

		int sx = -1;
		int sy = -1;
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();

			if (sx == -1) {
				for (int j = 0; j < M; ++j) {
					if (map[i][j] == 'S') {
						sx = j;
						sy = i;
						map[i][j] = '.';
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		ArrayList<Node> first = deliver(sx, sy, -1);
		ArrayList<Node> second;
		for (Node f : first) {
			map[f.y][f.x] = '.';
			second = deliver(f.x, f.y, f.prevDir);
			for (Node s: second) {
				if ((f.step + s.step) < min) min = f.step + s.step;
			}
			map[f.y][f.x] = 'C';
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
	}

	public static ArrayList<Node> deliver(int sx, int sy, int sd) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				Arrays.fill(visit[i][j], false);	
			}
		}
		
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(sx, sy, sd, 0));
		ArrayList<Node> ans = new ArrayList<>();

		Node cur;
		int nx, ny;
		while (!q.isEmpty()) {
			cur = q.poll();

			for (int dir = 0; dir < 4; ++dir) {
				if (dir == cur.prevDir)
					continue;
				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];

				if (0 <= nx && nx < M && 0 <= ny && ny < N && !visit[ny][nx][dir]) {
					if (map[ny][nx] == '.') {
						q.offer(new Node(nx, ny, dir, cur.step + 1));
						visit[ny][nx][dir] = true;
					} else if (map[ny][nx] == 'C') {
						ans.add(new Node(nx, ny, dir, cur.step + 1));
					}
				}
			}
		}

		return ans;
	}
}