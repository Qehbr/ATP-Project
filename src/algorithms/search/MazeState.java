package algorithms.search;

public class MazeState extends AState implements Comparable {
    private int x;
    private int y;

    public MazeState(int x, int y, AState comeFrom) {
        this.x = x;
        this.y = y;
        super.comeFrom = comeFrom;
        super.cost = -1;
    }

    public MazeState(int x, int y, AState comeFrom, int cost) {
        this.x = x;
        this.y = y;
        super.comeFrom = comeFrom;
        super.cost = cost;
    }

    @Override
    int[] getPosition() {
        return new int[]{x, y};
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
        return (this.x == state.x && this.y == state.y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
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
