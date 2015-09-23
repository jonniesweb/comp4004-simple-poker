package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ca.jonsimpson.comp4004.simplepoker.Card;
import ca.jonsimpson.comp4004.simplepoker.GameCards;

public class CardTest {
	@Test
	public void testNoDuplicateCards() throws Exception {
		GameCards cards = new GameCards();
		
		Set<Card> cardSet = new HashSet<Card>(cards);
		
		assertEquals(cards.size(), cardSet.size());
	}
	
	@Test
	public void testHave52Cards() throws Exception {
		GameCards cards = new GameCards();
		
		int cardCount = 0;
		
		for (Card card : cards) {
			cardCount++;
		}
		
		assertEquals(52, cardCount);
	}
	
}
