import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static boolean[] isComposite;
	private static List<Integer> prime;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		isComposite = new boolean[N + 1];
		prime = new ArrayList<>();
		
		int number;
		for (int i = 2, limit = (int)(Math.sqrt(N) + 1); i < limit; ++i) {
			number = i + i;
			while (number <= N) {
				isComposite[number] = true;
				number += i;
			}
		}
		
		for (int i = 2; i <= N; ++i) {
			if (!isComposite[i]) prime.add(i);
		}
		
		int pN = prime.size();
		int answer = 0;
		if (pN > 0) {
			for (int begin = 0, end = 0, sum = prime.get(0); begin <= end && end < pN;) {
				if (sum < N) {
					if (++end >= pN) break;
					sum += prime.get(end);
				} else if (sum > N) {
					sum -= prime.get(begin);
					if (++begin >= pN) break;
				} else {
					answer++;
	
					sum -= prime.get(begin);
					if (++begin >= pN) break;
					if (++end >= pN) break;
					sum += prime.get(end);
				}
			}
		}
		
		System.out.println(answer);
	}
}