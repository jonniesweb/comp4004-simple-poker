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
		Card card2h = new Card("2H");
		Card card3h = new Card("3H");
		Card card4h = new Card("4H");
		Card card5h = new Card("5H");
		Card card6h = new Card("6H");
		straightFlushCards.add(card2h);
		straightFlushCards.add(card3h);
		straightFlushCards.add(card4h);
		straightFlushCards.add(card5h);
		straightFlushCards.add(card6h);
		
		assertNotNull(straightFlushCards.isStraightFlush());
		
		HandResult handResult = straightFlushCards.isStraightFlush();
		
		// verify that straight flush cards are matched
		List<Card> correctCards = Arrays.asList(card2h, card3h, card4h, card5h, card6h);
		assertContainsSameElements(correctCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertTrue(handResult.getHighCards().isEmpty());
		
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
	
	@Test
	public void testIsTwoPair() throws Exception {
		GameCards cards = new GameCards();
		Card card2s = new Card("2S");
		Card card2d = new Card("2D");
		Card card4h = new Card("4H");
		Card card6c = new Card("6C");
		Card card6h = new Card("6H");
		cards.add(card2d);
		cards.add(card6c);
		cards.add(card4h);
		cards.add(card2s);
		cards.add(card6h);
		
		HandResult handResult = cards.isTwoPair();
		assertNotNull(handResult);
		
		List<Card> matchedCards = Arrays.asList(card6h, card6c, card2d, card2s);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		List<Card> highCards = Arrays.asList(card4h);
		assertContainsSameElements(highCards, handResult.getHighCards());
	}
	
	@Test
	public void testThreeOfAKind() throws Exception {
		GameCards cards = new GameCards();
		Card cardah = new Card("AH");
		Card cardas = new Card("AS");
		Card cardad = new Card("AD");
		Card card6c = new Card("6C");
		Card card3h = new Card("3H");
		cards.add(cardas);
		cards.add(card6c);
		cards.add(cardad);
		cards.add(cardah);
		cards.add(card3h);
		
		HandResult handResult = cards.isThreeOfAKind();
		assertNotNull(handResult);
		
		
		List<Card> matchedCards = Arrays.asList(cardah, cardad, cardas);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		List<Card> highCards = Arrays.asList(card6c, card3h);
		assertContainsSameElements(highCards, handResult.getHighCards());
	}
	
	@Test
	public void testStraight() throws Exception {
		// create straight
		GameCards straightCards = new GameCards();
		Card card2s = new Card("2S");
		Card card3c = new Card("3C");
		Card card4h = new Card("4H");
		Card card5d = new Card("5D");
		Card card6h = new Card("6H");
		straightCards.add(card6h);
		straightCards.add(card4h);
		straightCards.add(card3c);
		straightCards.add(card2s);
		straightCards.add(card5d);
		
		HandResult handResult = straightCards.isStraight();
		assertNotNull(handResult);
		
		
		// verify that straight cards are matched
		List<Card> matchedCards = Arrays.asList(card2s, card3c, card4h, card5d, card6h);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
	}
	
}
