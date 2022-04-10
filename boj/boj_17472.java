package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, islandCnt, INF = 999;
	public static int[][] map;
	public static int[][] graph;
	public static List<int[]>[] islandCoords;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		islandCoords = new ArrayList[7];
		for (int i = 0; i < 7; ++i) {
			islandCoords[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		islandCnt = numberingIsland();
		graph = new int[islandCnt + 1][islandCnt + 1];
		updateGraph();
		System.out.println(doPrim());
	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static int numberingIsland() {
		int no = 1;
		boolean[][] v = new boolean[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (!v[i][j] && map[i][j] == 1) {
					Queue<int[]> q = new LinkedList<int[]>();
					q.add(new int[] { i, j });
					v[i][j] = true;
					map[i][j] = no;
					islandCoords[no].add(new int[] {i, j});
					
					int[] cur;
					while (!q.isEmpty()) {
						cur = q.poll();
						
						for (int dir = 0; dir < 4; ++dir) {
							int nx = cur[1] + dx[dir];
							int ny = cur[0] + dy[dir];
							
							if (0 <= nx && nx < M && 0 <= ny && ny < N && !v[ny][nx] && map[ny][nx] == 1) {
								q.add(new int[] { ny, nx });
								v[ny][nx] = true;
								map[ny][nx] = no;
								islandCoords[no].add(new int[] {ny, nx});
							}
						}
					}
					no++;
				}
			}
		}

		return no - 1;
	}
	
	public static void updateGraph() {
		for (int i = 1; i <= islandCnt; ++i) {
			for (int j = 1; j <= islandCnt; ++j) {
				graph[i][j] = INF;
			}
		}
		
		for (int i = 1; i <= islandCnt; ++i) {
			for (int[] coord : islandCoords[i]) {
				for (int dir = 0; dir < 4; ++dir) {
					for (int dist = 1; dist < 10; ++dist) {
						int nx = coord[1] + (dx[dir] * dist);
						int ny = coord[0] + (dy[dir] * dist);
						if (0 <= nx && nx < M && 0 <= ny && ny < N && map[ny][nx] != 0) {
							if (map[ny][nx] != i && dist > 2) graph[i][map[ny][nx]] = Math.min(graph[i][map[ny][nx]], dist - 1);
							break;
						}
					}
				}
			}
		}
	}

	public static int doPrim() {
		int res = 0;
		boolean[] v = new boolean[islandCnt + 1];
		int[] minBridge = new int[islandCnt + 1];
		for (int i = 2; i <= islandCnt; ++i) minBridge[i] = INF;
		
		for (int i = 1; i <= islandCnt; ++i) {
			int min = INF;
			int minIsland = 0;
			
			for (int j = 1; j <= islandCnt; ++j) {
				if (!v[j] && min > minBridge[j]) {
					min = minBridge[j];
					minIsland = j;
				}
			}
			
			if (min == INF) return -1;
			
			v[minIsland] = true;
			res += min;
			
			for (int j = 1; j <= islandCnt; ++j) {
				if (!v[j] && graph[minIsland][j] != INF && minBridge[j] > graph[minIsland][j]) {
					minBridge[j] = graph[minIsland][j];
				}
			}
		}
		
		return res;
	}
}
