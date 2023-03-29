package algorithms.maze3D;

/**
 * Position class for position in 3d maze
 */
public class Position3D {
    //type of position, e.g. START, GOAL
    private String type;
    private int row, col, depth;

    /**
     * Position constructor
     *
     * @param depth depth of position
     * @param row   row of position
     * @param col   column of position
     * @param type  type of position (START,GOAL)
     */
    public Position3D(int depth, int row, int col, String type) {
        this.depth = depth;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    //getters
    public int getDepthIndex() {
        return depth;
    }

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
        return depth + "," + row + "," + col + ",";
    }


}
