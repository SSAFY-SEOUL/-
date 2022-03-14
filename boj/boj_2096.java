import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int a, b, c;
		int maxA, maxB, maxC, minA, minB, minC, tmpA, tmpB, tmpC;
		maxA = minA = Integer.parseInt(st.nextToken());
		maxB = minB = Integer.parseInt(st.nextToken());
		maxC = minC = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine()," ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			tmpA = a + Math.max(maxA, maxB);
			tmpB = b + Math.max(maxA, Math.max(maxB, maxC));
			tmpC = c + Math.max(maxB, maxC);

			maxA = tmpA;
			maxB = tmpB;
			maxC = tmpC;
			
			tmpA = a + Math.min(minA, minB);
			tmpB = b + Math.min(minA, Math.min(minB, minC));
			tmpC = c + Math.min(minB, minC);
			
			minA = tmpA;
			minB = tmpB;
			minC = tmpC;
		}
		
		System.out.println(Math.max(maxA, Math.max(maxB, maxC)) + " " + Math.min(minA, Math.min(minB, minC)));
	}
}