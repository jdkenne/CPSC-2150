package cpsc2150.extendedConnectX.GameBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Correspondence Board = board
 *
 * @invariant MIN_ROWS < numRows <= MAX_ROWS
 * @invariant MIN_COLS < numCols <= MAX_COLS
 * @invariant MIN_TO_WIN <= numToWin <= MAX_TO_WIN
 */
public class GameBoardMem extends AbsGameBoard{
    Map<Character, List<BoardPosition>> board = new HashMap<>();
    private final int rows;
    private final int cols;
    private final int numWin;

    /**
     *
     * @pre rows = number of rows to make the game board
     *      cols = number of columns to make the game board
     *      win = number of consecutive tokens needed to win
     *
     * @post rows = r
     *       cols = c
     *       numWin = w
     */
    public GameBoardMem(int r, int c, int w){
        rows = r;
        cols = c;
        numWin = w;
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
    public char whatsAtPos(BoardPosition pos){
        for (Map.Entry<Character, List<BoardPosition>> player : board.entrySet()) {
            for (int i = 0; i < player.getValue().size(); i++) {
                if (isPlayerAtPos(pos, player.getKey())) {
                    return player.getKey();
                }
            }
        }
        return ' ';
    }

    /**
     * @pre c is col 0 <= col <= maxCol, p = 'some character value', r is row 0 <= row <= getNumRows()
     * @post board.getKey() = p
     * @param p is the player char = 'some character value'
     * @param c is the column of the game board
     */
    public void placeToken(char p, int c){
        if(!board.containsKey(p)) {
            List<BoardPosition> playerPositions = new ArrayList<>();
            board.put(p, playerPositions);
        }
        for (int r = 0; r < getNumRows(); r++){
            if (whatsAtPos(new BoardPosition(r,c)) == ' ') {
                board.get(p).add(new BoardPosition(r,c));
                break;
            }
        }
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (!board.containsKey(player)){
            return false;
        }
        for (BoardPosition check : board.get(player)){
            if (check.equals(pos)){
                return true;
            }
        }
        return false;
    }
}
