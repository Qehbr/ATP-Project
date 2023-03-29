package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

/**
 * Adapter from ISearchable interface to Maze3D class
 */
public class SearchableMaze3D implements ISearchable {
    Maze3D sMaze;
    Maze3DState startState, goalState;

    /**
     * Searchable 3D maze constructor
     *
     * @param maze 3D Maze to adapt for ISearchable
     */
    public SearchableMaze3D(Maze3D maze) {
        sMaze = maze;
        startState = new Maze3DState(sMaze.getStartPosition().getDepthIndex(), sMaze.getStartPosition().getRowIndex(), sMaze.getStartPosition().getRowIndex(), null, 0);
        goalState = new Maze3DState(sMaze.getGoalPosition().getDepthIndex(), sMaze.getGoalPosition().getRowIndex(), sMaze.getGoalPosition().getRowIndex(), null);
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        int[] statePosition = state.getPosition();
        ArrayList<int[]> possiblePositions = sMaze.getNeighbors(statePosition[0], statePosition[1], statePosition[2]);
        ArrayList<AState> possibleStates = new ArrayList<>();
        for (int[] pState : possiblePositions) {
            possibleStates.add(new Maze3DState(pState[0], pState[1], pState[2], null, 1));
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
