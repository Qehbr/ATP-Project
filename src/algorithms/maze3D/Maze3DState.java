package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState implements Comparable {
    private int x, y, z;

    public Maze3DState(int x, int y, int z, AState comeFrom) {
        this.x = x;
        this.y = y;
        this.z = z;
        super.comeFrom = comeFrom;
        super.cost = -1;
    }

    public Maze3DState(int x, int y, int z, AState comeFrom, int cost) {
        this.x = x;
        this.y = y;
        this.z = z;
        super.comeFrom = comeFrom;
        super.cost = cost;
    }

    @Override
    public int[] getPosition() {
        return new int[]{x, y, z};
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
        return (this.x == state.x && this.y == state.y && this.z == state.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ')';
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
