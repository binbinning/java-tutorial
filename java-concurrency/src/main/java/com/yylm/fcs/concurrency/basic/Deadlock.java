package com.yylm.fcs.concurrency.basic;

/**
 *
 * Deadlock
 * 
 * @see https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
 *
 */
public class Deadlock {
	static class Friend {
		private final String name;

		public Friend(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s" + " has bowed to me!%n", this.name, bower.getName());
			bower.bowBack(this);
		}

		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
		}
	}

	public static void main(String[] args) {
		final Friend lilei = new Friend("Li Lei");
		final Friend hanmeimei = new Friend("Han Meimei");
		new Thread(new Runnable() {
			public void run() {
				lilei.bow(hanmeimei);
			}
		}).start();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		new Thread(new Runnable() {
			public void run() {
				hanmeimei.bow(lilei);
			}
		}).start();
	}
}