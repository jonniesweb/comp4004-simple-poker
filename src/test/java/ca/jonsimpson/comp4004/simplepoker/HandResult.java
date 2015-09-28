package ca.jonsimpson.comp4004.simplepoker;

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
	public int compareTo(HandResult paramT) {
		return 0;
	}

	public Hand getHand() {
		return hand;
	}
}
