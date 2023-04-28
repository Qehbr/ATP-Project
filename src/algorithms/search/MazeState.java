package algorithms.search;

import java.io.Serializable;

/**
 * State of maze for searching algorithms
 */
public class MazeState extends AState implements Comparable, Serializable {
    private int row;
    private int col;

    /**
     * MazeState constructor (cost should be set using setCost function)
     *
     * @param row      Row of state
     * @param col      Column of state
     * @param comeFrom Predecessor of state
     */
    public MazeState(int row, int col, AState comeFrom) {
        this.row = row;
        this.col = col;
        super.comeFrom = comeFrom;
        super.cost = -1;
    }

    /**
     * MazeState constructor
     *
     * @param row      Row of state
     * @param col      Column of state
     * @param comeFrom Predecessor of state
     * @param cost     Cost of state
     */
    public MazeState(int row, int col, AState comeFrom, int cost) {
        this.row = row;
        this.col = col;
        super.comeFrom = comeFrom;
        super.cost = cost;
    }

    //getters and overrided functions
    @Override
    public int[] getPosition() {
        return new int[]{row, col};
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MazeState)) {
            return false;
        }

        MazeState state = (MazeState) obj;
        return (this.row == state.row && this.col == state.col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ')';
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }


    @Override
    public int compareTo(Object state) {
        return Integer.compare(this.cost, ((MazeState) state).cost);
    }
}
