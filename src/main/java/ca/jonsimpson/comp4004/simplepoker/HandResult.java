package ca.jonsimpson.comp4004.simplepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * A group of cards that make up the result of a users best hand.
 */
public class HandResult implements Comparable<HandResult> {

	public enum Hand {
		ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_KIND, FULL_HOUSE, FLUSH, STRAIGHT,
		THREE_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
	};
	
	private final List<Card> matchCards;
	private final List<Card> highCards;
	private final Hand hand;

	public HandResult(Hand hand, List<Card> matchCards, List<Card> highCards) {
		this.hand = hand;
		this.matchCards = matchCards;
		this.highCards = highCards;
		
	}

	public List<Card> getMatchCards() {
		return new UnmodifiableList<Card>(matchCards);
	}

	public List<Card> getHighCards() {
		if (highCards != null) {
			return new UnmodifiableList<Card>(highCards);
		} else {
			return null;
		}
	}

	@Override
	public int compareTo(HandResult otherHand) {
		if (otherHand == null) {
			throw new NullPointerException();
		}
		
		List<Card> cards = getAllCards();
		List<Card> otherCards = otherHand.getAllCards();
		
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			Card card2 = otherCards.get(i);
			int compareResult = card.getRank().compareTo(card2.getRank());
			
			if (compareResult <= 1) {
				return 1;
			} else if (compareResult >= 1) {
				return -1;
			}
		}
		
		return 0;
	}

	private List<Card> getAllCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.addAll(matchCards);
		if (highCards != null) {
			cards.addAll(highCards);
		}
		
		Collections.sort(cards);
		return cards;
	}

	public Hand getHand() {
		return hand;
	}
}
