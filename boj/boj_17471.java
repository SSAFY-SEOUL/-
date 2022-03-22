import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static int N, ans, all;
	public static int[] population;
	public static boolean[][] adj;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		
		int pSum = 0;
		population = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			population[i] = sc.nextInt();
			pSum += population[i];
		}
		
		int len, tmp;
		adj = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; ++i) {
			len = sc.nextInt();
			for (int j = 0; j < len; ++j) {
				tmp = sc.nextInt();
				adj[i][tmp] = true;
			}
		}
		
		all = (1 << (N + 1)) - 1;
		ans = 1001;
		combination(0, 1, 0, 0, pSum);
		System.out.println(ans == 1001 ? -1 : ans);
	}
	
	public static void combination(int cnt, int start, int visit, int a, int b) {
		
		if (0 < cnt && cnt < N) {

			boolean aChecked = false;
			boolean bChecked = false;
			boolean aConnected = false;
			boolean bConnected = false; 
			for (int i = 1; i <= N; ++i) {
				if (!aChecked && (visit & (1 << i)) != 0) {
					aConnected = isConnected(i, visit, cnt);
					aChecked = true;
				} else if (!bChecked && (visit & (1 << i)) == 0) {
					bConnected = isConnected(i, (all & ~visit), N - cnt);
					bChecked = true;
				}
				
				if (aChecked && bChecked) break;
			}
			
			if (aConnected && bConnected) {
				int diff = Math.abs(a - b);
				ans = Math.min(ans, diff);
			}
		}
		
		for (int i = start; i <= N; ++i) {
			combination(cnt + 1, i + 1, visit | 1 << i, a + population[i], b - population[i]);
		}
	}
	
	
	
	public static boolean isConnected(int start, int visit, int targetCnt) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		int cnt = 1;
		int now = 1 << start;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; ++i) {
				if (adj[cur][i] && (visit & (1 << i)) != 0 && (now & (1 << i)) == 0) {
					q.offer(i);
					now |= (1 << i);
					cnt++;
				}
			}			
			
		}
		
		if (cnt == targetCnt) return true;
		else return false;
	}
}