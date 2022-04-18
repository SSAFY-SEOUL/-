import java.util.Arrays;
import java.util.HashMap;

class Solution {
	public int[] solution(String[] gems) {
		int N = gems.length;
		int[] g = new int[N];
		int gCnt = 0;
		
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; ++i) {
			if (map.get(gems[i]) == null) {
				g[i] = gCnt;
				map.put(gems[i], gCnt++);
			} else {
				g[i] = map.get(gems[i]);
			}
		}
		
		int[] gCheck = new int[gCnt];
		int gKindCnt = 0;
		
		int start = 0, end = 0, minLength = 100000 + 1, minStart = 0, minEnd = 0;		
		while (true) {
			if (start >= N && end >= N) break;
			
			for (; end < N; ++end) {
				gCheck[g[end]]++;
				if (gCheck[g[end]] == 1) {
					gKindCnt++;
				}
				
				if (gKindCnt == gCnt) break;
			}
			
			for (; start < N; ++start) {
				if (gCheck[g[start]] == 1 && gKindCnt == gCnt) {
					if (end - start + 1 < minLength) {
						minLength = end - start + 1;
						minStart = start;
						minEnd = end;
					}
					gCheck[g[start]]--;
					gKindCnt--;
					end++;
					start++;
					break;
				} else gCheck[g[start]]--;
			}
			
		}

		return new int[] {minStart + 1, minEnd + 1};
	}

}