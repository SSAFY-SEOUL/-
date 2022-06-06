import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static class Cell {
		int status, rd;

		public Cell(int status, int rd) {
			super();
			this.status = status;
			this.rd = rd;
		}
	}
	
	private static Cell[][] board;
	private static boolean[] rd;
	private static int N, maxOddBishop, maxEvenBishop, D;
	private static final int NO = 0, OK = 1, BISHOP = 2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		D = (2 * N) - 1;
		board = new Cell[N][N];
		rd = new boolean[D];
		maxOddBishop = 0;
		maxEvenBishop = 0;
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				board[i][j] = new Cell(Integer.parseInt(st.nextToken()), N + i - j - 1);
			}
		}
		
		dfs(0, 0);
		dfs(1, 0);
		System.out.println(maxOddBishop + maxEvenBishop);
	}
	
	private static void dfs(int d, int bishop) {
		if (d > D - 1) {
			if (d % 2 == 0) maxEvenBishop = Math.max(maxEvenBishop, bishop);
			else maxOddBishop = Math.max(maxOddBishop, bishop);
			return;
		}
		
		int x, y;
		if (d <= N - 1) {
			x = d;
			y = 0;
		} else {
			x = N - 1;
			y = d - N + 1;
		}
		
		Cell cur;
		while (x >= 0 && y < N) {
			cur = board[y][x];
			if (cur.status == OK && !rd[cur.rd]) {
				rd[cur.rd] = true;
				dfs(d + 2, bishop + 1);
				rd[cur.rd] = false;
			} else dfs(d + 2, bishop);
			
			x--;
			y++;
		}
	}
}