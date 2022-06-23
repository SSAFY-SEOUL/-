import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int MAX = 100000;
	
	private static int[] graph, visit;
	private static boolean[] insideCycle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		graph = new int[MAX];
		visit = new int[MAX];
		insideCycle = new boolean[MAX];
		int[] seq = new int[MAX];
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; ++i) {
				graph[i] = Integer.parseInt(st.nextToken()) - 1;
				visit[i] = 0;
				insideCycle[i] = false;
			}

			for (int i = 0; i < n; ++i) if (visit[i] == 0) dfs(i, seq, 0);
			
			int sum = 0;
			for (int i = 0; i < n; ++i) if (!insideCycle[i]) sum++;
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int student, int[] seq, int depth) {
		if (visit[student] > 2) return;
		if (visit[student] == 1) {
			for (int i = 0; i < depth; ++i) {
				if (seq[i] == student) {
					for (int j = i; j < depth; ++j) {
						insideCycle[seq[j]] = true;
					}
					return;
				}
			}
		}

		visit[student]++;
		seq[depth] = student;
		dfs(graph[student], seq, depth + 1);
	}
}