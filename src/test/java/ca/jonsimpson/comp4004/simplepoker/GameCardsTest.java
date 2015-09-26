package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
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
		GameCards cards = new GameCards();
		cards.add(new Card("2S"));
		cards.add(new Card("3H"));
		cards.add(new Card("4H"));
		cards.add(new Card("6C"));
		cards.add(new Card("6H"));
		
		assertNotNull(cards.isOnePair());
		
		// get the HandResult
		HandResult handResult = cards.isOnePair();
		
		// assert that the match cards are the same
		List<Card> winnerCards = Arrays.asList(new Card("6C"), new Card("6H"));
		assertContainsSameElements(winnerCards, handResult.getMatchCards());
		
		// assert that the list from getHighCards() is in descending order of Rank
		List<Card> leftoverHighCards = Arrays.asList(new Card("4H"), new Card("3H"), new Card("2S"));
		assertContainsSameElements(leftoverHighCards, handResult.getHighCards());
		
		// check that all cards in the HandResult add up to 5
		assertEquals(5, handResult.getMatchCards().size() + handResult.getHighCards().size());
	}

	/**
	 * Each given list must have the same size and elements. Order does not
	 * matter.
	 * 
	 * @param expected
	 * @param actual
	 */
	private void assertContainsSameElements(List<Card> expected, List<Card> actual) {
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
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
