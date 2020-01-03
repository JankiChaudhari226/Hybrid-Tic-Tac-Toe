/**
 * 
 * @author Janki Chaudhari
 *
 */
public class nk_TicTacToe {
	private Dictionary dic;
	private char[][] gameBoard;
	private int board_size;
	private int inline;
	private int max_levels;

	/**
	 * 
	 * @param board_size specifies the size of the board
	 * @param inline symbols in-line needed to win the game
	 * @param max_levels  maximum level of the game tree that will be explored by the program
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;

		gameBoard = new char[board_size][board_size];
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				gameBoard[row][col] = ' '; // assigns every character as ' '
			}

		}

	}

	/**
	 * 
	 * @return an empty Dictionary of the size that you have selected.

	 */
	public Dictionary createDictionary() {
		dic = new Dictionary(6669);
		return dic;
	}

	/**
	 *  This method first represents the content of gameBoard
	 *  then it checks whether the string representing the 
	 *  gameBoard is in the configurations dictionary
	 *   If it is in the dictionary this method returns its associated score, otherwise it returns the value -1.

	 * @param configurations variable of class dictionary 
	 * @return score associated with dictionary method if configuration else return -1
	 */
	public int repeatedConfig(Dictionary configurations) {
		String scoreBoard = "";
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				scoreBoard = scoreBoard + String.valueOf(gameBoard[row][col]); //represents gameBoard as string
			}
		}

		if (configurations.get(scoreBoard) != -1) {
			return configurations.get(scoreBoard);

		}

		return -1; // returns -1 if configuration is not found
	}

	/**
	 * 
	 * @param configurations inserts string and score into configuration dictionary 
	 * @param score score that needs to be inserted in configuration dictionary
	 */
	public void insertConfig(Dictionary configurations, int score) {
		String scoreBoard = "";
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				scoreBoard = scoreBoard + (String.valueOf(this.gameBoard[row][col])); // converts the gameboard into string 
			}
		}
		try {
			configurations.insert(new Record(scoreBoard, score)); // inserts the string into configuration dictionary 
		} catch (DictionaryException e) {
			System.out.println(" Dictionary Exception caught : Record already does exist in the Dictionary ");
		}

	}

	/**
	 * 
	 * @param row is the row position that stores symbol 
	 * @param col is the column position that stores symbol 
	 * @param symbol is 'X' , 'O' , ' ' used in tic tac toe 
	 */
	public void storePlay(int row, int col, char symbol) {

		gameBoard[row][col] = symbol; 

	}

	/**
	 * 
	 * @param row is the position of row in the gameBoard 
	 * @param col is the position of the column in the gameBoard
	 * @return returns true is there is an empty space else returns false
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param symbol is the symbol in the GameBoard 
	 * @return true if there are k adjacent occurrences of symbol in the same row, column, or diagonal of gameBoard
	 */
	
		 public boolean wins(char symbol) {

		        int inlineCount = 0;            // Records the number in a row

		        // Checks each Row for consecutive in line win 
		        for (int i = 0; i < gameBoard.length; i++) {

		            // Reset the counter for the row
		            inlineCount = 0;

		            for (int j = 0; j < gameBoard.length; j++) {
		                if (gameBoard[i][j] == symbol) {
		                    inlineCount = inlineCount + 1;      // Add one onto count

		                } else {
		                    inlineCount = 0;                     // Reset count
		                }

		                if (inlineCount == inline) {       // Check if count is enough to win
		                    return true;
		                }
		            }
		        }

		        // Checks each Column for consecutive in line win 
		        for (int k = 0; k < gameBoard.length; k++) {

		            // Reset the counter for the row
		            inlineCount = 0;

		            for (int l = 0; l < gameBoard.length; l++) {
		                if (gameBoard[l][k] == symbol) {
		                    inlineCount = inlineCount + 1;       // Add one onto count

		                } else {
		                    inlineCount = 0;                     // Reset count
		                }

		                if (inlineCount == inline) {        // Check if count is enough to win
		                    return true;
		                }
		            }
		        }

		        // Checks the Upper part of the Left Diagonal (top half - Along rows - Descending)
		        for (int m = 0; m < gameBoard.length; m++) {
		            inlineCount = 0;
		            if((gameBoard.length - m) >= inline){
		                for (int n = 0; n < gameBoard.length; n++) {

		                    if ( (m + n) < gameBoard.length) {            // Keep index in array bounds
		                        if (gameBoard[n][n + m] == symbol) {
		                            inlineCount = inlineCount + 1;        // Add one onto count

		                        } else {
		                            inlineCount = 0;                      // Reset count
		                        }

		                        if (inlineCount == inline) {         // Check if count is enough to win
		                            return true;
		                        }
		                    } else {
		                        inlineCount = 0;
		                    }
		                }
		            }
		        }

		        // Check Left Diagonal (bottom half - Along Columns - Descending)
		        for (int m = 0; m < gameBoard.length; m++) {
		            inlineCount = 0;
		            if((gameBoard.length - m) >= inline){
		                for (int n = 0; n < gameBoard.length; n++) {

		                    if ( (m + n) < gameBoard.length) {            // Keep index in array bounds
		                        if (gameBoard[n + m][n] == symbol) {
		                            inlineCount = inlineCount + 1;        // Add one onto count

		                        } else {
		                            inlineCount = 0;                      // Reset count
		                        }

		                        if (inlineCount == inline) {         // Check if count is enough to win
		                            return true;
		                        }
		                    } else {
		                        inlineCount = 0;
		                    }
		                }
		            }
		        }


		        // Check Left Diagonal (top half - Along Rows - Ascending)
		        for (int q = 0; q < gameBoard.length; q++) {
		            inlineCount = 0;
		            if((gameBoard.length - q) >= inline){
		                for (int r = gameBoard.length - 1; r >= 0; r--) {

		                    if ( (r - q) >= 0) {                                             // Keep index in array bounds
		                        if (gameBoard[gameBoard.length-r-1][r - q] == symbol) {
		                            inlineCount = inlineCount + 1;                           // Add one onto count

		                        } else {
		                            inlineCount = 0;                                         // Reset count
		                        }

		                        if (inlineCount == inline) {                            // Check if count is enough to win
		                            return true;
		                        }

		                    }



		                }
		            }
		        }

		        // Check Left Diagonal (top half - Along Columns - Ascending)
		        for (int q = 0; q < gameBoard.length; q++) {
		            inlineCount = 0;
		            if((gameBoard.length - q) >= inline){
		                for (int r = gameBoard.length - 1; r >= 0; r--){

		                    if ( (r - q) >= 0 && gameBoard.length-r < gameBoard.length) {     // Keep index in array bounds
		                        if (gameBoard[r-q][gameBoard.length-r] == symbol) {
		                            inlineCount = inlineCount + 1;                            // Add one onto count

		                        } else {
		                            inlineCount = 0;                                          // Reset count
		                        }

		                        if (inlineCount == inline) {                             // Check if count is enough to win
		                            return true;
		                        }

		                    }



		                }
		            }
		        }

		        return false;
		    }


	
	/**
	 * 
	 * @return Returns true if gameBoard has no empty positions left and no player has won the game.
	 */
	public boolean isDraw() {
		boolean isEmptyPos = false;
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				if (gameBoard[row][col] == ' ')
					isEmptyPos = true;
			}
		}
		if (!isEmptyPos && !wins('X') && !wins('O')) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return the 3 is the computer wins 
	 * returns 2 if their is a draw 
	 * returns 0 if human wins 
	 */
	public int evalBoard() {
		if (wins('O') == true) {
			return 3;
		} else if (wins('X') == true) {
			return 0;
		} else if (isDraw() == true) {
			return 2;
		} else {
			return 1;
		}

	}

}
