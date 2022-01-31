#include <iostream>

int dp[100001][2];

int main()
{
	int N = 0;
	std::cin >> N;


	int start, end, p;
	for (int i = 0; i < N; ++i)
	{
		std::cin >> start >> end >> p;
		if (i < 1)
		{
			dp[i][0] = 0;
			dp[i][1] = p;
		}
		else
		{
			dp[i][0] = dp[i - 1][1]  > dp[i - 1][0] ? dp[i - 1][1] : dp[i- 1][0];
			dp[i][1] = dp[i - 1][0] + p;
		}
	}

	int max = dp[N - 1][0] > dp[N - 1][1] ? dp[N - 1][0] : dp[N - 1][1];
	std::cout << max;

	return 0;
}