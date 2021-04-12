package cpsc2150.extendedConnectX.GameBoard;

/**
 * This interface holds the information of a ConnectX board, and processes what occurs within the game
 * @Defines: numRows: Z - The number of rows on the game board
 *           numCols: Z - The number of columns on the game board
 *           numToWin: Z - The number of tokens in a row needed to win
 *
 * @Initialization_Ensures: Board will be size numRows x numCols
 *
 * @Constraints: numRows = getNumRows && numCols = getNumCols && numToWin = getNumToWin
 *
 */
public interface IGameBoard {
    int defaultNumRows = 6;
    int defaultNumCols = 9;
    int defaultNumToWin = 5;
    int doubleDigitNum = 10;

    /**
     * @return Number of rows
     * @post getNumRows = numRows
     */
     int getNumRows();

    /**
     * @return Number of columns
     * @post getNumColumns = numCols
     */
     int getNumColumns();

    /**
     * @return Number of tokens in a row needed to win
     * @post getNumToWin = numToWin
     */
    int getNumToWin();

    /**
     * @return If the game has resulted in a tie
     * @pre [checkForWinner has just been called]
     * @post checkTie = true if every space in the board is filled
     */
    default boolean checkTie() {
        BoardPosition pos = new BoardPosition(0, 0);
        for (int i = 0; i < getNumColumns(); i++) {
            if (checkIfFree(pos.getColumn()))
                return false;
            pos = new BoardPosition(pos.getRow(), pos.getColumn() + 1);
        }

        return true;
    }

    /**
     * @param pos    Coordinates to be checked
     * @param player The current players token
     * @return If a player is stored in board at pos
     * @pre [board contains pos]
     * @post isPlayerAtPos = true if player is at pos
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }


    /**
     * @pre c is col 0 <= col <= maxCol, p = 'some character value'
     * @post board[i][c] = p
     * @param p is the player char = 'some character value'
     * @param c is the column of the game board
     */
    void placeToken(char p, int c);

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the row the previous token was placed on contains numToWin consecutive equivalent characters
     * @pre [board contains pos] and p == 'some character value'
     * @post checkHorizWin = true if numToWin consecutive horizontal spaces are occupied by player
     */
    default boolean checkHorizWin(BoardPosition pos, char p) {
        int lastCol = pos.getColumn() - 1 - getNumToWin();
        int nextCol = pos.getColumn() + 1 + getNumToWin();
        int inARow = 0;

        if (lastCol < 0)
            lastCol = 0;

        BoardPosition newPos = new BoardPosition(pos.getRow(), lastCol);

        while (newPos.getColumn() < nextCol) {
            if (isPlayerAtPos(newPos, p) && whatsAtPos(newPos) == p) {
                inARow++;
                if (inARow >= getNumToWin())
                    return true;
            } else {
                inARow = 0;
            }

            newPos = new BoardPosition(newPos.getRow(), newPos.getColumn() + 1);
        }

        return false;
    }

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the column the previous token was placed on contains numToWn consecutive equivalent characters
     * @pre [board contains pos] and p == 'X' || 'O'
     * @post checkVertWin = true iff numToWin consecutive vertical spaces are occupied by player
     */
    default boolean checkVertWin(BoardPosition pos, char p) {
        int lastRow = pos.getRow() - 1 - getNumToWin();
        int nextRow = pos.getRow() + 1 + getNumToWin();
        int inARow = 0;

        if (lastRow < 0)
            lastRow = 0;

        BoardPosition newPos = new BoardPosition(lastRow, pos.getColumn());

        while (newPos.getRow() < nextRow) {
            if (isPlayerAtPos(newPos, p) && whatsAtPos(newPos) == p) {
                inARow++;
                if (inARow >= getNumToWin())
                    return true;
            } else {
                inARow = 0;
            }

            newPos = new BoardPosition(newPos.getRow() + 1, newPos.getColumn());
        }
        return false;
    }

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the diagonals on which the previous token was placed contain numToWin consecutive equivalent characters
     * @pre [board contains pos] and p == 'X' || 'O'
     * @post checkDiagWin = true if numToWin consecutive diagonal spaces occupied by player
     */
    default boolean checkDiagWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int counter = 1;
        char playerToken;
        //Not going back to the original position that's being passed
        //Only checking whats on the sides of it and never truly staring at the last token position

        //Going up and right
        //Incrementing both row and column at the same time
        for (int i = row + 1, j = col + 1; i < getNumRows() && j < getNumColumns(); i++, j++) {
            BoardPosition initialPosition = new BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p) {
                counter++;
            } else {
                break;
            }
        }
        if (counter >= getNumToWin()) {
            return true;
        }
        //Going down and left
        //Decrementing both row and column at the same time
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            BoardPosition initialPosition = new BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
            } else {
                break;
            }
        }
        if (counter >= getNumToWin()) {
            return true;
        }
        //Going up and left
        //Incrementing row Decrementing column
        counter = 1;
        for (int i = row + 1, j = col - 1; i < getNumRows() && j >= 0; i++, j--) {
            BoardPosition initialPosition = new BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
            } else {
                break;
            }
        }
        if (counter >= getNumToWin()) {
            return true;
        }
        //Going down and right
        //Decrementing row and incrementing column
        for (int i = row - 1, j = col + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            BoardPosition initialPosition = new BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
                if (counter >= getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return counter >= getNumToWin();
    }

    /**
     * @param pos Coordinates at which character will be accessed
     * @return The character stored in the board at pos
     * @pre pos is in the board and initialized
     * @post whatsAtPos = [char at pos in board]
     */
     char whatsAtPos(BoardPosition pos);

    /**
     * @param c The column the last space was placed in
     * @return Whether or not the last token placed resulted in a win
     * @pre 0 <= c <= numCols
     * @post checkForWin = true if checkHorizWin == true || checkVertWin == true || checkDiagWin == true
     */
    default boolean checkForWin(int c) {
        int row = 0;

        for (int i = 0; i < getNumRows(); i++) {
            BoardPosition initialPosition = new BoardPosition(i, c);
            if (whatsAtPos(initialPosition) != ' ') {
                row = i;
            }
        }
        BoardPosition pos = new BoardPosition(row, c);

        if (checkHorizWin(pos, whatsAtPos(pos))) {
            return true;
        } else if (checkVertWin(pos, whatsAtPos(pos))) {
            return true;
        } else {
            return checkDiagWin(pos, whatsAtPos(pos));
        }
    }



    /**
     * @param c is col
     * @return checkIfFree() = board[i][c] == ' '
     * @pre 0 <= col <= maxCol
     * @post col >= 0
     */
    default boolean checkIfFree(int c) {
        return ' ' == whatsAtPos(new BoardPosition(getNumRows() - 1, c));
    }
}