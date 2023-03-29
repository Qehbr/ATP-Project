package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Maze class
 */
public class Maze {
    //rows and cols of the maze
    private int rows, cols;
    //each maze has start and goal positions
    private Position startPosition, goalPosition;
    //2d array of ints (0 representing passage, 1 representing wall) for maze
    protected int[][] mazeMap;

    //Strings for different positions in maze
    protected static String MazeWALL = "▓";
    protected static String MazePASS = " ";

    protected static String MazeGOAL = "E";
    protected static String MazeSTART = "S";

    /**
     * Maze constructor
     *
     * @param rows          Rows of maze
     * @param cols          Columns of maze
     * @param startPosition Start position
     * @param goalPosition  Goal position
     */
    public Maze(int rows, int cols, Position startPosition, Position goalPosition) {
        this.rows = rows;
        this.cols = cols;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
        //make empty maze
        this.mazeMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMap[i][j] = 0;
            }

        }
    }

    /**
     * Maze constructor (goal should be set with setGoal function)
     *
     * @param rows          Rows of maze
     * @param cols          Columns of maze
     * @param startPosition Start position
     */
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

    /**
     * Printing the maze
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.print(startPosition.getType());
                } else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()) {
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

    /**
     * Returns if position is in maze range
     *
     * @param row Row of position
     * @param col Column of position
     * @return true if position is in maze
     */
    private boolean inMaze(int row, int col) {
        return row < rows && row >= 0 && col < cols && col >= 0;
    }

    /**
     * Returns all neighbors of given position (including neighbor not in maze)
     *
     * @param row Row of position
     * @param col Column of position
     * @return Arraylist of all neighbors
     */
    private ArrayList<int[]> getAllNeighbors(int row, int col) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{row - 1, col}); //north
        neighbors.add(new int[]{row + 1, col}); //south
        neighbors.add(new int[]{row, col - 1}); //west
        neighbors.add(new int[]{row, col + 1}); //east
        return neighbors;

    }

    /**
     * Function finds vertical and horizontal passages neighbors of given position
     *
     * @param row Row of position
     * @param col Column of position
     * @return array list of passage neighbors
     */
    public ArrayList<int[]> getPassages(int row, int col) {
        ArrayList<int[]> neighbors = getAllNeighbors(row, col);
        ArrayList<int[]> passages = new ArrayList<>();
        for (int[] n : neighbors) {
            if (inMaze(n[0], n[1])) {
                if (mazeMap[n[0]][n[1]] == 0) {
                    passages.add(n);
                }
            }
        }
        return passages;

    }

    /**
     * Checks if diagonal neighbor is approachable
     *
     * @param neighbor1        Horizontal/Vertical neighbor
     * @param neighbor2        Vertical/Horizontal neighbor
     * @param diagonalNeighbor Diagonal Neighbor
     * @return true if diagonal neighbor is approachable
     */
    private boolean isDiagonalApproachable(int[] neighbor1, int[] neighbor2, int[] diagonalNeighbor) {
        if (inMaze(diagonalNeighbor[0], diagonalNeighbor[1]) && inMaze(neighbor1[0], neighbor1[1]) && inMaze(neighbor2[0], neighbor2[1])) {
            if (mazeMap[diagonalNeighbor[0]][diagonalNeighbor[1]] == 0) {
                if (mazeMap[neighbor1[0]][neighbor1[1]] == 0 || mazeMap[neighbor2[0]][neighbor2[1]] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Function finds diagonal passages neighbors of given position
     *
     * @param row Row of position
     * @param col Column of position
     * @return array list of passage neighbors
     */
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

        if (isDiagonalApproachable(northNeighbor, eastNeighbor, northEastNeighbor)) {
            neighbors.add(northEastNeighbor);
        }
        if (isDiagonalApproachable(southNeighbor, eastNeighbor, southEastNeighbor)) {
            neighbors.add(southEastNeighbor);
        }
        if (isDiagonalApproachable(southNeighbor, westNeighbor, southWestNeighbor)) {
            neighbors.add(southWestNeighbor);
        }
        if (isDiagonalApproachable(northNeighbor, westNeighbor, northWestNeighbor)) {
            neighbors.add(northWestNeighbor);
        }

        return neighbors;
    }

    //getters and setters
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }
}
