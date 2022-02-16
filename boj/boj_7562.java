import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Move {
	public int x;
	public int y;
	public int step;

	public Move(int x, int y, int step) {
		this.x = x;
		this.y = y;
		this.step = step;
	}
}

public class Main {
	public static int dx[] = { -2, -2, -1, -1, 1, 1, 2, 2 };
	public static int dy[] = { -1, 1, -2, 2, -2, 2, -1, 1 };
	public static boolean visit[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		Queue<Move> q = new LinkedList<Move>();
		while (t-- > 0) {
			q.clear();
			int n = sc.nextInt();
			int startX = sc.nextInt();
			int startY = sc.nextInt();
			int endX = sc.nextInt();
			int endY = sc.nextInt();

			visit = new boolean[n][n];
			q.add(new Move(startX, startY, 0));
			visit[startY][startX] = true;
			while (!q.isEmpty()) {
				int curX = q.peek().x;
				int curY = q.peek().y;
				int step = q.peek().step;
				q.poll();
				
				if (curX == endX && curY == endY) {
					System.out.println(step);
					break;
				}

				for (int i = 0; i < 8; ++i) {
					int nextX = curX + dx[i];
					int nextY = curY + dy[i];
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visit[nextY][nextX]) {
						q.add(new Move(nextX, nextY, step + 1));
						visit[nextY][nextX] = true;
					}
				}
			}

		}

	}
}
