package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] paper;
	private static int[] used;
	private static int targetCount, minPaper;
	private static final int MAX_SIZE = 10;
	private static final int INF = 30;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		paper = new int[10][10];
		used = new int[6];
		targetCount = 0;
		minPaper = INF;

		StringTokenizer st = null;
		for (int i = 0; i < MAX_SIZE; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < MAX_SIZE; ++j) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1)
					targetCount++;
			}
		}
		
		dfs(0, 0, 0);

		System.out.print(minPaper == INF ? -1 : minPaper);
	}

	private static void dfs(int x, int y, int usedPaper) {
		if (targetCount == 0) {
			minPaper = Math.min(minPaper, usedPaper);
			return;
		}

		if (usedPaper >= minPaper) return;
		
		if (x == MAX_SIZE) {
			dfs(0, y + 1, usedPaper);
			return;
		}
		
		if (paper[y][x] == 1) {
			for (int k = 5; k > 0; --k) {
				if (used[k] >= 5) continue;

				int covered = cover(x, y, k);
				if (covered > 0) {
					targetCount -= covered;
					used[k]++;

					dfs(x + 1, y, usedPaper + 1);

					undo(x, y, k);
					targetCount += covered;
					used[k]--;
				}

			}
		} else {
			dfs(x + 1, y, usedPaper);
		}
	}

	private static int cover(int x, int y, int size) {
		// check
		for (int i = x; i < x + size; ++i) {
			for (int j = y; j < y + size; ++j) {
				if (i >= MAX_SIZE || j >= MAX_SIZE)
					return 0;
				if (paper[j][i] == 0)
					return 0;
			}
		}

		// fill
		for (int i = x; i < x + size; ++i) {
			for (int j = y; j < y + size; ++j) {
				paper[j][i] = 0;
			}
		}

		return size * size;
	}

	private static int undo(int x, int y, int size) {
		for (int i = x; i < x + size; ++i) {
			for (int j = y; j < y + size; ++j) {
				paper[j][i] = 1;
			}
		}

		return size * size;
	}
}