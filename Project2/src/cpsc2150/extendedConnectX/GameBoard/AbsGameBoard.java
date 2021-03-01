package cpsc2150.extendedConnectX.GameBoard;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * @return toString() = toString(board)
     * @pre none
     * @post the board is changed into a string representation
     */
    @Override
    public String toString() {
        StringBuilder gameBoard = new StringBuilder();
        BoardPosition[] boardPos = new BoardPosition[getNumColumns() * getNumRows()];
        int count = 0;
        for (int i = 0; i < getNumColumns(); i++) {
            gameBoard.append("|").append(i);
        }
        gameBoard.append("|\n");
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                boardPos[count] = new BoardPosition(i, j);
                gameBoard.append("|").append(this.whatsAtPos(boardPos[count]));
                count++;
            }
            gameBoard.append("|\n");
        }
        return gameBoard.toString();
    }
}
