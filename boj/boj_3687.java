import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int[] digit = { 0, 0, 1, 7, 4, 2, 0, 8 };
	private static long[] min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		min = new long[101];
		for (int i = 0; i < 101; ++i) min[i] = Long.MAX_VALUE;
		min[2] = 1;
		min[3] = 7;
		min[4] = 4;
		min[5] = 2;
		min[6] = 6;
		min[7] = 8;
		min[8] = 10;
		min[9] = 18;

		for (int i = 10; i <= 100; ++i) {
			for (int j = 2; j <= 7; ++j) {
				min[i] = Math.min(min[i], min[i - j] * 10 + digit[j]);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			int N = Integer.parseInt(br.readLine());

			String max = N % 2 == 1 ? "7" : "1";
			for (int i = 1; i < N / 2; ++i)
				max += "1";

			sb.append(min[N]).append(" ").append(max.toString()).append("\n");
		}

		System.out.println(sb.toString());
	}

}