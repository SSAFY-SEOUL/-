package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static class Node {
		private Node[] next;
		private int childrenCnt;
		private boolean isTerminal;

		public Node() {
			super();
			next = new Node[26];
			childrenCnt = 0;
		}
		
		public void insertString(String str, int at) {
			if (str.length() == at) {
				isTerminal = true;
				return;
			}
			
			int idx = str.charAt(at) - 'a';
			if (next[idx] == null) {
				next[idx] = new Node();
				childrenCnt++;
			}
			
			next[idx].insertString(str, at + 1);
		}
		
		public int foundWord(String word, int at, int auto) {
			if (word.length() == at) {
				return word.length() - auto;
			}
			
			int idx = word.charAt(at) - 'a';
			if (at > 0 && childrenCnt == 1 && !isTerminal) {
				return next[idx].foundWord(word, at + 1, auto + 1);
			} else {
				return next[idx].foundWord(word, at + 1, auto);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		String size;
		while ((size = br.readLine()) != null) {
			int N = Integer.parseInt(size);
			Node root = new Node();
			String[] str = new String[N];
			
			for (int i = 0; i < N; ++i) {
				str[i] = br.readLine();
				root.insertString(str[i], 0);
			}
			
			long sum = 0;
			for (int i = 0; i < N; ++i) {
				sum += root.foundWord(str[i], 0, 0);
			}
			sb.append(String.format("%.2f\n", (double)sum / N));
		}
		
		System.out.println(sb.toString());
	}

}