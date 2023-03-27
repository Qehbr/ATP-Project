package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    Queue<AState> open;
    HashSet<AState> openHash;
    HashSet<AState> closed;
    int numberOfNodesEvaluated = 0;


    public BreadthFirstSearch() {
        super("BreadthFirstSearch");
        open = new LinkedList<>();
        closed = new HashSet<>();
        openHash = new HashSet<>();
    }

    protected BreadthFirstSearch(String name, PriorityQueue<AState> open) {
        super(name);
        this.open = open;
        closed = new HashSet<>();
        openHash = new HashSet<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        open.add(searchable.getStartState());
        openHash.add(searchable.getStartState());

        while (!open.isEmpty()) {
            AState n = open.remove();
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
                    } else if (s.getCost() >= s.getCost() + n.getCost()) {
                        s.setCost(s.getCost() + n.getCost());
                        s.setComeFrom(n);
                    }
                }
            }
        }
        System.out.println("Goal state was not found");
        return null;
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}



