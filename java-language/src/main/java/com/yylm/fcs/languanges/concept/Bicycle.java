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
	int cadence = 0;
	int speed = 0;
	int gear = 1;

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

	void printStates() {
		System.out.println("cadence:" + cadence + " speed:" + speed + " gear:" + gear);
	}
}
