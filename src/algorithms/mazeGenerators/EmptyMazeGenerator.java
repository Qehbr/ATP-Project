package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int rows, int cols) {
        Position sp = new Position(0,0, MazeSTART);
        Position gp = new Position(rows-1,cols-1, MazeGOAL);
        return new Maze(rows, cols, sp, gp);
    }
}
