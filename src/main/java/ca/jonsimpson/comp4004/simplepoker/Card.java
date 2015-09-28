package ca.jonsimpson.comp4004.simplepoker;

public class Card implements Comparable<Card> {
	
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
	
	private static final String ace = "Ace";
	private static final String two = "Two";
	private static final String three = "Three";
	private static final String four = "Four";
	private static final String five = "Five";
	private static final String six = "Six";
	private static final String seven = "Seven";
	private static final String eight = "Eight";
	private static final String nine = "Nine";
	private static final String ten = "Ten";
	private static final String jack = "Jack";
	private static final String queen = "Queen";
	private static final String king = "King";
	
	private Suit suit;
	private Rank rank;
	
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
	
	/**
	 * Create a card from a String of the RankSuit format. Eg. <code>4D</code>
	 * is the Four of Diamonds.
	 * 
	 * Valid Ranks are <code>2 3 4 5 6 7 8 9 10 J Q K A</code>.
	 * Valid Suits are <code>C H D S</code>.
	 * 
	 * @param rankSuit
	 */
	public Card(String rankSuit) {
		// check input if null and length if too short or too long
		if (rankSuit == null) {
			throw new NullPointerException("rankSuit cannot be null");
		} else if (rankSuit.length() < 2) {
			throw new InvalidCardException("Invalid RankSuit format");
		}
		
		/*
		 * First method
		 */
		
		String suitString = parseRank(rankSuit);
		if (suitString != null) {
			boolean success = parseSuit(suitString);
			if (success) {
				return;
			} else {
				rank = null;
				suit = null;
			}
		}
		
		
		/*
		 * Second method
		 */
		
		// grab the suit and rank out of the rankSuit String
		char suit = rankSuit.charAt(rankSuit.length() - 1);
		String rank = rankSuit.substring(0, rankSuit.length() - 1);
		
		// determine the Suit
		if (suit == 'S') {
			this.suit = Suit.SPADE;
		} else if (suit == 'C') {
			this.suit = Suit.CLUB;
		} else if (suit == 'H') {
			this.suit = Suit.HEART;
		} else if (suit == 'D') {
			this.suit = Suit.DIAMOND;
		} else {
			throw new InvalidCardException("Invalid suit specified: " + suit);
		}
		
		// determine the Rank
		if ("2".equals(rank)) {
			this.rank = Rank.TWO;
		} else if ("3".equals(rank)) {
			this.rank = Rank.THREE;
		} else if ("4".equals(rank)) {
			this.rank = Rank.FOUR;
		} else if ("5".equals(rank)) {
			this.rank = Rank.FIVE;
		} else if ("6".equals(rank)) {
			this.rank = Rank.SIX;
		} else if ("7".equals(rank)) {
			this.rank = Rank.SEVEN;
		} else if ("8".equals(rank)) {
			this.rank = Rank.EIGHT;
		} else if ("9".equals(rank)) {
			this.rank = Rank.NINE;
		} else if ("10".equals(rank)) {
			this.rank = Rank.TEN;
		} else if ("J".equals(rank)) {
			this.rank = Rank.JACK;
		} else if ("Q".equals(rank)) {
			this.rank = Rank.QUEEN;
		} else if ("K".equals(rank)) {
			this.rank = Rank.KING;
		} else if ("A".equals(rank)) {
			this.rank = Rank.ACE;
		} else {
			throw new InvalidCardException("Invalid rank specified: " + rank);
		}
		
	}

	private boolean parseSuit(String s) {
		if ("Spades".equals(s)) {
			suit = Suit.SPADE;
			return true;
		} else if ("Hearts".equals(s)) {
			suit = Suit.HEART;
			return true;
		} else if ("Diamonds".equals(s)) {
			suit = Suit.DIAMOND;
			return true;
		} else if ("Clubs".equals(s)) {
			suit = Suit.CLUB;
			return true;
		} else {
			return false;
		}
	}

	private String parseRank(String s) {
		if (s.startsWith(ace)) {
			rank = Rank.ACE;
			return s.substring(ace.length());
		} else if (s.startsWith(two)) {
			rank = Rank.TWO;
			return s.substring(two.length());
		} else if (s.startsWith(three)) {
			rank = Rank.THREE;
			return s.substring(three.length());
		} else if (s.startsWith(four)) {
			rank = Rank.FOUR;
			return s.substring(four.length());
		} else if (s.startsWith(five)) {
			rank = Rank.FIVE;
			return s.substring(five.length());
		} else if (s.startsWith(six)) {
			rank = Rank.SIX;
			return s.substring(six.length());
		} else if (s.startsWith(seven)) {
			rank = Rank.SEVEN;
			return s.substring(seven.length());
		} else if (s.startsWith(eight)) {
			rank = Rank.EIGHT;
			return s.substring(eight.length());
		} else if (s.startsWith(nine)) {
			rank = Rank.NINE;
			return s.substring(nine.length());
		} else if (s.startsWith(ten)) {
			rank = Rank.TEN;
			return s.substring(ten.length());
		} else if (s.startsWith(jack)) {
			rank = Rank.JACK;
			return s.substring(jack.length());
		} else if (s.startsWith(queen)) {
			rank = Rank.QUEEN;
			return s.substring(queen.length());
		} else if (s.startsWith(king)) {
			rank = Rank.KING;
			return s.substring(king.length());
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card)) {
			return false;
		}
		
		Card card = (Card) obj;
		return card.rank.equals(rank) && card.suit.equals(suit);
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * Compare this card with another card by rank. Returns a number less than
	 * zero, zero or greater than zero for if this card's rank is less than,
	 * equal or greater than the comparison card.
	 */
	@Override
	public int compareTo(Card card) {
		if (card == null) {
			throw new NullPointerException();
		}
		
		int indexOfThisCard = -1;
		int indexOfCompareCard = -1;
		Rank[] ranks = Rank.values();
		
		// iterate over the cards, finding the index position of the ranks
		for (int i = 0; i < ranks.length; i++) {
			if (getRank() == ranks[i]) {
				indexOfThisCard = i;
			}
			if (card.getRank() == ranks[i]) {
				indexOfCompareCard = i;
			}
			if (indexOfThisCard != -1 && indexOfCompareCard != -1) {
				break;
			}
		}
		
		// do the comparison of the indexes
		return indexOfCompareCard - indexOfThisCard;
	}
	
	@Override
	public int hashCode() {
		int result = 37;
		result = 37 * result + suit.hashCode();
		result = 37 * result + rank.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		return "Card " + rank + " " + suit;
	}
}
