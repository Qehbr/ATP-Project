package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Abstract class Maze Generator implementing interface Maze Generator
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    public static Random random = new Random();


    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long startTime = System.currentTimeMillis();
        generate(rows, cols);
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Static function for generating random position on edge of the maze (!does not include corners!)
     * @param rows Rows of maze
     * @param cols Columns of maze
     * @return positions[0] - row, positions[1] - column of position
     */
    protected static int[] generatePositionsOnEdge(int rows, int cols) {
        int X = 0, Y = 0;
        //make sure it is not corner
        while ((X == 0 && Y == 0) || (X == 0 && Y == cols - 1) || (X == rows - 1 && Y == 0) || (X == rows - 1 && Y == cols - 1)) {
            int side = random.nextInt(4);
            switch (side) {
                case 0: // Top side
                    Y = random.nextInt(cols);
                    break;
                case 1: // Bottom side
                    X = rows - 1;
                    Y = random.nextInt(cols);
                    break;
                case 2: // Left side
                    X = random.nextInt(rows);
                    break;
                case 3: // Right side
                    X = random.nextInt(rows);
                    Y = cols - 1;
                    break;
            }
        }
        int[] positions = new int[2];
        positions[0] = X;
        positions[1] = Y;
        return positions;
    }
}
