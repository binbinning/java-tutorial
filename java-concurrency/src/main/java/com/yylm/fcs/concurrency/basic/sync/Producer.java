package com.yylm.fcs.concurrency.basic.sync;

/**
 * 生产者线程
 * 
 * @author ningjb
 *
 */
public class Producer implements Runnable {

	private ProductContainer productConatiner;
	private int index = 0;

	public Producer(ProductContainer productConatiner) {
		super();
		this.productConatiner = productConatiner;
	}

	@Override
	public void run() {
		while (true) {
			productConatiner.addProduct(generateProduct());
		}
	}

	/**
	 * 生产产品
	 * 
	 * @return
	 */
	public Product generateProduct() {
		// 模拟生产过程
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Product product = new Product(Thread.currentThread().getName() + " product " + index++, "description");
		System.out.println("generating one product..." + product);
		return product;
	}
}
