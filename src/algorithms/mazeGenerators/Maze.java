package algorithms.mazeGenerators;

import java.util.ArrayList;

import static algorithms.mazeGenerators.AMazeGenerator.*;

public class Maze {
    private int rows, cols;
    private Position startPosition, goalPosition;
    protected int[][] mazeMap;

    protected static String MazeWALL = "▓";
    protected static String MazePASS = " ";

    protected static String MazeGOAL = "E";
    protected static String MazeSTART = "S";


    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == startPosition.getRow() && j == startPosition.getCol()) {
                    System.out.print(startPosition.getType());
                } else if (i == goalPosition.getRow() && j == goalPosition.getCol()) {
                    System.out.print(goalPosition.getType());
                } else if (mazeMap[i][j] == 0) {
                    System.out.print(MazePASS);
                } else {
                    System.out.print(MazeWALL);
                }
            }
            System.out.println();
        }
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public Maze(int rows, int cols, Position startPosition, Position goalPosition) {
        this.rows = rows;
        this.cols = cols;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
        this.mazeMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMap[i][j] = 0;
            }

        }
    }

    public Maze(int rows, int cols, Position startPosition) {
        this.rows = rows;
        this.cols = cols;
        this.startPosition = startPosition;
        this.mazeMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMap[i][j] = 0;
            }

        }
    }

    public ArrayList<int[]> getPassages(int row, int col) {
        int[] northNeighbor = {row - 1, col};
        int[] southNeighbor = {row + 1, col};
        int[] westNeighbor = {row, col - 1};
        int[] eastNeighbor = {row, col + 1};
        ArrayList<int[]> neighbors = new ArrayList<>();

        if (inMaze(northNeighbor[0], northNeighbor[1])) {
            if (mazeMap[northNeighbor[0]][northNeighbor[1]] == 0) {
                neighbors.add(northNeighbor);
            }
        }
        if (inMaze(southNeighbor[0], southNeighbor[1])) {
            if (mazeMap[southNeighbor[0]][southNeighbor[1]] == 0) {
                neighbors.add(southNeighbor);
            }
        }
        if (inMaze(westNeighbor[0], westNeighbor[1])) {
            if (mazeMap[westNeighbor[0]][westNeighbor[1]] == 0) {
                neighbors.add(westNeighbor);
            }
        }
        if (inMaze(eastNeighbor[0], eastNeighbor[1])) {
            if (mazeMap[eastNeighbor[0]][eastNeighbor[1]] == 0) {
                neighbors.add(eastNeighbor);
            }
        }
        return neighbors;

    }

    public ArrayList<int[]> getDiagonalPassages(int row, int col) {
        int[] northNeighbor = {row - 1, col};
        int[] southNeighbor = {row + 1, col};
        int[] westNeighbor = {row, col - 1};
        int[] eastNeighbor = {row, col + 1};

        int[] northEastNeighbor = {row - 1, col + 1};
        int[] southEastNeighbor = {row + 1, col + 1};
        int[] southWestNeighbor = {row + 1, col - 1};
        int[] northWestNeighbor = {row - 1, col - 1};

        ArrayList<int[]> neighbors = new ArrayList<>();

        if (inMaze(northEastNeighbor[0], northEastNeighbor[1]) && inMaze(northNeighbor[0], northNeighbor[1]) && inMaze(eastNeighbor[0], eastNeighbor[1])) {
            if (mazeMap[northEastNeighbor[0]][northEastNeighbor[1]] == 0) {
                if (mazeMap[northNeighbor[0]][northNeighbor[1]] == 0 || mazeMap[eastNeighbor[0]][eastNeighbor[1]] == 0) {
                    neighbors.add(northEastNeighbor);
                }
            }
        }


        if (inMaze(southEastNeighbor[0], southEastNeighbor[1]) && inMaze(southNeighbor[0], southNeighbor[1]) && inMaze(eastNeighbor[0], eastNeighbor[1])) {
            if (mazeMap[southEastNeighbor[0]][southEastNeighbor[1]] == 0) {
                if (mazeMap[southNeighbor[0]][southNeighbor[1]] == 0 || mazeMap[eastNeighbor[0]][eastNeighbor[1]] == 0) {
                    neighbors.add(southEastNeighbor);
                }
            }
        }

        if (inMaze(southWestNeighbor[0], southWestNeighbor[1]) && inMaze(southNeighbor[0], southNeighbor[1]) && inMaze(westNeighbor[0], westNeighbor[1])) {
            if (mazeMap[southWestNeighbor[0]][southWestNeighbor[1]] == 0) {
                if (mazeMap[southNeighbor[0]][southNeighbor[1]] == 0 || mazeMap[westNeighbor[0]][westNeighbor[1]] == 0) {
                    neighbors.add(southWestNeighbor);
                }
            }
        }

        if (inMaze(northWestNeighbor[0], northWestNeighbor[1]) && inMaze(northNeighbor[0], northNeighbor[1]) && inMaze(westNeighbor[0], westNeighbor[1])) {
            if (mazeMap[northWestNeighbor[0]][northWestNeighbor[1]] == 0) {
                if (mazeMap[northNeighbor[0]][northNeighbor[1]] == 0 || mazeMap[westNeighbor[0]][westNeighbor[1]] == 0) {
                    neighbors.add(northWestNeighbor);
                }
            }
        }

        return neighbors;
    }

    private boolean inMaze(int row, int col) {
        return row < rows && row >= 0 && col < cols && col >= 0;
    }
}
