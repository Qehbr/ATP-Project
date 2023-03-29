package algorithms.maze3D;

import java.util.ArrayList;

/**
 * 3D Maze class
 */
public class Maze3D {
    //3d array of ints (0 representing passage, 1 representing wall) for maze
    protected int[][][] mazeMap;
    //each maze has start and goal positions
    private Position3D start, goal;
    //dimensions of the maze
    private int depths, rows, cols;

    //Strings for different positions in maze
    protected static String Maze3DWALL = "▓";
    protected static String Maze3DPASS = " ";

    protected static String Maze3DGOAL = "E";
    protected static String Maze3DSTART = "S";

    /**
     * Maze constructor (goal should be set with setGoal function)
     *
     * @param rows          Rows of maze
     * @param cols          Columns of maze
     * @param startPosition Start position
     */
    public Maze3D(int depths, int rows, int cols, Position3D startPosition) {
        this.depths = depths;
        this.rows = rows;
        this.cols = cols;
        this.start = startPosition;
        this.mazeMap = new int[depths][rows][cols];
        //make maze empty
        for (int i = 0; i < depths; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    mazeMap[i][j][k] = 0;
                }
            }
        }
    }

    /**
     * Prining maze
     */
    public void print() {
        for (int i = 0; i < depths; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    if (i == start.getDepthIndex() && j == start.getRowIndex() && k == start.getColumnIndex()) {
                        System.out.print(start.getType());
                    } else if (i == goal.getDepthIndex() && j == goal.getRowIndex() && k == goal.getColumnIndex()) {
                        System.out.print(goal.getType());
                    } else if (mazeMap[i][j][k] == 0) {
                        System.out.print(Maze3DPASS);
                    } else {
                        System.out.print(Maze3DWALL);
                    }
                }
                System.out.println();
            }
            System.out.println("*New****************Dimension*");
        }
    }

    /**
     * Returns all neighbors of given position within the maze (excluding diagonal)
     *
     * @param depthIndex  Depth of position
     * @param rowIndex    Row of position
     * @param columnIndex Column of position
     * @return Arraylist of all neighbors within the maze
     */
    public ArrayList<int[]> getNeighbors(int depthIndex, int rowIndex, int columnIndex) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        // Iterate over all possible neighbor coordinates
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    // Exclude diagonal neighbors
                    if (dx != 0 && dy == 0 && dz == 0 ||
                            dx == 0 && dy != 0 && dz == 0 ||
                            dx == 0 && dy == 0 && dz != 0) {
                        // Calculate the coordinates of the neighbor
                        int nx = depthIndex + dx;
                        int ny = rowIndex + dy;
                        int nz = columnIndex + dz;

                        // Check if the neighbor is within the bounds of the array
                        if (nx >= 0 && ny >= 0 && nz >= 0 && nx < this.depths && ny < this.rows && nz < this.cols) {
                            neighbors.add(new int[]{nx, ny, nz});
                        }
                    }
                }
            }
        }

        return neighbors;

    }

    /**
     * Checks if given position is on edge (outside) of the 3D maze
     *
     * @param depth depth of position
     * @param row   row of position
     * @param col   col of position
     * @return
     */
    public boolean isOnOutside(int depth, int row, int col) {
        return depth == 0 || row == 0 || col == 0 || depth == this.depths - 1 || row == this.rows - 1 || col == this.cols - 1;
    }

    //getters
    public int[][][] getMap() {
        return mazeMap;
    }

    public Position3D getStartPosition() {
        return start;
    }

    public Position3D getGoalPosition() {
        return goal;
    }

    public void setGoalPosition(Position3D goalPosition) {
        this.goal = goalPosition;
    }

}
