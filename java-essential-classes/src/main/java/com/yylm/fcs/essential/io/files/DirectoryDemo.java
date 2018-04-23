package com.yylm.fcs.essential.io.files;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creating and Reading Directories
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
 *
 */
public class DirectoryDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * Listing a File System's Root Directories
		 */
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path name : dirs) {
			System.err.println(name);
		}

		/**
		 * Creating a Directory
		 */
		Files.createDirectories(Paths.get("target/foo/bar/test"));

		/**
		 * Listing a Directory's Contents
		 */
		Path dir = Paths.get("target");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file : stream) {
				System.out.println(file.getFileName());
			}
		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}

		/**
		 * Filtering a Directory Listing By Using Globbing
		 */
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{txt,class,jar}")) {
			for (Path entry : stream) {
				System.out.println(entry.getFileName());
			}
		} catch (IOException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can // only be thrown by newDirectoryStream.
			System.err.println(x);
		}

		/**
		 * Writing Your Own Directory Filter
		 */
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, file -> Files.isDirectory(file))) {
			for (Path entry : stream) {
				System.out.println(entry.getFileName());
			}
		} catch (IOException x) {
			System.err.println(x);
		}
	}
}
