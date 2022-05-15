public class Main {
    public static int solution(int[] money) {
    	int N = money.length;
    	
    	int[] yesDp = new int[N];
    	int[] noDp = new int[N];
    	int[] yesDp2 = new int[N];
    	int[] noDp2 = new int[N];
    	
    	yesDp[0] = money[0];
    	noDp[0] = 0;
    	for (int i = 1; i < N; ++i) {
    		yesDp[i] = Math.max(noDp[i - 1] + money[i], yesDp[(i - 2 + N) % N] + money[i]);
    		noDp[i] = Math.max(noDp[i - 1], yesDp[i - 1]);
    	}
    	
    	for (int i = 1; i < N; ++i) {
    		yesDp2[i] = Math.max(noDp2[i - 1] + money[i], yesDp2[(i - 2 + N) % N] + money[i]);
    		noDp2[i] = Math.max(noDp2[i - 1], yesDp2[i - 1]);
    	}
    	
        return Math.max(yesDp2[N - 1], noDp[N - 1]);
    }
}