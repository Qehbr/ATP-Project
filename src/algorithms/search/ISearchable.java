package algorithms.search;

import java.util.ArrayList;

/**
 * Interface for searchable dimension
 */
public interface ISearchable {
    /**
     * Given state of searchable dimension return all possible states
     *
     * @param state Given state of searchable dimension
     * @return all possible states
     */
    ArrayList<AState> getAllPossibleStates(AState state);

    //getters
    AState getStartState();

    AState getGoalState();
}
