class Solution {
    public static long solution(long price, long money, long count) {
    	long sum = price * count * (count + 1) / 2;
        return sum <= money ? 0 : sum - money;
    }
}