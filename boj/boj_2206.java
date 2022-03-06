import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Move {
		int x, y, step, crushing;

		public Move(int x, int y, int step, int crushing) {
			super();
			this.x = x;
			this.y = y;
			this.step = step;
			this.crushing = crushing;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][][] visit = new int[2][N][M];

		for (int i = 0; i < N; ++i) map[i] = br.readLine().toCharArray();
		for (int i = 0; i < N; ++i) {
			Arrays.fill(visit[0][i], Integer.MAX_VALUE);
			Arrays.fill(visit[1][i], Integer.MAX_VALUE);
		}
		
		Queue<Move> q = new LinkedList<>();
		q.offer(new Move(0, 0, 1, 1));
		visit[1][0][0] = 1;

		int[] dx = new int[] { 0, 0, -1, 1 };
		int[] dy = new int[] { -1, 1, 0, 0 };
		Move cur;
		int nx, ny;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for (int i = 0; i < 4; ++i) {
				nx = cur.x + dx[i];
				ny = cur.y + dy[i];
				if (0 <= nx && nx < M && 0 <= ny && ny < N && visit[cur.crushing][ny][nx] > (cur.step + 1)) {
					if (map[ny][nx] == '1' && cur.crushing > 0) {
						q.offer(new Move(nx, ny, cur.step + 1, cur.crushing - 1));
						visit[cur.crushing][ny][nx] = cur.step + 1;
					} else if (map[ny][nx] == '0') {
						q.offer(new Move(nx, ny, cur.step + 1, cur.crushing));
						visit[cur.crushing][ny][nx] = cur.step + 1;
					}
				}
			}
		}
		
		int min = visit[0][N - 1][M - 1] < visit[1][N - 1][M - 1] ? visit[0][N - 1][M - 1] : visit[1][N - 1][M - 1];
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
}