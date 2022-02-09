#include <iostream>

void applyPower(int ans[5][5], int A[5][5], long long n);
void product(int A[5][5], int B[5][5]);

void processInput();
void printAnswer();

long long N, B;
int ans[5][5];
int A[5][5];

int main()
{
	processInput();
	applyPower(ans, A, B);
	printAnswer();

	return 0;
}

void applyPower(int ans[5][5], int A[5][5], long long n)
{
	if (n == 1)
	{
		product(ans, A);
	}
	else
	{
		applyPower(ans, A, n / 2);
		product(ans, ans);

		if (n % 2 == 1) product(ans, A);
	}
}

void product(int A[5][5], int B[5][5])
{
	int temp[5][5];
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			temp[i][j] = 0;
			for (int k = 0; k < N; ++k)
			{
				temp[i][j] += (A[i][k] * B[k][j]);
			}
			temp[i][j] %= 1000;
		}
	}

	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			A[i][j] = temp[i][j];
		}
	}
}

void processInput()
{
	std::cin >> N >> B;
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			std::cin >> A[i][j];
		}
		ans[i][i] = 1;
	}
}

void printAnswer()
{
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			std::cout << ans[i][j] << " ";
		}
		std::cout << "\n";
	}
}