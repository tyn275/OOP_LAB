package java_64_;

import java.util.Scanner;

public class Display {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int year;
		String month;

		while (true) {
			System.out.print("Enter a year (non-negative): ");
			year = scanner.nextInt();
			if (year >= 0) {
				break;
			}
			System.out.println("Invalid year. Please enter a non-negative number.");
		}

		scanner.nextLine();

		while (true) {
			System.out.print("Enter a month (name, abbreviation, or number 1-12): ");
			month = scanner.nextLine().trim();

			int days = getDaysInMonth(month, year);
			if (days != -1) {
				System.out.println("The number of days in " + month + " " + year + " is: " + days);
				break;
			} else {
				System.out.println("Invalid month. Please try again.");
			}
		}

		scanner.close();
	}

	public static int getDaysInMonth(String month, int year) {
		switch (month.toLowerCase()) {
		case "january":
		case "jan.":
		case "jan":
		case "1":
		case "march":
		case "mar.":
		case "mar":
		case "3":
		case "may":
		case "5":
		case "july":
		case "jul.":
		case "jul":
		case "7":
		case "august":
		case "aug.":
		case "aug":
		case "8":
		case "october":
		case "oct.":
		case "oct":
		case "10":
		case "december":
		case "dec.":
		case "dec":
		case "12":
			return 31;

		case "april":
		case "apr.":
		case "apr":
		case "4":
		case "june":
		case "jun.":
		case "jun":
		case "6":
		case "september":
		case "sep.":
		case "sep":
		case "9":
		case "november":
		case "nov.":
		case "nov":
		case "11":
			return 30;

		case "february":
		case "feb.":
		case "feb":
		case "2":
			return isLeapYear(year) ? 29 : 28;

		default:
			return -1;
		}
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
}
