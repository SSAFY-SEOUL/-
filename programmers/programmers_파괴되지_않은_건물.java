package com.ssafy.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	private static class Coordinate {
		public int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Block {
		Coordinate start, end;
		int size_x, size_y, cells;
		boolean used;
		List<long[]> hashes;

		public Block(Coordinate start, Coordinate end, int cells) {
			this.start = start;
			this.end = end;
			this.cells = cells;
			this.used = false;
			size_x = end.x - start.x + 1;
			size_y = end.y - start.y + 1;
			hashes = new ArrayList<long[]>();
		}

		public void initialize(int[][] board) {
			long[] hash = new long[size_y];
			int line = 0;
			for (int y = start.y; y <= end.y; ++y) {
				for (int x = start.x; x <= end.x; ++x) {
					hash[line] = hash[line] << 1;
					hash[line] += board[y][x];
				}
				line++;
			}
			hashes.add(hash);
		}

		public void initialize_4(int[][] board) {
			long[] hash_1 = new long[size_y];
			int line = 0;
			for (int y = start.y; y <= end.y; ++y) {
				for (int x = start.x; x <= end.x; ++x) {
					hash_1[line] = hash_1[line] << 1;
					hash_1[line] += board[y][x];
				}
				line++;
			}

			long[] hash_2 = new long[size_x];
			line = 0;
			for (int x = end.x; x >= start.x; --x) {
				for (int y = start.y; y <= end.y; ++y) {
					hash_2[line] = hash_2[line] << 1;
					hash_2[line] += board[y][x];
				}
				line++;
			}

			long[] hash_3 = new long[size_y];
			line = 0;
			for (int y = end.y; y >= start.y; --y) {
				for (int x = end.x; x >= start.x; --x) {
					hash_3[line] = hash_3[line] << 1;
					hash_3[line] += board[y][x];
				}
				line++;
			}

			long[] hash_4 = new long[size_x];
			line = 0;
			for (int x = start.x; x <= end.x; ++x) {
				for (int y = end.y; y >= start.y; --y) {
					hash_4[line] = hash_4[line] << 1;
					hash_4[line] += board[y][x];
				}
				line++;
			}
			
			hashes.add(hash_1);
			hashes.add(hash_2);
			hashes.add(hash_3);
			hashes.add(hash_4);
		}

		public boolean isSame(Block b) {
			if (this.cells != b.cells) return false;
			
			int hash1_len, hash2_len;
			for (long[] hash1 : hashes) {
				for (long[] hash2 : b.hashes) {
					boolean same = true;
					hash1_len = hash1.length;
					hash2_len = hash2.length;
					if (hash1_len != hash2_len) continue;
					
					for (int i = 0; i < hash1_len; ++i) {
						if (hash1[i] != hash2[i]) same = false;
					}
					if (same) return true;
				}
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return start.x + " " + start.y + " // " + end.x + " " + end.y;
		}
	}

	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };
	private static int N;

	public static int solution(int[][] game_board, int[][] table) {
		N = game_board.length;

		for (int y = 0; y < N; ++y)
			for (int x = 0; x < N; ++x)
				game_board[y][x] = game_board[y][x] ^ 1;

		boolean[][] visit_game_board = new boolean[N][N];
		boolean[][] visit_table = new boolean[N][N];

		List<Block> blanks = new ArrayList<>();
		List<Block> blocks = new ArrayList<>();
		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < N; ++x) {
				if (game_board[y][x] == 1 && !visit_game_board[y][x]) {
					blanks.add(get_block(x, y, game_board, visit_game_board));
				}
				if (table[y][x] == 1 && !visit_table[y][x]) {
					blocks.add(get_block(x, y, table, visit_table));
				}
			}
		}

		for (Block b : blanks)
			b.initialize(game_board);
		for (Block b : blocks)
			b.initialize_4(table);
		
		int cells = 0;
		for (Block b1 : blanks) {
			for (Block b2 : blocks) {
				if (!b2.used && b1.isSame(b2)) {
					b2.used = true;
					cells += b2.cells;
					break;
				}
			}
		}
		
		return cells;
	}

	private static Block get_block(int x, int y, int[][] board, boolean[][] visit) {
		Queue<Coordinate> q = new LinkedList<>();
		q.offer(new Coordinate(x, y));
		visit[y][x] = true;

		Coordinate start = new Coordinate(x, y);
		Coordinate end = new Coordinate(x, y);

		Coordinate cur;
		int nx, ny, cells = 1;
		while (!q.isEmpty()) {
			cur = q.poll();

			if (cur.x < start.x)
				start.x = cur.x;
			if (cur.y < start.y)
				start.y = cur.y;
			if (cur.x > end.x)
				end.x = cur.x;
			if (cur.y > end.y)
				end.y = cur.y;

			for (int dir = 0; dir < 4; ++dir) {
				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];

				if (0 <= nx && nx < N && 0 <= ny && ny < N && board[ny][nx] == 1 && !visit[ny][nx]) {
					q.offer(new Coordinate(nx, ny));
					cells++;
					visit[ny][nx] = true;
				}
			}
		}

		return new Block(start, end, cells);
	}
}