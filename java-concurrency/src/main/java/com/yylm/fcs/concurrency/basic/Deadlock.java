package com.yylm.fcs.concurrency.basic;

/**
 *
 * Deadlock
 * 
 *
 * 
 * Thread dump shows dead lock
 * 
 * <pre>
 Thread-1" #11 prio=5 os_prio=0 tid=0x0000000022507000 nid=0x5dd8 waiting for monitor entry [0x000000002312f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.yylm.fcs.concurrency.basic.Deadlock$Friend.bowBack(Deadlock.java:28)
	- waiting to lock <0x000000075af88c18> (a com.yylm.fcs.concurrency.basic.Deadlock$Friend)
	at com.yylm.fcs.concurrency.basic.Deadlock$Friend.bow(Deadlock.java:24)
	- locked <0x000000075af88c28> (a com.yylm.fcs.concurrency.basic.Deadlock$Friend)

"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000022506000 nid=0x2428 waiting for monitor entry [0x000000002302e000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.yylm.fcs.concurrency.basic.Deadlock$Friend.bowBack(Deadlock.java:28)
	- waiting to lock <0x000000075af88c28> (a com.yylm.fcs.concurrency.basic.Deadlock$Friend)
	at com.yylm.fcs.concurrency.basic.Deadlock$Friend.bow(Deadlock.java:24)
	- locked <0x000000075af88c18> (a com.yylm.fcs.concurrency.basic.Deadlock$Friend)
 *
 * </pre>
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
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		new Thread(new Runnable() {
			public void run() {
				hanmeimei.bow(lilei);
			}
		}).start();
	}
}