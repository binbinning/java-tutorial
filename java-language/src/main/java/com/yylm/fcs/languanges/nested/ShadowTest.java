package com.yylm.fcs.languanges.nested;

public class ShadowTest {

	public int x = 0;

	class FirstLevel {

		public int x = 1;

		/**
		 * 字节码说明
		 * 
		 * <pre>
		 * {
		public int x;
		descriptor: I
		flags: ACC_PUBLIC
		
		final com.yylm.fcs.languanges.nested.ShadowTest this$0; // 外部类的this引用
		descriptor: Lcom/yylm/fcs/languanges/nested/ShadowTest;
		flags: ACC_FINAL, ACC_SYNTHETIC
		...
		7: ldc           #33                 // String x =
		 9: invokespecial #35                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
		12: iload_1                           // 此处引用的为x参数值，位于局部变量表
		...
		22: getstatic     #25                 // Field java/lang/System.out:Ljava/io/PrintStream;
		25: new           #31                 // class java/lang/StringBuilder
		28: dup
		29: ldc           #51                 // String this.x =
		31: invokespecial #35                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
		34: aload_0                            // 此处引用的为方法的this，位于局部变量表0位
		35: getfield      #17                 // Field x:I 
		....
		50: new           #31                 // class java/lang/StringBuilder
		53: dup
		54: ldc           #53                 // String ShadowTest.this.x =
		56: invokespecial #35                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
		59: aload_0                           // 通过两次this，获取到外部类的x成员变量
		60: getfield      #12                 // Field this$0:Lcom/yylm/fcs/languanges/nested/ShadowTest;
		63: getfield      #55                 // Field com/yylm/fcs/languanges/nested/ShadowTest.x:I
		 * 
		 * 
		 * </pre>
		 * 
		 * 
		 * 
		 * @param x
		 */
		void methodInFirstLevel(int x) {
			System.out.println("x = " + x);
			System.out.println("this.x = " + this.x);
			System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
		}
	}

	public static void main(String... args) {
		/**
		 * <pre>
		 * st 对象占用20个字节，field: x: int 
		 * fl 对象占用28个字节， field： x: int， this$0:ShadowTest
		 * java 内部类通过this指针因为外部类对象属性
		 * 
		 * </pre>
		 */
		ShadowTest st = new ShadowTest();
		ShadowTest.FirstLevel fl = st.new FirstLevel();
		fl.methodInFirstLevel(23);

		// 便于visualvm查看jvm内存对象
		try {
			Thread.sleep(1000 * 60 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}