package algorithms.search;

import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super("BestFirstSearch", new PriorityQueue<>());
    }
}
