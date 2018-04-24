package com.yylm.fcs.concurrency.basic.sync;

public class SyncMain {
	public static void main(String[] args) {

		ProductContainer container = new ProductContainer();

		new Thread(new Producer(container), "Producer I").start();
		new Thread(new Producer(container), "Producer II").start();

		new Thread(new Consumer(container), "Consumer I").start();
		new Thread(new Consumer(container), "Consumer II").start();
		new Thread(new Consumer(container), "Consumer III").start();
		new Thread(new Consumer(container), "Consumer IV").start();
	}
}
