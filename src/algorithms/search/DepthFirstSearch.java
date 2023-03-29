package algorithms.search;

import java.util.*;

/**
 * DFS extends abstract class Searching Algorithm
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    //uses stack
    Stack<AState> open;
    //hash for searching open and closed states fast
    HashSet<AState> openHash, closed;
    int numberOfNodesEvaluated = 0;


    public DepthFirstSearch() {
        super("DepthFirstSearch");
        open = new Stack<>();
        closed = new HashSet<>();
        openHash = new HashSet<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        open.push(searchable.getStartState());
        openHash.add(searchable.getStartState());

        while (true) {
            if (open.isEmpty()) {
                System.out.println("OPEN IS EMPTY, NOT FOUND");
                break;
            }
            AState n = open.pop();
            openHash.remove(n);
            numberOfNodesEvaluated++;
            closed.add(n);
            if (n.equals(searchable.getGoalState())) {
                return new Solution(n);
            }
            ArrayList<AState> successors = searchable.getAllPossibleStates(n);

            for (AState s : successors) {
                if (!closed.contains(s)) {
                    if (!openHash.contains(s)) {
                        s.setCost(s.getCost() + n.getCost());
                        s.setComeFrom(n);
                        open.add(s);
                        openHash.add(s);
                    }
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
