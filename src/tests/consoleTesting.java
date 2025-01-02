package tests;

import model.BoggleGame;

//@author Lorenzo Washington

public class consoleTesting {

	public static void main(String args[]) {
		BoggleGame theGame = new BoggleGame(randChars);
		theGame.consolePlay();
		/*
		 * theGame.createDictionary(); theGame.findAllWordsOnBoard();
		 * theGame.printBoard(); System.out.println("Enter words or ZZ to quit: \n");
		 * theGame.generateInput(); theGame.stringToList(); theGame.goodWords();
		 * theGame.score(); theGame.wordsNotFound(); System.out.println("Your Score: " +
		 * theGame.getScore()); System.out.println("Words you found:");
		 * System.out.println("================");
		 * System.out.println(theGame.foundWordsOutput()); System.out.println("");
		 * System.out.println("Incorrect words:");
		 * System.out.println("================");
		 * System.out.println(theGame.incorrectWordsOutput()); System.out.println("");
		 * System.out.println("Words you didn't find: ");
		 * System.out.println("================");
		 * System.out.println(theGame.wordsNotFoundOutput());
		 */

		// System.out.println("Input: " + theGame.rawWordList);
		// System.out.println("Good words: " + theGame.goodWords);
		// System.out.println("Not words: " + theGame.incorrectWords);
		// System.out.println("All words on the board: " + theGame.wordsOnBoard);
		// System.out.println("Words that you didn't find: " + theGame.wordsNotFound);
	}

	private static char[][] randChars = {

			{ 'R', 'E', 'D', 'M' },

			{ 'B', 'A', 'N', 'O' },

			{ 'T', 'Q', 'D', 'F' },

			{ 'L', 'O', 'E', 'V' } };

}
