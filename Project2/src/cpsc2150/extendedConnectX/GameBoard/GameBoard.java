//Joshua Kennerly, CPSC 2150, Section 003, 02-07-2021
package cpsc2150.extendedConnectX.GameBoard;

/**
 * @invariants 0 <= row <= 4 and 0 <= col <= 8
 * @invariants numToWin < numRows && numToWin < numCols
 */
public class GameBoard extends AbsGameBoard {

    private final char[][] Board;
    /**
     * @pre none
     * @post [Board is created and Board is empty]
     */
    public GameBoard() {
        Board = new char[maxRow][maxCol];
        for(int i = 0; i <= maxRow-1; i++) {
            for(int j = 0; j <= maxCol-1; j++) {
                Board[i][j] = ' ';
            }
        }

    }

    /**
     * @pre c is col 0 <= col <= maxCol, p = 'X' or 'O'
     * @post board[i][c] = p
     * @param p is the player char = 'X', 'O'
     * @param c is the column of the game board
     */
    public void placeToken(char p, int c) {
        /*
        places a token p in column c on the game
        board. The token will be placed in the lowest available row in column c.
         */
        if(checkIfFree(c)) {
            for(int i = getNumRows()-1; i >= 0; i--) {
                if(Board[i][c] == ' ') {
                    Board[i][c] = p;
                    break;
                }
            }
        }
    }

    /**
     * @pre [pos has a row and a column]
     * @post [pos is in range of game board]
     * @param pos the position being stored or read and is not null
     * @return player = 'X', 'O' or ' '
     */
    public char whatsAtPos(BoardPosition pos) {
        // returns the char that is in position pos of
        // the game board. If there is no token at the spot it should return a blank space character: ‘ ’.
        return Board[pos.getRow()][pos.getColumn()];
    }

}
