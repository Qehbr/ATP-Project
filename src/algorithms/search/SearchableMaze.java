package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

/**
 * Adapter from ISearchable interface to Maze class
 */
public class SearchableMaze implements ISearchable {
    //maze and its states
    Maze sMaze;
    MazeState startState, goalState;

    /**
     * Searchable maze constructor
     *
     * @param maze Maze to adapt for ISearchable
     */
    public SearchableMaze(Maze maze) {
        sMaze = maze;
        startState = new MazeState(sMaze.getStartPosition().getRowIndex(), sMaze.getStartPosition().getColumnIndex(), null, 0);
        goalState = new MazeState(sMaze.getGoalPosition().getRowIndex(), sMaze.getGoalPosition().getColumnIndex(), null);
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        //*explicitly [0][1] because this class only for 2d mazes*
        ArrayList<int[]> passages = sMaze.getPassages(state.getPosition()[0], state.getPosition()[1]);
        ArrayList<int[]> diagonalPassages = sMaze.getDiagonalPassages(state.getPosition()[0], state.getPosition()[1]);
        ArrayList<AState> possibleStates = new ArrayList<>();
        for (int[] passage : passages) {
            possibleStates.add(new MazeState(passage[0], passage[1], state, 10));
        }
        for (int[] passage : diagonalPassages) {
            possibleStates.add(new MazeState(passage[0], passage[1], state, 15));
        }
        return possibleStates;
    }

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }

}
