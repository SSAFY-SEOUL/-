import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int[][] graph;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];
		String[] tok;
		
		for (int i = 1; i <= N; ++i) {
			tok = br.readLine().split(" ");
			int edge = 0;
			for (int j = 1; j <= N; ++j) {
				graph[i][j] = Integer.parseInt(tok[j - 1]);
				edge += graph[i][j];
			}
			if (edge % 2 == 1) {
				System.out.print(-1);
				return;
			}
		}		
		
		dfs(1, N);
		System.out.print(sb.toString());
		
	}
	
	public static void dfs(int current, int N) {
		
		for (int i = 1; i <= N; ++i) {
			while (graph[current][i] > 0) {
				graph[current][i]--;
				graph[i][current]--;
				dfs(i, N);
			}
		}
		
		sb.append(current).append(" ");
	}
}