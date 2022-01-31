#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

int getSum(std::vector<int>& v);

int main()
{
	int N = 0;
	int max = -0x7fffffff;
	bool check[9] = { 0, };
	int seq[9] = { 0, };
	std::vector<int> v;
	std::cin >> N;

	for (int i = 0; i < N; ++i)
	{
		std::cin >> seq[i];
		v.push_back(seq[i]);
	}

	std::sort(v.begin(), v.end());
	int sum = 0;
	do {
		sum = getSum(v);
		max = sum > max ? sum : max;
	} while (std::next_permutation(v.begin(), v.end()));

	std::cout << max;
	return 0;
}

int getSum(std::vector<int>& v)
{
	int sum = 0;
	for (int i = 0; i < v.size() - 1; ++i)
	{
		sum += abs(v[i] - v[i + 1]);
	}

	return sum;
}