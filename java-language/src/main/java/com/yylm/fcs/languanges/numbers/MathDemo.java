package com.yylm.fcs.languanges.numbers;

// Using the static import language feature, you don't have to write Math in front of every math function:
import static java.lang.Math.*;
import static com.yylm.fcs.languanges.utils.AnalyzeUtil.*;

/**
 * 
 * Math usage
 *
 */
public class MathDemo {
	public static void main(String[] args) {
		basicMathDemo();
		exponentialDemo();
		trigonometricDemo();
	}

	/**
	 * 基本运算
	 */
	private static void basicMathDemo() {
		log("Enter basicMathDemo\n");
		double a = -191.635;
		double b = 43.74;
		int c = 16, d = 45;

		System.out.printf("The absolute value " + "of %.3f is %.3f%n", a, abs(a));

		System.out.printf("The ceiling of " + "%.2f is %.0f%n", b, ceil(b));

		System.out.printf("The floor of " + "%.2f is %.0f%n", b, floor(b));

		System.out.printf("The rint of %.2f " + "is %.0f%n", b, rint(b));

		System.out.printf("The max of %d and " + "%d is %d%n", c, d, max(c, d));

		System.out.printf("The min of of %d " + "and %d is %d%n", c, d, min(c, d));

		log("Exit basicMathDemo\n");
	}

	/**
	 * 平方对数
	 */
	public static void exponentialDemo() {
		log("Enter exponentialDemo\n");

		double x = 11.635;
		double y = 2.76;

		System.out.printf("The value of " + "e is %.4f%n", Math.E);

		System.out.printf("exp(%.3f) " + "is %.3f%n", x, Math.exp(x));

		System.out.printf("log(%.3f) is " + "%.3f%n", x, Math.log(x));

		System.out.printf("pow(%.3f, %.3f) " + "is %.3f%n", x, y, Math.pow(x, y));

		System.out.printf("sqrt(%.3f) is " + "%.3f%n", x, Math.sqrt(x));

		log("Exit exponentialDemo\n");
	}

	/**
	 * 三角函数
	 */
	public static void trigonometricDemo() {
		log("Enter trigonometricDemo\n");
		double degrees = 45.0;
		double radians = Math.toRadians(degrees);

		System.out.format("The value of pi " + "is %.4f%n", Math.PI);

		System.out.format("The sine of %.1f " + "degrees is %.4f%n", degrees, Math.sin(radians));

		System.out.format("The cosine of %.1f " + "degrees is %.4f%n", degrees, Math.cos(radians));

		System.out.format("The tangent of %.1f " + "degrees is %.4f%n", degrees, Math.tan(radians));

		System.out.format("The arcsine of %.4f " + "is %.4f degrees %n", Math.sin(radians),
				Math.toDegrees(Math.asin(Math.sin(radians))));

		System.out.format("The arccosine of %.4f " + "is %.4f degrees %n", Math.cos(radians),
				Math.toDegrees(Math.acos(Math.cos(radians))));

		System.out.format("The arctangent of %.4f " + "is %.4f degrees %n", Math.tan(radians),
				Math.toDegrees(Math.atan(Math.tan(radians))));

		log("Exit trigonometricDemo\n");
	}

}
