import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] indegree = new int[N + 1];
		int[] start = new int[N + 1];
		int[] time = new int[N + 1];
		List<Integer>[] next = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) next[i] = new ArrayList<>();
		
		for (int i = 1; i <= N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			indegree[i] = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < indegree[i]; ++j) {
				next[Integer.parseInt(st.nextToken())].add(i);
			}
		}
		
		boolean[] visit = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; ++i) {
			if (indegree[i] == 0) {
				q.offer(i);
				visit[i] = true;
			}
		}
		
		int cur;
		int max = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			
			max = Math.max(max, start[cur] + time[cur]);
			
			for (int n : next[cur]) {
				if (visit[n]) continue;
				
				indegree[n]--;
				start[n] = Math.max(start[n], start[cur] + time[cur]);
			}
			
			for (int i = 1; i <= N; ++i) {
				if (!visit[i] && indegree[i] == 0) {
					q.add(i);
					visit[i] = true;
				}
			}
		}
		
		System.out.println(max);
	}
}