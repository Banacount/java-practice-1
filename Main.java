import java.awt.Point;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

class Mortgage {
	final byte PERCENT = 100;
	final byte MONTH = 12;
	private double monthly_interest_rate;
	private int principal_loan;
	private int payment_number;

	Mortgage (int principal_loan, double annual_rate, int payment_number) {
		this.principal_loan = principal_loan;
		this.monthly_interest_rate = (annual_rate / PERCENT) / MONTH;
		this.payment_number = payment_number;
	}

	public double calculate () {
		double numerator = monthly_interest_rate * (
				Math.pow(
					(1 + this.monthly_interest_rate), 
					this.payment_number));

		double denominator = Math.pow(
				1 + monthly_interest_rate, 
				this.payment_number) - 1;

		double rawPayment = principal_loan * (numerator / denominator);

		return Math.round(rawPayment * 100.0) / 100.0;
	}
}

final class Separated {
	public static void PrimitiveTypes () {
		byte test_1 = 10;
		int test_2 = 123_456_789;
		float test_3 = 120.99F;

		System.out.println("-- Primitive types and shits --");
		System.out.printf("byte: %d\n", test_1);
		System.out.printf("integer: %d\n", test_2);
		System.out.printf("float: %f\n\n", test_3);
	}
	
	public static void ReferencesTypes () {
		Date now = new Date();
		Point point_1 = new Point(0, 0);
		Point point_2 = point_1;
		Point point_3 = point_2;
		
		System.out.println("-- References types --");
		System.out.println(now);

		System.out.println("\nOriginal or Initialized(Point) value:");
		System.out.println(point_1);
		System.out.println(point_2);
		System.out.println(point_3);

		point_1.x = 67;
		point_2.y = 76;

		System.out.println("Changed value to 67:");
		System.out.println(point_1);
		System.out.println(point_2);
		System.out.println(point_3);
	}

	public static void StringThings () {
		// Easy way to initialize a fukin string
		String non_complex = "  -> Your mum is gorgeous.";

		// Rad way to initialize a fukin string
		char[] rad = {'T', 'h', 'e', ' ', 'r', 'a', 'd', ' ', 'w', 'a', 'y', '.', '\n'};

		System.out.println("");
		System.out.println(non_complex);
		System.out.println(rad);

		System.out.println("'non_complex' string properties:");
		System.out.println(non_complex.length());
		System.out.println(non_complex.strip());
		System.out.println(non_complex.strip().concat(" Concatenated."));
		System.out.println(non_complex.strip().indexOf("the"));
		System.out.println(non_complex.strip().replace("gorgeous", "fat"));
		System.out.println(non_complex.strip().replaceAll("o", "0"));

		System.out.println("Next line:\n\"How did I do this?\"");
		System.out.println("\t\"Now what about this thing?? lmao\"");
		System.out.println("\t\"A whole lotta backslashes.\"\n\n");
	}

	public static void ArrayThings () {
		// 1D
		int[] number_array = { 20, 56, 123, 67, 534 };
		System.out.println("Original array: " + Arrays.toString(number_array));

		// Sorting the shitter
		Arrays.sort(number_array);
		System.out.println("Sorted array: " + Arrays.toString(number_array));

		// 2D
		int[][] two_d_array = {
			{10, 20, 30, 40, 50},
			{60, 70, 80, 90, 100},
			{110, 120, 130, 140, 150}
		};
		System.out.println("\n2D Array: " + Arrays.deepToString(two_d_array));

		// Constants - cannot be changed.
		final String your_mum = "A constant string lmao";
		System.out.println(your_mum);
	}

	public static void Parsings () {
		// Implicit and Explicit fukin type casting should be easy asf
		// Like implicit is automatic convertion from type to another
		// While explicit is the same but manual.
		//
		// Now what about parsing??
		String test_num = "67";
		int cast_me = 20 + Integer.parseInt(test_num);
		System.out.println(cast_me);

		System.out.println("\nRandom number generator from 20 - 30:");
		int random_num = (int)(Math.random() * (30 - 20 + 1)) + 20;
		System.out.println("Random number: " + random_num);

		// Dollar Dollar
		double my_bank_account_someday = 100_112_123.67;
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String my_total_fr = currency.format(my_bank_account_someday);
		System.out.println("My bank account: " + my_total_fr);
	}

	public static void TestMortgage (Scanner scan) {
		System.out.println("\n-- Mortgage calculator shits --");

		int principal;
		double monthly;
		byte years;

		while (true) {
			try {
				System.out.print("Principal: ");             principal = scan.nextInt();
				System.out.print("Annual interest rate: ");  monthly = scan.nextDouble();
				System.out.print("Period (Years): ");        years = scan.nextByte();
				break;
			} catch (Exception err) {
				System.out.println("Invalid input...");
				scan.nextLine();
				continue;
			}
		}

		Mortgage basic = new Mortgage(principal, monthly, years * 12);
		NumberFormat currency = NumberFormat.getCurrencyInstance();

		System.out.println("\nMortage result: " + currency.format(basic.calculate()));
	}

	public static void FizzBuzz (Scanner scan) {
		System.out.println("\n-- FizzBuzz --");
		System.out.print("Number: ");
		int user_num = scan.nextInt();
		scan.close();

		String fizz = (user_num % 5 == 0 ? "Fizz" : "");
		String buzz = (user_num % 3 == 0 ? "Buzz" : "");
		String result = fizz + buzz;

		if (result.strip() == "") 
			System.out.println(user_num);
		else
			System.out.println(result);
	}
}

class Main {
	public static void main (String[] args) {
		// Primitive Types
		Separated.PrimitiveTypes();

		// References Types
		Separated.ReferencesTypes();

		// String thingies
		Separated.StringThings();

		// Arrays
		Separated.ArrayThings();

		// Parse things
		Separated.Parsings();

		// User input
		/*
		Scanner in_user = new Scanner(System.in);
		System.out.print("You're a: ");
		String test_in_1 = in_user.nextLine();
		in_user.close();

		System.out.println("Are you a " + test_in_1.strip() + "?");
		*/

		// Test Mortgage class
		//Scanner scan = new Scanner(System.in);
		//Separated.TestMortgage(scan);

		// Fizzbuzz problemo
		//Separated.FizzBuzz(scan);

		// For loop process
		System.out.println("\nFor loop: ");
		for (byte i = 10; i >= 0; i--)
			System.out.printf("%d ", i);

		System.out.println("\nWhile loop: ");
		byte i = 10;

		while (i >= 0) {
			System.out.printf("%d ", i);
			i--;
		}

		System.out.println("\n\nExample while loop program: ");

		Scanner in_user = new Scanner(System.in);
		while (true) {
			System.out.print("Say the n-word: ");
			String user_input = in_user.nextLine();

			if (user_input.equals("nigga")) {
				System.out.println("That's right!");
				break;
			} else if (user_input.equals("nigger")) {
				System.out.println("Too wild.");
				System.out.println("Close but not quite there..");
			} else {
				System.out.println("Wrong dud, jus say it..");
			}
		}
		in_user.close();

		// I'll finish this later, I stopped at ('While loops' / 2:09:53) [Java Full Course for Beginners]
	}
}
