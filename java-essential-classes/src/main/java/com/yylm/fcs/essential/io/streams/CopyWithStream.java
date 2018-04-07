package com.yylm.fcs.essential.io.streams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * copy data from source to target using Java I/O streams
 * <li>Byte stream
 * <li>Char stream
 * <li>Buffered stream
 * <li>Scanner
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/streams.html
 *
 */
public class CopyWithStream {

	public static void main(String[] args) throws IOException {
		copyBytes();
		copyCharacters();
		scanXan();
	}

	/**
	 * Programs use byte streams to perform input and output of 8-bit bytes.
	 * 
	 * @throws IOException
	 */
	public static void copyBytes() throws IOException {

		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("target/xanadu.txt");
			out = new FileOutputStream("target/outagain.txt");
			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * he Java platform stores character values using Unicode conventions. Character
	 * stream I/O automatically translates this internal format to and from the
	 * local character set.
	 * 
	 * @throws IOException
	 */
	public static void copyCharacters() throws IOException {
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader("target/xanadu.txt");
			out = new FileWriter("target/characteroutput.txt");

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Line-Oriented I/O
	 * 
	 * @throws IOException
	 */
	public static void copyLines() throws IOException {

		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("target/xanadu.txt"));
			outputStream = new PrintWriter(new FileWriter("target/characteroutput.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				outputStream.println(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * Objects of type Scanner are useful for breaking down formatted input into
	 * tokens and translating individual tokens according to their data type.
	 * 
	 * @throws IOException
	 */
	public static void scanXan() throws IOException {
		Scanner s = null;

		try {
			s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));
			while (s.hasNext()) {
				System.out.println(s.next());
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
