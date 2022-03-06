import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Item {
		int w, v;

		public Item(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Item[] item = new Item[N + 1];
		int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			item[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int w = 1; w <= K; ++w) {
				if (w >= item[i].w) {
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - item[i].w] + item[i].v);
				} else {
					dp[i][w] = dp[i - 1][w];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}