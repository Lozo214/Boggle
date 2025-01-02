package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

/**
 * Model the tray of dice in the game Boggle. A DiceTray can be constructed with
 * a 4x4 array of characters for testing.
 * 
 * A 2nd constructor with no arguments can be added later to simulate the
 * shaking of 16 dice and selection of one side.
 * 
 * @author Lorenzo Washington and Rick Mercer
 */

public class DiceTray {

	private char[][] path;
	private char[][] theBoard;
	public static final char TRIED = '@';
	public static final char PART_OF_WORD = '!';
	private String attempt;
	private int index;
	public static final int SIZE = 4;
	public static final int NUMBER_SIDES = 6;

	/**
	 * Construct a tray of dice using a hard coded 2D array of chars. Use this for
	 * testing
	 * 
	 * @param newBoard The 2D array of characters used in testing
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	// Constructor for random board.
	public DiceTray() {
		// Create the dice
		char[] dice1 = { 'L', 'R', 'Y', 'T', 'T', 'E' };
		char[] dice2 = { 'A', 'N', 'A', 'E', 'E', 'G' };
		char[] dice3 = { 'A', 'F', 'P', 'K', 'F', 'S' };
		char[] dice4 = { 'Y', 'L', 'D', 'E', 'V', 'R' };
		char[] dice5 = { 'V', 'T', 'H', 'R', 'W', 'E' };
		char[] dice6 = { 'I', 'D', 'S', 'Y', 'T', 'T' };
		char[] dice7 = { 'X', 'L', 'D', 'E', 'R', 'I' };
		char[] dice8 = { 'Z', 'N', 'R', 'N', 'H', 'L' };
		char[] dice9 = { 'E', 'G', 'H', 'W', 'N', 'E' };
		char[] dice10 = { 'O', 'A', 'T', 'T', 'O', 'W' };
		char[] dice11 = { 'H', 'C', 'P', 'O', 'A', 'S' };
		char[] dice12 = { 'N', 'M', 'I', 'H', 'U', 'Q' };
		char[] dice13 = { 'S', 'E', 'O', 'T', 'I', 'S' };
		char[] dice14 = { 'M', 'T', 'O', 'I', 'C', 'U' };
		char[] dice15 = { 'E', 'N', 'S', 'I', 'E', 'U' };
		char[] dice16 = { 'O', 'B', 'B', 'A', 'O', 'J' };

		// Put dice in arrary list so they can be shuffled.
		ArrayList<char[]> theDice = new ArrayList<>(List.of(dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8,
				dice9, dice10, dice11, dice12, dice13, dice14, dice15, dice16));

		// Randomly create the board
		Collections.shuffle(theDice);
		Random rand = new Random();

		char[][] newBoard = {
				{ theDice.get(0)[rand.nextInt(5)], theDice.get(1)[rand.nextInt(5)], theDice.get(2)[rand.nextInt(5)],
						theDice.get(3)[rand.nextInt(5)] },
				{ theDice.get(4)[rand.nextInt(5)], theDice.get(5)[rand.nextInt(5)], theDice.get(6)[rand.nextInt(5)],
						theDice.get(7)[rand.nextInt(5)] },
				{ theDice.get(8)[rand.nextInt(5)], theDice.get(9)[rand.nextInt(5)], theDice.get(10)[rand.nextInt(5)],
						theDice.get(11)[rand.nextInt(5)] },
				{ theDice.get(12)[rand.nextInt(5)], theDice.get(13)[rand.nextInt(5)], theDice.get(14)[rand.nextInt(5)],
						theDice.get(15)[rand.nextInt(5)] }, };

		theBoard = newBoard;
	}

	/**
	 * Return true if search is word that can found on the board following the rules
	 * of Boggle like the same letter can only be used once.
	 * 
	 * @param str A word that may be in the board by connecting consecutive letters
	 *
	 * @return True if search is found
	 */
	public boolean found(String str) {
		if (str.length() < 3)
			return false;
		attempt = str.toUpperCase().trim();
		boolean found = false;
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++)
				if (theBoard[r][c] == attempt.charAt(0)) {
					init();
					found = recursiveSearch(r, c);
					if (found) {
						return true;
					}
				}
		}
		return found;
	}

	// Keep a 2nd 2D array to remember the characters that have been tried
	private void init() {
		path = new char[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				path[r][c] = '.';
		index = 0;
	}

	// This is like the Obstacle course escape algorithm.
	// Now we are checking 8 possible directions (no wraparound)
	private boolean recursiveSearch(int r, int c) {
		boolean found = false;

		if (valid(r, c)) { // valid returns true if in range AND one letter was found
			path[r][c] = TRIED;
			if (theBoard[r][c] == 'Q')
				index += 2;
			else
				index++;

			// Look in 8 directions for the next character
			if (index >= attempt.length())
				found = true;
			else {
				found = recursiveSearch(r - 1, c - 1);
				if (!found)
					found = recursiveSearch(r - 1, c);
				if (!found)
					found = recursiveSearch(r - 1, c + 1);
				if (!found)
					found = recursiveSearch(r, c - 1);
				if (!found)
					found = recursiveSearch(r, c + 1);
				if (!found)
					found = recursiveSearch(r + 1, c - 1);
				if (!found)
					found = recursiveSearch(r + 1, c);
				if (!found)
					found = recursiveSearch(r + 1, c + 1);
				// If still not found, allow backtracking to use the same letter in a
				// different location later as in looking for "BATTLING" in this board
				//
				// L T T X // Mark leftmost T as untried after it finds a 2nd T but not the L.
				// I X A X
				// N X X B
				// G X X X
				//
				if (!found) {
					path[r][c] = '.'; // Rick used . to mark the 2nd 2D array as TRIED
					index--; // 1 less letter was found. Let algorithm find the right first (col 2)
				}
			} // End recursive case

			if (found) {
				// Mark where the letter was found. Not required, but could be used to
				// show the actual path of the word that was found.
				path[r][c] = theBoard[r][c];
			}
		}
		return found;
	}

	// Determine if a current value of row and columns can or should be tried
	private boolean valid(int r, int c) {
		return r >= 0 && r < SIZE && c >= 0 && c < SIZE && path[r][c] != TRIED
				&& theBoard[r][c] == attempt.charAt(index);
	}

	public String toString() {

		String retVal = "";
		int i = 0;
		while (i < 4) {
			retVal += theBoard[0][i] + " ";
			i++;
		}
		retVal = retVal.trim() + "\n";

		i = 0;
		while (i < 4) {
			retVal += theBoard[1][i] + " ";
			i++;
		}
		retVal = retVal.trim() + "\n";

		i = 0;
		while (i < 4) {
			retVal += theBoard[2][i] + " ";
			i++;
		}
		retVal = retVal.trim() + "\n";

		i = 0;
		while (i < 4) {
			retVal += theBoard[3][i] + " ";
			i++;
		}
		retVal = retVal.trim() + "\n";

		return retVal;
	}

	// getter. return 2D array of whats on the board.
	public char[][] getBoard() {
		return theBoard;
	}
}
