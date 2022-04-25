import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	private static class Step implements Comparable<Step> {
		int node, distance, state;

		public Step(int node, int distance, int state) {
			this.node = node;
			this.distance = distance;
			this.state = state;
		}

		@Override
		public int compareTo(Step o) {
			return this.distance - o.distance;
		}
	}

	private static class Road {
		int from, to, distance;

		public Road(int from, int to, int distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
	}

	private static List<Road>[] roadList;
	private static Map<Integer, Integer> trapMap;
	private static int[] visit;
	private static final int INF = 3000 + 1;

	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int roadSize = roads.length;
		int trapSize = traps.length;
		int noTrap = 1 << (trapSize + 1);

		trapMap = new HashMap<>();
		for (int i = 0; i < trapSize; ++i) trapMap.put(traps[i], i);

		roadList = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i) roadList[i] = new ArrayList<>();

		int from, to, dist;
		for (int i = 0; i < roadSize; ++i) {
			from = roads[i][0];
			to = roads[i][1];
			dist = roads[i][2];

			roadList[from].add(new Road(from, to, dist));
			roadList[to].add(new Road(from, to, dist));
		}

		visit = new int[n + 1];
		PriorityQueue<Step> pq = new PriorityQueue<>();
		pq.offer(new Step(start, 0, noTrap));
		while (!pq.isEmpty()) {
			Step cur = pq.poll();
			if (visit[cur.node] == cur.state) continue;
			visit[cur.node] = cur.state; 
			
			if (cur.node == end) {
				return cur.distance;
			}

			for (int i = 0, limit = roadList[cur.node].size(); i < limit; ++i) {
				Step next = go(cur, roadList[cur.node].get(i));
				if (next != null) pq.offer(next);
			}
		}

		return 0;
	}

	private static Step go(Step cur, Road road) {
		int from = cur.node;
		int state = cur.state;

		boolean fromTrap = false;
		if (trapMap.containsKey(road.from)) fromTrap = (state & (1 << trapMap.get(road.from))) > 0 ? true : false;

		boolean toTrap = false;
		if (trapMap.containsKey(road.to)) toTrap = (state & (1 << trapMap.get(road.to))) > 0 ? true : false;

		int to = road.to;
		if (fromTrap != toTrap) to = road.from;

		int dist = INF;
		if (from != to) dist = cur.distance + road.distance;
		if (dist == INF) return null;

		int newState = cur.state;
		if (trapMap.containsKey(to)) newState ^= (1 << trapMap.get(to));

		return new Step(to, dist, newState);
	}
}