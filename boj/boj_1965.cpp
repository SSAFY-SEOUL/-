#include <iostream>

int main()
{
	int n = 0;
	int b[1001] = { 0, };
	int dp[1001];
	int max = 0;
	std::cin >> n;

	for (int i = 0; i < n; ++i) std::cin >> b[i];

	dp[0] = 1;
	int deepest = 0;
	for (int i = 1; i < n; ++i)
	{
		deepest = 0;
		for (int j = i - 1; j >= 0; --j)
		{
			if (b[i] > b[j])
			{
				deepest = deepest > dp[j] ? deepest : dp[j];
			}
		}
		dp[i] = deepest + 1;
		max = max > dp[i] ? max : dp[i];
	}
	std::cout << max;
	return 0;
}