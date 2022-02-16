import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long k = sc.nextLong();

		boolean rev = false;
		while (k > 2) {
			int power = 0;
			while (k > (1L << power)) {
				power++;
			}
			power--;

			k -= (1L << power);
			rev = !rev;
		}

		long a = ((k + 1) % 2);
		if (rev)
			System.out.println(1 - a);
		else
			System.out.println(a);
	}
}