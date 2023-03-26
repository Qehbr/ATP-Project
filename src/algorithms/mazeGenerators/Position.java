package algorithms.mazeGenerators;

public class Position {
    protected String type;
    protected int row, col;

    @Override
    public String toString() {
        return row + "," + col;
    }

    public String getType() {
        return type;
    }

    public Position(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
