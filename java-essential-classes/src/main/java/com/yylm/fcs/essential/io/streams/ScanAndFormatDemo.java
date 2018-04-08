package com.yylm.fcs.essential.io.streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Scanning and Formatting
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/scanfor.html
 *
 */
public class ScanAndFormatDemo {

	public static void main(String[] args) throws IOException {
		scanSum();
		format();
	}

	/**
	 * Objects of type Scanner are useful for breaking down formatted input into
	 * tokens and translating individual tokens according to their data type.
	 * 
	 * @throws IOException
	 */
	public static void scanXan() throws IOException {
		Scanner s = null;

		try {
			s = new Scanner(new BufferedReader(new FileReader("target/xanadu.txt")));
			while (s.hasNext()) {
				System.out.println(s.next());
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	/**
	 * Reads a list of double values and adds them up.
	 * 
	 * @throws IOException
	 */
	public static void scanSum() throws IOException {
		Scanner s = null;
		double sum = 0;

		try {
			s = new Scanner(new BufferedReader(new FileReader("target/usnumbers.txt")));
			s.useLocale(Locale.US);

			while (s.hasNext()) {
				if (s.hasNextDouble()) {
					sum += s.nextDouble();
				} else {
					s.next();
				}
			}
		} finally {
			s.close();
		}

		System.out.println(sum);
	}

	/**
	 * Working from the right, the optional elements are: Precision. Width.
	 * Flags. Argument Index.
	 * 
	 * @see /java-language/src/main/java/com/yylm/fcs/languanges/numbers/TestFormat.java
	 */
	public static void format() {
		System.out.format("%f, %1$+020.10f %n", Math.PI);
	}
}
