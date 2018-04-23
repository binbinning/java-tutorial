package com.yylm.fcs.essential.io.files;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Random Access Files using SeekableByteChannel and FileChannel
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/rafs.html
 *
 */
public class RandomAccessDemo {
	public static void main(String[] args) {
		String s = "I was here!\n";
		byte data[] = s.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);

		ByteBuffer copy = ByteBuffer.allocate(12);
		Path file = Paths.get("target/characteroutput.txt");
		try (FileChannel fc = (FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE))) {
			// Read the first 12 bytes of the file.
			int nread;
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

			// Write "I was here!" at the beginning of the file.
			fc.position(0);
			while (out.hasRemaining()) {
				fc.write(out);
			}
			out.rewind();

			// Move to the end of the file. Copy the first 12 bytes to
			// the end of the file. Then write "I was here!" again.
			long length = fc.size();
			fc.position(length - 1);
			copy.flip();
			while (copy.hasRemaining()) {
				fc.write(copy);
			}
			while (out.hasRemaining()) {
				fc.write(out);
			}
		} catch (IOException x) {
			System.out.println("I/O Exception: " + x);
		}
	}
}