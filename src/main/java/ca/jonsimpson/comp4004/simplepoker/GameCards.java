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

	/**
	 * If the current cards are a straight from a ten to an ace with all five
	 * cards of the same suit.
	 * 
	 * @return
	 */
	public boolean isRoyalFlush() {
		
		GameCards heartsRoyalFlush = new GameCards();
		heartsRoyalFlush.add(new Card("AH"));
		heartsRoyalFlush.add(new Card("KH"));
		heartsRoyalFlush.add(new Card("QH"));
		heartsRoyalFlush.add(new Card("JH"));
		heartsRoyalFlush.add(new Card("10H"));
		
		GameCards diamondsRoyalFlush = new GameCards();
		diamondsRoyalFlush.add(new Card("AD"));
		diamondsRoyalFlush.add(new Card("KD"));
		diamondsRoyalFlush.add(new Card("QD"));
		diamondsRoyalFlush.add(new Card("JD"));
		diamondsRoyalFlush.add(new Card("10D"));
		
		GameCards spadesRoyalFlush = new GameCards();
		spadesRoyalFlush.add(new Card("AS"));
		spadesRoyalFlush.add(new Card("KS"));
		spadesRoyalFlush.add(new Card("QS"));
		spadesRoyalFlush.add(new Card("JS"));
		spadesRoyalFlush.add(new Card("10S"));
		
		GameCards clubsRoyalFlush = new GameCards();
		clubsRoyalFlush.add(new Card("AC"));
		clubsRoyalFlush.add(new Card("KC"));
		clubsRoyalFlush.add(new Card("QC"));
		clubsRoyalFlush.add(new Card("JC"));
		clubsRoyalFlush.add(new Card("10C"));
		
		return containsAll(heartsRoyalFlush) || containsAll(diamondsRoyalFlush)
				|| containsAll(spadesRoyalFlush) || containsAll(clubsRoyalFlush);
	}

	public boolean isStraightFlush() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
