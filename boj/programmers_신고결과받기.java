import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        Map<String, Integer> id = new HashMap<>();
        for (int i = 0; i < N; ++i) {
        	id.put(id_list[i], i);
        }
        
        boolean[][] reportStatus = new boolean[N][N];
        int[] reportCount = new int[N];
        
        int R = report.length;
        for (int i = 0; i < R; ++i) {
        	String[] tok = report[i].split(" ");
        	int reporter = id.get(tok[0]);
        	int reportee = id.get(tok[1]);
        	
        	if (!reportStatus[reporter][reportee]) {
        		reportStatus[reporter][reportee] = true;
            	reportCount[reportee]++;
        	}
        }
        
        int[] answer = new int[N];
        for (int reporter = 0; reporter < N; ++reporter) {
        	for (int reportee = 0; reportee < N; ++reportee) {
        		if (reportStatus[reporter][reportee] && reportCount[reportee] >= k) {
        			answer[reporter]++;
        		}
        	}
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
	}
}