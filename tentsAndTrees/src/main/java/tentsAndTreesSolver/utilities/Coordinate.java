package tentsAndTreesSolver.utilities;

public class Coordinate {
    private int row;
    private int col;

    /**
     * keeps the coordinate of a cell in a 2d array
     * @param row row number of cell
     * @param col column number of cell
     */
    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     *
     * @return returns the row number of cell
     */
    public int getrow() {
        return row;
    }

    /**
     *
     * @return returns the col number of cell
     */
    public int getcol() {
        return col;
    }

    /**
     *
     * @param coordinate that will be checked
     * @return returns true if the given coordinate equals this
     */
    public boolean equals(Coordinate coordinate) {
        boolean row = coordinate.getrow() == this.row;
        boolean col = coordinate.getcol() == this.col;
        return row && col;
    }
}
