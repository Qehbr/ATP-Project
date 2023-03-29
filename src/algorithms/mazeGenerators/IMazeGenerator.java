package algorithms.mazeGenerators;

/**
 * Interface Maze Generator
 */
public interface IMazeGenerator {

    /**
     * Generating random Maze Object with given rows,cols
     *
     * @param rows Rows of maze
     * @param cols Columns of maze
     * @return Randomly generated Maze
     */
    Maze generate(int rows, int cols);

    /**
     * Measure time in millis for generating randon Maze with given rows, cols
     *
     * @param rows Rows of maze
     * @param cols Columns of maze
     * @return Time in millis
     */
    long measureAlgorithmTimeMillis(int rows, int cols);
}
