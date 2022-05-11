package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static Queue<Integer> q;
	private static int max, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new LinkedList<>();
		max = 0;
		dfs(0, board);
		System.out.println(max);
	}
	
	private static void dfs(int move, int[][] board) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				max = Math.max(max, board[i][j]);
			}
		}
		
		if (move == 5) return;
		
		
		// UP
		int[][] upBoard = new int[N][N];
		for (int i = 0; i < N; ++i) {
			int cnt = 0;
			for (int j = 0; j < N; ++j) {
				if (board[j][i] == 0) continue;
				if (q.isEmpty()) q.offer(board[j][i]);
				else {
					if (q.peek() == board[j][i]) {
						upBoard[cnt++][i] = board[j][i] + q.poll();
					} else {
						upBoard[cnt++][i] = q.poll();
						q.offer(board[j][i]);
					}
					
				}
			}
			
			if (!q.isEmpty()) upBoard[cnt][i] = q.poll();
		}
		dfs(move + 1, upBoard);
		
		// DOWN
		int[][] downBoard = new int[N][N];
		for (int i = 0; i < N; ++i) {
			int cnt = N - 1;
			for (int j = N - 1; j >= 0; --j) {
				if (board[j][i] == 0) continue;
				if (q.isEmpty()) q.offer(board[j][i]);
				else {
					if (q.peek() == board[j][i]) {
						downBoard[cnt--][i] = board[j][i] + q.poll();
					} else {
						downBoard[cnt--][i] = q.poll();
						q.offer(board[j][i]);
					}
				}
			}

			if (!q.isEmpty()) downBoard[cnt][i] = q.poll();
		}
		dfs(move + 1, downBoard);
		
		// LEFT
		int[][] leftBoard = new int[N][N];
		for (int i = 0; i < N; ++i) {
			int cnt = 0;
			for (int j = 0; j < N; ++j) {
				if (board[i][j] == 0) continue;
				if (q.isEmpty()) q.offer(board[i][j]);
				else {
					if (q.peek() == board[i][j]) {
						leftBoard[i][cnt++] = board[i][j] + q.poll();
					} else {
						leftBoard[i][cnt++] = q.poll();
						q.offer(board[i][j]);
					}
					
				}
			}
			
			if (!q.isEmpty()) leftBoard[i][cnt] = q.poll();
		}
		dfs(move + 1, leftBoard);
		
		// RIGHT
		int[][] rightBoard = new int[N][N];
		for (int i = 0; i < N; ++i) {
			int cnt = N - 1;
			for (int j = N - 1; j >= 0; --j) {
				if (board[i][j] == 0) continue;
				if (q.isEmpty()) q.offer(board[i][j]);
				else {
					if (q.peek() == board[i][j]) {
						rightBoard[i][cnt--] = board[i][j] + q.poll();
					} else {
						rightBoard[i][cnt--] = q.poll();
						q.offer(board[i][j]);
					}
					
				}
			}
			
			if (!q.isEmpty()) rightBoard[i][cnt] = q.poll();
		}
		dfs(move + 1, rightBoard);
	}
}