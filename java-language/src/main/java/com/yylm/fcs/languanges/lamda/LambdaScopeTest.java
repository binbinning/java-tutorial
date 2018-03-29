package com.yylm.fcs.languanges.lamda;

import java.util.function.Consumer;

/**
 * 
 * lamda作用域 - lambda expressions do not have any shadowing issues. Lambda
 * expressions are lexically scoped. you can directly access fields, methods,
 * and local variables of the enclosing scope.
 */
public class LambdaScopeTest {

	public int x = 0;

	class FirstLevel {

		public int x = 1;

		void methodInFirstLevel(int x) {

			// The following statement causes the compiler to generate
			// the error "local variables referenced from a lambda expression
			// must be final or effectively final" in statement A:
			//
			// x = 99;

			Consumer<Integer> myConsumer = (y) -> {
				System.out.println("x = " + x); // Statement A
				System.out.println("y = " + y);
				System.out.println("this.x = " + this.x);
				System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
			};

			/**
			 * Lamda的匿名内部类对等实现
			 */
			Consumer<Integer> myConsumer1 = new Consumer<Integer>() {
				@Override
				public void accept(Integer y) {
					System.out.println("x = " + x); // Statement A
					System.out.println("y = " + y);
					// compile error
					// System.out.println("this.x = " + this.x);
					System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
				}
			};

			myConsumer.accept(x);
			myConsumer1.accept(x);

		}
	}

	public static void main(String... args) {
		LambdaScopeTest st = new LambdaScopeTest();
		LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
		fl.methodInFirstLevel(23);
	}
}
