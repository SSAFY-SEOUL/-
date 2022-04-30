import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

	private static List<Integer>[] adj, tree;
	private static boolean[] visit;
	private static int[] need, indegree;
	private static int N;

	public static boolean solution(int n, int[][] path, int[][] order) {
		N = n;
		adj = new ArrayList[n];
		for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
		for (int i = 0, limit = path.length; i < limit; ++i) {
			adj[path[i][0]].add(path[i][1]);
			adj[path[i][1]].add(path[i][0]);
		}

		visit = new boolean[n];
		indegree = new int[n];
		tree = new ArrayList[n];
		for (int i = 0; i < n; ++i) tree[i] = new ArrayList<>();
		
		visit[0] = true;
		dfs(0);
		
		need = new int[n];
		for (int i = 0, limit = order.length; i < limit; ++i) {
			need[order[i][0]] = order[i][1];
			indegree[order[i][1]]++;
		}

		return topologicalSort();
	}
	
	private static void dfs(int node) {
		for (int next : adj[node]) {
			if (!visit[next]) {
				tree[node].add(next);
				visit[next] = true;
				indegree[next]++;
				dfs(next);
			}
		}
	}
	
	private static boolean topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		if (indegree[0] > 0) return false;
		q.add(0);
		
		int cur;
		int sortedCnt = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			sortedCnt++;
			
			for (int next : tree[cur]) {
				indegree[next]--;
				if (indegree[next] == 0) q.offer(next);
			}
			
			if (need[cur] > 0) {
				indegree[need[cur]]--;
				if (indegree[need[cur]] == 0) q.offer(need[cur]);
			}
		}
		
		return sortedCnt == N;
	}

}