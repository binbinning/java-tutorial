package com.yylm.fcs.languanges.lamda;

/**
 * 
 * 用于查看lamda class文件结构
 *
 */
public class Calculator {

	interface IntegerMath {
		int operation(int a, int b);
	}

	public int operateBinary(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}

	public static void main(String... args) {

		Calculator myApp = new Calculator();
		/**
		 * <pre>
		8: invokedynamic #32,  0             // InvokeDynamic #0:operation:()Lcom/yylm/fcs/languanges/lamda/Calculator$IntegerMath;
		
		private static int lambda$0(int, int);
		descriptor: (II)I
		flags: ACC_PRIVATE, ACC_STATIC, ACC_SYNTHETIC
		Code:
		stack=2, locals=2, args_size=2
		 0: iload_0
		 1: iload_1
		 2: iadd
		 3: ireturn
		LineNumberTable:
		line 21: 0
		LocalVariableTable:
		Start  Length  Slot  Name   Signature
		    0       4     0     a   I
		    0       4     1     b   I
		 * 
		 * </pre>
		 */
		IntegerMath addition = (a, b) -> a + b;
		// IntegerMath subtraction = (a, b) -> a - b;
		System.out.println("40 + 2 = " + myApp.operateBinary(40, 2, addition));
		System.out.println("20 - 10 = " + myApp.operateBinary(20, 10, (a, b) -> a - b));
	}
}