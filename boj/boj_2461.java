import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Student {
		int a, c;
		public Student(int a, int c) {
			this.a = a;
			this.c = c;
		}		
	}
	
	private static int[][] s;
	private static int[] pointer;
	private static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		s = new int[N][M];
		pointer = new int[N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(s[i]);
		}
		
		PriorityQueue<Student> pq = new PriorityQueue<>((Student A, Student B) -> {
			return A.a - B.a;
		});
		
		int curMax = -1;
		for (int i = 0; i < N; ++i) {
			if (s[i][0] > curMax) curMax = s[i][0];
			pq.add(new Student(s[i][0], i));
		}
		
		int minDiff = curMax - pq.peek().a;
		Student min;
		Student next;
		while (true) {
			min = pq.poll();
			if (pointer[min.c] == M - 1) break;
			
			next = new Student(s[min.c][++pointer[min.c]], min.c);
			pq.offer(next);
			
			if (next.a > curMax) curMax = next.a;
			minDiff = Math.min(minDiff, curMax - pq.peek().a);
		}
		
		System.out.println(minDiff);
	}
	
}