//Joshua Kennerly, CPSC 2150, Section 003, 02-25-21
package cpsc2150.extendedConnectX;

/**
 * @invariants 0 <= row <= 5 and 0 <= col <= 8
 */
public class BoardPosition {

    private final int row;
    private final int col;
    //constructor
    /**
     *
     * @param r row of position
     * @param c column of position
     * @pre none
     * @post row = r. col = c
     */
    public BoardPosition(int r, int c){
        this.row = r;
        this.col = c;
    }
    /**
     * @pre none
     * @post getRow = row
     *@return #row = row
     */
    public int getRow(){
        return row;
    }

    /**
     * @pre none
     * @post getColumn = col
     *@return #col = col
     */
    public int getColumn(){
        return col;
    }


    /**
     * @pre none
     * @post the Object created becomes an instance of BoardPosition
     * @return Object A == Object B
     */
    @Override
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }
        if (!(ob instanceof BoardPosition)) {
            return false;
        }
        BoardPosition that = (BoardPosition) ob;
        return getRow() == that.getRow() &&
                col == that.col;
    }

    /**
     * @pre none
     * @post the String representation of the column by row is returned
     * @return toString = <[getColumn()],[getRow()]>
     */
    @Override
    public String toString() {
        return "<" + this.getRow() + "," + this.getColumn() + ">";
    }
}


