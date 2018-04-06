package com.yylm.fcs.essential.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * Exception try-catch-finally Demo
 * 
 * Definition: An exception is an event, which occurs during the execution of a
 * program, that disrupts the normal flow of the program's instructions.
 * 
 * Valid Java programming language code must honor <code>the Catch or Specify
 * Requirement</code>. This means that code that might throw certain exceptions
 * must be enclosed by either of the following:
 * 
 * <li>A try statement that catches the exception. The try must provide a
 * handler for the exception.
 * <li>A method that specifies that it can throw the exception. The method must
 * provide a throws clause that lists the exception.
 * 
 * Errors and runtime exceptions are collectively known as unchecked exceptions,
 * which are not subject to the Catch or Specify Requirement.
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/exceptions/putItTogether.html
 */
public class ListOfNumbers {
	private List<Integer> list;
	private static final int INIT_SIZE = 10;

	public ListOfNumbers() {
		list = new ArrayList<Integer>(INIT_SIZE);
		/**
		 * For循环的字节码表达
		 * 
		 * <pre>
		17: iconst_0
		18: istore_1
		19: goto          43
		...
		40: iinc          1, 1
		43: iload_1
		44: bipush        10
		46: if_icmplt     22
		 * </pre>
		 */
		for (int i = 0; i < INIT_SIZE; i++) {
			list.add(new Integer(i));
		}
	}

	/**
	 * try-catch-finally
	 * 
	 * <pre>
	 * Exception table:
	     from    to  target type
	         2    79    82   Class java/lang/IndexOutOfBoundsException
	         2    79   138   Class java/io/IOException
	         2   108   194   any
	       138   164   194   any
	 *
	 * </pre>
	 */
	public void writeList(String fileName) {
		PrintWriter out = null;
		try {
			System.out.println("Entering" + " try statement");

			// The FileWriter constructor throws IOException, which must be caught.
			out = new PrintWriter(new FileWriter(fileName));

			for (int i = 0; i < list.size(); i++) {
				// The get(int) method throws IndexOutOfBoundsException, which must be caught.
				out.println("Value at: " + i + " = " + list.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());

		} finally {

			// Important: The finally block is a key tool for preventing resource leaks.
			// When closing a file or otherwise recovering resources, place the code in a
			// finally block to ensure that resource is always recovered.

			if (out != null) {
				System.out.println("Closing PrintWriter");
				out.close();
			} else {
				System.out.println("PrintWriter not open");
			}
		}

	}

	/**
	 * 
	 * @param fileName
	 */
	public void readList(String fileName) {
		BufferedReader in = null;
		try {
			System.out.println("Entering" + " try statement");

			// The FileWriter constructor throws IOException, which must be caught.
			in = new BufferedReader(new FileReader(fileName));
			String aLine = null;
			while ((aLine = in.readLine()) != null) {
				System.out.println("reading a line: " + aLine);
				list.add(Integer.parseInt(aLine));
			}

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} finally {

			if (in != null) {
				System.out.println("Closing BufferedReader");
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("BufferedReader not open");
			}
		}

	}

	/**
	 * The try-with-resources statement is a try statement that declares one or more
	 * resources. A resource is an object that must be closed after the program is
	 * finished with it.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	static String readFirstLineFromFile(String path) throws IOException {
		// Any object that implements java.lang.AutoCloseable, which includes all
		// objects which implement java.io.Closeable, can be used as a resource.
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			return br.readLine();
		}
	}

	/**
	 * Open zip file and create output file with try-with-resources statement
	 * 
	 * @param zipFileName
	 * @param outputFileName
	 * @throws java.io.IOException
	 */
	public static void writeToFileZipFileContents(String zipFileName, String outputFileName)
			throws java.io.IOException {

		java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.US_ASCII;
		java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

		try (java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);
				java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)) {
			// Enumerate each entry
			for (Enumeration<? extends ZipEntry> entries = zf.entries(); entries.hasMoreElements();) {
				// Get the entry name and write it to the output file
				String newLine = System.getProperty("line.separator");
				String zipEntryName = entries.nextElement().getName() + newLine;
				writer.write(zipEntryName, 0, zipEntryName.length());
			}
		}
	}

	public static void main(String[] args) {
		ListOfNumbers listOfNumbers = new ListOfNumbers();
		listOfNumbers.readList("target/numbers.txt");
		listOfNumbers.writeList("target/numbers_out.txt");
	}
}
