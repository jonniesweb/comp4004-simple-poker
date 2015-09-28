package ca.jonsimpson.comp4004.simplepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private enum State {
		ENTER_PLAYERS, ENTER_HANDS
	}
	
	private State state = State.ENTER_PLAYERS; 
	private int playerCount = 0;
	private Map<Integer, GameCards> cards;
	private List<Integer> scores = new ArrayList<>();;
	
	public Game() {
		reset();
	}
	
	public void processLine(String s) {
		try {
			if (state == State.ENTER_PLAYERS) {
				int numPlayers = Integer.parseInt(s);
				if (numPlayers >= 2 && numPlayers <= 4) {
					playerCount = numPlayers;
					state = State.ENTER_HANDS;
					System.out.println("Please enter a hand for each player. Format is: <player id> <RankSuit>...");
				}
				
			} else if (state == State.ENTER_HANDS) {
				String[] split = s.split(" ");
				if (split.length != 6) {
					System.out.println("invalid player hand. Format is: <player id> <RankSuit>...");
					return;
				}
				
				// get player id
				int playerId = Integer.parseInt(split[0]);
				if (playerId > playerCount && playerId < 2) {
					System.out.println("invalid player id, only numbers between 1 and "
							+ playerCount + " are allowed");
					return;
				}
				
				// get cards
				GameCards gameCards = new GameCards();
				for (int i = 1; i < split.length; i++) {
					Card card = new Card(split[i]);
					gameCards.add(card);
				}
				
				cards.put(playerId, gameCards);
				
				if (cards.keySet().size() >= playerCount) {
					determineWinner();
					reset();
					state = State.ENTER_PLAYERS;
					
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number");
		} catch (InvalidCardException e) {
			System.out.println("Invalid card entered");
		} catch (Exception e) {
			System.out.println("An problem occurred, try again");
		}
	}
	
	private void determineWinner() {
		ArrayList<GameCards> gameCards = new ArrayList<>(cards.values());
		Collections.sort(gameCards);
		
		System.out.println("Scores");
		for (GameCards c : gameCards) {
			for (Integer i : cards.keySet()) {
				if (cards.get(i).equals(c)) {
					scores.add(i);
					System.out.println(i + " " + c);
				}
			}
		}
	}

	private void reset() {
		playerCount = 0;
		cards = new HashMap<Integer, GameCards>();
		System.out.println("Please enter the number of players [2-4]:");
	}

	public Object getScores() {
		return scores;
	}

	
}
