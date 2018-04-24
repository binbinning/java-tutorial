package com.yylm.fcs.concurrency.basic.sync2;

/**
 * A Simple Producer-Consumer application. 演示线程间同步，高级实例参见sync包
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
 *
 */
public class ProducerConsumerExample {
	public static void main(String[] args) {
		Drop drop = new Drop();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}
