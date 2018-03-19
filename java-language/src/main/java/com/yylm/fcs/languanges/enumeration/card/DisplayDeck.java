package com.yylm.fcs.languanges.enumeration.card;

public class DisplayDeck {
	public static void main(String[] args) {
		Deck deck = new Deck();
		System.out.println("deck");
		deck.display();

		System.out.println("shuffle");
		deck.shuffle();
		deck.display();
	}
}
