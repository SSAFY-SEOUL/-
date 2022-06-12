package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[] scores;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int left = 0;
		int right = 0;
		
		scores = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			scores[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, scores[i]);
		}
		
		while (left < right) {
			int mid = (left + right) / 2;
			if (left == mid) break;
			
			if (split(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(split(left) ? left : right);

	}
	
	public static boolean split(int v) {
		int section = 1;
		int min = scores[0];
		int max = scores[0];
		
		for (int i = 1; i < N; ++i) {
			min = Math.min(min, scores[i]);
			max = Math.max(max, scores[i]);
			
			if ((max - min) > v) {
				section++;
				min = max = scores[i];
			}
		}
		
		return section <= M ? true : false;
	}
}