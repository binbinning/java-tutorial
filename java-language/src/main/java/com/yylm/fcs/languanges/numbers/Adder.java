package com.yylm.fcs.languanges.numbers;

/**
 * Create a program that reads an unspecified number of integer arguments from
 * the command line and adds them together.
 * 
 * @see https://docs.oracle.com/javase/tutorial/java/data/QandE/numbers-answers.html
 *
 */
public class Adder {

	public static void main(String[] args) {
		int numArgs = args.length;

		// this program requires at least two arguments on the command line
		if (numArgs < 2) {
			System.out.println("This program requires two command-line arguments.");
		} else {
			int sum = 0;
			for (int i = 0; i < args.length; i++) {
				sum += Integer.parseInt(args[i]);
			}

			// print the sum
			System.out.println("the sum of the argument is " + sum);
		}
	}

}
