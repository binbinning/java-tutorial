package com.yylm.fcs.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Reading from network
 */
public class URLReader {
	public static void main(String[] args) throws IOException {
		URLReader1();
		URLReader2();
	}

	/**
	 * Reading Directly from a URL
	 * 
	 * @param args
	 * @throws IOException
	 * 
	 * @see https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
	 */
	public static void URLReader1() throws IOException {
		URL oracle = new URL("http://localhost:8080/paas-webapp/user");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}

	/**
	 * Reading from a URLConnection
	 * 
	 * @see https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
	 */
	public static void URLReader2() throws IOException {
		URL myURL = new URL("http://localhost:8080/paas-webapp/user");
		URLConnection myURLConnection = myURL.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();

	}
}
