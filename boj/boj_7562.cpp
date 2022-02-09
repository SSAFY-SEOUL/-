#include <iostream>
#include <queue>
#include <memory.h>

int main()
{
	int dx[8] = { -2, -2, -1, -1,  1, 1, 2, 2 };
	int dy[8] = { -1, 1, -2, 2, -2, 2, -1, 1 };
	bool visit[301][301];
	int t = 0;
	std::cin >> t;

	while (t--)
	{
		std::queue<std::pair<std::pair<int, int>, int>> q;
		int startX, startY, endX, endY, size;
		std::cin >> size;
		std::cin >> startX >> startY >> endX >> endY;

		memset(visit, false, sizeof visit);
		q.push(std::make_pair(std::make_pair(startX, startY), 0));
		visit[startY][startX] = true;

		while (!q.empty())
		{
			std::pair<int, int> cur = q.front().first;
			int curX = cur.first;
			int curY = cur.second;
			int step = q.front().second;
			q.pop();

			if (curX == endX && curY == endY)
			{
				std::cout << step << "\n";
				break;
			}

			for (int i = 0; i < 8; ++i)
			{
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX >= 0 && nextX < size && nextY >= 0 && nextY < size && !visit[nextY][nextX])
				{
					q.push(std::make_pair(std::make_pair(nextX, nextY), step + 1));
					visit[nextY][nextX] = true;
				}
			}
		}
	}
	return 0;
}