package algorithms.maze3D;

public class Position3D {
    private String type;
    private int row, col, depth;


    @Override
    public String toString() {
        return depth + "," + row + "," + col + ",";
    }

    public String getType() {
        return type;
    }

    public Position3D(int depth, int row, int col, String type) {
        this.depth = depth;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getDepthIndex() {
        return depth;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return col;
    }
}
