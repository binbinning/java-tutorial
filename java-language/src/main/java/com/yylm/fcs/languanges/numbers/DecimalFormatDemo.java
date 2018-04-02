package com.yylm.fcs.languanges.numbers;

import java.text.*;

/**
 * Here is a sample program that illustrates the use of DecimalFormat:
 * 
 * @see https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
 *
 */
public class DecimalFormatDemo {

	static public void customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		System.out.println(value + "  " + pattern + "  " + output);
	}

	static public void main(String[] args) {

		customFormat("###,###.###", 123456.789);
		customFormat("###.##", 123456.789);
		customFormat("000000.000", 123.78);
		customFormat("$###,###.###", 12345.67);
	}
}
