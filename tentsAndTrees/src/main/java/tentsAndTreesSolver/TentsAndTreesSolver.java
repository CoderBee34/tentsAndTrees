package tentsAndTreesSolver;

import tentsAndTreesSolver.utilities.Coordinate;
import tentsAndTreesSolver.utilities.Node;
import tentsAndTreesSolver.utilities.Queue;
import tentsAndTreesSolver.utilities.State;

public class TentsAndTreesSolver {

    private char [][] puzzle;
    private char [][] clonePuzzle;
    private Coordinate lastCoordinate;
    private Coordinate[] tentsPlaced;
    private int treeNum;
    private int tentNum;

    /**
     * Constructs the class by taking initial puzzle to solve and initializing the attributes and initialize the first state of puzzle according to placed tents
     * @param puzzle puzzle to solve, puzzle should be in format of 2d char array and trees need to be represented with capital 'T', representation of spaces doesn't matter
     */
    public TentsAndTreesSolver(char [][] puzzle){
        this.puzzle = puzzle;
        clonePuzzle = new char[puzzle.length][puzzle[0].length];

        for (int row = 0; row < puzzle.length; row++){
            for (int col = 0; col < puzzle[0].length; col++){
                clonePuzzle[row][col] = puzzle[row][col];
            }
        }

        lastCoordinate = new Coordinate(0, -1);
        treeNum = 0;
        tentNum = 0;
        numOfTree();
        tentsPlaced = new Coordinate[treeNum];
        //checks for initial tents that placed
        for (int row = 0; row < puzzle.length; row ++){
            for (int col = 0; col < puzzle[0].length; col++){
                if (puzzle[row][col] == 'X'){
                    tentsPlaced[tentNum] = new Coordinate(row,col);
                    tentNum ++;
                    if(isCoordinatesValid(row - 1,col)){
                        if (puzzle[row - 1][col] == 'T'){
                            clonePuzzle[row - 1][col] = '*';
                            continue;
                        }
                    }
                    if(isCoordinatesValid(row + 1,col)){
                        if (puzzle[row + 1][col] == 'T'){
                            clonePuzzle[row + 1][col] = '*';
                            continue;
                        }
                    }
                    if(isCoordinatesValid(row,col - 1)){
                        if (puzzle[row][col - 1] == 'T'){
                            clonePuzzle[row][col - 1] = '*';
                            continue;
                        }
                    }
                    if(isCoordinatesValid(row,col + 1)){
                        if (puzzle[row][col + 1] == 'T'){
                            clonePuzzle[row][col + 1] = '*';
                        }
                    }
                }
            }
        }

    }

    /**
     * solves the given grid tents and trees puzzle, with breadth first search algorithm, if there is a solution modify the given grid puzzle if there is no solution promt the screen and return the grid puzzle as it given
     * @return if given puzzle solvable returns solved puzzle otherwise promts the screen and return null, in solution tents represented with capital 'X'
     */
    public char[][] solve(){
        Queue states = new Queue();
        states.enqueue(new Node(new State(tentsPlaced, lastCoordinate, tentNum)));

        while (!states.isEmpty()){
            Node stateNode = states.dequeue();
            State state = stateNode.getData();
            tentNum = state.getTentNum();
            tentsPlaced = state.getTentCoordinates();
            lastCoordinate = state.getLastCoordinate();
            if (isSolutionValid()){
                for (int i = 0; i < tentNum; i++){
                    Coordinate coor = tentsPlaced[i];
                    puzzle[coor.getrow()][coor.getcol()] = 'X';
                }
                return puzzle;
            } else {
                boolean breakFlag = false;
                for (int row = lastCoordinate.getrow(); row < puzzle.length; row++){
                    int colp;
                    if (row == lastCoordinate.getrow()){
                        colp = lastCoordinate.getcol() + 1;
                    }else {
                        colp = 0;
                    }
                    if (breakFlag)
                        break;

                    for (int col = colp; col < puzzle[0].length; col ++){
                        if (clonePuzzle[row][col] == 'T'){
                            Coordinate lastCoor = new Coordinate(row, col);
                            if (isCoordinatesValid(row - 1,col) && (!containsCoordinate(new Coordinate(row - 1,col))) && puzzle [row - 1][col] != 'T' && tentNum < treeNum){
                                Coordinate [] copy = tentsPlaced.clone();
                                copy[tentNum] = new Coordinate(row - 1,col);
                                states.enqueue(new Node(new State(copy, lastCoor,tentNum + 1)));
                            }
                            if (isCoordinatesValid(row + 1,col) && (!containsCoordinate(new Coordinate(row + 1,col))) && puzzle [row + 1][col] != 'T' && tentNum < treeNum){
                                Coordinate [] copy = tentsPlaced.clone();
                                copy[tentNum] = new Coordinate(row + 1,col);
                                states.enqueue(new Node(new State(copy, lastCoor,tentNum + 1)));
                            }
                            if (isCoordinatesValid(row,col - 1) && (!containsCoordinate(new Coordinate(row,col - 1))) && puzzle [row][col - 1] != 'T' && tentNum < treeNum){
                                Coordinate [] copy = tentsPlaced.clone();
                                copy[tentNum] = new Coordinate(row,col - 1);
                                states.enqueue(new Node(new State(copy, lastCoor,tentNum + 1)));
                            }
                            if (isCoordinatesValid(row,col + 1) && (!containsCoordinate(new Coordinate(row,col + 1))) && puzzle [row][col + 1] != 'T' && tentNum < treeNum){
                                Coordinate [] copy = tentsPlaced.clone();
                                copy[tentNum] = new Coordinate(row,col + 1);
                                states.enqueue(new Node(new State(copy, lastCoor,tentNum + 1)));
                            }
                            breakFlag = true;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("There is no solution");
        return null;
    }

    /**
     * helper function for solve function checks given row and column exists in puzzle or not
     * @param row row number to check
     * @param col column number to check
     * @return returns true if it exists in puzzle array otherwise return false
     */
    private boolean isCoordinatesValid(int row, int col){
        boolean rowB = (row > -1) & (row < puzzle.length);
        boolean colB = (col > -1) & (col < puzzle[0].length);
       return rowB && colB;
    }


    /**
     * helper function for solve function checks the solution created by solve function is valid or not
     * @return returns true if solution valid otherwise returns false
     */
    private boolean isSolutionValid(){

        if (treeNum != tentNum){
            return false;
        }
        // checks is there any tent around the tent
        for (int i = 0; i < tentNum; i++){
            Coordinate tent = tentsPlaced[i];
            for (int row = -1; row <= 1; row ++){
                for (int col = -1; col <= 1; col ++){
                    if (row == 0 && col == 0){
                        continue;
                    }
                    if(containsCoordinate(new Coordinate(tent.getrow() + row, tent.getcol() + col))){
                        return false;
                    }
                }
            }
        }
        // checks does every tree has a tent or not
        for (int row = 0; row < puzzle.length; row++){
            for (int col = 0; col < puzzle[0].length; col++){
                if (puzzle[row][col] == 'T') {
                    boolean up, down, left, right;
                    if (containsCoordinate(new Coordinate(row - 1,col))){
                        up = true;
                    } else {
                        up = false;
                    }
                    if (containsCoordinate(new Coordinate(row + 1,col))){
                        down = true;
                    } else {
                        down = false;
                    }
                    if (containsCoordinate(new Coordinate(row,col - 1))){
                        left = true;
                    } else {
                        left = false;
                    }
                    if (containsCoordinate(new Coordinate(row,col + 1))){
                        right = true;
                    } else {
                        right = false;
                    }
                    if (!( up || down || left || right ))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * counts the num of trees in puzzle
     */
    private void numOfTree(){
        for (int row = 0; row < puzzle.length; row++){
            for (int col = 0; col < puzzle[0].length; col++){
                if (puzzle[row][col] == 'T'){
                    treeNum ++;
                }
            }
        }
    }

    /**
     * checks the given coordinate is in the tentsPlaced array or not
     * @param coordinate coordinate to check is in list or not
     * @return returns true if it contains otherwise returns false
     */
    private boolean containsCoordinate(Coordinate coordinate){
        for (int i = 0; i < tentNum; i++) {
            if (tentsPlaced[i].equals(coordinate)){
                return true;
            }
        }
        return false;
    }


}
