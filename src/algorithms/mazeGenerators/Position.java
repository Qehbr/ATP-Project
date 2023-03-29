package algorithms.mazeGenerators;

/**
 * Position class for position in maze
 */
public class Position {
    //type of position, e.g. START, GOAL
    private String type;
    // row and column of position
    private int row, col;

    /**
     * Position constructor
     *
     * @param row  row of position
     * @param col  column of position
     * @param type type of position (START,GOAL)
     */
    public Position(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    //getters
    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return col;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return row + "," + col;
    }
}
