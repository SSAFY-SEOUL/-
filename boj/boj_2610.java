import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	private static class Person {
		List<Integer> link;

		public Person() {
			this.link = new ArrayList<Integer>();
		}
	}

	private static class Committee implements Comparable<Committee> {
		public List<Integer> member;
		public int representative;
		public int minTime;

		public Committee() {
			member = new ArrayList<Integer>();
			representative = -1;
			minTime = 0x7fffff;
		}

		public void addMember(int m) {
			member.add(m);
		}

		@Override
		public int compareTo(Committee o) {
			return representative - o.representative;
		}
	}

	public static int N, M, K;
	public static Person[] person;
	public static int[] personCom;
	public static List<Committee> com;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		K = 0;

		person = new Person[N + 1];
		personCom = new int[N + 1];
		com = new ArrayList<Committee>();
		for (int i = 1; i <= N; ++i) {
			person[i] = new Person();
			personCom[i] = -1;
		}

		int a, b;
		String[] tok;
		for (int i = 0; i < M; ++i) {
			tok = br.readLine().split(" ");
			a = Integer.parseInt(tok[0]);
			b = Integer.parseInt(tok[1]);
			person[a].link.add(b);
			person[b].link.add(a);
		}

		makeCommittee();
		updateRepresentative();
		Collections.sort(com);

		StringBuilder sb = new StringBuilder();
		sb.append(com.size()).append("\n");
		for (Committee c : com) {
			sb.append(c.representative).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void makeCommittee() {
		boolean[] check = new boolean[N + 1];

		for (int m = 1; m <= N; ++m) {
			if (personCom[m] == -1) {
				Committee newcom = new Committee();
				newcom.addMember(m);
				personCom[m] = com.size();

				Queue<Integer> q = new LinkedList<>();
				q.add(m);
				check[m] = true;

				int cur;
				while (!q.isEmpty()) {
					cur = q.poll();

					for (int newMember : person[cur].link) {
						if (check[newMember])
							continue;
						q.add(newMember);
						newcom.addMember(newMember);
						check[newMember] = true;
						personCom[newMember] = com.size();
					}
				}
				com.add(newcom);
			}
		}
	}

	public static void updateRepresentative() {
		for (Committee c : com) {
			for (int m : c.member) {
				boolean[] check = new boolean[N + 1];
				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] { m, 0 });
				check[m] = true;

				int[] cur;
				int max = 0;
				while (!q.isEmpty()) {
					cur = q.poll();
					for (int next : person[cur[0]].link) {
						if (check[next]) continue;
						q.add(new int[] { next, cur[1] + 1 });
						max = cur[1] + 1;
						check[next] = true;
					}
				}
				
				
				if (c.minTime > max) {
					c.minTime = max;
					c.representative = m;
				}
			}
		}
	}
}