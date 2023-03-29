package algorithms.maze3D;

/**
 * Interface Maze3D Generator
 */
public interface IMazeGenerator3D {
    /**
     * Generating random Maze3D Object with given rows,cols
     *
     * @param depth  Depth of maze
     * @param row    Rows of maze
     * @param column Columns of maze
     * @return Randomly generated 3D Maze
     */
    Maze3D generate(int depth, int row, int column);

    /**
     * Measure time in millis for generating randon 3D Maze with given depth, rows, cols
     *
     * @param depth  Depth of maze
     * @param row    Rows of maze
     * @param column Columns of maze
     * @return Time in millis
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
