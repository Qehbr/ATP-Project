package algorithms.mazeGenerators;

import java.util.*;

import static algorithms.mazeGenerators.Maze.MazeGOAL;
import static algorithms.mazeGenerators.Maze.MazeSTART;

/**
 * MyMazeGenerator extends abstract class AMazeGenerator.
 * Generator for maze using Prims Algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * Generating random maze using Prim's Algorithm
     *
     * @param rows Rows of maze
     * @param cols Columns of maze
     * @return Randomly generated maze
     */
    @Override
    public Maze generate(int rows, int cols) {
        //create maze with ONLY start position
        int[] startPositions = generatePositionsOnEdge(rows, cols);
        Position sp = new Position(startPositions[0], startPositions[1], MazeSTART);
        Maze maze = new Maze(rows, cols, sp);

        int[][] visited = new int[rows][cols];
        //fill the maze with walls and make all positions unvisited
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze.mazeMap[i][j] = 1;
                visited[i][j] = 0;
            }
        }

        //make start position passage
        maze.mazeMap[sp.getRowIndex()][sp.getColumnIndex()] = 0;

        //prims algorithm:
        ArrayList<int[]> walls = new ArrayList<>();

        //in order to create walls around maze we should take the neighbor of start position within the maze:
        int[] spCellWithinTheMaze = getCellWithinTheMaze(sp.getRowIndex(), sp.getColumnIndex(), rows, cols);
        // make it passage
        maze.mazeMap[spCellWithinTheMaze[0]][spCellWithinTheMaze[1]] = 0;
        //start prims algorithm from the start cell within the maze
        walls.addAll(getNeighborsWithinTheMaze(spCellWithinTheMaze[0], spCellWithinTheMaze[1], rows, cols));
        visited[spCellWithinTheMaze[0]][spCellWithinTheMaze[1]] = 1;

        //prims algorithm
        while (!walls.isEmpty()) {
            //take random wall
            int randomWallIndex = (int) (Math.random() * walls.size());
            int[] wall = walls.remove(randomWallIndex);

            //make the wall visited
            visited[wall[0]][wall[1]] = 1;

            //If only one of the cells that the wall divides is visited
            int visitedCells = 0;
            ArrayList<int[]> neighbors = maze.getPassages(wall[0], wall[1]);
            for (int[] neighbor : neighbors) {
                if (visited[neighbor[0]][neighbor[1]] == 1) {
                    visitedCells += 1;
                }
            }
            if (visitedCells == 1) {

                //make it passage
                maze.mazeMap[wall[0]][wall[1]] = 0;

                //find goal position if given wall is on edge
                if (wall[0] == 1 || wall[0] == rows - 2 || wall[1] == 1 || wall[1] == cols - 2) {
                    Position gp = null;
                    if (wall[0] == 1) {
                        gp = new Position(0, wall[1], MazeGOAL);
                    }
                    if (wall[0] == rows - 2) {
                        gp = new Position(rows - 1, wall[1], MazeGOAL);
                    }
                    if (wall[1] == 1) {
                        gp = new Position(wall[0], 0, MazeGOAL);
                    }
                    if (wall[1] == cols - 2) {
                        gp = new Position(wall[0], cols - 1, MazeGOAL);
                    }
                    if (!(gp.getRowIndex() == sp.getRowIndex() && gp.getColumnIndex() == sp.getColumnIndex()))
                        maze.setGoalPosition(gp);

                }
                //add neighboring walls of given wall
                walls.addAll(getNeighborsWithinTheMaze(wall[0], wall[1], rows, cols));
            }
        }
        maze.mazeMap[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()] = 0;
        return maze;
    }

    /**
     * Returns all neighbors of given position WITHIN the maze (not including walls)
     *
     * @param row        Row of position
     * @param col        Column of position
     * @param rowsOfMaze Rows of maze
     * @param colsOfMaze Cols of maze
     * @return Arraylist of neighbors within the maze
     */
    private List<int[]> getNeighborsWithinTheMaze(int row, int col, int rowsOfMaze, int colsOfMaze) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        if (row - 1 > 0) {
            neighbors.add(new int[]{row - 1, col}); //north
        }
        if (row + 1 < rowsOfMaze - 1) {
            neighbors.add(new int[]{row + 1, col}); //south
        }
        if (col - 1 > 0) {
            neighbors.add(new int[]{row, col - 1}); //west
        }
        if (col + 1 < colsOfMaze - 1) {
            neighbors.add(new int[]{row, col + 1}); //east
        }
        return neighbors;
    }


    /**
     * Given position on edge returns position within the maze
     * (Used to make positions near START/GOAL positions passages)
     *
     * @param row        Row of position
     * @param col        Column of position
     * @param rowsOfMaze Rows of maze
     * @param colsOfMaze Cols of maze
     * @return Position within the maze
     */
    private int[] getCellWithinTheMaze(int row, int col, int rowsOfMaze, int colsOfMaze) {
        if (row == 0) {
            return new int[]{row + 1, col};
        } else if (row == rowsOfMaze - 1) {
            return new int[]{row - 1, col};
        } else if (col == 0) {
            return new int[]{row, col + 1};
        } else if (col == colsOfMaze - 1) {
            return new int[]{row, col - 1};
        }
        throw new IllegalArgumentException("Wrong Cell");
    }

}



