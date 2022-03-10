import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, S;
	public static int[] drawings;
	public static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		drawings = new int[20000001];
		
		int curH, curC, minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			curH = Integer.parseInt(st.nextToken());
			curC = Integer.parseInt(st.nextToken());
			
			if (curH > maxH) maxH = curH;
			if (curH < minH) minH = curH;
			if (drawings[curH] < curC) drawings[curH] = curC;
		}
		
		dp = new int[maxH + 1];
		dp[minH] = drawings[minH];
		
		for (int h = minH + 1; h <= maxH; ++h) {
			dp[h] = Math.max(dp[h - 1], dp[h - S] + drawings[h]);
		}
		System.out.println(dp[maxH]);
	}
}