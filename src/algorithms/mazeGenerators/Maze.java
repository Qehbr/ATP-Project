package algorithms.mazeGenerators;

import static algorithms.mazeGenerators.AMazeGenerator.*;

public class Maze {
    private int rows, cols;
    private Position startPosition, goalPosition;
    protected int[][] mazeMap;


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
}
