package com.yylm.fcs.languanges.numbers;

import java.util.Calendar;
import java.util.Locale;

/**
 * 
 * The following program shows some of the formatting that you can do with
 * format. The output is shown within double quotes in the embedded comment:
 *
 * @see https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
 */
public class TestFormat {

	public static void main(String[] args) {
		long n = 461012;
		System.out.format("%d%n", n); // --> "461012"
		System.out.format("%08d%n", n); // --> "00461012"
		System.out.format("%+8d%n", n); // --> " +461012"
		System.out.format("%,8d%n", n); // --> " 461,012"
		System.out.format("%+,8d%n%n", n); // --> "+461,012"

		double pi = Math.PI;

		System.out.format("%f%n", pi); // --> "3.141593"
		System.out.format("%.3f%n", pi); // --> "3.142"
		System.out.format("%10.3f%n", pi); // --> " 3.142"
		System.out.format("%-10.3f%n", pi); // --> "3.142"
		System.out.format(Locale.FRANCE, "%-10.4f%n%n", pi); // --> "3,1416"

		Calendar c = Calendar.getInstance();
		System.out.format("%tB %te, %tY%n", c, c, c); // --> "May 29, 2006"

		System.out.format("%tl:%tM %tp%n", c, c, c); // --> "2:34 am"

		System.out.format("%tD%n", c); // --> "05/29/06"

		convertion();
	}

	// 格式转换
	public static void convertion() {
		System.out.println("格式转换");
		// 字符串转换为数字型
		// 最终调用的是java.lang.Integer.parseInt(String, int);
		System.out.println("Integer.parseInt(\"128\") = " + Integer.parseInt("128")); // 10进制
		System.out.println("Integer.parseInt(\"10000000\", 2) = " + Integer.parseInt("10000000", 2)); // 2进制
		System.out.println("Integer.valueOf(\"0xf0\", 16) = " + Integer.valueOf("80", 16)); // 16进制

		// String自带进制表示，使用decode
		System.out.println("Integer.decode(\"0xf0\")=" + Integer.decode("0x80")); // 16进制

		// int to string
		System.out.println(Integer.toString(128));

		// 进制转换
		System.out.println("Integer.toBinaryString(128) = " + Integer.toBinaryString(128));
		System.out.println("Integer.toOctalString(0x80) = " + Integer.toOctalString(0x80));
		System.out.println("Integer.toHexString(128)" + Integer.toHexString(128));
	}

}