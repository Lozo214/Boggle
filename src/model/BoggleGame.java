package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashSet;
import java.util.Set;

//@author Lorenzo Washington

public class BoggleGame {

	private DiceTray theBoard;
	private String input = "";
	private ArrayList<String> rawWordList = new ArrayList<>();
	public ArrayList<String> incorrectWords = new ArrayList<>();
	private ArrayList<String> goodWords = new ArrayList<>();
	private ArrayList<String> wordsOnBoard = new ArrayList<>();
	private ArrayList<String> wordsNotFound = new ArrayList<>();
	private Set<String> dictionary = new HashSet<>();
	private int score = 0;

	// Constructor for testing.
	public BoggleGame(char[][] board) {
		theBoard = new DiceTray(board);
	}

	// Constructor for random boards.
	public BoggleGame() {
		theBoard = new DiceTray();
	}

	// Print the game board (for console).
	public void printBoard() {
		System.out.print(theBoard.toString());
	}

	// Get user input. Returns a string.
	public void generateInput() {

		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNextLine()) {
			String line = keyboard.nextLine();
			input += line + " ";
			if (line.contains("ZZ"))
				break;
		}

		keyboard.close();
	}

	// Turn user input into an array list of words.
	// Removes empty strings and makes all strings lowercase.
	public void stringToList() {

		String[] wordArray = input.split(" ");
		for (String word : wordArray) {
			word = word.toLowerCase();
			word.trim();
			if (word.equals(""))
				continue;
			else if (word.equals("zz"))
				break;
			else if (!rawWordList.contains(word)) // gets rid of dupes
				rawWordList.add(word);
		}
	}

	// Same as other, but without "ZZ" stopping it
	public void stringToListGUI() {

		String[] wordArray = input.split(" ");
		for (String word : wordArray) {
			word = word.toLowerCase();
			word.trim();
			if (word.equals(""))
				continue;
			else if (!rawWordList.contains(word)) // gets rid of dupes
				rawWordList.add(word);
		}
	}

	public void createDictionary() {

		File fileName = new File("BoggleWords.txt");
		Scanner scan;
		try {
			scan = new Scanner(fileName);
			while (scan.hasNextLine()) {
				String word = scan.nextLine();
				dictionary.add(word);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// Finds every word that is on the board AND in the dictionary.
	public void findAllWordsOnBoard() {

		for (String word : dictionary) {
			if (theBoard.found(word)) {
				wordsOnBoard.add(word);
			}
		}
	}

	// Put words that are on the board AND in the dictionary into a new list.
	public void goodWords() {

		for (String word : rawWordList) {
			if (wordsOnBoard.contains(word)) {
				goodWords.add(word);
			} else {
				incorrectWords.add(word);
			}
		}
	}

	// Makes a list that is wordsOnBoard minus goodWords.
	public void wordsNotFound() {
		for (String word : wordsOnBoard) {
			if (!goodWords.contains(word)) {
				wordsNotFound.add(word);
			}
		}
	}

	public void score() {
		for (String word : goodWords) {

			int length = word.length();

			if (length >= 8)
				score += 11;
			else if (length == 7)
				score += 5;
			else if (length == 6)
				score += 3;
			else if (length == 5)
				score += 2;
			else if (length == 4)
				score += 1;
			else if (length == 3)
				score += 1;
		}
	}

	public void consolePlay() {
		this.createDictionary();
		this.findAllWordsOnBoard();
		this.printBoard();
		System.out.println("Enter words or ZZ to quit: \n");
		this.generateInput();
		this.stringToList();
		this.goodWords();
		this.score();
		this.wordsNotFound();
		System.out.println("");
		System.out.println("Your Score: " + this.getScore());
		System.out.println("Words you found:");
		System.out.println("================");
		System.out.println(this.foundWordsOutput());
		System.out.println("");
		System.out.println("Incorrect words:");
		System.out.println("================");
		System.out.println(this.incorrectWordsOutput());
		System.out.println("");
		System.out.println("You could have found " + wordsNotFound.size() + " more words.");
		System.out.println("The computer found all of your words plus these:");
		System.out.println("================");
		System.out.println(this.wordsNotFoundOutput());
	}

	// for the GUI
	public void endGame() {
		this.createDictionary();
		this.findAllWordsOnBoard();
		this.stringToListGUI();
		this.goodWords();
		this.score();
		this.wordsNotFound();
	}

	// for the GUI
	public void newGame() {

	}

	// getters
	public int getScore() {
		return score;
	}

	public int getNumOfWordsNotFound() {
		return wordsNotFound.size();
	}

	// setter
	public void setInput(String textArea) {
		input = textArea;
	}

	public String foundWordsOutput() {

		goodWords.sort(null);
		String retVal = "";
		for (int i = 0; i < goodWords.size(); i++) {
			retVal += goodWords.get(i) + " ";
			if (i != 0 && i % 10 == 0)
				retVal += "\n";
		}
		retVal = retVal.trim();

		return retVal;
	}

	public String incorrectWordsOutput() {

		incorrectWords.sort(null);

		String retVal = "";
		for (int i = 0; i < incorrectWords.size(); i++) {
			retVal += incorrectWords.get(i) + " ";
			if (i != 0 && i % 10 == 0)
				retVal += "\n";
		}

		retVal = retVal.trim();

		return retVal;
	}

	public String wordsNotFoundOutput() {

		wordsNotFound.sort(null);

		String retVal = "";
		for (int i = 0; i < wordsNotFound.size(); i++) {
			retVal += wordsNotFound.get(i) + " ";
			if (i != 0 && i % 10 == 0)
				retVal += "\n";
		}
		/*
		 * for (String word : wordsNotFound) { retVal += word + " "; }
		 */
		retVal = retVal.trim();

		return retVal;
	}

	public char[][] theBoard() {
		return theBoard.getBoard();
	}

}
