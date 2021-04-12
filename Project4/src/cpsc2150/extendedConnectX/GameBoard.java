//Joshua Kennerly CPSC 2150, Section 003, 03-11-2021
package cpsc2150.extendedConnectX;

/**
 * @invariants 0 <= row <= 4 and 0 <= col <= 8
 * @invariants numToWin < numRows && numToWin < numCols
 */
public class GameBoard extends AbsGameBoard {

    private final char[][] Board;
    private final int rows;
    private final int cols;
    private final int numWin;

    /**
     * @pre none
     * @post [Board is created and Board is empty]
     */
    public GameBoard() {
        rows = defaultNumRows;
        cols = defaultNumCols;
        numWin = defaultNumToWin;
        Board = new char[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++){
                Board[i][j] = ' ';
            }
        }
    }

    /**
     *
     * @param r The number of rows
     * @param c The number of columns
     * @param w The number of tokens in a row needed to win
     * @post Returns a r X c blank game board
     */
    public GameBoard(int r, int c, int w){
        rows = r;
        cols = c;
        numWin = w;
        Board = new char[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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
        if(checkIfFree(c)) {
            for(int i = 0; i <= getNumRows()-1; i++) {
                if (whatsAtPos(new BoardPosition(i, c)) == ' ') {
                    Board[i][c] = p;
                    break;
                }
            }
        }
    }

    public int getNumRows(){
        return rows;
    }

    public int getNumColumns(){
        return cols;
    }

    public int getNumToWin(){
        return numWin;
    }

    /**
     * @pre [pos has a row and a column]
     * @post [pos is in range of game board]
     * @param pos the position being stored or read and is not null
     * @return player = 'some character value' or ' '
     */
    public char whatsAtPos(BoardPosition pos) {
        if (pos.getRow() >= rows || pos.getColumn() >= cols || pos.getRow() < 0 || pos.getColumn() < 0) {
            return '0';
        }
        return Board[pos.getRow()][pos.getColumn()];
    }
}
