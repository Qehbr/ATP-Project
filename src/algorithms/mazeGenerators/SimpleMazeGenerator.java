package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int rows, int cols) {
        // start and goal at the edges
        Position sp = new Position(1, 0, MazeSTART);
        Position gp = new Position(rows - 2, cols - 1, MazeGOAL);
        Maze simpleMaze = new Maze(rows, cols, sp, gp);

        // fill the maze with walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                simpleMaze.mazeMap[i][j] = 1;
            }
        }
        //each time increase the step
        for (int k = 0; k < Math.min(Math.sqrt(rows), Math.sqrt(cols)); k++) {
            int i = 1, j = 1, step = 0;
            //make simple route from start to goal
            while (i < rows - 2 && j < cols - 2) {
                simpleMaze.mazeMap[i][j] = 0;
                step = (int) (Math.random() * k);
                if (step == 0)
                    i++;
                else
                    j++;
            }
            //if come to end of the maze, make simple road to goal
            if (i == rows - 2) {
                while (j < cols - 1) {
                    simpleMaze.mazeMap[i][j] = 0;
                    j++;
                }
            } else {
                while (i < rows - 1) {
                    simpleMaze.mazeMap[i][j] = 0;
                    i++;
                }
            }
        }

        //make start and goal passes
        simpleMaze.mazeMap[sp.getRow()][sp.getCol()] = 0;
        simpleMaze.mazeMap[gp.getRow()][gp.getCol()] = 0;

        return simpleMaze;
    }
}

