package cpsc2150.extendedConnectX;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * @return toString() = toString(board)
     * @pre none
     * @post the board is changed into a string representation
     */
    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();

        for (int c = 0; c < getNumColumns(); c++) {
            if (c < doubleDigitNum) {
                display.append("| ").append(c);
            } else {
                display.append("|").append(c);
            }
        }
        display.append("|\n");

        for (int r = getNumRows() - 1; r >= 0; r--) {
            for (int c = 0; c < getNumColumns(); c++) {
                display.append("|").append(whatsAtPos(new BoardPosition(r, c))).append(" ");
            }

            display.append("|\n");
        }

        return display.toString();
    }
}
