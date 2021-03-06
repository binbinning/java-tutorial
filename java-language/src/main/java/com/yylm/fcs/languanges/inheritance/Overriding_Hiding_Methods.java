package com.yylm.fcs.languanges.inheritance;

import com.yylm.fcs.languanges.utils.AnalyzeUtil;

/**
 * Overriding and Hiding Methods
 * 
 * The distinction between hiding a static method and overriding an instance
 * method has important implications:
 * <li>The version of the overridden instance method that gets invoked is the
 * one in the subclass.</li>
 * <li>The version of the hidden static method that gets invoked depends on
 * whether it is invoked from the superclass or the subclass.</li>
 *
 */
public class Overriding_Hiding_Methods {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Cat myCat = new Cat();
		Animal_ myAnimal = myCat;

		/**
		 * 方法解析的静态分派，动态分派
		 * 
		 * <pre>
		10: invokestatic  #35                 // Method com/yylm/fcs/languanges/inheritance/Animal.testClassMethod:()V
		13: aload_2
		14: invokevirtual #37                 // Method com/yylm/fcs/languanges/inheritance/Animal.testInstanceMethod:()V
		 * </pre>
		 */
		Animal_.testClassMethod();
		// 检查方法表
		AnalyzeUtil.printMethodSignatures(myAnimal);
		myAnimal.testClassMethod(); // 编译期解析 invokestatic
		myAnimal.testInstanceMethod();

		/**
		 * 动态单分派，编译后方法签名已经确定，根据实际类型确定虚方法表的调用
		 * 
		 * <pre>
		 *  27: invokevirtual #35 // Method com/yylm/fcs/languanges/inheritance/Animal_.sayHello:(Ljava/lang/Object;)V
		 * </pre>
		 */
		myAnimal.sayHello("");

		/**
		 * 静态分派
		 * 
		 * <pre>
		 33: invokevirtual #38 // Method com/yylm/fcs/languanges/inheritance/Cat.sayHello:(Ljava/lang/String;)V
		 * </pre>
		 */
		myCat.sayHello("");
	}
}

class Animal_ {
	public static void testClassMethod() {
		System.out.println("The static method in Animal");
	}

	public void testInstanceMethod() {
		System.out.println("The instance method in Animal");
	}

	public void sayHello(Object obj) {
		System.out.println("Hello Animal");
	}
}

class Cat extends Animal_ {

	public static void testClassMethod() {
		System.out.println("The static method in Cat");
	}

	@Override
	public void testInstanceMethod() {
		System.out.println("The instance method in Cat");
	}

	@Override
	public void sayHello(Object obj) {
		System.out.println("Hello Cat with obj.");
	}

	public void sayHello(String obj) {
		System.out.println("Hello Cat with string");
	}

}
