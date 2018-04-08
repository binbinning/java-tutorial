package com.yylm.fcs.essential.io.files;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/**
		 * Creating a Path
		 */
		Path p1 = Paths.get("/tmp/foo");
		Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));

		/**
		 * Retrieving Information about a Path
		 */
		// None of these methods requires that the file corresponding
		// to the Path exists.
		// Microsoft Windows syntax
		Path path = Paths.get("C:\\home\\joe\\foo");

		// // Solaris syntax
		// Path path = Paths.get("/home/joe/foo");

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());

		/**
		 * Converting a Path
		 */
		Path p4 = Paths.get("/home/logfile");
		// Result is file:///home/logfile
		System.out.format("%s%n", p4.toUri());
		System.out.format("%s%n", p4.toAbsolutePath());
	}
}
