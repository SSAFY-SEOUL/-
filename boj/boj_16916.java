package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		int sLength = S.length;
		int pLength = P.length;

		int[] pi = new int[pLength];
		for (int i = 1, j = 0; i < pLength; ++i) {
			while (j > 0 && P[i] != P[j]) 
				j = pi [j - 1];
			
			if (P[i] == P[j]) pi [i] = ++j;
			else pi [i] = 0;
		}
		
		
		int ans = 0;
		for (int i = 0, j = 0; i < sLength; ++i) {
			
			while (j > 0 && S[i] != P[j])
				j = pi[j - 1];
			
			if (S[i] == P[j]) {
				
				if (j == pLength - 1) {
					ans = 1;
					break;
				} else {
					++j;
				}

			}
		}
		
		System.out.println(ans);
		
	}
}