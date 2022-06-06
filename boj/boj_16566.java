import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, K;
	private static int[] cards;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		cards = new int[M];
		for (int i = 0; i < M; ++i) {
			cards[i] = Integer.parseInt(st.nextToken()); 
		}
		Arrays.sort(cards);

		Set<Integer> minsu = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		
		int chulsu = 0;
		int idx = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; ++i) {
			chulsu = Integer.parseInt(st.nextToken());
			idx = search(chulsu + 1);
			
			while (minsu.contains(cards[idx])) {
				idx++;
			}
			
			sb.append(cards[idx]).append("\n");
			minsu.add(cards[idx]);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int search(int v) {
		int low = 0;
		int high = M - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			
			if (cards[mid] >= v) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		
		return high;
	}
}