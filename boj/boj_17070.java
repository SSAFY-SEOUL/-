import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int[][] home;
	public static int N, answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		home = new int[N + 1][N + 1];
		String[] tok;
		for (int i = 0; i < N; ++i) {
			tok = br.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				home[i][j] = Integer.parseInt(tok[j]);
			}
		}
		
		for (int i = 0; i <= N; ++i) {
			home[i][N] = 1;
			home[N][i] = 1;
		}
		
		answer = 0;
		dfs(1, 0, 0);
		
		System.out.println(answer);
	}
	
	public static void dfs(int x, int y, int dir) { // dir 0: 우, 1: 우하, 2: 하
		if (x == N - 1 && y == N - 1) {
			answer++;
			return;
		}
		
		switch (dir) {
		case 0:
			if (home[y][x + 1] == 0) dfs(x + 1, y, 0);
			if (home[y][x + 1] == 0 && home[y + 1][x + 1] == 0 && home[y + 1][x] == 0) dfs(x + 1, y + 1, 1);
			break;
		case 1:
			if (home[y][x + 1] == 0) dfs(x + 1, y, 0);
			if (home[y][x + 1] == 0 && home[y + 1][x + 1] == 0 && home[y + 1][x] == 0) dfs(x + 1, y + 1, 1);
			if (home[y + 1][x] == 0) dfs(x, y + 1, 2);
			break;
		case 2:
			if (home[y][x + 1] == 0 && home[y + 1][x + 1] == 0 && home[y + 1][x] == 0) dfs(x + 1, y + 1, 1);
			if (home[y + 1][x] == 0) dfs(x, y + 1, 2);
			break;
		}
	}
}
