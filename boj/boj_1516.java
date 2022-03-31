package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_게임개발 {

	public static List<Integer>[] link;
	public static int[] t, indegree, sorted;
	public static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		t = new int[N + 1];
		indegree = new int[N + 1];
		visit = new boolean[N + 1];
		sorted = new int[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i)
			link[i] = new ArrayList<Integer>();

		String[] tok = null;
		for (int i = 1; i <= N; ++i) {
			tok = br.readLine().split(" ");
			t[i] = Integer.parseInt(tok[0]);

			for (int j = 1, size = tok.length - 1; j < size; ++j) {
				int to = Integer.parseInt(tok[j]);
				link[to].add(i);
				indegree[i]++;
			}
		}
		int cnt = 1;

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; ++i) {
			if (indegree[i] == 0) {
				q.offer(i);
				visit[i] = true;
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			sorted[cnt++] = cur;

			for (int to : link[cur]) {
				indegree[to]--;
			}

			for (int i = 1; i <= N; ++i) {
				if (!visit[i] && indegree[i] == 0) {
					q.offer(i);
					visit[i] = true;
				}
			}
		}

		int[] minTime = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			int node = sorted[i];
			minTime[node] += t[node];
			for (int to : link[node]) {
				if (minTime[to] < minTime[node]) minTime[to] = minTime[node];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i<=N; ++i) {
			sb.append(minTime[i]);
			if (i != N) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}