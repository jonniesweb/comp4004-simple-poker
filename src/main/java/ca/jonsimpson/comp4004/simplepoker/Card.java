package ca.jonsimpson.comp4004.simplepoker;

public class Card {
	
	/**
	 * The suit of this card. Possibilities are Diamond, Heart, Spade and Club.
	 */
	public static enum Suit {
		DIAMOND, HEART, SPADE, CLUB
	};
	
	/**
	 * The part of the card that isn't the suit. It represents numbers two
	 * through 10 and Jack, Queen, King Ace.
	 */
	public static enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	};
	
	private Suit suit;
	private Rank rank;
	/**
	 * @param rank
	 * @param suit
	 */
	public Card(Rank rank, Suit suit) {
		throw new UnsupportedOperationException();
	}
	
	
}
