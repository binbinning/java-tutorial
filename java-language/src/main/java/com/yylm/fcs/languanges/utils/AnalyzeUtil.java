package com.yylm.fcs.languanges.utils;

import java.lang.reflect.Method;

/**
 * 
 * java语言学习分析工具
 * 
 * @author ningjb
 */
public class AnalyzeUtil {
	// set DEBUG = false and compile to stop debug messages
	final static boolean DEBUG = true;

	/**
	 * 检查对象的方法表
	 * 
	 * @param dragon
	 */
	public static void printMethodSignatures(Object obj) {
		log("methods list start:\n");
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			// skip method in Object
			if (methods[i].getDeclaringClass().getName().equals("java.lang.Object")) {
				continue;
			}
			log("\t" + methods[i].getDeclaringClass().getName() + "." + methods[i].getName() + "(");
			@SuppressWarnings("rawtypes")
			Class[] parmTypes = methods[i].getParameterTypes();
			for (int j = 0; j < parmTypes.length; j++) {
				log(parmTypes[j].getName());
				if (j < (parmTypes.length - 1)) {
					log(",");
				}
			}
			log(")\n");
		}
		log("methods list finished!\n");
	}

	public static void log(String str) {
		if (DEBUG) {
			System.out.print(str);
		}
	}

	/**
	 * 暂停应用以便分析
	 */
	public static void waitForVisualVM() {
		try {
			Thread.sleep(1000 * 60 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
