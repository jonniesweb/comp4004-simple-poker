package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.jonsimpson.comp4004.simplepoker.HandResult.Hand;

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
		GameCards royalFlushCards = new GameCards();
		royalFlushCards.add(new Card("AH"));
		royalFlushCards.add(new Card("KH"));
		royalFlushCards.add(new Card("QH"));
		royalFlushCards.add(new Card("JH"));
		royalFlushCards.add(new Card("10H"));
		
		// create a random hand
		GameCards flushCards = new GameCards();
		flushCards.add(new Card("2S"));
		flushCards.add(new Card("3S"));
		flushCards.add(new Card("4S"));
		flushCards.add(new Card("5S"));
		flushCards.add(new Card("10S"));
		
		// do the comparison
		assertEquals(1, royalFlushCards.compareTo(flushCards));
	}
	
	@Test
	public void testIsRoyalFlush() throws Exception {
		// create royal flush
		GameCards royalFlushCards = new GameCards();
		Card card10h = new Card("10H");
		Card cardjh = new Card("JH");
		Card cardqh = new Card("QH");
		Card cardkh = new Card("KH");
		Card cardah = new Card("AH");
		royalFlushCards.add(card10h);
		royalFlushCards.add(cardah);
		royalFlushCards.add(cardjh);
		royalFlushCards.add(cardkh);
		royalFlushCards.add(cardqh);
		
		HandResult handResult = royalFlushCards.isRoyalFlush();
		assertNotNull(handResult);
		assertEquals(Hand.ROYAL_FLUSH, handResult.getHand());
		
		// verify that royal flush cards are matched
		List<Card> matchedCards = Arrays.asList(card10h, cardjh, cardqh, cardkh, cardah);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
		
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
		
		
		HandResult handResult = straightFlushCards.isStraightFlush();
		assertNotNull(handResult);
		assertEquals(Hand.STRAIGHT_FLUSH, handResult.getHand());
		
		// verify that straight flush cards are matched
		List<Card> correctCards = Arrays.asList(card2h, card3h, card4h, card5h, card6h);
		assertContainsSameElements(correctCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
		
	}
	
	@Test
	public void testIsOnePair() throws Exception {
		GameCards cards = new GameCards();
		cards.add(new Card("2S"));
		cards.add(new Card("3H"));
		cards.add(new Card("4H"));
		cards.add(new Card("6C"));
		cards.add(new Card("6H"));
		
		
		// get the HandResult
		HandResult handResult = cards.isOnePair();
		assertNotNull(handResult);
		assertEquals(Hand.ONE_PAIR, handResult.getHand());
		
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
		assertEquals(Hand.TWO_PAIR, handResult.getHand());
		
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
		assertEquals(Hand.THREE_KIND, handResult.getHand());
		
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
		assertEquals(Hand.STRAIGHT, handResult.getHand());
		
		// verify that straight cards are matched
		List<Card> matchedCards = Arrays.asList(card2s, card3c, card4h, card5d, card6h);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
	}
	
	@Test
	public void testIsFlush() throws Exception {
		// create flush
		GameCards flushCards = new GameCards();
		Card card2s = new Card("2S");
		Card card7s = new Card("7S");
		Card card4s = new Card("4S");
		Card cardas = new Card("AS");
		Card card6s = new Card("6S");
		Card card3d = new Card("3D");
		flushCards.add(card6s);
		flushCards.add(card3d);
		flushCards.add(card4s);
		flushCards.add(card7s);
		flushCards.add(card2s);
		flushCards.add(cardas);
		
		HandResult handResult = flushCards.isFlush();
		assertNotNull(handResult);
		assertEquals(Hand.FLUSH, handResult.getHand());
		
		// verify that the flush cards are matched
		List<Card> matchedCards = Arrays.asList(card6s, card4s, card7s, card2s, cardas);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
	}
	
	
	@Test
	public void testIsFullHouse() throws Exception {
		// create full house
		GameCards fullHouseCards = new GameCards();
		Card card2s = new Card("2S");
		Card card2d = new Card("2D");
		Card card4s = new Card("4S");
		Card cardas = new Card("AS");
		Card cardad = new Card("AD");
		Card cardac = new Card("AC");
		fullHouseCards.add(cardad);
		fullHouseCards.add(cardac);
		fullHouseCards.add(card4s);
		fullHouseCards.add(card2d);
		fullHouseCards.add(card2s);
		fullHouseCards.add(cardas);
		
		HandResult handResult = fullHouseCards.isFullHouse();
		assertNotNull(handResult);
		assertEquals(Hand.FULL_HOUSE, handResult.getHand());
		
		// verify full house cards are matched
		List<Card> matchedCards = Arrays.asList(card2s, card2d, cardas, cardad, cardac);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
	}
	
	@Test
	public void testIsFourOfAKind() throws Exception {
		// create four of a kind
		GameCards fourOfAKindCards = new GameCards();
		Card card2s = new Card("2S");
		Card card6d = new Card("6D");
		Card cardah = new Card("AH");
		Card cardas = new Card("AS");
		Card cardad = new Card("AD");
		Card cardac = new Card("AC");
		fourOfAKindCards.add(cardad);
		fourOfAKindCards.add(cardac);
		fourOfAKindCards.add(cardah);
		fourOfAKindCards.add(card6d);
		fourOfAKindCards.add(card2s);
		fourOfAKindCards.add(cardas);
		
		HandResult handResult = fourOfAKindCards.isFourOfAKind();
		assertNotNull(handResult);
		assertEquals(Hand.FOUR_KIND, handResult.getHand());
		
		List<Card> matchedCards = Arrays.asList(cardah, cardad, cardas, cardac);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		List<Card> highCards = Arrays.asList(card6d);
		assertContainsSameElements(highCards, handResult.getHighCards());
	}
	
	@Test
	public void isHighCard() throws Exception {
		// create high cards
		GameCards highCards = new GameCards();
		Card card2s = new Card("2S");
		Card card6d = new Card("6D");
		Card cardah = new Card("AH");
		Card cardas = new Card("AS");
		Card cardad = new Card("AD");
		Card cardac = new Card("AC");
		highCards.add(cardad);
		highCards.add(cardac);
		highCards.add(cardah);
		highCards.add(card6d);
		highCards.add(card2s);
		highCards.add(cardas);
		
		HandResult handResult = highCards.isHighCard();
		assertNotNull(handResult);
		assertEquals(Hand.HIGH_CARD, handResult.getHand());
		
		List<Card> matchedCards = Arrays.asList(cardac, cardad, cardas, cardah, card6d);
		assertContainsSameElements(matchedCards, handResult.getMatchCards());
		
		// verify that there's no high cards
		assertNull(handResult.getHighCards());
	}
}
