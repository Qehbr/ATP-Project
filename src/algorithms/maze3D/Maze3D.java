package algorithms.maze3D;


import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class Maze3D {

    protected int[][][] mazeMap;
    private Position3D start, goal;

    private int depths, rows, cols;

    protected static String Maze3DWALL = "▓";
    protected static String Maze3DPASS = " ";

    protected static String Maze3DGOAL = "E";
    protected static String Maze3DSTART = "S";


    public Maze3D(int depths, int rows, int cols, Position3D startPosition) {
        this.depths = depths;
        this.rows = rows;
        this.cols = cols;
        this.start = startPosition;
        this.mazeMap = new int[depths][rows][cols];
        for (int i = 0; i < depths; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    mazeMap[i][j][k] = 0;
                }
            }
        }
    }

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
            System.out.println("|||||||||||||||");
        }
    }

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

    public boolean isOnOutside(int x, int y, int z) {
        return x == 0 || y == 0 || z == 0 || x == this.depths - 1 || y == this.rows - 1 || z == this.cols - 1;
    }

}
