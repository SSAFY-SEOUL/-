#include <iostream>
#include <string>
#include <stdlib.h>

bool check(int n);

int main()
{
	int N = 0;
	std::cin >> N;
	int answer = 0;
	for (int i = 1; i <= N; ++i)
	{
		if (check(i)) answer++;
	}

	std::cout << answer;
	return 0;
}

bool check(int n)
{
	int arr[5] = { 0, };
	int seq[5] = { 0, };
	int len = 0;
	while (n > 0)
	{
		arr[len] = n % 10;
		n /= 10;
		len++;

		if (len > 1) {
			seq[len - 2] = arr[len - 1] - arr[len - 2];
		}
	}

	for (int i = 0; i < len - 2; ++i)
	{
		if (seq[i] != seq[i + 1])
		{
			return false;
		}
	}

	return true;
}