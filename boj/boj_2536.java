import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Step {
		int bus_idx, cnt;

		public Step(int bus_idx, int cnt) {
			this.bus_idx = bus_idx;
			this.cnt = cnt;
		}
	}

	private static class Bus {
		int no, x1, y1, x2, y2;
		boolean horizontal, start, end;

		public Bus(int no, int x1, int y1, int x2, int y2, boolean horizontal) {
			this.no = no;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.horizontal = horizontal;
		}
		
		public boolean isIntersect(Bus b) {
			if (this.horizontal) {
				if (b.horizontal) {
					if (this.y1 == b.y1) {
						if (this.x1 > b.x2 || this.x2 < b.x1) return false;
						return true;
					} else return false;
				} else {
					if (b.y1 <= this.y1 && this.y1 <= b.y2) {
						if (this.x1 <= b.x1 && b.x1 <= this.x2) return true;
						return false;
					} else return false;
				}
			} else {
				if (b.horizontal) {
					if (this.y1 <= b.y1 && b.y1 <= this.y2) {
						if (b.x1 <= this.x1 && this.x1 <= b.x2) return true;
						return false;
					} else return false;
					
				} else {
					if (this.x1 == b.x1) {
						if (this.y1 > b.y2 || this.y2 < b.y1) return false;
						return true;
					} else return false;
				}
			}
		}
	}

	private static Bus[] bus;
	private static boolean[][] intersect;
	private static boolean[] visit;
	private static int m, n, k, sx, sy, dx, dy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		intersect = new boolean[k][k];
		visit = new boolean[k];
		bus = new Bus[k];

		int no, x1, x2, y1, y2;
		boolean horizontal;
		for (int i = 0; i < k; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			no = Integer.parseInt(st.nextToken());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			horizontal = y1 == y2;
			if (horizontal) {
				if (x1 > x2) bus[i] = new Bus(no, x2, y2, x1, y1, horizontal);
				else bus[i] = new Bus(no, x1, y1, x2, y2, horizontal);
			} else {
				if (y1 > y2) bus[i] = new Bus(no, x2, y2, x1, y1, horizontal);
				else bus[i] = new Bus(no, x1, y1, x2, y2, horizontal);
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		dx = Integer.parseInt(st.nextToken());
		dy = Integer.parseInt(st.nextToken());

		Queue<Step> q = new LinkedList<>();
		for (int i = 0; i < k; ++i) {
			if (bus[i].horizontal) {
				if (bus[i].y1 == sy && bus[i].x1 <= sx && sx <= bus[i].x2) {
					bus[i].start = true;
					q.add(new Step(i, 1));
					visit[i] = true;
				}
				
				if (bus[i].y1 == dy && bus[i].x1 <= dx && dx <= bus[i].x2) {
					bus[i].end = true;
				}
			} else {
				if (bus[i].x1 == sx && bus[i].y1 <= sy && sy <= bus[i].y2) {
					bus[i].start = true;
					q.add(new Step(i, 1));
					visit[i] = true;
				}
				
				if (bus[i].x1 == dx && bus[i].y1 <= dy && dy <= bus[i].y2) {
					bus[i].end = true;
				}
			}
			
			for (int j = 0; j < k; ++j) {
				if (i != j && !intersect[i][j] && bus[i].isIntersect(bus[j])) {
					intersect[i][j] = true;
					intersect[j][i] = true;
				}
			}
		}
		
		Step cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			if (bus[cur.bus_idx].end) {
				System.out.println(cur.cnt);
				break;
			}
			
			for (int i = 0; i < k; ++i) {
				if (!visit[i] && intersect[cur.bus_idx][i]) {
					q.add(new Step(i, cur.cnt + 1));
					visit[i] = true;
				}
			}
			
		}
	}

}