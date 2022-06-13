class Solution {
	public static int solution(int[] stones, int k) {
		int min = 1;
		int max = 200000000;
		
		while (min < max) {
			int mid = (max + min) / 2;
			if (check(stones, k, mid)) {
				if (min == mid) break;
				min = mid;
			} else {
				max = mid;
			}
		}
		
		return min;
	}
	
	private static boolean check(int[] stones, int k, int val) {
		int n = stones.length;
		int skip = 0;
		for (int i = 0; i < n; ++i) {
			if (stones[i] < val) skip++;
			else skip = 0;
			
			if (skip == k) return false;
		}
		
		return true;
	}
}