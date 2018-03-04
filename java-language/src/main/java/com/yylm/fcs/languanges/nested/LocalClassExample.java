/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.yylm.fcs.languanges.nested;

/**
 * local class example. 这个例子实际应用场景不好！演示local class的访问规则。
 * 
 * Definition: Local classes are classes that are defined in a block, which is a
 * group of zero or more statements between balanced braces. You typically find
 * local classes defined in the body of a method.
 * 
 * 访问规则：
 * <ul>
 * <li>1. A local class has access to the members of its enclosing class.</li>
 * <li>2. A local class has access to local variables. However, a local class
 * can only access local variables that are declared final or effectively
 * final(1.8).</li>
 * <li>3. it can access the method's parameters.</li>
 * </ul>
 * 
 * @See https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html
 *
 */
public class LocalClassExample {

	static String regularExpression = "[^0-9]";

	public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {

		// final int numberLength = 10;

		// Valid in JDK 8 and later:
		int numberLength = 10;

		/**
		 * local class的字节码，合成构造函数，和对外部方法参数的访问。因为外部类是static，所以内部类没有this指针。
		 * 
		 * <pre>
		 * 
		java.lang.String formattedPhoneNumber;
		descriptor: Ljava/lang/String;
		flags:
		
		private final java.lang.String val$phoneNumber1;   // 合成的成员，访问外部方法参数
		descriptor: Ljava/lang/String;
		flags: ACC_PRIVATE, ACC_FINAL, ACC_SYNTHETIC
		
		private final java.lang.String val$phoneNumber2;
		descriptor: Ljava/lang/String;
		flags: ACC_PRIVATE, ACC_FINAL, ACC_SYNTHETIC
		
		com.yylm.fcs.languanges.nested.LocalClassExample$1PhoneNumber(java.lang.String, int, java.lang.String, java.lang.String);
		descriptor: (Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
		 * </pre>
		 *
		 */
		class PhoneNumber {

			String formattedPhoneNumber = null;

			PhoneNumber(String phoneNumber) {
				// numberLength = 7;
				String currentNumber = phoneNumber.replaceAll(regularExpression, "");
				if (currentNumber.length() == numberLength)
					formattedPhoneNumber = currentNumber;
				else
					formattedPhoneNumber = null;
			}

			public String getNumber() {
				return formattedPhoneNumber;
			}

			// Valid in JDK 8 and later:
			public void printOriginalNumbers() {
				System.out.println("Original numbers are " + phoneNumber1 + " and " + phoneNumber2);
			}
		}

		PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
		PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

		// Valid in JDK 8 and later:

		myNumber1.printOriginalNumbers();

		if (myNumber1.getNumber() == null)
			System.out.println("First number is invalid");
		else
			System.out.println("First number is " + myNumber1.getNumber());
		if (myNumber2.getNumber() == null)
			System.out.println("Second number is invalid");
		else
			System.out.println("Second number is " + myNumber2.getNumber());

	}

	public static void main(String... args) {
		validatePhoneNumber("123-456-7890", "456-7890");
	}
}
