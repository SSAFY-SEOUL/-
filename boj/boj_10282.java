package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static final int INF = 0x7fffffff;

	public static List<int[]>[] adj;
	public static boolean[] visit;
	public static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj = new ArrayList[n + 1];
			for (int i = 1; i <= n; ++i)
				adj[i] = new ArrayList<int[]>();

			for (int i = 0; i < d; ++i) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				adj[b].add(new int[] { a, s });
			}

			visit = new boolean[n + 1];
			distance = new int[n + 1];
			Arrays.fill(distance, INF);
			distance[c] = 0;

			PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>((int[] a, int[] b) -> {
				return a[1] - b[1];
			});

			pQueue.offer(new int[] { c, 0 });

			while (!pQueue.isEmpty()) {
				int[] current = pQueue.poll();
				
				if (visit[current[0]]) continue;
				else visit[current[0]] = true;

				int[] node;
				for (int j = 0, size = adj[current[0]].size(); j < size; ++j) {
					node = adj[current[0]].get(j);
					if (node == null)
						continue;
					if (!visit[node[0]] && distance[node[0]] > distance[current[0]] + node[1]) {
						distance[node[0]] = distance[current[0]] + node[1];
						pQueue.offer(new int[] {node[0], distance[node[0]]});
					}
				}
			}

			int maxDistance = 0;
			int infectedComputer = 0;
			for (int i = 1; i <= n; ++i) {
				if (distance[i] != Integer.MAX_VALUE) {
					infectedComputer++;
					if (maxDistance < distance[i]) {
						maxDistance = Math.max(maxDistance, distance[i]);
					}
				}
			}

			sb.append(infectedComputer).append(" ").append(maxDistance).append("\n");
		}

		System.out.println(sb.toString());
	}

}