package com.ssafy.programmers;

class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] damage = new int[N + 1][M + 1];
        
        int SN = skill.length;
        int[] s;
        int type, sr, sc, er, ec, degree;
        for (int i = 0; i < SN; ++i) {
            s = skill[i];
            type = s[0];
            sr = s[1];
            sc = s[2];
            er = s[3] + 1;
            ec = s[4] + 1;
            degree = type == 1 ? -s[5] : s[5];
            
            damage[sr][sc] += degree;
            damage[sr][ec] -= degree;
            damage[er][sc] -= degree;
            damage[er][ec] += degree;
        }
        
        for (int r = 0; r < N; ++r) {
            for (int c = 1; c < M; ++c) {
                damage[r][c] += damage[r][c - 1];
            }
        }
        
        for (int c = 0; c < M; ++c) {
            for (int r = 1; r < N; ++r) {
                damage[r][c] += damage[r - 1][c];
            }
        }
        
        int answer = 0;
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < M; ++c) {
                if ((board[r][c] + damage[r][c]) > 0) answer++;
            }
        }
        return answer;
    }
}