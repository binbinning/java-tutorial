package com.yylm.fcs.essential.io.streams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copy data from source to target using Java I/O streams
 * <li>Byte stream
 * <li>Char stream
 * <li>Buffered stream
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/streams.html
 *
 */
public class CopyWithStreams {

	public static void main(String[] args) throws IOException {
		copyBytes();
		copyCharacters();
		copyLines();
	}

	/**
	 * Programs use byte streams to perform input and output of 8-bit bytes.
	 * 
	 * @throws IOException
	 */
	public static void copyBytes() throws IOException {
		int readCount = 0; // 统计API调用次数
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("src/main/resources/xanadu.txt");
			out = new FileOutputStream("target/outagain.txt");
			int c;

			while ((c = in.read()) != -1) {
				readCount++;
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

		System.out.format("FileInputStream.read调用了%d次%n", readCount);
	}

	/**
	 * The Java platform stores character values using Unicode conventions.
	 * Character stream I/O automatically translates this internal format to and
	 * from the local character set.
	 * 
	 * @throws IOException
	 */
	public static void copyCharacters() throws IOException {
		int readCount = 0; // 统计API调用次数
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader("src/main/resources/xanadu.txt");
			out = new FileWriter("target/characteroutput.txt");

			int c;
			while ((c = in.read()) != -1) {
				readCount++;
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
		System.out.format("FileReader.read调用了%d次%n", readCount);
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
			inputStream = new BufferedReader(new FileReader("src/main/resources/xanadu.txt"));
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

}
