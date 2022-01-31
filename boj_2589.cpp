#include <iostream>
#include <queue>

class Node
{
public:
	int x;
	int y;
	int depth;
};

void bfs(Node n);

int max;
int row, col;
std::queue<Node> q;
char map[52][52];
bool check[52][52];

int main()
{
	std::cin >> row >> col;

	for (int i = 1; i <= row; ++i)
	{
		for (int j = 1; j <= col; ++j)
		{
			std::cin >> map[i][j];
		}
	}

	for (int i = 1; i <= row; ++i)
	{
		for (int j = 1; j <= col; ++j)
		{
			if (map[i][j] == 'L')
			{
				for (int a = 1; a <= row; ++a)
				{
					for (int b = 1; b <= col; ++b)
					{
						check[a][b] = false;
					}
				}

				Node node;
				node.x = i;
				node.y = j;
				node.depth = 0;
				check[i][j] = true;

				bfs(node);
			}
		}
	}

	std::cout << max;

	return 0;
}


void bfs(Node n)
{
	int d[4][2] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	q.push(n);

	while (!q.empty())
	{
		Node node = q.front();
		q.pop();
		max = node.depth > max ? node.depth : max;

		for (int i = 0; i < 4; ++i)
		{
			int x = node.x + d[i][0];
			int y = node.y + d[i][1];
			if (map[x][y] == 'L' && check[x][y] == false)
			{
				Node newNode;
				newNode.x = x;
				newNode.y = y;
				newNode.depth = node.depth + 1;
				q.push(newNode);
				check[x][y] = true;
			}
		}
	}
}