package com.ssafy.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n, m, k;
	public static List<int[]>[] adj;
	public static PriorityQueue<Integer>[] dist;
	public static int[] visit;

	public static void main(String[] args) throws IOException {
		processInput();
		doDijkstra();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; ++i) {
			if (dist[i].size() < k) sb.append(-1).append("\n");
			else {
				for (int j = 0; j < k - 1; ++j) {
					dist[i].poll();
				}
				sb.append(dist[i].poll()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n + 1];
		dist = new PriorityQueue[n + 1];
		visit = new int[n + 1];

		for (int i = 1; i <= n; ++i) {
			dist[i] = new PriorityQueue<>((Integer a, Integer b) -> a - b);
			adj[i] = new ArrayList<>();
		}

		int a, b, c;
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adj[a].add(new int[] {b, c});
		}
	}

	public static void doDijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[]b) -> a[1] - b[1]);
		pq.add(new int[] {1, 0});
		dist[1].add(0);
		
		int[] cur;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visit[cur[0]] > k) continue;
			visit[cur[0]]++;
			for (int[] link : adj[cur[0]]) {
				dist[link[0]].add(cur[1] + link[1]);
				pq.add(new int[] {link[0], cur[1] + link[1]});
			}
		}
	}
}