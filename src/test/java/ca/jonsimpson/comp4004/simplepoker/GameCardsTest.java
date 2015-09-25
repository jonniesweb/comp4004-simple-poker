package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.jonsimpson.comp4004.simplepoker.Card.Rank;

public class GameCardsTest {
	
	@Test
	public void testNoDuplicateCards() throws Exception {
		GameCards cards = GameCards.getFullDeck();
		
		Set<Card> cardSet = new HashSet<Card>(cards);
		
		assertEquals(cards.size(), cardSet.size());
	}
	
	@Test
	public void testHave52Cards() throws Exception {
		GameCards cards = GameCards.getFullDeck();
		
		int cardCount = 0;
		
		for (Card card : cards) {
			cardCount++;
		}
		
		assertEquals(52, cardCount);
	}
	
	@Test
	public void testCompareRoyalFlushToFlush() throws Exception {
		// create a hand with a royal flush
		GameCards winnerCards = new GameCards();
		winnerCards.add(new Card("AH"));
		winnerCards.add(new Card("KH"));
		winnerCards.add(new Card("QH"));
		winnerCards.add(new Card("JH"));
		winnerCards.add(new Card("10H"));
		
		// create a random hand
		GameCards loserCards = new GameCards();
		loserCards.add(new Card("2S"));
		loserCards.add(new Card("3S"));
		loserCards.add(new Card("4S"));
		loserCards.add(new Card("5S"));
		loserCards.add(new Card("10S"));
		
		// do the comparison
		assertEquals(1, winnerCards.compareTo(loserCards));
	}
	
	@Test
	public void testIsRoyalFlush() throws Exception {
		// create royal flush
		GameCards royalFlushCards = new GameCards();
		royalFlushCards.add(new Card("AH"));
		royalFlushCards.add(new Card("KH"));
		royalFlushCards.add(new Card("QH"));
		royalFlushCards.add(new Card("JH"));
		royalFlushCards.add(new Card("10H"));
		
		assertTrue(royalFlushCards.isRoyalFlush());
	}
	
	@Test
	public void testIsStraightFlush() throws Exception {
		// create straight flush
		GameCards straightFlushCards = new GameCards();
		straightFlushCards.add(new Card("2H"));
		straightFlushCards.add(new Card("3H"));
		straightFlushCards.add(new Card("4H"));
		straightFlushCards.add(new Card("5H"));
		straightFlushCards.add(new Card("6H"));
		
		assertTrue(straightFlushCards.isStraightFlush());
	}
	
	@Test
	public void testIsOnePair() throws Exception {
		GameCards highCards = new GameCards();
		highCards.add(new Card("2S"));
		highCards.add(new Card("3H"));
		highCards.add(new Card("4H"));
		highCards.add(new Card("6C"));
		highCards.add(new Card("6H"));
		
		assertTrue(highCards.isOnePair());
	}
	
	@Test
	public void testGetCardsOfRank() throws Exception {
		GameCards cards = new GameCards();
		Card card3h = new Card("3H");
		Card card4h = new Card("4H");
		Card card6c = new Card("6C");
		Card card6h = new Card("6H");
		cards.add(card3h);
		cards.add(card4h);
		cards.add(card6c);
		cards.add(card6h);
		
		List<Card> cardsOfRankSix = cards.getCardsOfRank(Rank.SIX);
		assertEquals(2, cardsOfRankSix.size());
		assertTrue(cardsOfRankSix.contains(card6c));
		assertTrue(cardsOfRankSix.contains(card6h));
		
		List<Card> cardsOfRankFour = cards.getCardsOfRank(Rank.FOUR);
		assertEquals(1, cardsOfRankFour.size());
		assertTrue(cardsOfRankFour.contains(card4h));
		
		List<Card> cardsOfRankAce = cards.getCardsOfRank(Rank.ACE);
		assertEquals(0, cardsOfRankAce.size());
	}
	
	
	
	
}
