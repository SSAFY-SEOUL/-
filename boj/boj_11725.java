import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static class Node {
		List<Integer> connected;
		
		public Node() {
			connected = new ArrayList<Integer>();
		}
		
		public void connect(int n) {
			connected.add(n);
		}
		
	}

	public static int N;
	public static Node[] nodes;
	public static int[] parent;
	public static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N + 1];
		parent = new int[N + 1];
		visit = new boolean[N + 1];

		String[] tok;
		for (int i = 1; i < N; ++i) {
			tok = br.readLine().split(" ");
			int a = Integer.parseInt(tok[0]);
			int b = Integer.parseInt(tok[1]);
			
			if (nodes[a] == null) nodes[a] = new Node();
			nodes[a].connect(b);
			if (nodes[b] == null) nodes[b] = new Node();
			nodes[b].connect(a);
		}
		
		visit[1] = true;
		dfs(1);
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; ++i) {
			sb.append(parent[i]).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void dfs(int n) {
		if (nodes[n] == null) return;
		
		List<Integer> child = nodes[n].connected;
		for (Integer c : child) {
			if (!visit[c]) {
				parent[c] = n;
				visit[c] = true;
				dfs(c);
				visit[c] = false;
			}
		}
	}
}