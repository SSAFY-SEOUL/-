import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N + 1];
		int[] sum = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) A[i] = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> map = new HashMap<>();
		sum[1] = A[1];
		map.put(sum[1], 1);
		for (int i = 2; i <= N; ++i) {
			sum[i] = sum[i - 1] + A[i];
			if (map.containsKey(sum[i])) map.put(sum[i], map.get(sum[i]) + 1);
			else map.put(sum[i], 1);
		}
		
		long answer = 0;
		for (int i = 1; i <= N; ++i) {
			map.put(sum[i], map.get(sum[i]) - 1);
			if (sum[i] == K) answer++;
			
			if (map.containsKey(K + sum[i])) {
				answer += map.get(K + sum[i]);
			}
		}
		
		System.out.println(answer);
	}
}