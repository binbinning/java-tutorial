package com.yylm.fcs.languanges.inheritance;

import com.yylm.fcs.languanges.concept.Bicycle;

/**
 * 
 * An Example of Inheritance.
 * 
 * A subclass does not inherit the private members of its parent class.
 */
public class MountainBike extends Bicycle {
	// the MountainBike subclass adds one field
	private int seatHeight;

	// the MountainBike subclass has one constructor
	public MountainBike(int startHeight, int startCadence, int startSpeed, int startGear) {
		super(startCadence, startSpeed, startGear);
		seatHeight = startHeight;
	}

	public void setSeatHeight(int seatHeight) {
		this.seatHeight = seatHeight;
	}

	public int getSeatHeight() {
		return seatHeight;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MountainBike bike = new MountainBike(0, 0, 0, 0);

		// 便于visualvm查看jvm内存对象
		try {
			Thread.sleep(1000 * 60 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
