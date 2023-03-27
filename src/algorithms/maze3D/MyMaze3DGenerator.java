package algorithms.maze3D;


import java.util.ArrayList;


import static algorithms.maze3D.Maze3D.Maze3DGOAL;
import static algorithms.maze3D.Maze3D.Maze3DSTART;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    @Override
    public Maze3D generate(int depth, int row, int column) {
        int[] startPositions = generatePositionsOnEdge(depth, row, column);
        Position3D start3D = new Position3D(startPositions[0], startPositions[1], startPositions[2], Maze3DSTART);
        Maze3D maze = new Maze3D(depth, row, column, start3D);

        int[][][] visited = new int[depth][row][column];
        //fill the maze with walls and make all positions unvisited
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    maze.mazeMap[i][j][k] = 1;
                    visited[i][j][k] = 0;
                }
            }
        }

        //make start goal passage and visited
        maze.mazeMap[start3D.getDepthIndex()][start3D.getRowIndex()][start3D.getColumnIndex()] = 0;
        visited[start3D.getDepthIndex()][start3D.getRowIndex()][start3D.getColumnIndex()] = 1;

        //prims algorithm:
        ArrayList<int[]> walls = new ArrayList<>();
        walls.addAll(maze.getNeighbors(start3D.getDepthIndex(), start3D.getRowIndex(), start3D.getColumnIndex()));

        while (!walls.isEmpty()) {
            //take random wall
            int randomWallIndex = (int) (Math.random() * walls.size());
            int[] wall = walls.remove(randomWallIndex);

            //make the wall visited
            visited[wall[0]][wall[1]][wall[2]] = 1;

            //all neighbors of given wall
            ArrayList<int[]> neighbors = maze.getNeighbors(wall[0], wall[1], wall[2]);

            //If only one of the cells that the wall divides is visited
            int visitedCells = 0;
            for (int[] neighbor : neighbors) {
                if (visited[neighbor[0]][neighbor[1]][neighbor[2]] == 1) {
                    visitedCells += 1;
                }
            }

            if (visitedCells == 1) {
                //make it passage
                maze.mazeMap[wall[0]][wall[1]][wall[2]] = 0;

                //find goal position if given wall is on edge
                if (maze.isOnOutside(wall[0], wall[1], wall[2])) {
                    maze.setGoalPosition(new Position3D(wall[0], wall[1], wall[2], Maze3DGOAL));
                }
                //add neighboring walls of given wall
                walls.addAll(maze.getNeighbors(wall[0], wall[1], wall[2]));
            }
        }
        maze.mazeMap[maze.getGoalPosition().getDepthIndex()][maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()] = 0;
        return maze;
    }


}
