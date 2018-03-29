package com.yylm.fcs.languanges.inheritance;

import com.yylm.fcs.languanges.utils.AnalyzeUtil;

/**
 * Default methods and abstract methods in interfaces的多重继承, 编译时解析方法
 * <li>Instance methods are preferred over interface default methods.
 * <li>Methods that are already overridden by other candidates are ignored.
 */
public class Interface_Methods {
	public static void main(String... args) {
		Pegasus pegasus = new Pegasus();
		System.out.println(pegasus.identifyMyself());
		// com.yylm.fcs.languanges.inheritance.Horse.identifyMyself()
		AnalyzeUtil.printMethodSignatures(pegasus);

		Dragon dragon = new Dragon();
		System.out.println(dragon.identifyMyself());
		// com.yylm.fcs.languanges.inheritance.EggLayer.identifyMyself()
		AnalyzeUtil.printMethodSignatures(dragon);
	}

}

// start rule 1
class Horse {
	public String identifyMyself() {
		return "I am a horse.";
	}
}

interface Flyer {
	default public String identifyMyself() {
		return "I am able to fly.";
	}
}

interface Mythical {
	default public String identifyMyself() {
		return "I am a mythical creature.";
	}
}

class Pegasus extends Horse implements Flyer, Mythical {
}
// end rule 1

// start rule 2
interface Animal {
	default public String identifyMyself() {
		return "I am an animal.";
	}
}

interface EggLayer extends Animal {
	default public String identifyMyself() {
		return "I am able to lay eggs.";
	}
}

interface FireBreather extends Animal {
	// duplicate default methods
	// default public String identifyMyself() {
	// return "I am able to lay fireBreather.";
	// }
}

class Dragon implements EggLayer, FireBreather {
}
// end rule 2