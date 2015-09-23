package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ca.jonsimpson.comp4004.simplepoker.Card;
import ca.jonsimpson.comp4004.simplepoker.Card.Suit;
import ca.jonsimpson.comp4004.simplepoker.Card.Rank;
import ca.jonsimpson.comp4004.simplepoker.GameCards;

public class CardTest {
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
	
	@Test(expected = InvalidCardException.class)
	public void testCreateCardWithNullSuit() throws Exception {
		new Card(Rank.ACE, null);
	}
	
	@Test(expected = InvalidCardException.class)
	public void testCreateCardWithNullValue() throws Exception {
		new Card(null, Suit.CLUB);
	}
	
	@Test(expected = InvalidCardException.class)
	public void testCreateCardWithBothNull() throws Exception {
		new Card(null, null);
	}
	
	@Test
	public void testCardsEqual() throws Exception {
		assertTrue(new Card(Rank.ACE, Suit.CLUB).equals(new Card(Rank.ACE, Suit.CLUB)));
	}
	
	@Test
	public void testCardsNotEqual() throws Exception {
		assertFalse(new Card(Rank.ACE, Suit.CLUB).equals(new Card(Rank.ACE, Suit.SPADE)));
	}
	
}
