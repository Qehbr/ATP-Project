package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{

    Stack<AState> open;
    HashSet<AState> closed;
    int numberOfNodesEvaluated = 0;


    public DepthFirstSearch() {
        super("DepthFirstSearch");
        open = new Stack<>();
        closed = new HashSet<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        open.push(searchable.getStartState());

        while (true) {
            if (open.isEmpty()) {
                System.out.println("OPEN IS EMPTY");
                break;
            }
            AState n = open.pop();
            numberOfNodesEvaluated++;
            closed.add(n);
            if (n.equals(searchable.getGoalState())) {
                return new Solution(n);
            }
            ArrayList<AState> successors = searchable.getAllPossibleStates(n);

            for (AState s : successors) {
                if (!closed.contains(s)) {
                    s.setComeFrom(n);
                    open.push(s);
                }
            }
        }
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}
