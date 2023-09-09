package tentsAndTreesSolver.utilities;

public class State {

    private Coordinate[] tentCoordinates;
    private Coordinate lastCoordinate;

    private int tentNum;
    /**
     * initialize the state by taking the tentCoordinates and the lastCoordinate
     * @param tentCoordinates coordinate of tents that placed for this state
     * @param lastCoordinate last coordinate of puzzle array checked for this state
     * @param tentNum number of tree that state contains
     */
    public State(Coordinate[] tentCoordinates, Coordinate lastCoordinate, int tentNum){

        this.tentCoordinates = tentCoordinates;
        this.lastCoordinate = lastCoordinate;
        this.tentNum = tentNum;
    }

    /**
     * @return returns the last coordinate of this state
     */
    public Coordinate getLastCoordinate() {
        return lastCoordinate;
    }

    /**
     * @return returns the coordinate of tents for this state
     */
    public Coordinate[] getTentCoordinates() {
        return tentCoordinates;
    }

    /**
     *
     * @return returns the number of tents that state contains
     */
    public int getTentNum() {
        return tentNum;
    }
}
