package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static List<Integer>[] pathBi;
	public static int[][] path;
	public static long[] subTreeSize;
	public static int[] level;
	public static boolean[] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pathBi = new ArrayList[N + 1];
		path = new int[N][2];
		subTreeSize = new long[N + 1];
		level = new int[N + 1];
		used = new boolean[N + 1];
		
		StringTokenizer st;
		int a, b;
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			path[i][0] = a;
			path[i][1] = b;
			
			if (pathBi[a] == null) pathBi[a] = new ArrayList<>();
			pathBi[a].add(b);
			if (pathBi[b] == null) pathBi[b] = new ArrayList<>();
			pathBi[b].add(a);
		}
		
		dfs(1, 0);
		
		long ans = 0;
		long nC2 = (long)N * (N - 1) / 2;
		long sub = 0;
		long subC2 = 0;
		for (int i = 0; i < N -1; ++i) {
			a = path[i][0];
			b = path[i][1];
			
			if (level[b] < level[a]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			sub = N - (subTreeSize[b] + 1);
			subC2 = sub * (sub - 1) / 2;
			ans += (nC2 - subC2);  
		}
		
		System.out.println(ans);
	}
	
	public static long dfs(int cur, int lv) {
		used[cur] = true;
		level[cur] = lv;
		
		long sum = 0;
		for (int next : pathBi[cur]) {
			if (!used[next]) {
				sum += (1 + dfs(next, lv + 1));
			}
		}
		
		return subTreeSize[cur] = sum;
	}
}