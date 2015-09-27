package ca.jonsimpson.comp4004.simplepoker;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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

	public HandResult isStraightFlush() {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	public List<Card> getCardsOfRank(Rank rank) {
		// create return list
		ArrayList<Card> result = new ArrayList<Card>(4);
		
		for (Card card : cards) {
			if (card.getRank() == rank) {
				result.add(card);
			}
		}
		
		// return the result
		return result;
	}
	
	private List<Card> getCardsOfSuit(Suit suit) {
		// create cards to compare to
		ArrayList<Card> compareCards = new ArrayList<Card>();
		for (Rank rank : Rank.values()) {
			compareCards.add(new Card(rank, suit));
		}
		
		// keep all ranks that are in cards 
		compareCards.retainAll(cards);
		
		return compareCards;
	}
	
	private Map<Suit, List<Card>> sortAndGroupCardsBySuit() {
		ArrayList<Card> heart = new ArrayList<Card>();
		ArrayList<Card> diamond = new ArrayList<Card>();
		ArrayList<Card> club = new ArrayList<Card>();
		ArrayList<Card> spade = new ArrayList<Card>();
		
		// put cards into their lists by suit
		for (Card card : cards) {
			if (card.getSuit() == Suit.HEART) {
				heart.add(card);
			} else if (card.getSuit() == Suit.DIAMOND) {
				diamond.add(card);
			} else if (card.getSuit() == Suit.CLUB) {
				club.add(card);
			} else if (card.getSuit() == Suit.SPADE) {
				spade.add(card);
			}
		}
		
		// sort the lists by highest rank first
		Collections.sort(heart);
		Collections.sort(diamond);
		Collections.sort(club);
		Collections.sort(spade);
		
		// put the lists into a map
		EnumMap<Suit,List<Card>> map = new EnumMap<Suit, List<Card>>(Suit.class);
		map.put(Suit.HEART, heart);
		map.put(Suit.DIAMOND, diamond);
		map.put(Suit.CLUB, club);
		map.put(Suit.SPADE, spade);
		
		return map;
	}

	/**
	 * Checks if the cards contain one pair.
	 * 
	 * @return A {@link HandResult} object with the cards if the cards have a
	 *         one pair, null if it does not
	 */
	public HandResult isOnePair() {
		List<Rank> ranks = Arrays.asList(Rank.values());
		ListIterator<Rank> iterator = ranks.listIterator(ranks.size());
		
		// iterate over ranks in reverse order, highest card to lowest
		while (iterator.hasPrevious()) {
			Rank rank = iterator.previous();
			List<Card> cardsOfRank = getCardsOfRank(rank);
			if (cardsOfRank.size() >= 2) {
				
				// remove extra cards if there's more than two
				if (cardsOfRank.size() > 2) {
					cardsOfRank.subList(2, cardsOfRank.size()).clear();
				}
				
				// create the HandResult
				ArrayList<Card> highCards = new ArrayList<Card>(cards);
				highCards.removeAll(cardsOfRank);
				Collections.sort(highCards);
				
				// remove extra high cards if we have over 5 cards
				if (highCards.size() > 3) {
					highCards.subList(2, highCards.size()).clear();
				}
				
				return new HandResult(cardsOfRank, highCards);
			}
		}
		
		return null;
	}

	public HandResult isTwoPair() {
		List<Rank> ranks = Arrays.asList(Rank.values());
		ListIterator<Rank> iterator = ranks.listIterator(ranks.size());
		
		List<Card> matchedCards = new ArrayList<Card>();
		
		// iterate over ranks in reverse order, highest card to lowest
		while (iterator.hasPrevious()) {
			Rank rank = iterator.previous();
			List<Card> cardsOfRank = getCardsOfRank(rank);
			if (cardsOfRank.size() >= 2) {
				
				// remove extra cards if there's more than two
				if (cardsOfRank.size() > 2) {
					cardsOfRank.subList(2, cardsOfRank.size()).clear();
				}
				
				// add the two matched cards to the matchedCards list
				matchedCards.addAll(cardsOfRank);
				
				// check if we already have two pairs
				if (matchedCards.size() >= 4) {
					break;
				}
				
			}
		}
		
		if (matchedCards.size() >= 4) {
			// get high cards
			ArrayList<Card> highCards = new ArrayList<Card>(cards);
			highCards.removeAll(matchedCards);
			Collections.sort(highCards);
			
			// remove extra high cards if we have over 5 cards
			if (highCards.size() > 1) {
				highCards.subList(4, highCards.size()).clear();
			}
			
			// create the HandResult
			return new HandResult(matchedCards, highCards);
			
		}
		
		return null;
		
	}

	public HandResult isThreeOfAKind() {
		List<Rank> ranks = Arrays.asList(Rank.values());
		ListIterator<Rank> iterator = ranks.listIterator(ranks.size());
		
		// iterate over ranks in reverse order, highest card to lowest
		while (iterator.hasPrevious()) {
			Rank rank = iterator.previous();
			List<Card> cardsOfRank = getCardsOfRank(rank);
			
			if (cardsOfRank.size() >= 3) {
				
				// remove extra cards if there's more than three
				if (cardsOfRank.size() > 3) {
					cardsOfRank.subList(3, cardsOfRank.size()).clear();
				}
				
				// get the high cards
				ArrayList<Card> highCards = new ArrayList<Card>(cards);
				highCards.removeAll(cardsOfRank);
				Collections.sort(highCards);
				
				// remove extra high cards if we have over 5 cards
				if (highCards.size() > 2) {
					highCards.subList(2, highCards.size()).clear();
				}
				
				return new HandResult(cardsOfRank, highCards);
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
