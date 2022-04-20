import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static class Dir implements Comparable<Dir> {
		String name;
		List<Dir> children;
		
		public Dir(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}

		@Override
		public int compareTo(Dir o) {
			return this.name.compareTo(o.name);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		
		Dir root = new Dir("root");
		HashMap<String, Dir> map = new HashMap<>();
		
		
		Dir parent = null, child = null;
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), "\\");
			String path = "root";
			parent = root;
			while (st.hasMoreTokens()) {
				String name = st.nextToken();
				path += ("\\" + name);
				if (!map.containsKey(path)) {
					child = new Dir(name);
					map.put(path, child);

					parent.children.add(child);
					parent = child;
				} else {
					parent = map.get(path);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		dfs(root, sb, 0);
		System.out.println(sb.toString());
	}
	
	private static void dfs(Dir now, StringBuilder sb, int level) {
		Collections.sort(now.children);
		
		for (Dir d : now.children) {
			for (int i = 0; i < level; ++i) {
				sb.append(" ");
			}
			sb.append(d.name).append("\n");
			dfs(d, sb, level + 1);
		}
		
	}
}