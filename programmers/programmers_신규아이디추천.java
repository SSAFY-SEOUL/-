class Solution {

	public static void main(String[] args) {
		String a = solution("...!@BaT#*..y.abcdefghijklm");
	}

	public static String solution(String new_id) {
		String answer = new_id.toLowerCase().replaceAll("[^a-z\\0-9\\-_.]*", "").replaceAll("\\.{2,}", ".").replaceAll("^[.]|[.]$", "");
		if (answer.length() == 0) answer = "a";
		if (answer.length() > 15) answer = answer.substring(0, 15);
		answer = answer.replaceAll("[.]$", "");
		while (answer.length() < 3) {
			answer += answer.charAt(answer.length() - 1);
		}
		return answer;
	}
}