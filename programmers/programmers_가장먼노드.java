import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private static class State {
		int node, step;

		public State(int node, int step) {
			this.node = node;
			this.step = step;
		}
	}
	
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) adj[i] = new ArrayList<>();
        
        for (int i = 0, cnt = edge.length; i < cnt; ++i) {
        	adj[edge[i][0]].add(edge[i][1]);
        	adj[edge[i][1]].add(edge[i][0]);
        }
        
        
        boolean[] visit = new boolean[n + 1];
        Queue<State> q = new LinkedList<>();
        q.add(new State(1, 0));
        visit[1] = true;
        
        State cur;
        int maxDistance = 0;
        answer = 0;
        while (!q.isEmpty()) {
        	cur = q.poll();
        	
        	if (cur.step == maxDistance) answer++;
        	else if (cur.step > maxDistance) {
        		maxDistance = cur.step;
        		answer = 1;
        	}
        	
        	for (int next : adj[cur.node]) {
        		if (!visit[next]) {
        			q.add(new State(next, cur.step + 1));
        			visit[next] = true;
        		}
        	}
        }
        
        return answer;
    }
}