package view_controller;

import model.BoggleGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//@author Loreno Washington

public class BoggleGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	// Instance Variables
	private BoggleGame theGame;
	private GridPane dice;
	private GridPane wordGuess;
	private TextArea wordGuessText;
	private Button newGame;
	private Button endGame;
	private Label wordsFoundLabel;
	private TextArea wordsFoundText;
	private Label incorrectWordsLabel;
	private TextArea incorrectWordsText;
	private Label notFoundLabel;
	private TextArea notFoundText;
	private char[][] board;
	private GridPane grid = new GridPane();

	private Label letterLabel1 = new Label();
	private Label letterLabel2 = new Label();
	private Label letterLabel3 = new Label();
	private Label letterLabel4 = new Label();
	private Label letterLabel5 = new Label();
	private Label letterLabel6 = new Label();
	private Label letterLabel7 = new Label();
	private Label letterLabel8 = new Label();
	private Label letterLabel9 = new Label();
	private Label letterLabel10 = new Label();
	private Label letterLabel11 = new Label();
	private Label letterLabel12 = new Label();
	private Label letterLabel13 = new Label();
	private Label letterLabel14 = new Label();
	private Label letterLabel15 = new Label();
	private Label letterLabel16 = new Label();

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Boggle!");

		theGame = new BoggleGame();

		createGridElements();

		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(new Label(""), 3, 9); // spacer
		grid.add(dice, 1, 1);
		grid.add(wordGuess, 2, 1);
		grid.add(newGame, 1, 2);
		grid.add(endGame, 2, 2);
		grid.add(wordsFoundLabel, 1, 3);
		grid.add(wordsFoundText, 1, 4, 2, 1);
		grid.add(incorrectWordsLabel, 1, 5);
		grid.add(incorrectWordsText, 1, 6, 2, 1);
		grid.add(notFoundLabel, 1, 7);
		grid.add(notFoundText, 1, 8, 2, 1);

		newGame.setOnAction(new NewGameHandler());
		endGame.setOnAction(new EndGameHandler());

		Scene scene = new Scene(grid, 750, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	// Assign values to all grid elements.
	private void createGridElements() {

		board = theGame.theBoard();
		createDice();
		wordGuess = createWordGuessSpace();
		newGame = new Button("New Game");
		endGame = new Button("End Game");
		wordsFoundLabel = new Label("Words Found");
		wordsFoundText = createWordDisplay();
		incorrectWordsLabel = new Label("Incorrect Words");
		incorrectWordsText = createWordDisplay();
		notFoundLabel = new Label("Words Not Found");
		notFoundText = createWordDisplay();

	}

	private class EndGameHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			String input = wordGuessText.getText();
			input = input.replaceAll("\n", " ");
			theGame.setInput(input);
			theGame.endGame();
			wordsFoundText.setText(theGame.foundWordsOutput());
			incorrectWordsText.setText(theGame.incorrectWordsOutput());
			notFoundLabel.setText("Words Not Found: " + theGame.getNumOfWordsNotFound());
			notFoundText.setText(theGame.wordsNotFoundOutput());
			endGame.setDisable(true);
		}

	}

	private class NewGameHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			wordGuessText.clear();
			wordsFoundText.clear();
			incorrectWordsText.clear();
			notFoundText.clear();
			notFoundLabel.setText("Words not found");

			theGame = new BoggleGame();
			board = theGame.theBoard();

			letterLabel1.setText(String.valueOf(board[0][0]));
			letterLabel2.setText(String.valueOf(board[1][0]));
			letterLabel3.setText(String.valueOf(board[2][0]));
			letterLabel4.setText(String.valueOf(board[3][0]));

			letterLabel5.setText(String.valueOf(board[0][1]));
			letterLabel6.setText(String.valueOf(board[1][1]));
			letterLabel7.setText(String.valueOf(board[2][1]));
			letterLabel8.setText(String.valueOf(board[3][1]));

			letterLabel9.setText(String.valueOf(board[0][2]));
			letterLabel10.setText(String.valueOf(board[1][2]));
			letterLabel11.setText(String.valueOf(board[2][2]));
			letterLabel12.setText(String.valueOf(board[3][2]));

			letterLabel13.setText(String.valueOf(board[0][3]));
			letterLabel14.setText(String.valueOf(board[1][3]));
			letterLabel15.setText(String.valueOf(board[2][3]));
			letterLabel16.setText(String.valueOf(board[3][3]));

			endGame.setDisable(false);
		}

	}

	private TextArea createWordDisplay() {

		TextArea words = new TextArea();
		Font font = Font.font("Courier", FontWeight.NORMAL, 12);
		words.setFont(font);
		words.setWrapText(true);
		words.setEditable(false);

		return words;
	}

	private GridPane createWordGuessSpace() {

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(new Label("Enter Words"), 0, 0);

		wordGuessText = new TextArea();
		Font font = Font.font("Courier", FontWeight.NORMAL, 12);
		wordGuessText.setFont(font);
		wordGuessText.setWrapText(true);

		grid.add(wordGuessText, 0, 1);

		return grid;
	}

	private void createDice() {

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		letterLabel1.setText(String.valueOf(board[0][0]));
		letterLabel2.setText(String.valueOf(board[0][1]));
		letterLabel3.setText(String.valueOf(board[0][2]));
		letterLabel4.setText(String.valueOf(board[0][3]));

		letterLabel5.setText(String.valueOf(board[1][0]));
		letterLabel6.setText(String.valueOf(board[1][1]));
		letterLabel7.setText(String.valueOf(board[1][2]));
		letterLabel8.setText(String.valueOf(board[1][3]));

		letterLabel9.setText(String.valueOf(board[2][0]));
		letterLabel10.setText(String.valueOf(board[2][1]));
		letterLabel11.setText(String.valueOf(board[2][2]));
		letterLabel12.setText(String.valueOf(board[2][3]));

		letterLabel13.setText(String.valueOf(board[3][0]));
		letterLabel14.setText(String.valueOf(board[3][1]));
		letterLabel15.setText(String.valueOf(board[3][2]));
		letterLabel16.setText(String.valueOf(board[3][3]));

		letterLabel1.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel2.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel3.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel4.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");

		letterLabel5.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel6.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel7.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel8.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");

		letterLabel9.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel10.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel11.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel12.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");

		letterLabel13.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel14.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel15.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");
		letterLabel16.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 5px;");

		letterLabel1.setMinSize(50, 50);
		letterLabel2.setMinSize(50, 50);
		letterLabel3.setMinSize(50, 50);
		letterLabel4.setMinSize(50, 50);

		letterLabel5.setMinSize(50, 50);
		letterLabel6.setMinSize(50, 50);
		letterLabel7.setMinSize(50, 50);
		letterLabel8.setMinSize(50, 50);

		letterLabel9.setMinSize(50, 50);
		letterLabel10.setMinSize(50, 50);
		letterLabel11.setMinSize(50, 50);
		letterLabel12.setMinSize(50, 50);

		letterLabel13.setMinSize(50, 50);
		letterLabel14.setMinSize(50, 50);
		letterLabel15.setMinSize(50, 50);
		letterLabel16.setMinSize(50, 50);

		letterLabel1.setAlignment(Pos.CENTER);
		letterLabel2.setAlignment(Pos.CENTER);
		letterLabel3.setAlignment(Pos.CENTER);
		letterLabel4.setAlignment(Pos.CENTER);

		letterLabel5.setAlignment(Pos.CENTER);
		letterLabel6.setAlignment(Pos.CENTER);
		letterLabel7.setAlignment(Pos.CENTER);
		letterLabel8.setAlignment(Pos.CENTER);

		letterLabel9.setAlignment(Pos.CENTER);
		letterLabel10.setAlignment(Pos.CENTER);
		letterLabel11.setAlignment(Pos.CENTER);
		letterLabel12.setAlignment(Pos.CENTER);

		letterLabel13.setAlignment(Pos.CENTER);
		letterLabel14.setAlignment(Pos.CENTER);
		letterLabel15.setAlignment(Pos.CENTER);
		letterLabel16.setAlignment(Pos.CENTER);

		grid.add(letterLabel1, 0, 0);
		grid.add(letterLabel2, 1, 0);
		grid.add(letterLabel3, 2, 0);
		grid.add(letterLabel4, 3, 0);

		grid.add(letterLabel5, 0, 1);
		grid.add(letterLabel6, 1, 1);
		grid.add(letterLabel7, 2, 1);
		grid.add(letterLabel8, 3, 1);

		grid.add(letterLabel9, 0, 2);
		grid.add(letterLabel10, 1, 2);
		grid.add(letterLabel11, 2, 2);
		grid.add(letterLabel12, 3, 2);

		grid.add(letterLabel13, 0, 3);
		grid.add(letterLabel14, 1, 3);
		grid.add(letterLabel15, 2, 3);
		grid.add(letterLabel16, 3, 3);

		dice = grid;

	}

}
