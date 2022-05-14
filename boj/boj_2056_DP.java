import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] end = new int[N + 1];
		int[] time = new int[N + 1];
		
		int max = 0, need = 0;
		for (int i = 1; i <= N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			need = Integer.parseInt(st.nextToken());
			
			end[i] = time[i];
			for (int j = 0; j < need; ++j) {
				int before = Integer.parseInt(st.nextToken());
				end[i] = Math.max(end[i], end[before] + time[i]);
			}
			
			max = Math.max(max, end[i]);
		}
		
		System.out.println(max);
	}
}