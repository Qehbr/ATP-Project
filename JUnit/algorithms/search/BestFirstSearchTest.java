package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    public void BestFirstSearchtest()
    {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze  = mg.generate(50,50);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = new BestFirstSearch().solve(searchableMaze);
        assertEquals(searchableMaze.goalState, solution.getSolutionState());
    }

    @Test
    public void BFSSmallNumbers()
    {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze  = mg.generate(4,3);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = new BestFirstSearch().solve(searchableMaze);
        assertEquals(searchableMaze.goalState, solution.getSolutionState());
    }

    @Test
    public void BFSNegative()
    {
        IMazeGenerator mg = new MyMazeGenerator();
        try {
            mg.generate(-4, 3);
        }
        catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }


}