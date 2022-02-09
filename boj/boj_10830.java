package com.ssafy.boj;

import java.util.Scanner;

public class Main {

	public static int N;
	public static long B;
	public static int A[][];
	public static int ans[][];

	public static void main(String[] args) {
		processInput();
		ans = power(A, B);
		printAnswer();
	}

	public static void processInput() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		B = sc.nextLong();

		A = new int[N][N];
		ans = new int[N][N];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				A[i][j] = sc.nextInt();
			}
			ans[i][i] = 1;
		}
	}

	public static void printAnswer() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int[][] power(int[][] A, long B) {
		int[][] ret = createIdentity();

		if (B == 1) {
			ret = product(ret, A);
		} else {
			ret = power(A, B / 2);
			ret = product(ret, ret);

			if (B % 2 == 1)
				ret = product(ret, A);
		}

		return ret;
	}

	public static int[][] product(int[][] m1, int[][] m2) {
		int[][] ret = new int[N][N];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < N; ++k) {
					ret[i][j] += (m1[i][k] * m2[k][j]);
				}
				ret[i][j] %= 1000;
			}
		}

		return ret;
	}

	public static int[][] createIdentity() {
		int[][] ret = new int[N][N];
		for (int i = 0; i < N; ++i)
			ret[i][i] = 1;
		return ret;
	}
}
