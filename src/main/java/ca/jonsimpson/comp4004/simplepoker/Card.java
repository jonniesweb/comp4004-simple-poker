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
	
	private final Suit suit;
	private final Rank rank;
	
	/**
	 * @param rank
	 * @param suit
	 */
	public Card(Rank rank, Suit suit) {
		
		// check for invalid rank or suit
		if (rank == null) {
			throw new InvalidCardException("rank cannot be null");
		} else if (suit == null) {
			throw new InvalidCardException("suit cannot be null");
		}
		
		// everything's ok, set the values
		this.rank = rank;
		this.suit = suit;
		
	}
	
	public Card(String string) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card)) {
			return false;
		}
		
		Card card = (Card) obj;
		return card.rank.equals(rank) && card.suit.equals(suit);
	}

}
