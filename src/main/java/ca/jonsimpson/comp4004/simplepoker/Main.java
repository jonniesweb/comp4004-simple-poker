package ca.jonsimpson.comp4004.simplepoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Game game = new Game();
		while (true) {
			String line;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			game.processLine(line);
		}
	}
}
