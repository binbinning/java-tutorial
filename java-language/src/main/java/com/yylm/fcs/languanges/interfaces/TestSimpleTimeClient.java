package com.yylm.fcs.languanges.interfaces;

/**
 * 
 * default method in interface
 *
 */
public class TestSimpleTimeClient {
	public static void main(String... args) {
		TimeClient myTimeClient = new SimpleTimeClient();
		System.out.println("Current time: " + myTimeClient.toString());
		System.out.println("Time in Beijing: " + myTimeClient.getZonedDateTime("GMT+8").toString());
	}
}
