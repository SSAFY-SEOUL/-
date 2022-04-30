import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

	private static List<Integer>[] adj;
	private static boolean[] visit;
	private static int[] before, after;

	public static boolean solution(int n, int[][] path, int[][] order) {
		adj = new ArrayList[n];
		for (int i = 0; i < n; ++i)
			adj[i] = new ArrayList<>();
		for (int i = 0, limit = path.length; i < limit; ++i) {
			adj[path[i][0]].add(path[i][1]);
			adj[path[i][1]].add(path[i][0]);
		}

		before = new int[n];
		for (int i = 0, limit = order.length; i < limit; ++i) {
			before[order[i][1]] = order[i][0];
		}

		visit = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);

		int cur;
		int visited = 0;
		after = new int[n];
		while (!q.isEmpty()) {
			cur = q.poll();
			
			if (before[cur] > 0 && !visit[before[cur]]) {
				after[before[cur]] = cur;
				continue;
			}
			
			visit[cur] = true;
			visited++;
			
			if (after[cur] > 0) q.add(after[cur]);
			for (Integer next : adj[cur]) if (!visit[next]) q.add(next);
		}

		return visited == n;
	}
}