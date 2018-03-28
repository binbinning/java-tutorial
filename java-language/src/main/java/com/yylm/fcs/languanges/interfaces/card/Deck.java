package com.yylm.fcs.languanges.interfaces.card;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 
 * 一副牌的接口
 *
 */
public interface Deck {

	List<Card> getCards();

	Deck deckFactory();

	int size();

	void addCard(Card card);

	void addCards(List<Card> cards);

	void addDeck(Deck deck);

	/**
	 * 洗牌
	 */
	void shuffle();

	void sort();

	void sort(Comparator<Card> c);

	String deckToString();

	Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException;

}
