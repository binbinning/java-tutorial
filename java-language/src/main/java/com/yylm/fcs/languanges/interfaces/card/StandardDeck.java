package com.yylm.fcs.languanges.interfaces.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * 扑克牌的实现类
 *
 */
public class StandardDeck implements Deck {

	private List<Card> entireDeck;

	public StandardDeck(List<Card> existingList) {
		this.entireDeck = existingList;
	}

	public StandardDeck() {
		this.entireDeck = new ArrayList<>();
		for (Card.Suit s : Card.Suit.values()) {
			for (Card.Rank r : Card.Rank.values()) {
				this.entireDeck.add(new PlayingCard(r, s));
			}
		}
	}

	public Deck deckFactory() {
		return new StandardDeck(new ArrayList<Card>());
	}

	public int size() {
		return entireDeck.size();
	}

	public List<Card> getCards() {
		return entireDeck;
	}

	public void addCard(Card card) {
		entireDeck.add(card);
	}

	public void addCards(List<Card> cards) {
		entireDeck.addAll(cards);
	}

	public void addDeck(Deck deck) {
		List<Card> listToAdd = deck.getCards();
		entireDeck.addAll(listToAdd);
	}

	public void sort() {
		Collections.sort(entireDeck);
	}

	public void sort(Comparator<Card> c) {
		Collections.sort(entireDeck, c);
	}

	public void shuffle() {
		Collections.shuffle(entireDeck);
	}

	public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
		int cardsDealt = players * numberOfCards;
		int sizeOfDeck = entireDeck.size();
		if (cardsDealt > sizeOfDeck) {
			throw new IllegalArgumentException("Number of players (" + players + ") times number of cards to be dealt ("
					+ numberOfCards + ") is greater than the number of cards in the deck (" + sizeOfDeck + ").");
		}

		Map<Integer, List<Card>> dealtDeck = entireDeck.stream().collect(Collectors.groupingBy(card -> {
			int cardIndex = entireDeck.indexOf(card);
			if (cardIndex >= cardsDealt)
				return (players + 1);
			else
				return (cardIndex % players) + 1;
		}));

		// Convert Map<Integer, List<Card>> to Map<Integer, Deck>
		Map<Integer, Deck> mapToReturn = new HashMap<>();

		for (int i = 1; i <= (players + 1); i++) {
			Deck currentDeck = deckFactory();
			currentDeck.addCards(dealtDeck.get(i));
			mapToReturn.put(i, currentDeck);
		}
		return mapToReturn;
	}

	public String deckToString() {
		return this.entireDeck.stream().map(Card::toString).collect(Collectors.joining("\n"));
	}

	public static void main(String... args) {
		StandardDeck myDeck = new StandardDeck();
		System.out.println("Creating deck:");
		myDeck.sort();
		System.out.println("Sorted deck");
		System.out.println(myDeck.deckToString());

		myDeck.shuffle();
		myDeck.sort(new SortByRankThenSuit());
		System.out.println("Sorted by rank, then by suit");
		System.out.println(myDeck.deckToString());

		myDeck.shuffle();

		/**
		 * anonymous implementation
		 * 
		 * <pre>
		 * myDeck.sort(new Comparator<Card>() {
		 * 
		 * 	&#64;Override
		 * 	public int compare(Card firstCard, Card secondCard) {
		 * 		int compVal = firstCard.getRank().value() - secondCard.getRank().value();
		 * 		if (compVal != 0)
		 * 			return compVal;
		 * 		else
		 * 			return firstCard.getSuit().value() - secondCard.getSuit().value();
		 * 	}
		 * });
		 *
		 * lamda implementation
		 * myDeck.sort((firstCard, secondCard) -> {
		 * 	int compVal = firstCard.getRank().value() - secondCard.getRank().value();
		 * 	if (compVal != 0)
		 * 		return compVal;
		 * 	else
		 * 		return firstCard.getSuit().value() - secondCard.getSuit().value();
		 * });
		 * </pre>
		 */

		/**
		 * 字节码
		 * 
		 * <pre>
		75: aload_1
		76: invokedynamic #237,  0            // InvokeDynamic #2:apply:()Ljava/util/function/Function;
		81: invokestatic  #238                // InterfaceMethod java/util/Comparator.comparing:(Ljava/util/function/Function;)Ljava/util/Comparator;
		84: invokedynamic #244,  0            // InvokeDynamic #3:apply:()Ljava/util/function/Function;
		89: invokestatic  #238                // InterfaceMethod java/util/Comparator.comparing:(Ljava/util/function/Function;)Ljava/util/Comparator;
		92: invokeinterface #245,  2          // InterfaceMethod java/util/Comparator.thenComparing:(Ljava/util/Comparator;)Ljava/util/Comparator;
		97: invokevirtual #233                // Method sort:(Ljava/util/Comparator;)V
		 * </pre>
		 * 
		 * 表达式的完全展开
		 * 
		 * <pre>
		 * 
		 * 1. 最原始的实现是116行的anonymous implementation和131行的lamda implementation
		 *     myDeck.sort((firstCard, secondCard) -> firstCard.getRank().value() - secondCard.getRank().value()); 
		 * 
		 * 2. compartor自身实现了一个default method可以通过comparing返回comparator实例
		 *     myDeck.sort(Comparator.comparing((card) -> card.getRank()));  
		 *     
		 *     comparing的内部实现返回一个lamda表达式，等于161行
		 *        return (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
		 *
		 * 3. 据此，lamda替换为method reference
		 * 	   myDeck.sort(Comparator.comparing(Card::getRank));  
		 * 
		 * 4. 多条件排序
		 *   Comparator.thenComparing()的实现比较绕，多级lamda + functional interface + method reference
		 *    c1 = Comparator.comparing(Card::getRank);
		 *    c2 = Comparator.comparing(Card::getSuit);
		 *    c3 = c1.thenComparing(c2){
		 * 		c1.compare(c1,c2);
		 *      c2.compare(c1,c2);
		 * }
		 * </pre>
		 */
		myDeck.sort(Comparator.comparing(Card::getRank).thenComparing(Comparator.comparing(Card::getSuit)));
		System.out.println("Sorted by rank, then by suit " + "with static and default methods");
		System.out.println(myDeck.deckToString());

		/**
		 * 
		 * <pre>
		 * 
		 * 5. reverse order
		 * 
		 * Comparator.comparing(Card::getRank).reversed();
		 * 
		 * new Comparator<Card>(){
		 *    public int compare(Card firstCard, Card secondCard) {
		 * 		return firstCard.getRank().compareTo(secondCard.getRank());
		 * 
		 *    default Comparator<T> reversed() {
			          if (cmp == null)
				            return reverseOrder();
				
				        if (cmp instanceof ReverseComparator2)
				            return ((ReverseComparator2<T>)cmp).cmp;
				
				        return new ReverseComparator2<>(cmp);
			    }		
		 * 
		 * }
		 * 
		 * </pre>
		 */
		myDeck.sort(Comparator.comparing(Card::getRank).reversed().thenComparing(Comparator.comparing(Card::getSuit)));
		System.out.println("Sorted by rank reversed, then by suit " + "with static and default methods");
		System.out.println(myDeck.deckToString());
	}
}
