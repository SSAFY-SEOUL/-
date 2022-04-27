import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class State implements Comparable<State> {
		int city, step, sum;

		public State(int city, int sum, int step) {
			this.city = city;
			this.sum = sum;
			this.step = step;
		}

		public int compareTo(State o) {
			return this.sum - o.sum;
		}
	}
	
	private static class Road {
		int to, p;

		public Road(int to, int p) {
			this.to = to;
			this.p = p;
		}
	}
	
	private static int N, M, K;
	private static List<Road>[] roads;
	private static int[][] dist;
	private static int[] p;
	private static final int INF = (1000 * 30000) + (10 * 30000) + 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int S = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		roads = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) roads[i] = new ArrayList<Road>();
		
		int a, b, w;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			roads[a].add(new Road(b, w));
			roads[b].add(new Road(a, w));
		}
		
		p = new int[K + 1];
		for (int i = 0; i < K; ++i) {
			p[i] = Integer.parseInt(br.readLine());
		}
		
		dist = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; ++i) Arrays.fill(dist[i], INF);
		
		getPromisingStates(S, D);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= K; ++i) {
			int min = INF;
			for (int j = 0; j <= N; ++j) {
				min = Math.min(min, dist[D][j]);
				dist[D][j] += (p[i] * j);
			}
			sb.append(min).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static void getPromisingStates(int S, int D) {
		Queue<State> q = new PriorityQueue<State>();
		q.offer(new State(S, 0, 0));
		dist[S][0] = 0;

		State cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur.step >= N) continue;
			if (dist[cur.city][cur.step] < cur.sum) continue;
			
			for (Road r : roads[cur.city]) {
				int nextSum = cur.sum + r.p;
				if (dist[r.to][cur.step + 1] > nextSum) {
					q.offer(new State(r.to, nextSum, cur.step + 1));
					dist[r.to][cur.step + 1] = nextSum;
				}
				
			}	
		}
	}
}