#include <iostream>
#include <stack>
#include <queue>
#include <memory.h>

void initialize();
void dfs();
void bfs();

int N, M, V;
bool edge[1001][1001];
bool check[1001];

int main()
{
	initialize();
	dfs();
	bfs();
	return 0;
}

void initialize()
{
	std::cin >> N >> M >> V;
	int a, b;
	for (int i = 0; i < M; ++i)
	{
		std::cin >> a >> b;
		edge[a][b] = true;
		edge[b][a] = true;
	}
}

void dfs()
{
	memset(check, false, 1001 * sizeof(bool));
	std::stack<int> s;
	s.push(V);

	while (!s.empty())
	{
		int current = s.top();
		s.pop();

		if (check[current]) continue;
		check[current] = true;
		std::cout << current << " ";

		for (int i = N; i >= 1; --i)
		{
			if (edge[current][i] && !check[i]) s.push(i);
		}
	}

	std::cout << "\n";
}

void bfs()
{
	memset(check, false, 1001 * sizeof(bool));
	std::queue<int> q;
	q.push(V);

	while (!q.empty())
	{
		int current = q.front();
		q.pop();

		if (check[current]) continue;
		check[current] = true;
		std::cout << current << " ";

		for (int i = 1; i <= N; ++i)
		{
			if (edge[current][i] && !check[i]) q.push(i);
		}
	}
	std::cout << "\n";

}