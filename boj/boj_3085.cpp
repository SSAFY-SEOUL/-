#include <iostream>

void swap(int x1, int y1, int x2, int y2);
int checkRow(int x, int y);
int checkCol(int x, int y);

char board[52][52];
int n = 0;

int main()
{
	int max = 0;
	std::cin >> n;
	for (int i = 1; i <= n; ++i)
	{
		for (int j = 1; j <= n; ++j)
		{
			std::cin >> board[i][j];
		}
	}

	int tmp;
	for (int i = 1; i <= n; ++i)
	{
		for (int j = 1; j <= n; ++j)
		{
			tmp = checkRow(i, j);
			max = max > tmp ? max: tmp;
			tmp = checkCol(i, j);
			max = max > tmp ? max: tmp;
		}
	}

	for (int i = 1; i <= n; ++i)
	{
		for (int j = 1; j <= n; ++j)
		{
			// left
			swap(i, j, i - 1, j);
			tmp = checkRow(i, j);
			max = max > tmp ? max : tmp;
			tmp = checkCol(i, j);
			max = max > tmp ? max : tmp;
			swap(i, j, i - 1, j);

			// right
			swap(i, j, i + 1, j);
			tmp = checkRow(i, j);
			max = max > tmp ? max : tmp;
			tmp = checkCol(i, j);
			max = max > tmp ? max : tmp;
			swap(i, j, i + 1, j);

			// up
			swap(i, j, i, j - 1);
			tmp = checkRow(i, j);
			max = max > tmp ? max : tmp;
			tmp = checkCol(i, j);
			max = max > tmp ? max : tmp;
			swap(i, j, i, j - 1);

			// down
			swap(i, j, i, j + 1);
			tmp = checkRow(i, j);
			max = max > tmp ? max : tmp;
			tmp = checkCol(i, j);
			max = max > tmp ? max : tmp;
			swap(i, j, i, j + 1);
		}
	}

	std::cout << max;

	return 0;
}

void swap(int x1, int y1, int x2, int y2)
{
	char tmp = board[x1][y1];
	board[x1][y1] = board[x2][y2];
	board[x2][y2] = tmp;
}

int checkRow(int x, int y)
{
	char color = board[x][y];
	int count = 1;
	for (int x2 = x + 1; x2 <= n; ++x2)
	{
		if (board[x2][y] == color) count++;
		else break;
	}

	for (int x2 = x - 1; x2 >= 1; --x2)
	{
		if (board[x2][y] == color) count++;
		else break;
	}

	return count;
}

int checkCol(int x, int y)
{
	char color = board[x][y];
	int count = 1;
	for (int y2 = y + 1; y2 <= n; ++y2)
	{
		if (board[x][y2] == color) count++;
		else break;
	}

	for (int y2 = y - 1; y2 >= 1; --y2)
	{
		if (board[x][y2] == color) count++;
		else break;
	}

	return count;
}