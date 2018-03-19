package com.yylm.fcs.languanges.enumeration.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 一副牌
 *
 */
public class Deck {
	private static Card[] cards = new Card[52]; // 原始扑克牌
	private List<Card> play_cards; // 洗牌

	public Deck() {
		int i = 0;
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards[i++] = new Card(rank, suit);
			}
		}
		play_cards = new ArrayList<Card>(Arrays.asList(cards));
	}

	/**
	 * 洗牌, 随机取牌算法
	 */
	public void shuffle() {
		play_cards.clear();
		List<Card> initialCards = new ArrayList<Card>(Arrays.asList(cards));
		Random rand = new Random();
		for (int index = 0; index < cards.length; index++) {
			Card card = initialCards.get(rand.nextInt(initialCards.size()));
			play_cards.add(index, card);
			initialCards.remove(card); // remove card from old list
		}
	}

	/**
	 * 明面顺序列出
	 */
	public void display() {
		for (Card card : play_cards) {
			System.out.format("%s of %s%n", card.getRank(), card.getSuit());
		}
	}
}
