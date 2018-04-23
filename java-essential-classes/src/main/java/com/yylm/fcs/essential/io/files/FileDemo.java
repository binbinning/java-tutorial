package com.yylm.fcs.essential.io.files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * File demo
 * 
 * <li>Files.isXXX() - Checking a File or Directory</li>
 * <li>Files.delete/copy/move() - to copy/move/delete files</li>
 * <li>Files.readAttributes() - Managing Metadata</li>
 * <li>Files.newXXXStream() - Reading, Writing, and Creating Files</li>
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/fileio.html
 */
public class FileDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * Checking a File or Directory
		 */
		// Microsoft Windows syntax
		Path path = Paths.get("C:\\home\\joe\\foo");
		if (Files.exists(path)) {
			System.out.println(path.toString() + " exists");
		} else {
			System.out.println(path.toString() + " does not exists|unknown");
		}

		/**
		 * Copying a File or Directory
		 */
		Path source = Paths.get("src/main/resources/xanadu.txt");
		Path target = Paths.get("target/xanadu_bk.txt");
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Basic File Attributes
		 */
		BasicFileAttributes attr = Files.readAttributes(source, BasicFileAttributes.class);

		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());

		/**
		 * Reading All Bytes or Lines from a File
		 */
		byte[] fileArray = Files.readAllBytes(source);
		Files.write(target, fileArray);

		/**
		 * Buffered I/O Methods for Text Files
		 */
		// Reading a File by Using Buffered Stream I/O
		Charset charset = Charset.forName("GBK");
		try (BufferedReader reader = Files.newBufferedReader(source, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			x.printStackTrace();
			System.err.format("IOException: %s%n", x);
		}
		// Writing a File by Using Buffered Stream I/O
		String s = "Hello World! ";
		try (BufferedWriter writer = Files.newBufferedWriter(target, charset)) {
			writer.write(s, 0, s.length());
		} catch (IOException x) {
			x.printStackTrace();
			System.err.format("IOException: %s%n", x);
		}

		/**
		 * Reading a File by Using Stream I/O
		 */
		try (InputStream in = Files.newInputStream(source);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			System.err.println(x);
		}

		// Convert the string to a byte array.
		byte data[] = s.getBytes();
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(target, CREATE, APPEND))) {
			out.write(data, 0, data.length);
		} catch (IOException x) {
			System.err.println(x);
		}

		/**
		 * Reading and Writing Files by Using Channel I/O
		 * TODO 不太懂
		 */
		// Defaults to READ
		try (SeekableByteChannel sbc = Files.newByteChannel(source)) {
			ByteBuffer buf = ByteBuffer.allocate(10);

			// Read the bytes with the proper encoding for this platform. If
			// you skip this step, you might see something that looks like
			// Chinese characters when you expect Latin-style characters.
			// String encoding = System.getProperty("file.encoding");
			while (sbc.read(buf) > 0) {
				buf.rewind();
				System.out.print(Charset.forName("GBK").decode(buf));
				buf.flip();
			}
		} catch (IOException x) {
			System.out.println("caught exception: " + x);
		}

		// Writing Files by Using Channel I/O
		logFilePermissionsTest();

	}

	/**
	 * The following example, written for UNIX and other POSIX file systems,
	 * creates a log file with a specific set of file permissions. This code
	 * creates a log file or appends to the log file if it already exists. The
	 * log file is created with read/write permissions for owner and read only
	 * permissions for group.
	 */
	public static void logFilePermissionsTest() {

		// Create the set of options for appending to the file.
		Set<OpenOption> options = new HashSet<OpenOption>();
		options.add(APPEND);
		options.add(CREATE);

		// Create the custom permissions attribute.
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

		// Convert the string to a ByteBuffer.
		String s = "Hello World! ";
		byte data[] = s.getBytes();
		ByteBuffer bb = ByteBuffer.wrap(data);

		Path file = Paths.get("target/permissions.log");

		try (SeekableByteChannel sbc = Files.newByteChannel(file, options, attr)) {
			sbc.write(bb);
		} catch (IOException x) {
			System.out.println("Exception thrown: " + x);
		}
	}

}
