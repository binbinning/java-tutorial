package com.yylm.fcs.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Writing to a URLConnection
 * 
 * @see https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
 *
 */
public class WriteToURL {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println(
					"Usage:  java WriteToURL " + "http://<location of your servlet/script>" + " string_to_reverse");
			System.exit(1);
		}

		String stringToReverse = URLEncoder.encode(args[1], "UTF-8");

		URL url = new URL(args[0]);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write("string=" + stringToReverse);
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String decodedString;
		while ((decodedString = in.readLine()) != null) {
			System.out.println(decodedString);
		}
		in.close();
	}
}
