package ca.jonsimpson.comp4004.simplepoker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class GameTest {
	
	@Test
	public void testProcessUserCommand() throws Exception {
		Game g = new Game();
		
		// enter two players
		g.processLine("2");
		
		// enter each players hand
		g.processLine("2 TenSpades TenHearts TenDiamonds TenClubs TwoSpades");
		g.processLine("1 AceSpades AceHearts AceDiamonds AceClubs TwoDiamonds");
		
		assertEquals(Arrays.asList(1, 2), g.getScores());
	}
}
