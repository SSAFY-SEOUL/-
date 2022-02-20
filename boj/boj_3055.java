package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static class Node {
		int x;
		int y;
		char c;
		int t;

		public Node(int x, int y, char c, int t) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.t = t;
		}
	}

	public static char[][] map;
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<Node> q = new LinkedList<Node>();
		String[] tok = br.readLine().split(" ");
		int R = Integer.parseInt(tok[0]);
		int C = Integer.parseInt(tok[1]);
		map = new char[R][C];

		int hx = 0;
		int hy = 0;
		for (int i = 0; i < R; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == '*')
					q.add(new Node(j, i, '*', 0));
				else if (map[i][j] == 'S') {
					hx = j;
					hy = i;
				}
			}
		}
		q.add(new Node(hx, hy, 'S', 0));

		int currentT = 0;
		while (!q.isEmpty()) {
			int hMove = 0;
			while (!q.isEmpty() && q.peek().t == currentT) {
				Node n = q.poll();

				for (int i = 0; i < 4; ++i) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (0 <= nx && nx < C && 0 <= ny && ny < R) {
						if (map[ny][nx] == '.') {
							map[ny][nx] = n.c;
							q.add(new Node(nx, ny, n.c, n.t + 1));
							if (n.c == 'S') hMove++;							
						} else if (map[ny][nx] == 'D' && n.c == 'S') {
							System.out.println(currentT + 1);
							System.exit(0);
						}
					}
				}
			}
			if (hMove == 0) {
				System.out.println("KAKTUS");
				System.exit(0);
			}
			
			currentT++;
		}
		System.out.println("KAKTUS");
	}
}