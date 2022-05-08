package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	private static class Node {
		int h, cnt;

		public Node(int h, int cnt) {
			super();
			this.h = h;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		for (int i = 0; i < N; ++i) {
			h[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Node> s = new Stack<Node>();
		long sum = 0;
		for (int i = 0; i < N; ++i) {
			if (s.isEmpty()) {
				s.push(new Node(h[i], 1));
			} else {
				if (s.peek().h > h[i]) {
					sum++;
				} else {
					while (!s.isEmpty() && s.peek().h < h[i]) {
						sum += s.peek().cnt;
						s.pop();
					}
					
					if (s.size() > 0) {
						if (s.peek().h == h[i]) {
							Node tmp = s.pop();
							sum += tmp.cnt;
							if (s.size() > 0) sum++;
							s.push(tmp);
						} else {
							sum++;	
						}
					}
				}
				
				if (s.size() > 0) {
					if (s.peek().h > h[i]) s.push(new Node(h[i], 1));
					else s.peek().cnt++;
				} else s.push(new Node(h[i], 1));
			}
		}
		
		System.out.println(sum);
	}
}