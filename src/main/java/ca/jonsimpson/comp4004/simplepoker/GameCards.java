package ca.jonsimpson.comp4004.simplepoker;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.jonsimpson.comp4004.simplepoker.Card.Rank;
import ca.jonsimpson.comp4004.simplepoker.Card.Suit;

/**
 * A representation of a set of playing cards. Does not contain duplicate cards.
 */
public class GameCards extends AbstractSet<Card> implements Comparable<GameCards> {

	Set<Card> cards = new HashSet<Card>();
	
	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}

	@Override
	public int size() {
		return cards.size();
	}
	
	@Override
	public boolean add(Card e) {
		return cards.add(e);
	}
	
	/**
	 * Get a full deck of 52 unique cards.
	 * @return
	 */
	public static GameCards getFullDeck() {
		
		GameCards gameCards = new GameCards();
		
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				gameCards.add(new Card(rank, suit));
			}
		}
		
		return gameCards;
	}

	@Override
	public int compareTo(GameCards o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the best five cards that are possible out of the given gameCards.
	 * 
	 * @param gameCards
	 * @return
	 */
	private static BestHand determineBestHand(GameCards gameCards) {
		return null;
	}

	public boolean isRoyalFlush() {
		throw new UnsupportedOperationException();
	}
}
