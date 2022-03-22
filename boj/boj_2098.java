import java.util.Scanner;

public class Main {
	public static int[][] memo;
	public static int[][] W;
	public static int N, ans, all, start, max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		W = new int[N][N];
		memo = new int[N][1 << N];
		all = (1 << N) - 1;
		max = 1000000 * 16 + 1;
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				W[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <= all; ++j) {
				memo[i][j] = max;
			}
		}
		

		start = 0;
		System.out.println(dfs(start, 1));
	}
	
	public static int dfs(int cur, int visit) {
		if (visit == all) {
			if (W[cur][start] > 0) return memo[cur][visit] = W[cur][start];
			return max;
		}
		
		if (memo[cur][visit] != max) return memo[cur][visit];
		
		for (int dest = 0; dest < N; ++dest) {
			if (W[cur][dest] > 0 && (visit & (1 << dest)) == 0) {
				memo[cur][visit] = Math.min(memo[cur][visit], dfs(dest,  visit | (1 << dest)) + W[cur][dest]);
			}
		}
		
		return memo[cur][visit];
	}
}