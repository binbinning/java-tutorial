package com.yylm.fcs.concurrency.basic;

/**
 * Defining and Starting a Thread
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
 *
 */
public class HelloRunnable implements Runnable {

	public void run() {
		System.out.println("Hello from a thread!");
	}

	public static void main(String args[]) {
		(new Thread(new HelloRunnable())).start();
	}

}
