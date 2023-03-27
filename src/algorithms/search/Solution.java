package algorithms.search;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    AState solutionState;

    public Solution(AState solutionState) {
        this.solutionState = solutionState;
    }

    public ArrayList<AState> getSolutionPath() {
        AState current = solutionState;
        ArrayList<AState> solution = new ArrayList<>();
        while (current != null) {
            solution.add(current);
            current = current.comeFrom;
        }
        Collections.reverse(solution);
        return solution;

    }
}
