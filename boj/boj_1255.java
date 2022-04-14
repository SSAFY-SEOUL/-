package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
		int x, y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static class Line {
		Node A, B;

		public Line(Node a, Node b) {
			super();
			A = a;
			B = b;
		}
	}

	private static class People {
		int x, y, v;

		public People(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	private static int N, M;
	private static People[] people;
	private static int[] start;
	private static List<Integer> end;
	private static Line[] lines;
	private static List<Node> nodes;
	private static double[][] adjMatrix;

	public static void main(String[] args) throws IOException {
		processInput();
		makeGraph();

		double maxTime = 0;
		double distance = 0;
		double time = 0;
		for (int i = 0; i < N; ++i) {
			distance = doDijkstra(start[i]);
			time = distance / people[i].v;
			maxTime = Math.max(maxTime, time);
		}
		System.out.println(Math.round(maxTime*10)/10.0);
	}

	private static double doDijkstra(int start) {
		int size = nodes.size();
		double[] distance = new double[size];
		boolean[] visited = new boolean[size];
		
		Arrays.fill(distance, Double.MAX_VALUE);
		distance[start] = 0.0;
		
		for (int i = 0; i < size; ++i) {
			double min = Double.MAX_VALUE;
			int current = 0;
			for (int j = 0; j < size; ++j) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			
			for (int j = 0; j < end.size(); ++j) {
				if (current == end.get(j)) return distance[current];
			}
			
			visited[current] = true;
			for (int j = 0; j < size; ++j) {
				if (!visited[j] && adjMatrix[current][j] > 0 && distance[j] > distance[current] + adjMatrix[current][j]) {
					distance[j] = distance[current] + adjMatrix[current][j];
				}
			}
		}
		return 0;
	}

	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		people = new People[N];
		lines = new Line[M];
		nodes = new ArrayList<>();
		start = new int[N];
		end = new ArrayList<>();

		String str;
		String[] tok;
		int x1, y1, x2, y2, v;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			str = st.nextToken();
			tok = str.substring(1, str.length() - 1).split(",");
			x1 = Integer.parseInt(tok[0].trim());
			y1 = Integer.parseInt(tok[1].trim());
			v = Integer.parseInt(st.nextToken());
			people[i] = new People(x1, y1, v);
		}

		Node A, B;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");

			str = st.nextToken();
			tok = str.substring(1, str.length() - 1).split(",");
			x1 = Integer.parseInt(tok[0].trim());
			y1 = Integer.parseInt(tok[1].trim());
			A = new Node(x1, y1);

			st.nextToken();

			str = st.nextToken();
			tok = str.substring(1, str.length() - 1).split(",");
			x2 = Integer.parseInt(tok[0].trim());
			y2 = Integer.parseInt(tok[1].trim());
			B = new Node(x2, y2);

			nodes.add(A);
			nodes.add(B);
			lines[i] = new Line(A, B);
		}

	}

	private static void makeGraph() {
		for (int i = 0; i < N; ++i) {
			start[i] = nodes.size();
			nodes.add(new Node(people[i].x, people[i].y));
		}

		Node A, B;
		int size = nodes.size();
		int endCnt = 0;
		for (int i = 0; i < size; ++i) {
			A = nodes.get(i);
			B = new Node(A.x, 0);
			if (canGo(A, B)) {
				nodes.add(B);
				end.add(size + endCnt++);
			}
		}

		size = nodes.size();
		adjMatrix = new double[size][size];
		for (int i = 0; i < size; ++i) {
			A = nodes.get(i);
			for (int j = 0; j < size; ++j) {
				if (i == j)
					continue;
				B = nodes.get(j);
				if (adjMatrix[i][j] == 0) {
					if (canGo(A, B)) {
						adjMatrix[i][j] = getDistance(A, B);
						adjMatrix[j][i] = getDistance(A, B);
					}
				}

			}
		}
	}

	private static boolean canGo(Node A, Node B) {
		for (Line l : lines) {
			if ((l.A.x == A.x && l.A.y == A.y) || (l.B.x == A.x && l.B.y == A.y) || (l.A.x == B.x && l.A.y == B.y)
					|| (l.B.x == B.x && l.B.y == B.y)) {
				continue;
			}

			if (isIntersect(A, B, l.A, l.B))
				return false;
		}

		return true;
	}

	private static boolean isIntersect(Node A, Node B, Node C, Node D) {
		int AB = ccw(A, B, C) * ccw(A, B, D);
		int CD = ccw(C, D, A) * ccw(C, D, B);
		if (AB <= 0 && CD <= 0)
			return true;
		else
			return false;
	}

	private static int ccw(Node A, Node B, Node C) {
		int op = (A.x * B.y) + (B.x * C.y) + (C.x * A.y);
		op -= ((A.y * B.x) + (B.y * C.x) + (C.y * A.x));

		if (op > 0)
			return 1;
		else if (op == 0)
			return 0;
		else
			return -1;
	}

	private static double getDistance(Node A, Node B) {
		return Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
	}
}
