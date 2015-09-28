package ca.jonsimpson.comp4004.simplepoker;

import java.util.List;

import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * A group of cards that make up the result of a users best hand.
 */
public class HandResult implements Comparable<HandResult> {

	private final List<Card> matchCards;
	private final List<Card> highCards;

	public HandResult(List<Card> matchCards, List<Card> highCards) {
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
}
