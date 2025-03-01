package java_226_;

import java.util.Scanner;

public class Solve_equations {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nChon loai phuong trinh de giai:");
			System.out.println("1 - Phuong trinh bac nhat mot an (ax + b = 0)");
			System.out.println("2 - He phuong trinh bac nhat hai an");
			System.out.println("3 - Phuong trinh bac hai mot an (ax^2 + bx + c = 0)");
			System.out.println("0 - Thoat");
			System.out.print("Nhap lua chon: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				solveLinearEquation(scanner);
				break;
			case 2:
				solveLinearSystem(scanner);
				break;
			case 3:
				solveQuadraticEquation(scanner);
				break;
			case 0:
				System.out.println("Thoat chuong trinh.");
				scanner.close();
				return;
			default:
				System.out.println("Lua chon khong hop le. Vui long nhap lai!");
			}
		}
	}

	// ax + b = 0
	public static void solveLinearEquation(Scanner scanner) {
		System.out.print("Nhap a: ");
		double a = scanner.nextDouble();
		System.out.print("Nhap b: ");
		double b = scanner.nextDouble();

		if (a == 0) {
			if (b == 0) {
				System.out.println("Phuong trinh co vo so nghiem.");
			} else {
				System.out.println("Phuong trinh vo nghiem.");
			}
		} else {
			double x = -b / a;
			System.out.println("Nghiem cua phuong trinh: x = " + x);
		}
	}

	// He phuong trinh bac nhat hai an
	public static void solveLinearSystem(Scanner scanner) {
		System.out.println("Nhap he so cua he phuong trinh:");
		System.out.print("Nhap a11: ");
		double a11 = scanner.nextDouble();
		System.out.print("Nhap a12: ");
		double a12 = scanner.nextDouble();
		System.out.print("Nhap b1: ");
		double b1 = scanner.nextDouble();
		System.out.print("Nhap a21: ");
		double a21 = scanner.nextDouble();
		System.out.print("Nhap a22: ");
		double a22 = scanner.nextDouble();
		System.out.print("Nhap b2: ");
		double b2 = scanner.nextDouble();

		double D = a11 * a22 - a21 * a12;
		double D1 = b1 * a22 - b2 * a12;
		double D2 = a11 * b2 - a21 * b1;

		if (D != 0) {
			double x1 = D1 / D;
			double x2 = D2 / D;
			System.out.println("Nghiem cua he phuong trinh: x1 = " + x1 + ", x2 = " + x2);
		} else {
			if (D1 == 0 && D2 == 0) {
				System.out.println("He phuong trinh co vo so nghiem.");
			} else {
				System.out.println("He phuong trinh vo nghiem.");
			}
		}
	}

	// ax^2 + bx + c = 0
	public static void solveQuadraticEquation(Scanner scanner) {
		System.out.print("Nhap a: ");
		double a = scanner.nextDouble();
		System.out.print("Nhap b: ");
		double b = scanner.nextDouble();
		System.out.print("Nhap c: ");
		double c = scanner.nextDouble();

		if (a == 0) {
			System.out.println("He so a phai khac 0. Đay khong phai la phuong trinh bac hai!");
			return;
		}

		double delta = b * b - 4 * a * c;

		if (delta > 0) {
			double x1 = (-b + Math.sqrt(delta)) / (2 * a);
			double x2 = (-b - Math.sqrt(delta)) / (2 * a);
			System.out.println("Phuong trinh co hai nghiem phan biet:");
			System.out.println("x1 = " + x1 + ", x2 = " + x2);
		} else if (delta == 0) {
			double x = -b / (2 * a);
			System.out.println("Phuong trinh co nghiem kep: x = " + x);
		} else {
			System.out.println("Phuong trinh vo nghiem.");
		}
	}
}
