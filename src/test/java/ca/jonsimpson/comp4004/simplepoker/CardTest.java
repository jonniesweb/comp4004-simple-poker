package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

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
	
	@Test
	public void testCreateCardFromRankSuit() throws Exception {
		
		assertEquals(new Card("2C"), new Card(Rank.TWO, Suit.CLUB));
		assertEquals(new Card("3C"), new Card(Rank.THREE, Suit.CLUB));
		assertEquals(new Card("4H"), new Card(Rank.FOUR, Suit.HEART));
		assertEquals(new Card("5D"), new Card(Rank.FIVE, Suit.DIAMOND));
		assertEquals(new Card("6S"), new Card(Rank.SIX, Suit.SPADE));
		assertEquals(new Card("7C"), new Card(Rank.SEVEN, Suit.CLUB));
		assertEquals(new Card("8H"), new Card(Rank.EIGHT, Suit.HEART));
		assertEquals(new Card("9D"), new Card(Rank.NINE, Suit.DIAMOND));
		assertEquals(new Card("10S"), new Card(Rank.TEN, Suit.SPADE));
		assertEquals(new Card("JC"), new Card(Rank.JACK, Suit.CLUB));
		assertEquals(new Card("QH"), new Card(Rank.QUEEN, Suit.HEART));
		assertEquals(new Card("KD"), new Card(Rank.KING, Suit.DIAMOND));
		assertEquals(new Card("AS"), new Card(Rank.ACE, Suit.SPADE));
		
		assertEquals(new Card("AceSpades"), new Card(Rank.ACE, Suit.SPADE));
		assertEquals(new Card("TwoHearts"), new Card(Rank.TWO, Suit.HEART));
	}
	
	@Test(expected = NullPointerException.class)
	public void testCreateCardWithNullRankSuit() throws Exception {
		new Card(null);
	}
	
	@Test(expected = InvalidCardException.class)
	public void testCreateCardRankSuitInvalidShortLength() throws Exception {
		new Card("S");
	}
	
	@Test(expected = InvalidCardException.class)
	public void testCreateCardRankSuitInvalidLongLength() throws Exception {
		new Card("10DD");
	}
	
	@Test
	public void testCompareRank() throws Exception {
		// 2 < 4
		assertTrue(0 < new Card("2H").compareTo(new Card("4D")));
		
		// cards of same rank but different suit are the same
		assertEquals(0, new Card("4H").compareTo(new Card("4D")));
		
		// ace is high
		assertTrue(0 > new Card("AH").compareTo(new Card("4D")));
		
		// same card
		assertEquals(0, new Card("2D").compareTo(new Card("2D")));
	}
	
	@Test(expected = NullPointerException.class)
	public void testCompareRankNPE() throws Exception {
		new Card("4D").compareTo(null);
	}
	
	@Test
	public void testSortListOfCards() throws Exception {
		// create cards
		Card card2 = new Card("2H");
		Card card3 = new Card("3H");
		Card card5 = new Card("5H");
		Card card6 = new Card("6H");
		Card cardAce = new Card("AH");
		
		// add cards in unsorted order
		ArrayList<Card> list = new ArrayList<Card>();
		list.add(card2);
		list.add(card6);
		list.add(card3);
		list.add(cardAce);
		list.add(card5);
		
		// sort the cards
		Collections.sort(list);
		
		// assert correct order
		assertEquals(cardAce, list.get(0));
		assertEquals(card6, list.get(1));
		assertEquals(card5, list.get(2));
		assertEquals(card3, list.get(3));
		assertEquals(card2, list.get(4));
		
	}
}
