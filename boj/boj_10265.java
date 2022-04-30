package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	private static class Component {
		public int v, minV, maxV;
		public List<Component> children;

		public Component() {
			this.v = 0;
			children = new ArrayList<>();
		}
	}

	private static int n, k;
	private static int[] x;
	private static List<Integer>[] rx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		x = new int[n + 1];

		rx = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i) rx[i] = new ArrayList<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1, to; i <= n; ++i) {
			to = Integer.parseInt(st.nextToken());
			x[i] = to;
			rx[to].add(i);
		}

		Component root = makeSCCTree();

		for (Component c : root.children) {
			c.minV = c.v;
			c.maxV = getMaxV(c);
		}

		int size = root.children.size();
		int[][] dp = new int[size + 1][k + 1];
		for (int i = 1; i <= size; ++i) {
			Component cur = root.children.get(i - 1);
			for (int j = 0; j <= k; ++j) {
				if (cur.minV > j) {
					dp[i][j] = dp[i - 1][j];
					continue;
				}
				
				for (int value = cur.minV; value <= cur.maxV; ++value) {
					if (value > j) break;
					
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - value] + value);
				}

				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
			}
		}

		System.out.println(dp[size][k]);
	}

	// Kosaraju
	public static Component makeSCCTree() {
		boolean[] visit = new boolean[n + 1];
		int[] componentIdx = new int[n + 1];
		Stack<Integer> st = new Stack<>();
		int sccSize = 0;

		// inDFS
		for (int i = 1; i <= n; ++i)
			if (!visit[i])
				inDFS(st, visit, i);

		// outDFS
		Arrays.fill(visit, false);
		while (!st.isEmpty()) {
			int cur = st.pop();
			if (visit[cur])
				continue;

			sccSize++;
			outDFS(visit, componentIdx, cur, sccSize);
		}

		// makeSCC
		boolean[] childCheck = new boolean[sccSize + 1];
		Component[] components = new Component[sccSize + 1];
		for (int i = 1; i <= sccSize; ++i)
			components[i] = new Component();

		for (int i = 1; i <= n; ++i) {
			int childIdx = componentIdx[i];
			int parentIdx = componentIdx[x[i]];
			components[childIdx].v++;

			if (childIdx != parentIdx) {
				components[parentIdx].children.add(components[childIdx]);
				childCheck[childIdx] = true;
			}
		}

		Component root = new Component();
		for (int i = 1; i <= sccSize; ++i)
			if (!childCheck[i])
				root.children.add(components[i]);

		return root;
	}

	private static int getMaxV(Component cur) {
		int sum = cur.v;

		for (Component child : cur.children) {
			sum += getMaxV(child);
		}

		return sum;
	}

	private static void inDFS(Stack<Integer> st, boolean[] visit, int n) {
		visit[n] = true;

		if (!visit[x[n]])
			inDFS(st, visit, x[n]);

		st.push(n);
	}

	private static void outDFS(boolean[] visit, int[] componentIdx, int n, int ci) {
		visit[n] = true;
		componentIdx[n] = ci;

		for (int next : rx[n]) {
			if (!visit[next])
				outDFS(visit, componentIdx, next, ci);
		}

	}
}