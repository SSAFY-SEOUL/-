package com.ssafy.boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static ArrayList<Integer> nums = new ArrayList<Integer>();
	public static int N, M, realN;
	public static StringBuilder sb = new StringBuilder();
	public static int[] seq;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		seq = new int[M];
		
		Set<Integer> check = new HashSet<Integer>();
		for (int i = 0; i < N; ++i) {
			int val = sc.nextInt();
			if (!check.contains(val)) {
				nums.add(val);
				check.add(val);
			}
		}
		Collections.sort(nums);
		realN = nums.size();
		
		permutation(0, 0);
		System.out.print(sb.toString());
	}
	
	public static void permutation(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < cnt; ++i) {
				sb.append(seq[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < realN; ++i) {
			seq[cnt] = nums.get(i);
			permutation(cnt + 1, i);
		}
	}
}