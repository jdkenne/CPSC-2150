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
        for (int c = 0; c < getNumColumns(); c++) {
            if (c < doubleDigitNum) {
                gameBoard.append("| ").append(c);
            } else {
                gameBoard.append("|").append(c);
            }
        }
        gameBoard.append("|\n");

        for (int r = getNumRows() - 1; r >= 0; r--) {
            for (int c = 0; c < getNumColumns(); c++) {
                gameBoard.append("|").append(whatsAtPos(new BoardPosition(r, c))).append(" ");
            }

            gameBoard.append("|\n");
        }

        return gameBoard.toString();
    }
}
