import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static class Animal implements Comparable<Animal> {
		int L, R;

		public Animal(int l, int r) {
			this.L = l;
			this.R = r;
		}

		@Override
		public int compareTo(Animal o) {
			if (this.L == o.L) {
				return o.R - this.R;
			} else return this.L - o.L;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		Animal[] animals = new Animal[N];
		int l, r;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			animals[i] = new Animal(l, r);
		}
		Arrays.sort(animals);

		Animal[] LDS = new Animal[N];
		LDS[0] = animals[0];		
		int len = 1;
		for (int i = 1; i < N; ++i) {
			if (animals[i - 1].L == animals[i].L && animals[i - 1].R == animals[i].R) continue;
			
			if (LDS[len - 1].R >= animals[i].R) {
				LDS[len++] = animals[i];
			} else {
				int idx = getIdx(LDS, len, animals[i]);
				LDS[idx] = animals[i];
			}
		}
		
		System.out.println(len);
	}
	
	private static int getIdx(Animal[] LDS, int len, Animal animal) {
		int left = 0, right = len - 1;
		while (right >= 0 && left <= right) {
			int mid = (left + right) / 2;
			
			if (LDS[mid].R >= animal.R) left = mid + 1;
			else right = mid - 1;
		}
		
		return right + 1;
	}
}