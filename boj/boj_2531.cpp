#include <iostream>
#include <map>

void mapAdd(std::map<int, int>& m, int n);
void mapRemove(std::map<int, int>& m, int n);

int s[33000];

int main()
{
	int n, d, k, c;
	std::cin >> n >> d >> k >> c;

	for (int i = 0; i < n; ++i)
	{
		std::cin >> s[i];
	}
	for (int i = 0; i < (k - 1); ++i)
	{
		s[n + i] = s[i];
	}

	std::map<int, int> m;
	m[c] = 1;
	int max = 0;
	for (int i = 0; i < k; ++i)
	{
		mapAdd(m, s[i]);
	}

	for (int i = k; i < n + k - 1; ++i)
	{
		mapRemove(m, s[i - k]);
		mapAdd(m, s[i]);
		int size = m.size();
		max = max > size ? max : size;
	}

	int size = m.size();
	max = max > size ? max : size;
	std::cout << max;

	return 0;
}

void mapAdd(std::map<int, int>& m, int n)
{
	auto end = m.find(n);
	if (end != m.end()) end->second++;
	else m[n] = 1;
}

void mapRemove(std::map<int, int>& m, int n)
{
	auto begin = m.find(n);
	if (begin != m.end())
	{
		if (begin->second == 1) m.erase(begin);
		else begin->second--;
	}
}