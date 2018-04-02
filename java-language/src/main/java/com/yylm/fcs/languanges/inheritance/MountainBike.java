package com.yylm.fcs.languanges.inheritance;

import com.yylm.fcs.languanges.concept.Bicycle;
import com.yylm.fcs.languanges.utils.AnalyzeUtil;

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

	@Override
	public void printStates() {
		// super.printStates();
		System.out.println("seatHeight:" + seatHeight);
	}

	public static void main(String[] args) {
		MountainBike bike = new MountainBike(0, 0, 0, 0);
		AnalyzeUtil.printMethodSignatures(bike);

		// 暂停便于visualvm查看jvm内存对象
		AnalyzeUtil.waitForVisualVM();
	}
}
