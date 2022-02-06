#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
	std::vector<int> coins;
	int N, K;
	std::cin >> N >> K;

	for (int i = 0; i < N; ++i)
	{
		int coin;
		std::cin >> coin;
		coins.push_back(coin);
	}
	std::sort(coins.begin(), coins.end(), std::greater<int>());

	int count = 0;
	for (int i = 0; i < N; ++i)
	{
		count += K / coins[i];
		K = K % coins[i];
		if (K == 0) break;
	}

	std::cout << count;

	return 0;
}