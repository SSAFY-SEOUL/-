package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N, C;
	private static int[] x;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		x = new int[N];
		for (int i = 0; i < N; ++i) x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);
		
		int left = 0;
		int right = 1000000001;
		while (left < right) {
			int mid = (left + right) / 2;
			if (mid == left || mid == right) break;
			if (check(mid)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(check(right) ? right : left);
	}
	
	private static boolean check(int dist) {
		int c = C - 1;
		int cur = x[0];
		for (int i = 1; i < N; ++i) {
			if (x[i] - cur >= dist) {
				cur = x[i];
				c--;
			}
		}
		
		return c <= 0 ? true : false;
	}
}