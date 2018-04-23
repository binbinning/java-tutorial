package com.yylm.fcs.essential.environment;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Exercises
 * 
 * Write an application, PersistentEcho, with the following features:
 * 
 * <ul>
 * <li>If PersistentEcho is run with command line arguments, it prints out those
 * arguments. It also saves the string printed out to a property, and saves the
 * property to a file called PersistentEcho.txt</li>
 * 
 * <li>If PersistentEcho is run with no command line arguments, it looks for an
 * environment variable called PERSISTENTECHO. If that variable exists,
 * PersistentEcho prints out its value, and also saves the value in the same way
 * it does for command line arguments.</li>
 * 
 * <li>If PersistentEcho is run with no command line arguments, and the
 * PERSISTENTECHO environment variable is not defined, it retrieves the property
 * value from PersistentEcho.txt and prints that out.
 * <li>
 * </ul>
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/environment/QandE/answers.html
 *
 */
public class PersistentEcho {

	public static void main(String[] args) {
		String argString = "";
		boolean notProperty = true;

		// Are there arguments? If so retrieve them.
		if (args.length > 0) {
			for (String arg : args) {
				argString += arg + " ";
			}
			argString = argString.trim();
		} else if ((argString = System.getenv("PERSISTENTECHO")) != null) {
			// No arguments, is there an environment variable?
			// If so, retrieve it.
		} else {
			// No environment variable either. Retrieve property value.
			notProperty = false;
			// Set argString to null. If it's still null after we exit the try block,
			// we've failed to retrieve the property value.
			argString = null;
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream("target/PersistentEcho.txt");
				Properties inProperties = new Properties();
				inProperties.load(fileInputStream);
				argString = inProperties.getProperty("argString");
			} catch (IOException e) {
				System.err.println("Can't read property file.");
				System.exit(1);
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
					}
					;
				}
			}
		}
		if (argString == null) {
			System.err.println("Couldn't find argString property");
			System.exit(1);
		}

		// Somehow, we got the value. Echo it already!
		System.out.println(argString);

		// If we didn't retrieve the value from the property,
		// save it in the property.
		if (notProperty) {
			Properties outProperties = new Properties();
			outProperties.setProperty("argString", argString);
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream("target/PersistentEcho.txt");
				outProperties.store(fileOutputStream, "PersistentEcho properties");
			} catch (IOException e) {
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
					}
					;
				}
			}
		}
	}
}