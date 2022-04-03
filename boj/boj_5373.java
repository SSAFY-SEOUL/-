package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static Map<String, int[][]> rotateMap = new HashMap<>();

	static class Cube {
		char[][] U = { { 'w', 'w', 'w' }, { 'w', 'w', 'w' }, { 'w', 'w', 'w' } };
		char[][] D = { { 'y', 'y', 'y' }, { 'y', 'y', 'y' }, { 'y', 'y', 'y' } };
		char[][] F = { { 'r', 'r', 'r' }, { 'r', 'r', 'r' }, { 'r', 'r', 'r' } };
		char[][] B = { { 'o', 'o', 'o' }, { 'o', 'o', 'o' }, { 'o', 'o', 'o' } };
		char[][] L = { { 'g', 'g', 'g' }, { 'g', 'g', 'g' }, { 'g', 'g', 'g' } };
		char[][] R = { { 'b', 'b', 'b' }, { 'b', 'b', 'b' }, { 'b', 'b', 'b' } };
		char[][][] planes = { U, D, F, B, L, R };

		public void rotate(String dir) {
			int[][] orders = rotateMap.get(dir);

			char[] tmp = new char[3];
			for (int i = 0; i < 3; ++i) {
				if (orders[3][1] == -1)
					tmp[i] = planes[orders[3][0]][i][orders[3][2]];
				else
					tmp[i] = planes[orders[3][0]][orders[3][1]][i];
			}

			for (int i = 3; i > 0; --i) {
				int[] from = orders[i - 1];
				int[] to = orders[i];

				char[] fromChars = new char[3];
				for (int j = 0; j < 3; ++j) {
					if (from[1] == -1)
						fromChars[j] = planes[from[0]][j][from[2]];
					else
						fromChars[j] = planes[from[0]][from[1]][j];
				}

				for (int j = 0; j < 3; ++j) {
					if (to[3] == 1) {
						if (to[1] == -1)
							planes[to[0]][j][to[2]] = fromChars[j];
						else
							planes[to[0]][to[1]][j] = fromChars[j];
					} else {
						if (to[1] == -1)
							planes[to[0]][2 - j][to[2]] = fromChars[j];
						else
							planes[to[0]][to[1]][2 - j] = fromChars[j];
					}
				}
			}

			for (int i = 0; i < 3; ++i) {
				if (orders[0][3] == 1) {
					if (orders[0][1] == -1)
						planes[orders[0][0]][i][orders[0][2]] = tmp[i];
					else
						planes[orders[0][0]][orders[0][1]][i] = tmp[i];
				} else {
					if (orders[0][1] == -1)
						planes[orders[0][0]][2 - i][orders[0][2]] = tmp[i];
					else
						planes[orders[0][0]][orders[0][1]][2 - i] = tmp[i];
				}
			}

			char[][] p = planes[orders[4][0]];
			for (int i = 0; i < 2; ++i) {
				int[] dx = { 1, 0, -1, 0 };
				int[] dy = { 0, 1, 0, -1 };
				if (dir.charAt(1) == '+') {
					dx = new int[] { 0, 1, 0, -1 };
					dy = new int[] { 1, 0, -1, 0 };

				}
				int go = 0, cx = 0, cy = 0, nx = 0, ny = 0;
				char tmpC = p[0][0];
				while (go < 4) {
					nx = cx + dx[go];
					ny = cy + dy[go];

					if (0 > nx || nx > 2 || 0 > ny || ny > 2) {
						go++;
						continue;
					}

					p[cy][cx] = p[ny][nx];

					cx = nx;
					cy = ny;
				}

				if (dir.charAt(1) == '+') {
					p[0][1] = tmpC;
				} else {
					p[1][0] = tmpC;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		// { 면, 행, 열, 정/역순 }
		// U = 0, D = 1, F = 2, B = 3, L = 4, R = 5
		rotateMap.put("U+", new int[][] { { 2, 0, -1, 1 }, { 4, 0, -1, 1 }, { 3, 0, -1, 1 }, { 5, 0, -1, 1 }, { 0 } });
		rotateMap.put("U-", new int[][] { { 2, 0, -1, 1 }, { 5, 0, -1, 1 }, { 3, 0, -1, 1 }, { 4, 0, -1, 1 }, { 0 } });
		rotateMap.put("D+", new int[][] { { 2, 2, -1, 1 }, { 5, 2, -1, 1 }, { 3, 2, -1, 1 }, { 4, 2, -1, 1 }, { 1 } });
		rotateMap.put("D-", new int[][] { { 2, 2, -1, 1 }, { 4, 2, -1, 1 }, { 3, 2, -1, 1 }, { 5, 2, -1, 1 }, { 1 } });

		rotateMap.put("F+", new int[][] { { 0, 2, -1, 0 }, { 5, -1, 0, 1 }, { 1, 2, -1, 1 }, { 4, -1, 2, 0 }, { 2 } });
		rotateMap.put("F-", new int[][] { { 0, 2, -1, 1 }, { 4, -1, 2, 0 }, { 1, 2, -1, 0 }, { 5, -1, 0, 1 }, { 2 } });
		rotateMap.put("B+", new int[][] { { 0, 0, -1, 1 }, { 4, -1, 0, 0 }, { 1, 0, -1, 0 }, { 5, -1, 2, 1 }, { 3 } });
		rotateMap.put("B-", new int[][] { { 0, 0, -1, 0 }, { 5, -1, 2, 1 }, { 1, 0, -1, 1 }, { 4, -1, 0, 0 }, { 3 } });

		rotateMap.put("L+", new int[][] { { 0, -1, 0, 0 }, { 2, -1, 0, 1 }, { 1, -1, 2, 0 }, { 3, -1, 2, 1 }, { 4 } });
		rotateMap.put("L-", new int[][] { { 0, -1, 0, 1 }, { 3, -1, 2, 0 }, { 1, -1, 2, 1 }, { 2, -1, 0, 0 }, { 4 } });
		rotateMap.put("R+", new int[][] { { 0, -1, 2, 1 }, { 3, -1, 0, 0 }, { 1, -1, 0, 1 }, { 2, -1, 2, 0 }, { 5 } });
		rotateMap.put("R-", new int[][] { { 0, -1, 2, 0 }, { 2, -1, 2, 1 }, { 1, -1, 0, 0 }, { 3, -1, 0, 1 }, { 5 } });

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			Cube cube = new Cube();

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				cube.rotate(st.nextToken());
			}

			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					sb.append(cube.U[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}
