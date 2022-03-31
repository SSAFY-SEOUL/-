package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static int[][] pool;
	public static boolean[][][] visit;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pool = new int[N][M];
		visit = new boolean[N][M][10];

		int maxH = 0, minH = 10;
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				pool[i][j] = line.charAt(j) - '0';
				if (pool[i][j] > maxH) maxH = pool[i][j];
				if (pool[i][j] < minH) minH = pool[i][j];
			}
		}
		
		int sum = 0;
		for (int h = minH + 1; h <= maxH; ++h) {
			for (int y = 0; y < N; ++y) {
				for (int x = 0; x < M; ++x) {
					if (!visit[y][x][h] && pool[y][x] < h) {
						sum += bfs(x, y, h);
					}
				}
			}
		}
		System.out.println(sum);
		
		return;
	}
	
	public static int bfs(int x, int y, int h) {
		visit[y][x][h] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		
		int waterFill = 1;
		boolean over = false;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s=0;s<size;s++) {
				int[] current = q.poll();
				
				for (int dir=0;dir<4;dir++) {
					int nx = current[0]+dx[dir];
					int ny = current[1]+dy[dir];
					if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
						over = true;
					} else if (!visit[ny][nx][h] && pool[ny][nx] < h){
						q.offer(new int[] {nx, ny});
						visit[ny][nx][h] = true;
						waterFill++;
					} 
				}
			}
		}
		
		return over ? 0 : waterFill;
	}
}