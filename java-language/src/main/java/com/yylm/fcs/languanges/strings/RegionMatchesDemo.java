package com.yylm.fcs.languanges.strings;

/**
 * The following program, RegionMatchesDemo, uses the regionMatches method to
 * search for a string within another string:
 * 
 * @see https://docs.oracle.com/javase/tutorial/java/data/comparestrings.html
 *
 */
public class RegionMatchesDemo {
	public static void main(String[] args) {
		String searchMe = "Green Eggs and Ham";
		String findMe = "Eggs";
		int searchMeLength = searchMe.length();
		int findMeLength = findMe.length();
		boolean foundIt = false;
		
		for (int i = 0; i <= (searchMeLength - findMeLength); i++) {
			if (searchMe.regionMatches(i, findMe, 0, findMeLength)) {
				foundIt = true;
				System.out.println(searchMe.substring(i, i + findMeLength));
				break;
			}
		}
		
		if (!foundIt)
			System.out.println("No match found.");
	}
}
