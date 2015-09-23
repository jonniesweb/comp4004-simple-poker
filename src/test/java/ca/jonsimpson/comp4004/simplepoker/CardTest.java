package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jonsimpson.comp4004.simplepoker.Card;
import ca.jonsimpson.comp4004.simplepoker.Card.Suit;
import ca.jonsimpson.comp4004.simplepoker.Card.Rank;

public class CardTest {
	
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
