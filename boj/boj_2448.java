import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	private static char[][] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int N2 = 2 * N;
		
		result = new char[N + 1][N2];
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j < N2; ++j) { 
				result[i][j] = ' ';
			}
		}
		
		print(N, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j < N2; ++j) {
				sb.append(result[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void print(int x, int y, int size) {
		if (size == 3) {
			result[y][x] = '*';
			
			result[y + 1][x - 1] = '*';
			result[y + 1][x + 1] = '*';
			
			result[y + 2][x - 2] = '*';
			result[y + 2][x - 1] = '*';
			result[y + 2][x] = '*';
			result[y + 2][x + 1] = '*';
			result[y + 2][x + 2] = '*';
			return;
		} else {
			int half = size / 2;
			print(x, y, half);
			print(x - half, y + half, half);
			print(x + half, y + half, half);
		}
	}
}