package com.yylm.fcs.concurrency.basic.sync;

/**
 * 产品容器
 * 
 * @author ningjb
 *
 */
public class ProductContainer {

	// private List<Product> productList = new ArrayList<Product>();
	private static final int CAPACITY = 10;
	private Product[] productList = new Product[CAPACITY];
	private int getIndex = 0;
	private int putIndex = 0;

	/**
	 * add product to the container
	 * 
	 * @param product
	 */
	public synchronized void addProduct(Product product) {
		while (isFull()) {
			System.out.println("container is full, wait for a while");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("adding product...");
		productList[this.putIndex] = product;
		this.putIndex = (this.putIndex + 1) % CAPACITY;
		notifyAll();
	}

	/**
	 * retrieve one oldest product from the container
	 * 
	 * @return
	 */
	public synchronized Product getProduct() {
		Product getProduct = null;
		while (isEmpty()) {
			System.out.println("container is empty, wait for a while");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("getting product...");
		getProduct = productList[this.getIndex];
		this.getIndex = (this.getIndex + 1) % CAPACITY;
		notifyAll();
		return getProduct;
	}

	/**
	 * whether the container is empty
	 * 
	 * @return
	 */
	private boolean isEmpty() {
		return this.getIndex == this.putIndex;
	}

	/**
	 * whether the container is full
	 * 
	 * @return
	 */
	private boolean isFull() {
		return (this.putIndex + 1) % CAPACITY == this.getIndex;
	}

	/**
	 * return the container size
	 */
	private int size() {
		if (this.putIndex > this.getIndex)
			return this.putIndex - this.getIndex;
		return this.putIndex + CAPACITY - this.getIndex;
	}

	/**
	 * List all product
	 */
	void printAllProduct() {
		int currentSize = this.size();
		for (int i = 0; i < currentSize; i++) {
			System.out.println(this.getProduct());
		}
	}

	public static void main(String[] args) {
		ProductContainer container = new ProductContainer();
		container.getProduct();
		for (int i = 0; i < 10; i++) {
			container.addProduct(new Product("java io" + i, "this is a Java IO 2nd edition!"));
		}

		container.getProduct();
		for (int i = 0; i < 10; i++) {
			container.addProduct(new Product("java io" + i, "this is a Java IO 2nd edition!"));
		}
		container.printAllProduct();
		container.getProduct();
	}
}
