package algorithms.maze3D;

import algorithms.search.AState;

/**
 * State of 3D maze for searching algorithms
 */
public class Maze3DState extends AState implements Comparable {
    private int depth, row, col;

    /**
     * Maze3DState constructor (cost should be set using setCost function)
     *
     * @param depth    Depth of state
     * @param row      Row of state
     * @param col      Column of state
     * @param comeFrom Predecessor of state
     */
    public Maze3DState(int depth, int row, int col, AState comeFrom) {
        this.depth = depth;
        this.row = row;
        this.col = col;
        super.comeFrom = comeFrom;
        super.cost = -1;
    }

    /**
     * Maze3DState constructor (cost should be set using setCost function)
     *
     * @param depth    Depth of state
     * @param row      Row of state
     * @param col      Column of state
     * @param comeFrom Predecessor of state
     * @param cost     Cost of state
     */
    public Maze3DState(int depth, int row, int col, AState comeFrom, int cost) {
        this.depth = depth;
        this.row = row;
        this.col = col;
        super.comeFrom = comeFrom;
        super.cost = cost;
    }

    //getters and overrided functions
    @Override
    public int[] getPosition() {
        return new int[]{depth, row, col};
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Maze3DState)) {
            return false;
        }

        Maze3DState state = (Maze3DState) obj;
        return (this.depth == state.depth && this.row == state.row && this.col == state.col);
    }

    @Override
    public String toString() {
        return "(" + depth + "," + row + "," + col + ')';
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }


    @Override
    public int compareTo(Object state) {
        return Integer.compare(this.cost, ((Maze3DState) state).cost);
    }
}
