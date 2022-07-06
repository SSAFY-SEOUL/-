import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int TC, N, M, W;
	private static final int INF = 2500 * 10000 + 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; ++tc) {
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 M = Integer.parseInt(st.nextToken());
			 W = Integer.parseInt(st.nextToken());
			 
			 int[][] adjMatrix = new int[N + 1][N + 1];
			 for (int i = 1; i <= N; ++i) Arrays.fill(adjMatrix[i], INF);
			 for (int i = 1; i <= N; ++i) adjMatrix[0][i] = 0;
			 
			 int S, E, T;
			 for (int i = 0; i < M; ++i) {
				 st = new StringTokenizer(br.readLine());
				 S = Integer.parseInt(st.nextToken());
				 E = Integer.parseInt(st.nextToken());
				 T = Integer.parseInt(st.nextToken());


				 adjMatrix[S][E] = Math.min(adjMatrix[S][E], T);
				 adjMatrix[E][S] = Math.min(adjMatrix[E][S], T);
			 }
			 
			 for (int i = 0; i < W; ++i) {
				 st = new StringTokenizer(br.readLine());
				 S = Integer.parseInt(st.nextToken());
				 E = Integer.parseInt(st.nextToken());
				 T = Integer.parseInt(st.nextToken());

				 adjMatrix[S][E] = Math.min(adjMatrix[S][E], -T);
			 }
			 
			 int[] distance = new int[N + 1];
			 Arrays.fill(distance, INF);
			 distance[0] = 0;
			 
			 for (int edge = 1; edge < N; ++edge) {
				 for (int from = 0; from <= N; ++from) {
					 if (distance[from] != INF) {
						 for (int to = 1; to <= N; ++to) {
							 if (distance[from] + adjMatrix[from][to] < distance[to]) {
								 distance[to] = distance[from] + adjMatrix[from][to];
							 }
						 }
					 }
				 }
			 }
			 
			 boolean possible = false;
			 for (int from = 0; from <= N && !possible; ++from) {
				 if (distance[from] != INF) {
					 for (int to = 1; to <= N && !possible; ++to) {
						 if (distance[from] + adjMatrix[from][to] < distance[to]) {
							 possible = true;
						 }
					 }
				 }
			 }
			 
			 System.out.println(possible ? "YES" : "NO");
		}
	}
}