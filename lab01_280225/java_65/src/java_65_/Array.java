package java_65_;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of elements: ");
		int size = scanner.nextInt();

		double[] numbers = new double[size];

		System.out.println("Enter " + size + " numbers:");
		for (int i = 0; i < size; i++) {
			numbers[i] = scanner.nextDouble();
		}

		Arrays.sort(numbers);

		double sum = 0;
		for (double num : numbers) {
			sum += num;
		}
		double average = sum / size;

		System.out.println("Sorted array: " + Arrays.toString(numbers));
		System.out.println("Sum of elements: " + sum);
		System.out.println("Average value: " + average);

		scanner.close();
	}
}
