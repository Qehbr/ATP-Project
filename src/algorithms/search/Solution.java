package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Solution class containing solution of searching algorithm
 */
public class Solution implements Serializable {


    //solution (goal) state
    private AState solutionState;

    public Solution(AState solutionState) {
        this.solutionState = solutionState;
    }

    /**
     * Returns arraylist of solution path
     *
     * @return arraylist containing solution path from start to goal states
     */
    public ArrayList<AState> getSolutionPath() {
        AState current = solutionState;
        ArrayList<AState> solution = new ArrayList<>();
        //iterate through all states
        while (current != null) {
            solution.add(current);
            current = current.comeFrom;
        }
        Collections.reverse(solution);
        return solution;

    }

    //getters
    public AState getSolutionState() {
        return solutionState;
    }
}
