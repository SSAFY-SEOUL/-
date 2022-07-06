import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class Node implements Comparable<Node> {
		int n, distance;

		public Node(int n, int distance) {
			super();
			this.n = n;
			this.distance = distance;
		}
		
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int[][] adjMatrix;
	private static int N, M, X;
	private static final int INF = 100 * 1000 + 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N + 1][N + 1];
		int S, E, T;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			adjMatrix[S][E] = T;
		}
		
		int[] go = new int[N + 1];
		int[] come = new int[N + 1];
		
		for (int i = 1; i <= N; ++i) {
			if (i == X) {
				getShortestPath(i, -1, come);
			}
			go[i] = getShortestPath(i, X, new int[N + 1]);
		}
		
		int maxTime = 0;
		for (int i = 1; i <= N; ++i) {
			if (maxTime < go[i] + come[i]) {
				maxTime = go[i] + come[i];
			}
		}
		
		System.out.println(maxTime);
	}
	
	private static int getShortestPath(int from, int to, int[] distance) {
		boolean[] visit = new boolean[N + 1];
		
		for (int i = 1; i <= N; ++i) distance[i] = INF;
		distance[from] = 0;
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(from, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.n == to) return cur.distance;
			
			if (visit[cur.n]) continue;
			visit[cur.n] = true;
			
			for (int i = 1; i <= N; ++i) {
				if (adjMatrix[cur.n][i] > 0 && distance[cur.n] + adjMatrix[cur.n][i] < distance[i]) {
					distance[i] = distance[cur.n] + adjMatrix[cur.n][i];
					pq.offer(new Node(i, distance[i]));
				}
			}
		}
		
		return -1;
	}
}