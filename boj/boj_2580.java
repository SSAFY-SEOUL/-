import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int[][] sudoku;
	public static boolean[][] checkRow;
	public static boolean[][] checkCol;
	public static boolean[][] checkSquare;
	public static List<int[]> blank;
	public static int blankCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[9][9];
		checkRow = new boolean[9][10];
		checkCol = new boolean[9][10];
		checkSquare = new boolean[9][10];
		blank = new ArrayList<>();

		StringTokenizer st;
		for (int i = 0; i < 9; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; ++j) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if (sudoku[i][j] == 0) {
					blank.add(new int[] { i, j });
					blankCnt++;
				}

				checkRow[i][sudoku[i][j]] = true;
				checkCol[j][sudoku[i][j]] = true;
				checkSquare[(i / 3) * 3 + j / 3][sudoku[i][j]] = true;
			}
		}

		dfs(0);
	}

	public static void dfs(int cnt) {
		if (cnt == blankCnt) {
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		int[] cur = blank.get(cnt);
		int squareIdx = (cur[0] / 3) * 3 + cur[1] / 3;
		
		for (int v = 1; v <= 9; ++v) {
			if (check(cur[0], cur[1], squareIdx, v)) {
				sudoku[cur[0]][cur[1]] = v;
				checkRow[cur[0]][v] = true;
				checkCol[cur[1]][v] = true;
				checkSquare[squareIdx][v] = true;
				
				dfs(cnt + 1);

				sudoku[cur[0]][cur[1]] = 0;
				checkRow[cur[0]][v] = false;
				checkCol[cur[1]][v] = false;
				checkSquare[squareIdx][v] = false;
			}
		}

	}
	
	public static boolean check(int row, int col, int squareIdx, int v) {
		if (checkRow[row][v]) return false;
		if (checkCol[col][v]) return false;
		if (checkSquare[squareIdx][v]) return false;
		return true;
	}

}