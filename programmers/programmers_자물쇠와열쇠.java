class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
    	int N = lock.length;
    	int M = key.length;
    	
    	for (int i = 0; i < 4; ++i) {
	    	for (int j = -M; j < N; ++j) {
	    		for (int k = -M; k < N; ++k) {
	    			if (open(key, lock, j, k)) return true;
	    		}
	    	}
	    	key = rotateKey(key);
    	}
    	
        return false;
    }
    
    private static int[][] rotateKey(int[][] key) {
    	int M = key.length;
    	int[][] rotated = new int[M][M];
    	
    	for (int i = 0; i < M; ++i) {
    		for (int j = 0; j < M; ++j) {
    			rotated[j][M - 1 - i] = key[i][j];
    		}
    	}
    	
    	return rotated;
    }
    
    private static boolean open(int[][] key, int[][] lock, int dx, int dy) {
    	int N = lock.length;
    	int M = key.length;
    	
    	int keyX, keyY;
    	for (int y = 0; y < N; ++y) {
    		for (int x = 0; x < N; ++x) {
    			keyX = x - dx;
    			keyY = y - dy;
    			if (0 <= keyX && keyX < M && 0 <= keyY && keyY < M) {
    				if ((lock[y][x] ^ key[keyY][keyX]) == 0) return false;
    			} else {
    				if (lock[y][x] == 0) return false;
    			}
    		}
    	}
    	
    	return true;
    }
}