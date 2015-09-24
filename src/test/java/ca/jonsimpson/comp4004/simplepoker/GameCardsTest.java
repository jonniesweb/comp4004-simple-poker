package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

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
}
