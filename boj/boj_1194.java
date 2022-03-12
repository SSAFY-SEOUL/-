import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Step {
		int x, y, step, key;

		public Step(int x, int y, int step, int key) {
			super();
			this.x = x;
			this.y = y;
			this.step = step;
			this.key = key;
		}
		
		public boolean hasKey(int door) {
			return (this.key & (1 << door)) > 0;
		}
	}
	
	public static int N, M;
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };
	public static char[][] map;
	public static int[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new int[N][M];

		int sx = -1;
		int sy = -1;
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
			if (sx == -1) {
				for (int j = 0; j < M; ++j) {
					if (map[i][j] == '0') {
						sx = j;
						sy = i;
						map[i][j] = '.';
					}
				}	
			}
		}

		int noKey = 1 << 7;
		Queue<Step> q = new LinkedList<>();
		q.offer(new Step(sx, sy, 0, noKey));
		
		Step cur;
		int nx, ny, newKey, door;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				nx = cur.x + dx[i];
				ny = cur.y + dy[i];
				if (0 > nx || M <= nx || 0 > ny || N <= ny || (visit[ny][nx] & cur.key) == cur.key || map[ny][nx] == '#') continue;
				
				if (map[ny][nx]== '.') {
					q.offer(new Step(nx, ny, cur.step + 1, cur.key));
					visit[ny][nx] = cur.key;
				} else if ('a' <= map[ny][nx] && map[ny][nx] <= 'f') {
					newKey = cur.key | (1 << map[ny][nx] - 'a');
					q.offer(new Step(nx, ny, cur.step + 1, newKey));
					visit[ny][nx] = newKey;
				} else if ('A' <= map[ny][nx] && map[ny][nx] <= 'F') {
					door = map[ny][nx] - 'A';
					if (cur.hasKey(door)) {
						q.offer(new Step(nx, ny, cur.step + 1, cur.key));
						visit[ny][nx] = cur.key;
					}
				} else if (map[ny][nx] == '1') {
					System.out.println(cur.step + 1);
					System.exit(0);
				}
			}
		}
		
		System.out.println(-1);
	}
}