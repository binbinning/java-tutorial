package com.yylm.fcs.languanges.concept;

import com.yylm.fcs.languanges.annotation.*;

/**
 * 
 * What Is a Class?
 * 
 * fields - constructors - methods
 * 
 * {@link https://docs.oracle.com/javase/tutorial/java/concepts/class.html}
 *
 */
@ClassPreamble(author = "Java tutorial", date = "3/17/2018", currentRevision = 1, lastModified = "4/12/2018", lastModifiedBy = "Nick Ning",
		// Note array notation
		reviewers = { "Tony", "Lank", "Suc" })
public class Bicycle {
	private int cadence = 0;
	private int speed = 0;
	private int gear = 1;

	public Bicycle() {
		super();
	}

	// the Bicycle class has one constructor
	public Bicycle(int startCadence, int startSpeed, int startGear) {
		gear = startGear;
		cadence = startCadence;
		speed = startSpeed;
		printStates(); // 构造方法中调用的如果不是final，则会被子类改变行为
	}

	void changeCadence(int newValue) {
		cadence = newValue;
	}

	void changeGear(int newValue) {
		gear = newValue;
	}

	void speedUp(int increment) {
		speed = speed + increment;
	}

	void applyBrakes(int decrement) {
		speed = speed - decrement;
	}

	public void printStates() {
		System.out.println("cadence:" + cadence + " speed:" + speed + " gear:" + gear);
	}
}
