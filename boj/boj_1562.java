import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int mod = 1000000000;
		int all = (1 << 10) - 1;
		int[][][] dp = new int[N + 1][10][1 << 10];
		
		for (int i = 1; i <= 9; ++i) {
			dp[1][i][1 << i] = 1;
		}
		
		for (int i = 2; i <= N; ++i) {
			for (int j = 0; j <= 9; ++j) {
				for (int k = 0; k <= all; ++k) {
					if (j == 0) {
						dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
					} else if (j == 9) {
						dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
					} else {
						dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
						dp[i][j][k | (1 << j)] %= mod;
						dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
					}
					dp[i][j][k | (1 << j)] %= mod;
				}
			}
		}
		
		
		int sum = 0;
		for (int i = 0; i <= 9; ++i) {
			sum = (sum + dp[N][i][all]) % mod;
		}
		System.out.println(sum);
	}
}