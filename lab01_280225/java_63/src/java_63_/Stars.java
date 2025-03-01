package java_63_;

import java.util.Scanner;

public class Stars {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the height of the triangle (n): ");
		int n = scanner.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		scanner.close();
	}
}
