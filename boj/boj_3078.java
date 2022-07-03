import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, K;
	private static int[] student;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		student = new int[N];
		
		for (int i = 0; i < N; ++i) {
			student[i] = br.readLine().length();
		}
		
		int[] friend = new int[21];
		for (int i = 0; i < K; ++i) friend[student[i]]++;
		
		long answer = 0;
		for (int i = 0; i < N; ++i) {
			friend[student[i]]--;
			if (i + K < N) friend[student[i + K]]++;
			answer += friend[student[i]];
		}
		
		System.out.println(answer);
	}
}