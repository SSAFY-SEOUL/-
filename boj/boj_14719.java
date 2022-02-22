package com.ssafy.swea;

import java.util.Scanner;

public class Solution {
	
	public static int[] b;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt();
		int w = sc.nextInt();
		b = new int[w];
		
		int maxW = 0;
		for (int i = 0; i < w; ++i) {
			b[i] = sc.nextInt();
			if (b[i] > maxW) maxW = b[i];
		}
		
		int sum = 0;
		for (int i = 1; i < w - 1; ++i) {
			int left = 0;
			for (int j = i - 1; j >= 0; --j) {
				if (b[j] > b[i] && b[j] > left) left = b[j];
			}
			
			int right = 0;
			for (int j = i + 1; j < w; ++j) {
				if (b[j] > b[i] && b[j] > right) right = b[j];
			}
			
			int min = right < left ? right : left;
			if (min > 0) sum += (min - b[i]);
		}
		
		System.out.println(sum);
	}
}