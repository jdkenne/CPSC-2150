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

    int maxRow = 6;
    int maxCol = 9;
    int numToWin = 5;

    /**
     * @return Number of rows
     * @post getNumRows = numRows
     */
     default int getNumRows() {
         return maxRow;
     }

    /**
     * @return Number of columns
     * @post getNumColumns = numCols
     */
    default int getNumColumns() {
        return maxCol;
    }

    /**
     * @return Number of tokens in a row needed to win
     * @post getNumToWin = numToWin
     */
     default int getNumToWin() {
         return numToWin;
     }

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
     * @pre c is col 0 <= col <= maxCol, p = 'X' or 'O'
     * @post board[i][c] = p
     * @param p is the player char = 'X', 'O'
     * @param c is the column of the game board
     */
    void placeToken(char p, int c);

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the row the previous token was placed on contains numToWin consecutive equivalent characters
     * @pre [board contains pos] and p == 'X' || 'O'
     * @post checkHorizWin = true if numToWin consecutive horizontal spaces are occupied by player
     */
    default boolean checkHorizWin(BoardPosition pos, char p) {
        int count = 0;
        for (int i = 0; i <= pos.getColumn(); i++) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow(), i), p))
                count++;
            else
                count = 0;
        }
        return count == getNumToWin();
    }

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the column the previous token was placed on contains numToWn consecutive equivalent characters
     * @pre [board contains pos] and p == 'X' || 'O'
     * @post checkVertWin = true iff numToWin consecutive vertical spaces are occupied by player
     */
    default boolean checkVertWin(BoardPosition pos, char p) {
        int count = 0;
        for (int i = 0; i < getNumRows(); i++) {
            if (isPlayerAtPos(new BoardPosition(i, pos.getColumn()), p))
                count++;
        }
        return count == getNumToWin();
    }

    /**
     * @param pos Coordinates the last token was placed
     * @param p   The player who placed the last token
     * @return If the diagonals on which the previous token was placed contain numToWin consecutive equivalent characters
     * @pre [board contains pos] and p == 'X' || 'O'
     * @post checkDiagWin = true if numToWin consecutive diagonal spaces occupied by player
     */
    default boolean checkDiagWin(BoardPosition pos, char p) {
        int count = 1;
        int index = 1;
        // checks spaces below and to the left
        while (pos.getRow() - index >= 0 && pos.getColumn() - index >= 0) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow() - index, pos.getColumn() - index), p)) {
                count++;
                if (count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }
        index = 1;
        // checks spaces above and to the right
        while (pos.getRow() + index < getNumRows() && pos.getColumn() + index < getNumColumns()) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow() + index, pos.getColumn() + index),p)) {
                count++;
                if (count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }

        //checks spaces above and to the left
        while (pos.getRow() + index < getNumRows() && pos.getColumn() - index >= 0) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow() + index, pos.getColumn() - index),p)) {
                count++;
                if (count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }
        index = 1;
        //checks spaces below and to the right
        while (pos.getRow() - index >= 0 && pos.getColumn() + index < getNumColumns()) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow() - index, pos.getColumn() + index),p)) {
                count++;
                if (count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }
        return false;
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
        int i = 0;
        if(checkIfFree(c)) {
            while(whatsAtPos(new BoardPosition(i,c)) == ' ') {
                i++;
            }
            row = i;
        }
        BoardPosition pos = new BoardPosition(row, c);
        return checkDiagWin(pos, whatsAtPos(pos)) || checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos));
    }


    /**
     * @param c is col
     * @return checkIfFree() = board[i][c] == ' '
     * @pre 0 <= col <= maxCol
     * @post col >= 0
     */
    default boolean checkIfFree(int c) {
        boolean free = false;
        for (int i = 0; i < maxRow; i++) {
            if (whatsAtPos(new BoardPosition(i, c)) == ' ') {
                free = true;

            }

        }
        return free;
    }
}