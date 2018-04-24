package com.yylm.fcs.concurrency.basic.sync;

/**
 * 消费者线程
 * 
 * @author ningjb
 *
 */
public class Consumer implements Runnable {
	private ProductContainer productConatiner;

	public Consumer(ProductContainer productConatiner) {
		super();
		this.productConatiner = productConatiner;
	}

	@Override
	public void run() {
		while (true) {
			processProduct(productConatiner.getProduct());
		}
	}

	/**
	 * processing product
	 * 
	 * @param product
	 */
	private void processProduct(Product product) {
		System.out.println(Thread.currentThread().getName() + " procesing " + product);
		// 模拟处理过程
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
