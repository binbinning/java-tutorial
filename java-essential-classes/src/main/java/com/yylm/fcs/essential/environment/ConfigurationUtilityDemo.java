package com.yylm.fcs.essential.environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.yylm.fcs.languanges.utils.AnalyzeUtil;

/**
 * Configuration Utilities Usage demo
 *
 * @see https://docs.oracle.com/javase/tutorial/essential/environment/config.html
 */
public class ConfigurationUtilityDemo {

	public static void main(String[] args) throws IOException {
		PropertiesUsage();
		// EnvMapUsage();
		SystemPropertiesTest();
	}

	/**
	 * Setting Up the Properties Object and Saving Properties
	 * 
	 * @throws IOException
	 */
	public static void PropertiesUsage() throws IOException {
		// create and load default properties
		Properties defaultProps = new Properties();
		FileInputStream in = new FileInputStream("target/defaultProperties");
		defaultProps.load(in);
		in.close();
		printProperties(defaultProps);

		// create application properties with default
		Properties applicationProps = new Properties(defaultProps);
		// now load properties from last invocation
		in = new FileInputStream("target/appProperties");
		applicationProps.load(in);
		// applicationProps.putAll(defaultProps);
		in.close();
		printProperties(applicationProps);

		// update properties in file
		FileOutputStream out = new FileOutputStream("target/appProperties");
		applicationProps.store(out, "---No Comment---");
		out.close();
	}

	/**
	 * print properties
	 * 
	 * @param applicationProps
	 */
	private static void printProperties(Properties applicationProps) {
		// print out all properties
		for (Map.Entry<Object, Object> e : applicationProps.entrySet()) {
			System.out.println(e.getKey() + "=" + e.getValue());
		}
	}

	/**
	 * Querying Environment Variables
	 */
	public static void EnvMapUsage() {
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n", envName, env.get(envName));
		}
	}

	/**
	 * Writing System Properties
	 * 
	 * @throws IOException
	 */
	public static void SystemPropertiesTest() throws IOException {
		AnalyzeUtil.log("Enter SystemPropertiesTest...\n");
		// set up new properties object from file "myProperties.txt"
		FileInputStream propFile = new FileInputStream("target/appProperties");
		Properties p = new Properties(System.getProperties());
		p.load(propFile);
		printProperties(p);

		// set the system properties
		System.setProperties(p);
		// display new properties
		System.getProperties().list(System.out);
		AnalyzeUtil.log("Exit SystemPropertiesTest...\n");
	}
}
