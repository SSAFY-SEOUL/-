import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int N, SN;
	private static String S;
	private static String[] A;
	private static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		SN = S.length();
		check = new boolean[SN + 1];
		
		N = Integer.parseInt(br.readLine());
		A = new String[N];
		for (int i = 0; i < N; ++i) {
			A[i] = br.readLine();
		}
		
		check[0] = true;
		for (int i = 0; i < SN; ++i) {
			
			if (check[i]) {
				for (int j = 0; j < N; ++j) {
					int AN = A[j].length();
					if (SN - i >= AN && S.substring(i, i + AN).equals(A[j])) {
						check[i + AN] = true;
					}
				}
			}
		}
		
		System.out.println(check[SN] ? "1" : "0");
	}
}