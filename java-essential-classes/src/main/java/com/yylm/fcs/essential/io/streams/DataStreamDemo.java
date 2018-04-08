package com.yylm.fcs.essential.io.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Data streams support binary I/O of primitive data type values (boolean, char,
 * byte, short, int, long, float, and double) as well as String values.
 * 
 * Note: DataStreams uses one very bad programming technique: it uses floating
 * point numbers to represent monetary values. The correct type to use for
 * currency values is java.math.BigDecimal.
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/datastreams.html
 *
 */
public class DataStreamDemo {
	static final String dataFile = "target/invoicedata";

	static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
	static final int[] units = { 12, 8, 13, 29, 50 };
	static final String[] descs = { "Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain" };

	public static void main(String[] args) throws IOException {
		writeData();
		readData();
	}

	/**
	 * write data out to a file using DataOutputStream
	 * 
	 * @throws IOException
	 */
	public static void writeData() throws IOException {
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for (int i = 0; i < prices.length; i++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		}
	}

	/**
	 * read data in
	 * 
	 * @throws IOException
	 */
	public static void readData() throws IOException {
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {

			double price;
			int unit;
			String desc;
			double total = 0.0;

			try {
				while (true) {
					price = in.readDouble();
					unit = in.readInt();
					desc = in.readUTF();
					System.out.format("You ordered %d" + " units of %s at $%.2f%n", unit, desc, price);
					total += unit * price;
				}
			} catch (EOFException e) {
			}

			System.out.println(total);

		}
	}

}
