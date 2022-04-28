package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node implements Comparable<Node> {
		int idx;
		long dist;
		public Node(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			Long l1 = new Long(this.dist);
			Long l2 = new Long(o.dist);
			return l1.compareTo(l2);
		}
	}
	
	private static class Edge {
		int to;
		long w;
		public Edge(int to, long w) {
			this.to = to;
			this.w = w;
		}
	}
	
	private static List<Edge>[] adj;
	private static int N, M, X, Z, P;
	private static int[] Y;
	private static long[][] dist;
	private static final long INF = 100000L * 1000000L * 4 + 1;
	
	public static void main(String[] args) throws IOException {
		processInput();
		System.out.println(solve());
	}
	
	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) adj[i] = new ArrayList<Edge>();
		
		int u, v, w;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			adj[u].add(new Edge(v, w));
			adj[v].add(new Edge(u, w));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		X = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());
		
		P = Integer.parseInt(br.readLine());
		Y = new int[P];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < P; ++i) {
			Y[i] = Integer.parseInt(st.nextToken());
		}

		dist = new long[P + 1][N + 1]; // 101 * 100000 = 10,100,000. 약 1000만 * 8byte = 80,000,000바이트 = 80MB 언저리..
	}
	
	private static long solve() {
		Map<Integer, Integer> nodes = new HashMap<>();
		nodes.put(X, 0);
		for (int i = 1; i < P + 1; ++i) nodes.put(Y[i - 1], i);
		
		dijkstra(X, nodes.get(X));
		for (int i = 0; i < P; ++i) dijkstra(Y[i], nodes.get(Y[i]));
		

		long min = INF;
		for (int i = 0; i < P; ++i) {
			for (int j = 0; j < P; ++j) {
				if (i == j) continue;
				for (int k = 0; k < P; ++k) {
					if (i == k || j == k) continue;

					long a = dist[nodes.get(X)][Y[i]];
					long b = dist[nodes.get(Y[i])][Y[j]];
					long c = dist[nodes.get(Y[j])][Y[k]];
					long d = dist[nodes.get(Y[k])][Z]; 
					
					if (a != INF && b != INF && c != INF && d != INF) {
						min = Math.min(min, a + b + c + d);
					}
				}
			}
		}
		
		return min == INF ? -1 : min;	
	}
	
	private static void dijkstra(int start, int distIdx) {
		Queue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(start, 0));
		
		Arrays.fill(dist[distIdx], INF);
		Node cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			if (cur.dist > dist[distIdx][cur.idx]) continue;
			
			for (Edge e : adj[cur.idx]) {
				if (dist[distIdx][e.to] > cur.dist + e.w) {
					dist[distIdx][e.to] = cur.dist + e.w;
					q.offer(new Node(e.to, dist[distIdx][e.to]));
				}
			}
		}
	}
}